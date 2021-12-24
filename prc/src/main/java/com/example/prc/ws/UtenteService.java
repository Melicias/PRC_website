package com.example.prc.ws;

import com.example.prc.dtos.ProfissionaSaudeDTO;
import com.example.prc.dtos.UtenteDTO;
import com.example.prc.ejbs.TipoProfissionalBean;
import com.example.prc.ejbs.UtenteBean;
import com.example.prc.entities.Utente;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import static io.smallrye.config.ConfigLogging.log;

@Path("utente")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON})
public class UtenteService {

    @EJB
    private UtenteBean utenteBean;

    @GET
    @Path("/")
    public List<UtenteDTO> getTipoProfissionalWS() {
        return toDTOs(utenteBean.getAllUtentes());
    }

    @GET
    @Path("{email}")
    public Response getUtentePerEmail(@PathParam("email") String email) throws MyEntityExistsException, MyEntityNotFoundException {
        Utente utente=utenteBean.findUtente(email);
        log.info(utente);
        return Response.ok(toDTO(utente)).build();
    }

    private UtenteDTO toDTO(Utente utente) {
        UtenteDTO ut = new UtenteDTO(
                utente.getEmail(),
                utente.getPassword(),
                utente.getName(),
                null,
                0,
                utente.getDataNasc(),
                utente.getProfissionalSaude(),
                utente.getDadosBiometricos(),
                utente.getPrcs()
        );
        return ut;
    }

    private List<UtenteDTO> toDTOs(List<Utente> utentes) {
        return utentes.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
