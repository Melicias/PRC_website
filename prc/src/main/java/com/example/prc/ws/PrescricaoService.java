package com.example.prc.ws;

import com.example.prc.dtos.PrescricaoDTO;
import com.example.prc.dtos.TipoPrescricaoDTO;
import com.example.prc.ejbs.PrescricaoBean;
import com.example.prc.entities.Prescricao;
import com.example.prc.entities.TipoPrescricao;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("prescricao")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class PrescricaoService {
    @EJB
    PrescricaoBean prescricaoBean;

    @GET
    @Path("/")
    public List<PrescricaoDTO> getAllPrescricoes() {
        return toDTOs(prescricaoBean.getAllPrescricoes());
    }

    @GET
    @Path("/comUmPrc/{idPrc}")
    public Response getAllPrescricoesComUmPrc(@PathParam("idPrc") int idPrc) {
        try{
            List<Prescricao> prescricoes = prescricaoBean.getPrescricoesComUmPrc(idPrc);
            return Response.ok(toDTOs(prescricoes)).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/semUmPrc/{idPrc}")
    public Response getAllPrescricoesSemUmPrc(@PathParam("idPrc") int idPrc) {
        try{
            List<Prescricao> prescricoes = prescricaoBean.getPrescricoesSemUmPrc(idPrc);
            return Response.ok(toDTOs(prescricoes)).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPrescricao(PrescricaoDTO prescricaoDTO) {
        try {
            prescricaoBean.create(
                    prescricaoDTO.getDescricao(),
                    prescricaoDTO.getName(),
                    prescricaoDTO.getTipoPrescricaoId()
            );
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(prescricaoDTO).build();
    }

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePrescricao(PrescricaoDTO prescricaoDTO) {
        try {
            prescricaoBean.update(
                    prescricaoDTO.getId(),
                    prescricaoDTO.getDescricao(),
                    prescricaoDTO.getName(),
                    prescricaoDTO.getTipoPrescricaoId()
            );
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(prescricaoDTO).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePrescricao(@PathParam("id") int id) throws MyEntityNotFoundException {
        try{
            Prescricao prescricao = prescricaoBean.delete(id);
            return Response.ok(toDTO(prescricao)).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    private PrescricaoDTO toDTO(Prescricao prescricao) {
        PrescricaoDTO prescricaoDTO = new PrescricaoDTO(
                prescricao.getId(),
                prescricao.getName(),
                prescricao.getDescricao(),
                prescricao.getCreated_at(),
                prescricao.getDeleted_at()
        );
        TipoPrescricaoDTO tipoPrescricaoDTO = ToDTOTipoPrescricaoDTO(prescricao.getTipoPrescricao());
        prescricaoDTO.setTipoPrescricao(tipoPrescricaoDTO);
        return prescricaoDTO;
    }

    private TipoPrescricaoDTO ToDTOTipoPrescricaoDTO(TipoPrescricao tipoPrescricao) {
        TipoPrescricaoService tipoPrescricaoService = new TipoPrescricaoService();
        TipoPrescricaoDTO tipoPrescricaoDTO = tipoPrescricaoService.toDTO(tipoPrescricao);
        return  tipoPrescricaoDTO;
    }

    public List<PrescricaoDTO> toDTOs(List<Prescricao> prescricaos) {
        return prescricaos.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
