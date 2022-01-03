package com.example.prc.ws;

import com.example.prc.ejbs.AdminBean;
import com.example.prc.ejbs.PrescricaoBean;
import com.example.prc.ejbs.ProfissionalSaudeBean;
import com.example.prc.ejbs.UtenteBean;
import com.example.prc.entities.Prescricao;
import com.example.prc.entities.ProfissionalSaude;
import com.example.prc.entities.Utente;
import com.nimbusds.jose.shaded.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("admin")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class AdminService {

    @EJB
    AdminBean adminBean;
    @EJB
    UtenteBean utenteBean;
    @EJB
    ProfissionalSaudeBean profissionalSaudeBean;
    private static final Logger log =
            Logger.getLogger(AdminService.class.getName());

    @GET
    @Path("statistics")
    public Response getStatisticsAdmin() {
        List<Utente> utentes;
        int utentesBloqueados = 0;
        int utentesApagados = 0;
        List<ProfissionalSaude> ps;
        int PSBloqueados = 0;
        int PSApagados = 0;
        try{
            utentes = utenteBean.getAllUtentes();
            ps = profissionalSaudeBean.getAllProfissionalSaude();
            for (Utente utente: utentes) {
                if(utente.getDeleted_at() != null)
                    utentesApagados++;
                if(utente.getBlocked() ==1)
                    utentesBloqueados++;
            }
            for (ProfissionalSaude profissionalSaude: ps) {
                if(profissionalSaude.getDeleted_at() != null)
                    PSApagados++;
                if(profissionalSaude.getBlocked() ==1)
                    PSBloqueados++;
            }


            JSONObject jsonObj = new JSONObject();

            jsonObj.put("nrUtentes", utentes.size());
            jsonObj.put("utentesBloqueados", utentesBloqueados);
            jsonObj.put("utentesApagados", utentesApagados);
            jsonObj.put("nrPS", ps.size());
            jsonObj.put("PSBloqueados", PSBloqueados);
            jsonObj.put("PSApagados", PSApagados);

            return Response.ok(jsonObj.toString()).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }
}
