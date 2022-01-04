package com.example.prc.ws;


import com.example.prc.dtos.TipoDadosBiometricosDTO;
import com.example.prc.dtos.TipoDadosBiometricosQuantitativoDTO;
import com.example.prc.ejbs.TipoDadosBiometricosBean;
import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.TipoDadosBiometricosQuantitativo;
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

@Path("tipoDadosBiometricos")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class TipoDadosBiometricosService {
    @EJB
    private TipoDadosBiometricosBean tipoDadosBiometricosBean;

    @GET
    @RolesAllowed({"Admin"})
    @Path("/")
    public List<TipoDadosBiometricosDTO> getTipoProfissionalWS() {
        return toDTOs(tipoDadosBiometricosBean.getAllTipoDadosBiometricos());
    }

    @GET
    @Path("/utente")
    public List<TipoDadosBiometricosDTO> getTipoProfissionalUtenteWS() {
        return toDTOs(tipoDadosBiometricosBean.getAllTipoDadosBiometricosNotDeleted());
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
    @RolesAllowed({"Admin"})
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
    @RolesAllowed({"Admin"})
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
    @RolesAllowed({"Admin"})
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
