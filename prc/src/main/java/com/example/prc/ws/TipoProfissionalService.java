package com.example.prc.ws;


import com.example.prc.dtos.TipoProfissionalDTO;
import com.example.prc.ejbs.TipoProfissionalBean;
import com.example.prc.entities.TipoProfissional;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("tipoprofissional")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class TipoProfissionalService {
    @EJB
    private TipoProfissionalBean tipoProfissionalBean;

    @GET
    @Path("/")
    public List<TipoProfissionalDTO> getTipoProfissionalWS() {
        return toDTOs(tipoProfissionalBean.getAllTipoProfissional());
    }

    private TipoProfissionalDTO toDTO(TipoProfissional tipoProfissional) {
        TipoProfissionalDTO tp = new TipoProfissionalDTO(
                tipoProfissional.getId(),
                tipoProfissional.getName(),
                tipoProfissional.getDeleted_at()
        );
        return tp;
    }

    private List<TipoProfissionalDTO> toDTOs(List<TipoProfissional> tipoProfissionals) {
        return tipoProfissionals.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @POST
    @RolesAllowed({"Admin"})
    @Path("/")
    public Response createNewTipoProfissional (TipoProfissionalDTO tipoProfissionalDTO)
            throws MyEntityExistsException, MyConstraintViolationException {
        try{
            String id = tipoProfissionalBean.create(
                    tipoProfissionalDTO.getName());
            tipoProfissionalDTO.setId(Integer.parseInt(id));
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(tipoProfissionalDTO).build();
    }

    @DELETE
    @RolesAllowed({"Admin"})
    @Path("{id}")
    public Response deleteTipoProfissional(@PathParam("id") int id) throws MyEntityExistsException, MyEntityNotFoundException {
        try{
            TipoProfissional tipoProfissional = tipoProfissionalBean.deleteTipoProfissional(id);
            if(tipoProfissional == null)
                return Response.ok(null).build();

            return Response.ok(toDTO(tipoProfissional)).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
