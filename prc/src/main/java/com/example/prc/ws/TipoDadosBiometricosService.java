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
                    tipoDadosBiometricos.getMax()
            );
        }else{
            tdb = new TipoDadosBiometricosDTO(
                    tipoDadosBiometricos.getId(),
                    tipoDadosBiometricos.getName(),
                    tipoDadosBiometricos.getQuantitativo()
            );
        }
        return tdb;
    }

    private List<TipoDadosBiometricosDTO> toDTOs(List<TipoDadosBiometricos> tipoDadosBiometricos) {
        return tipoDadosBiometricos.stream().map(this::toDTO).collect(Collectors.toList());
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
