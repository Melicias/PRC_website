package com.example.prc.ws;


import com.example.prc.dtos.TipoDadosBiometricosDTO;
import com.example.prc.dtos.TipoProfissionalDTO;
import com.example.prc.ejbs.TipoDadosBiometricosBean;
import com.example.prc.ejbs.TipoProfissionalBean;
import com.example.prc.entities.TipoDadosBiometricos;
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
    private static final Logger log =
            Logger.getLogger(LoginService.class.getName());
    @EJB
    private TipoDadosBiometricosBean tipoDadosBiometricosBean;

    @GET
    @Path("/")
    public List<TipoDadosBiometricosDTO> getTipoProfissionalWS() {
        return toDTOs(tipoDadosBiometricosBean.getAllTipoDadosBiometricos());
    }

    private TipoDadosBiometricosDTO toDTO(TipoDadosBiometricos tipoDadosBiometricos) {
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
                    tipoDadosBiometricos.getQuantitativo(),
                    tipoDadosBiometricos.getDeleted_at()
            );
        }
        return tdb;
    }

    private List<TipoDadosBiometricosDTO> toDTOs(List<TipoDadosBiometricos> tipoDadosBiometricos) {
        return tipoDadosBiometricos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @POST
    @Path("/")
    public Response createNewTipoDadoBiometrico (TipoDadosBiometricosDTO tipoDadosBiometricosDTO)
            throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        TipoDadosBiometricos tipoDadosBiometricos = tipoDadosBiometricosBean.findTipoDadoBiometrico(tipoDadosBiometricosDTO.getName());
        if(tipoDadosBiometricos != null){
            return Response.status(400).entity("Name already in use!").build();
        }
        try{
            tipoDadosBiometricosBean.create(
                    tipoDadosBiometricosDTO.getName(),
                    tipoDadosBiometricosDTO.getMin(),
                    tipoDadosBiometricosDTO.getMax(),
                    tipoDadosBiometricosDTO.getType() == TipoDadosBiometricos.QUALITATIVO ? null : tipoDadosBiometricosDTO.getQuantitativo());
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
                    tipoDadosBiometricosDTO.getMax(),
                    tipoDadosBiometricosDTO.getQuantitativo());
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
        TipoDadosBiometricos tipoDadosBiometricos = tipoDadosBiometricosBean.deleteTipoDadoBiometrico(id);
        return Response.ok(toDTO(tipoDadosBiometricos)).build();
    }
}
