package com.example.prc.ws;

import com.example.prc.dtos.ProfissionalSaudeDTO;
import com.example.prc.dtos.TipoProfissionalDTO;
import com.example.prc.dtos.UtenteDTO;
import com.example.prc.ejbs.TipoProfissionalBean;
import com.example.prc.ejbs.UtenteBean;
import com.example.prc.entities.ProfissionalSaude;
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


    @GET
    @Path("{email}")
    public  Response getUtente(@PathParam("email") String email){
        Utente utente= utenteBean.findUtente(email);
        log.info(utente.getName());
        return Response.ok(toDTO(utente)).build();
    }



    @GET
    @Path("/")
    public List<UtenteDTO> getUtenteWS() {
        return toDTOs(utenteBean.getAllUtentes());
    }

    private UtenteDTO toDTO(Utente utente) {
        UtenteDTO utenteDTO = new UtenteDTO(
                utente.getEmail(),
                utente.getPassword(),
                utente.getName(),
                utente.getDeleted_at(),
                utente.getBlocked(),
                utente.getDataNasc(),
                utente.getDadosBiometricos(),
                utente.getPrcs()
        );
        List<ProfissionalSaudeDTO> profissionalSaudeDTOS= ToDTOProfissionalSaude(utente.getProfissionalSaude());
        utenteDTO.setProfissionalSaude(profissionalSaudeDTOS);
        return utenteDTO;
    }

    private List<ProfissionalSaudeDTO> ToDTOProfissionalSaude(List<ProfissionalSaude> profissionalSaude) {
        ProfissionalSaudeService profissionalSaudeService=new ProfissionalSaudeService();
        List<ProfissionalSaudeDTO>profissionalSaudeDTOS= profissionalSaudeService.toDTOs(profissionalSaude);
        return  profissionalSaudeDTOS;
    }

    public List<UtenteDTO> toDTOs(List<Utente> utentes) {
        return utentes.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
