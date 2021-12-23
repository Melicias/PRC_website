package com.example.prc.ws;

import com.example.prc.dtos.ProfissionaSaudeDTO;
import com.example.prc.dtos.TipoDadosBiometricosDTO;
import com.example.prc.dtos.TipoProfissionalDTO;
import com.example.prc.ejbs.ProfissionalSaudeBean;
import com.example.prc.entities.ProfissionalSaude;
import com.example.prc.entities.TipoProfissional;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("profissionalsaude")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class ProfissionalSaudeService {
    @EJB
    private ProfissionalSaudeBean profissionalSaudeBean;

    @GET
    @Path("/")
    public List<ProfissionaSaudeDTO> getTipoProfissionalWS() {
        return toDTOs(profissionalSaudeBean.getAllProfissionalSaude());
    }

    private ProfissionaSaudeDTO toDTO(ProfissionalSaude profissionalSaude) {
        ProfissionaSaudeDTO profissionaSaudeDTO = new ProfissionaSaudeDTO(
                profissionalSaude.getEmail(),
                profissionalSaude.getPassword(),
                profissionalSaude.getName(),
                profissionalSaude.getDeleted_at(),
                profissionalSaude.getVersion(),
                profissionalSaude.getTipoProfissional(),
                profissionalSaude.getUtentes()
        );
        return profissionaSaudeDTO;
    }

    private List<ProfissionaSaudeDTO> toDTOs(List<ProfissionalSaude> profissionalSaude) {
        return profissionalSaude.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
