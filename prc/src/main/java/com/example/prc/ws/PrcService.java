package com.example.prc.ws;

import com.example.prc.dtos.*;
import com.example.prc.ejbs.PrcBean;
import com.example.prc.entities.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("prc")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class PrcService {
    @EJB
    PrcBean prcBean;

    @GET
    @Path("/")
    public List<PrcDTO> getAllPrcs() {
        return toDTOs(prcBean.getAllPrcs());
    }

    @GET
    @Path("{emailUtente}")
    public List<PrcDTO> getAllPrcsByUtente(@PathParam("emailUtente") String emailUtente) {
        return toDTOs(prcBean.getAllPrcsByUtente(emailUtente));
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPrescricao(PrcDTO prcDTO) {
        try {
            prcBean.create(
                    prcDTO.getEmailUtente(),
                    prcDTO.getEmailProfissionalSaude(),
                    prcDTO.getDoenca(),
                    prcDTO.getValidade(),
                    prcDTO.getIdPrescricao(),
                    false
            );
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(prcDTO).build();
    }

    private PrcDTO toDTO(Prc prc) {
        PrcDTO prcDTO = new PrcDTO(
                prc.getId(),
                prc.getDoenca(),
                prc.getValidade(),
                prc.getCreated_at(),
                prc.getDeleted_at()
        );
        List<PrescricaoDTO> prescricoesDTO = ToDTOsPrescricoesDTO(prc.getPrescricoes());
        prcDTO.setPrescricoes(prescricoesDTO);
        UtenteDTO utenteDTO = ToDTOUtenteDTO(prc.getUtente());
        prcDTO.setUtente(utenteDTO);
        ProfissionalSaudeDTO profissionalSaudeDTO = ToDTOProfissionalSaudeDTO(prc.getProfissionalSaude());
        prcDTO.setProfissionalSaude(profissionalSaudeDTO);
        return prcDTO;
    }

    private ProfissionalSaudeDTO ToDTOProfissionalSaudeDTO(ProfissionalSaude profissionalSaude) {
        ProfissionalSaudeService profissionalSaudeService = new ProfissionalSaudeService();
        ProfissionalSaudeDTO profissionalSaudeDTO = profissionalSaudeService.toDTO(profissionalSaude);
        return  profissionalSaudeDTO;
    }

    private UtenteDTO ToDTOUtenteDTO(Utente utente) {
        UtenteService utenteService = new UtenteService();
        UtenteDTO utenteDTO = utenteService.toDTO(utente);
        return  utenteDTO;
    }

    private List<PrescricaoDTO> ToDTOsPrescricoesDTO(List<Prescricao> prescricoes) {
        PrescricaoService prescricaoService = new PrescricaoService();
        List<PrescricaoDTO> prescricaoDTO = prescricaoService.toDTOs(prescricoes);
        return  prescricaoDTO;
    }

    public List<PrcDTO> toDTOs(List<Prc> prcs) {
        return prcs.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
