package com.example.prc.ws;

import com.example.prc.dtos.*;
import com.example.prc.ejbs.ProfissionalSaudeBean;
import com.example.prc.entities.*;
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

@Path("profissionalsaude")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class ProfissionalSaudeService {
    private static final Logger log =
            Logger.getLogger(LoginService.class.getName());
    @EJB
    private ProfissionalSaudeBean profissionalSaudeBean;

    @GET
    @Path("/")
    public List<ProfissionalSaudeDTO> getTipoProfissionalWS() {
        return toDTOs(profissionalSaudeBean.getAllProfissionalSaude());
    }

    @GET
    @Path("{email}")
    public Response getTipoProfissional(@PathParam("email") String email) throws MyEntityNotFoundException {
        ProfissionalSaude ps = profissionalSaudeBean.findTipoProfissional(email);
        return Response.ok(toDTOComplete(ps)).build();
    }

    @DELETE
    @Path("{email}")
    public Response deleteTipoProfissional(@PathParam("email") String email) throws MyEntityNotFoundException {
        ProfissionalSaude profissionalSaude = profissionalSaudeBean.deleteProfissionalSaude(email);
        return Response.ok(toDTO(profissionalSaude)).build();
    }

    @PUT
    @Path("/")
    public Response putProfissionalSaude(ProfissionalSaudeDTO profissionalSaudeDTO)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        try{
            profissionalSaudeBean.update(
                    profissionalSaudeDTO.getEmail(),
                    profissionalSaudeDTO.getName(),
                    profissionalSaudeDTO.getPassword(),
                    profissionalSaudeDTO.getTipoProfissional().getId());
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(profissionalSaudeDTO).build();
    }

    @PUT
    @Path("/block/{email}")
    public Response blockProfissionalSaude(@PathParam("email") String email)
            throws MyEntityNotFoundException {
        ProfissionalSaude profissionalSaude = profissionalSaudeBean.blockProfissionalSaude(email);
        return Response.ok(toDTO(profissionalSaude)).build();
    }

    @POST
    @Path("/")
    public Response createProfissionalSaude (ProfissionalSaudeDTO profissionalSaude)
            throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        try{
            profissionalSaudeBean.create(
                    profissionalSaude.getPassword(),
                    profissionalSaude.getName(),
                    profissionalSaude.getEmail(),
                    profissionalSaude.getTipoProfissional().getId());
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(profissionalSaude).build();
    }

    @POST
    @Path("/addUtente/{emailprofissional}")
    public Response addUtenteToProfissionalSaude (@PathParam("emailprofissional") String emailprofissional,UtenteDTO utenteDTO)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        Utente utente;
        try{
            utente = profissionalSaudeBean.addUtente(emailprofissional, utenteDTO.getEmail());
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(toDTOUtente(utente)).build();
    }

    @POST
    @Path("/removeUtente/{emailprofissional}")
    public Response removeUtenteToProfissionalSaude (@PathParam("emailprofissional") String emailprofissional,UtenteDTO utenteDTO)
            throws MyEntityNotFoundException, MyConstraintViolationException {
        Utente utente;
        try{
            utente = profissionalSaudeBean.removeUtente(emailprofissional, utenteDTO.getEmail());
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(toDTOUtente(utente)).build();
    }

    @GET
    @Path("/profissionaissemutente/{utenteemail}")
    public Response getProfissionaSemUtente(@PathParam("utenteemail") String utenteemail) throws MyEntityNotFoundException {
        List<ProfissionalSaude> profissionalSaudes;
        try{
            profissionalSaudes = profissionalSaudeBean.getProfissionaisSemUtente(utenteemail);
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(toDTOs(profissionalSaudes)).build();
    }

    private ProfissionalSaudeDTO toDTOComplete(ProfissionalSaude profissionalSaude) {
        ProfissionalSaudeDTO profissionaSaudeDTO = new ProfissionalSaudeDTO(
                profissionalSaude.getEmail(),
                profissionalSaude.getPassword(),
                profissionalSaude.getName(),
                profissionalSaude.getDeleted_at(),
                profissionalSaude.getBlocked(),
                profissionalSaude.getTipoProfissional()
        );
        List<UtenteDTO> utentesDTOS = toDTOUtentes(profissionalSaude.getUtentes());
        profissionaSaudeDTO.setUtentes(utentesDTOS);
        List<PrcDTO> prcsDTOS = toDTOPrcs(profissionalSaude.getPrcs());
        profissionaSaudeDTO.setPrcs(prcsDTOS);
        return profissionaSaudeDTO;
    }

    public ProfissionalSaudeDTO toDTO(ProfissionalSaude profissionalSaude) {
        ProfissionalSaudeDTO profissionaSaudeDTO = new ProfissionalSaudeDTO(
                profissionalSaude.getEmail(),
                profissionalSaude.getPassword(),
                profissionalSaude.getName(),
                profissionalSaude.getDeleted_at(),
                profissionalSaude.getBlocked(),
                profissionalSaude.getTipoProfissional()
        );
        return profissionaSaudeDTO;
    }


    private UtenteDTO toDTOUtente(Utente utente) {
        return new UtenteDTO(
                utente.getEmail(),
                utente.getPassword(),
                utente.getName(),
                utente.getDeleted_at(),
                utente.getBlocked(),
                utente.getDataNasc()
        );
    }

    private List<UtenteDTO> toDTOUtentes(List<Utente> utentes) {
        return utentes.stream().map(this::toDTOUtente).collect(Collectors.toList());
    }

    private PrcDTO toDTOPrc(Prc prc) {
        return new PrcDTO(
                prc.getId(),
                toDTOUtente(prc.getUtente()),
                toDTO(prc.getProfissionalSaude()),
                prc.getDoenca(),
                prc.getValidade(),
                prc.getCreated_at(),
                prc.getDeleted_at()
        );
    }

    private List<PrcDTO> toDTOPrcs(List<Prc> prcs) {
        return prcs.stream().map(this::toDTOPrc).collect(Collectors.toList());
    }

    public List<ProfissionalSaudeDTO> toDTOs(List<ProfissionalSaude> profissionalSaude) {
        return profissionalSaude.stream().map(this::toDTO).collect(Collectors.toList());
    }


}
