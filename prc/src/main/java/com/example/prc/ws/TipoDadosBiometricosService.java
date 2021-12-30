package com.example.prc.ws;


import com.example.prc.dtos.TipoDadosBiometricosDTO;
import com.example.prc.dtos.TipoDadosBiometricosQuantitativoDTO;
import com.example.prc.dtos.TipoProfissionalDTO;
import com.example.prc.ejbs.TipoDadosBiometricosBean;
import com.example.prc.ejbs.TipoProfissionalBean;
import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.TipoDadosBiometricosQuantitativo;
import com.example.prc.entities.TipoProfissional;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("tipoDadosBiometricos")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class TipoDadosBiometricosService {
    @EJB
    private TipoDadosBiometricosBean tipoDadosBiometricosBean;

    private static final Logger log =
            Logger.getLogger(LoginService.class.getName());

    @GET
    @Path("/")
    public List<TipoDadosBiometricosDTO> getTipoProfissionalWS() {
        return toDTOs(tipoDadosBiometricosBean.getAllTipoDadosBiometricos());
    }

    public TipoDadosBiometricosDTO toDTO(TipoDadosBiometricos tipoDadosBiometricos) {
        TipoDadosBiometricosDTO tdb;
        if(tipoDadosBiometricos.getType() == TipoDadosBiometricos.QUALITATIVO){
            tdb = new TipoDadosBiometricosDTO(
                    tipoDadosBiometricos.getId(),
                    tipoDadosBiometricos.getName(),
                    tipoDadosBiometricos.getMin(),
                    tipoDadosBiometricos.getMax(),
                    tipoDadosBiometricos.getDeleted_at()
            );
        }else{
            tdb = new TipoDadosBiometricosDTO(
                    tipoDadosBiometricos.getId(),
                    tipoDadosBiometricos.getName(),
                    tipoDadosBiometricos.getDeleted_at()
            );
            log.info(tipoDadosBiometricos.getTipoDadosBiometricosQuantitativo().size() + "");
            tdb.setTipoDadosBiometricosQuantitativo(toDTOsTipoDadosBiometricosQuantitativo(tipoDadosBiometricos.getTipoDadosBiometricosQuantitativo()));
        }
        return tdb;
    }

    private List<TipoDadosBiometricosDTO> toDTOs(List<TipoDadosBiometricos> tipoDadosBiometricos) {
        return tipoDadosBiometricos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TipoDadosBiometricosQuantitativoDTO toDTOTipoDadosBiometricosQuantitativo(TipoDadosBiometricosQuantitativo tipoDadosBiometricosQuantitativo) {
        return new TipoDadosBiometricosQuantitativoDTO(
          tipoDadosBiometricosQuantitativo.getId(),
          tipoDadosBiometricosQuantitativo.getMin(),
          tipoDadosBiometricosQuantitativo.getMax(),
          tipoDadosBiometricosQuantitativo.getName()
        );
    }

    private List<TipoDadosBiometricosQuantitativoDTO> toDTOsTipoDadosBiometricosQuantitativo(List<TipoDadosBiometricosQuantitativo> tipoDadosBiometricosQuantitativo) {
        return tipoDadosBiometricosQuantitativo.stream().map(this::toDTOTipoDadosBiometricosQuantitativo).collect(Collectors.toList());
    }


    @POST
    @Path("/")
    public Response createNewTipoDadoBiometrico (TipoDadosBiometricosDTO tipoDadosBiometricosDTO)
            throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        try{
            tipoDadosBiometricosBean.create(
                    tipoDadosBiometricosDTO.getName(),
                    tipoDadosBiometricosDTO.getMin(),
                    tipoDadosBiometricosDTO.getMax(),
                    tipoDadosBiometricosDTO.getType() == TipoDadosBiometricosDTO.QUALITATIVO ? true : false,
                    tipoDadosBiometricosDTO.getTipoDadosBiometricosQuantitativo());
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(tipoDadosBiometricosDTO).build();
    }

    @PUT
    @Path("/")
    public Response putTipoDadoBiometrico (TipoDadosBiometricosDTO tipoDadosBiometricosDTO)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        try{
            tipoDadosBiometricosBean.update(
                    tipoDadosBiometricosDTO.getId(),
                    tipoDadosBiometricosDTO.getMin(),
                    tipoDadosBiometricosDTO.getMax());
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(tipoDadosBiometricosDTO).build();
    }

    @GET
    @Path("{name}")
    public Response getTipoDadoBiometricoDetails(@PathParam("name") String name) throws MyEntityExistsException, MyEntityNotFoundException {
        TipoDadosBiometricos tipoDadosBiometricos = tipoDadosBiometricosBean.findTipoDadoBiometrico(name);
        return Response.ok(toDTO(tipoDadosBiometricos)).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTipoDadoBiometrico(@PathParam("id") int id) throws MyEntityExistsException, MyEntityNotFoundException {
        try{
            TipoDadosBiometricos tipoDadosBiometricos = tipoDadosBiometricosBean.deleteTipoDadoBiometrico(id);
            if(tipoDadosBiometricos == null)
                return Response.ok(null).build();
            return Response.ok(toDTO(tipoDadosBiometricos)).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }

    }
}
