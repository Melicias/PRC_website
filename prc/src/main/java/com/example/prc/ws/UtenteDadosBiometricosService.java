package com.example.prc.ws;

import com.example.prc.dtos.TipoDadosBiometricosDTO;
import com.example.prc.dtos.UtenteDTO;
import com.example.prc.dtos.UtenteDadosBiometricosDTO;
import com.example.prc.ejbs.UtenteDadosBiometricosBean;
import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.Utente;
import com.example.prc.entities.UtenteDadosBiometricos;
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

@Path("biometricdata")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON})
public class UtenteDadosBiometricosService {
    @EJB
    private UtenteDadosBiometricosBean utenteDadosBiometricosBean;

    @POST
    @Path("/")
    @RolesAllowed({"ProfissionalSaude","Utente"})
    public Response CreateDadosBiometricos(UtenteDadosBiometricosDTO utenteDadosBiometricosDTO)
            throws MyEntityExistsException, MyConstraintViolationException {
        try{
            UtenteDadosBiometricos utenteDadosBiometricos=  utenteDadosBiometricosBean.create(utenteDadosBiometricosDTO.getTipodadosBiometricos_id(),utenteDadosBiometricosDTO.getData_observacao(),utenteDadosBiometricosDTO.getValor(),utenteDadosBiometricosDTO.getUtenteEmail());
            utenteDadosBiometricosDTO = toDto(utenteDadosBiometricos);
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(utenteDadosBiometricosDTO.getAvaliacao()).build();
    }



    @GET
    @Path("{email}")
    @RolesAllowed({"ProfissionalSaude","Utente"})
    public  List<UtenteDadosBiometricosDTO> DadosBiometricos(@PathParam("email") String email) throws MyEntityNotFoundException {
           return toDtos(utenteDadosBiometricosBean.find(email));
    }

    public UtenteDadosBiometricosDTO toDto(UtenteDadosBiometricos utenteDadosBiometricos) {
        UtenteDadosBiometricosDTO utenteDadosBiometricosDTO = new UtenteDadosBiometricosDTO(
                toDtoTipoDados(utenteDadosBiometricos.getTipoDadosBiometricos()),
                utenteDadosBiometricos.getData_observacao(),
                utenteDadosBiometricos.getValor(),
                utenteDadosBiometricos.getAvaliacao()
        );
        utenteDadosBiometricosDTO.setUtente(toDtoUtente(utenteDadosBiometricos.getUtente()));
        return  utenteDadosBiometricosDTO;
    }

    public List<UtenteDadosBiometricosDTO> toDtos(List<UtenteDadosBiometricos> utenteDadosBiometricos){
        return utenteDadosBiometricos.stream().map(this::toDto).collect(Collectors.toList());
    }
    private TipoDadosBiometricosDTO toDtoTipoDados(TipoDadosBiometricos tipoDadosBiometricos) {
        TipoDadosBiometricosService tipoDadosBiometricosService = new TipoDadosBiometricosService();
        return tipoDadosBiometricosService.toDTO(tipoDadosBiometricos);
    }
    private UtenteDTO toDtoUtente(Utente utente) {
        UtenteService utenteService= new UtenteService();
        return utenteService.toDTO(utente);
    }
}
