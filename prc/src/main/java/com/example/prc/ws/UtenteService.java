package com.example.prc.ws;

import com.example.prc.dtos.*;
import com.example.prc.ejbs.*;
import com.example.prc.entities.*;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    @EJB
    private EmailBean emailBean;

    @GET
    @Path("/")
    @RolesAllowed({"Admin","ProfissionalSaude"})
    public List<UtenteDTO> getAllUtentes() {
        return toDTOs(utenteBean.getAllUtentes());
    }

    @POST
    @Path("/")
    @RolesAllowed({"Admin","ProfissionalSaude"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUtente(UtenteDTO utenteDTO) {
        try {
            utenteBean.create(utenteDTO.getPassword(),
                    utenteDTO.getName(),
                    utenteDTO.getEmail(),
                    utenteDTO.getDataNasc(),
                    utenteDTO.getEmailProfissionalSaude());
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(utenteDTO).build();
    }

    @PUT
    @Path("/")
    @RolesAllowed({"Admin","ProfissionalSaude","Utente"})
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
    @RolesAllowed({"Admin","ProfissionalSaude"})
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
    @RolesAllowed({"Admin","ProfissionalSaude"})
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

    @GET
    @Path("{email}")
    @RolesAllowed({"Admin","ProfissionalSaude","Utente"})
    public  Response getUtente(@PathParam("email") String email){
        Utente utente= utenteBean.findUtente(email);
        if(utente == null)
            return Response.noContent().build();
        utente.setPassword("");
        return Response.ok(toDTO(utente)).build();
    }

    @GET
    @Path("/semprofissional/{profissionalEmail}")
    @RolesAllowed({"Admin","ProfissionalSaude"})
    public Response getUtenteSemProfissionalSaude(@PathParam("profissionalEmail") String profissionalEmail) throws MyEntityNotFoundException {
        try{
            List<Utente> utentes = utenteBean.getUtentesSemProfissionalSaude(profissionalEmail);
            return Response.ok(toDTOs(utentes)).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("/comprofissional/{profissionalEmail}")
    @RolesAllowed({"Admin","ProfissionalSaude"})
    public Response getUtenteComProfissionalSaude(@PathParam("profissionalEmail") String profissionalEmail) throws MyEntityNotFoundException {
        try{
            List<Utente> utentes = utenteBean.getUtentesComProfissionalSaude(profissionalEmail);
            return Response.ok(toDTOs(utentes)).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/block/{email}")
    @RolesAllowed({"Admin","ProfissionalSaude"})
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
    @RolesAllowed({"Admin","ProfissionalSaude"})
    @Path("{email}")
    public Response deleteUtente(@PathParam("email") String email) throws MyEntityNotFoundException {
        try{
            Utente utente = utenteBean.deleteUtente(email);
            if(utente == null)
                return Response.ok(null).build();
            return Response.ok(toDTO(utente)).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{email}/send")
    @RolesAllowed({"Admin","ProfissionalSaude","Utente"})
    public Response sendEmail(@PathParam("email") String utenteEmail, EmailDTO email)
            throws MyEntityNotFoundException, MessagingException {
        Utente utente = utenteBean.findUtente(utenteEmail);
        if (utente == null) {
            throw new MyEntityNotFoundException("Utente with email '" + utenteEmail + "' not found in our records.");
        }
        emailBean.send(utenteEmail, email.getSubject(), email.getMessage());
        return Response.status(Response.Status.OK).entity("E-mail sent").build();
    }

    public UtenteDTO toDTO(Utente utente) {
        UtenteDTO utenteDTO = new UtenteDTO(
                utente.getEmail(),
                "",
                utente.getName(),
                utente.getDataNasc(),
                utente.getDeleted_at(),
                utente.getBlocked()
        );
        List<ProfissionalSaudeDTO> profissionalSaudeDTOS = ToDTOProfissionalSaude(utente.getProfissionalSaude());
        utenteDTO.setProfissionalSaude(profissionalSaudeDTOS);
        List<PrcDTO> prcs = ToDTOPrc(utente.getPrcs());
        utenteDTO.setPrcs(prcs);
        List<UtenteDadosBiometricosDTO> utenteDadosBiometricosDTOS= toDtosDadosBiomediocos(utente.getDadosBiometricos());
        utenteDTO.setDadosBiometricos(utenteDadosBiometricosDTOS);
        return utenteDTO;
    }

    private List<ProfissionalSaudeDTO> ToDTOProfissionalSaude(List<ProfissionalSaude> profissionalSaude) {
        ProfissionalSaudeService profissionalSaudeService=new ProfissionalSaudeService();
        List<ProfissionalSaudeDTO>profissionalSaudeDTOS= profissionalSaudeService.toDTOs(profissionalSaude);
        return  profissionalSaudeDTOS;
    }
    private ProfissionalSaudeDTO ToDTOProfissionalSaud(ProfissionalSaude profissionalSaude) {
        ProfissionalSaudeService profissionalSaudeService=new ProfissionalSaudeService();
       ProfissionalSaudeDTO profissionalSaudeDTOS= profissionalSaudeService.toDTO(profissionalSaude);
        return  profissionalSaudeDTOS;
    }

    public UtenteDadosBiometricosDTO toDtoDadosBiomedicos(UtenteDadosBiometricos utenteDadosBiometricos) {
        UtenteDadosBiometricosDTO utenteDadosBiometricosDTO = new UtenteDadosBiometricosDTO(
                toDtoTipoDados(utenteDadosBiometricos.getTipoDadosBiometricos()),
                utenteDadosBiometricos.getData_observacao(),
                utenteDadosBiometricos.getValor(),
                utenteDadosBiometricos.getAvaliacao()
        );
        return  utenteDadosBiometricosDTO;
    }

    public List<UtenteDadosBiometricosDTO> toDtosDadosBiomediocos(List<UtenteDadosBiometricos> utenteDadosBiometricos){
        return utenteDadosBiometricos.stream().map(this::toDtoDadosBiomedicos).collect(Collectors.toList());
    }
    private TipoDadosBiometricosDTO toDtoTipoDados(TipoDadosBiometricos tipoDadosBiometricos) {
        TipoDadosBiometricosService tipoDadosBiometricosService = new TipoDadosBiometricosService();
        return tipoDadosBiometricosService.toDTO(tipoDadosBiometricos);
    }

    private List<PrcDTO> ToDTOPrc(List<Prc> prcs) {
        return prcs.stream().map(this::prcDTO).collect(Collectors.toList());
    }
    private List<PrescricaoDTO> ToDTOsPrescricoesDTO(List<Prescricao> prescricoes) {
        PrescricaoService prescricaoService = new PrescricaoService();
        List<PrescricaoDTO> prescricaoDTO = prescricaoService.toDTOs(prescricoes);
        return  prescricaoDTO;
    }

    private  PrcDTO prcDTO(Prc prc){
        PrcDTO prcDTO = new PrcDTO(
                prc.getId(),
                prc.getDoenca(),
                prc.getValidade(),
                prc.getCreated_at(),
                prc.getDeleted_at()
        );
        List<PrescricaoDTO> prescricoesDTO = ToDTOsPrescricoesDTO(prc.getPrescricoes());
        prcDTO.setPrescricoes(prescricoesDTO);
        ProfissionalSaudeDTO profissionalSaudeDTO = ToDTOProfissionalSaud(prc.getProfissionalSaude());
        prcDTO.setProfissionalSaude(profissionalSaudeDTO);
        return prcDTO;
    }

    public List<UtenteDTO> toDTOs(List<Utente> utentes) {
        return utentes.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
