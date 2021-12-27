package com.example.prc.ws;

import com.example.prc.dtos.ProfissionalSaudeDTO;
import com.example.prc.dtos.TipoProfissionalDTO;
import com.example.prc.dtos.UtenteDTO;
import com.example.prc.ejbs.PrescricaoBean;
import com.example.prc.ejbs.ProfissionalSaudeBean;
import com.example.prc.ejbs.TipoProfissionalBean;
import com.example.prc.ejbs.UtenteBean;
import com.example.prc.entities.ProfissionalSaude;
import com.example.prc.entities.User;
import com.example.prc.entities.Utente;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityNotFoundException;
import com.example.prc.jwt.Jwt;
import org.jboss.resteasy.plugins.touri.URITemplateAnnotationResolver;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("utente")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class UtenteService {
    @EJB
    private UtenteBean utenteBean;
    @EJB
    private ProfissionalSaudeBean profissionalSaudeBean;
    @EJB
    private PrescricaoBean prescricaoBean;

    private static final Logger log =
            Logger.getLogger(UtenteService.class.getName());

    @GET
    @Path("/")
    public List<UtenteDTO> getAllUtentes() {
        return toDTOs(utenteBean.getAllUtentes());
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUtente(UtenteDTO utenteDTO) {
        try {
            System.out.println("HELLO "+utenteDTO.getDataNasc());
            utenteBean.create(utenteDTO.getPassword(),
                    utenteDTO.getName(),
                    utenteDTO.getEmail(),
                    new Date("29/06/1999"),
                    utenteDTO.getEmailProfissionalSaude());
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(utenteDTO).build();
    }

    @PUT
    @Path("/")
    public Response updateUtente(UtenteDTO utenteDTO) {
        try {
            utenteBean.updateUtente(
                    utenteDTO.getEmail(),
                    utenteDTO.getName(),
                    utenteDTO.getPassword(),
                    utenteDTO.getDataNasc());
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(utenteDTO).build();
    }

    @POST
    @Path("/addprofissionalsaude")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addProfissionalSaudeToUtente(UtenteDTO utenteDTO){
        try {
            profissionalSaudeBean.addUtente(utenteDTO.getEmailProfissionalSaude(), utenteDTO.getEmail());
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
        return  Response.ok(utenteDTO).build();
    }

    @POST
    @Path("/removeprofissionalsaude")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeProfissionalSaudeToUtente(UtenteDTO utenteDTO){
        try {
            profissionalSaudeBean.removeUtente(utenteDTO.getEmailProfissionalSaude(), utenteDTO.getEmail());
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
        return  Response.ok(utenteDTO).build();
    }


    /*@POST
    @Path("/prescricao")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPrescricaoToUtente(UtenteDTO utenteDTO){
        try {
            prescricaoBean.crea(utenteDTO.getEmail(), utenteDTO.getEmailProfissionalSaude());
            profissionalSaudeBean.addUtente(utenteDTO.getEmailProfissionalSaude(), utenteDTO.getEmail());
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()+"Entrou catch").build();
        }
        return  Response.ok(utenteDTO).build();
    }*/

    @GET
    @Path("{email}")
    public  Response getUtente(@PathParam("email") String email){
        Utente utente= utenteBean.findUtente(email);
        utente.setPassword("");
        return Response.ok(toDTO(utente)).build();
    }

    @GET
    @Path("/semprofissional/{profissionalEmail}")
    public Response getUtenteSemProfissionalSaude(@PathParam("profissionalEmail") String profissionalEmail) throws MyEntityNotFoundException {
        List<Utente> utentes;
        try{
            utentes = utenteBean.getUtentesSemProfissionalSaude(profissionalEmail);
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(toDTOs(utentes)).build();
    }

    @PUT
    @Path("/block/{email}")
    public Response blockUtente(@PathParam("email") String email)
            throws MyEntityNotFoundException {
        try{
            Utente utente = utenteBean.blockUtente(email);
            return Response.ok(toDTO(utente)).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("{email}")
    public Response deleteUtente(@PathParam("email") String email) throws MyEntityNotFoundException {
        try{
            Utente utente = utenteBean.deleteUtente(email);
            return Response.ok(toDTO(utente)).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    public UtenteDTO toDTO(Utente utente) {
        UtenteDTO utenteDTO = new UtenteDTO(
                utente.getEmail(),
                utente.getPassword(),
                utente.getName(),
                utente.getDataNasc(),
                utente.getDeleted_at(),
                utente.getBlocked()
        );
        List<ProfissionalSaudeDTO> profissionalSaudeDTOS = ToDTOProfissionalSaude(utente.getProfissionalSaude());
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
