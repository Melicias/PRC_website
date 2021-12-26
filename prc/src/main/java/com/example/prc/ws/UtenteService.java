package com.example.prc.ws;

import com.example.prc.dtos.TipoProfissionalDTO;
import com.example.prc.dtos.UtenteDTO;
import com.example.prc.ejbs.TipoProfissionalBean;
import com.example.prc.ejbs.UtenteBean;
import com.example.prc.entities.User;
import com.example.prc.entities.Utente;
import com.example.prc.jwt.Jwt;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import static io.smallrye.config.ConfigLogging.log;

@Path("utente")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class UtenteService {
    @EJB
    private UtenteBean utenteBean;

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUtente(UtenteDTO utenteDTO, String emailProfissionalSaude) {
        try {
            utenteBean.create(utenteDTO.getPassword(), utenteDTO.getName(), utenteDTO.getEmail(), utenteDTO.getDataNasc(), utenteDTO.getEmailProfissionalSaude());
            Utente utente = utenteBean.findUtente(utenteDTO.getEmail());
            if (utente == null)
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            return Response.status(Response.Status.CREATED)
                    .entity(toDTO(utente))
                    .build();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    private UtenteDTO toDTO(Utente utente) {
        UtenteDTO ut = new UtenteDTO(
                utente.getEmail(),
                utente.getPassword(),
                utente.getName(),
                utente.getDeleted_at(),
                utente.getBlocked(),
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
