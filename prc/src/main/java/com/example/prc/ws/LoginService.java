package com.example.prc.ws;
import com.example.prc.dtos.AuthDTO;
import com.example.prc.ejbs.AdminBean;
import com.example.prc.ejbs.UtenteBean;
import com.example.prc.entities.Admin;
import com.example.prc.entities.Utente;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import com.example.prc.ejbs.UserBean;
import com.example.prc.entities.User;
import com.example.prc.jwt.Jwt;
import com.example.prc.ejbs.JwtBean;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.logging.Logger;
@Path("/auth")
public class LoginService {
    private static final Logger log =
            Logger.getLogger(LoginService.class.getName());
    @EJB
    JwtBean jwtBean;
    @EJB
    UtenteBean utenteBean;
    @EJB
    AdminBean adminBean;
    @EJB
    UserBean userBean;
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUtente(AuthDTO credencials) {
        try {
            User user = userBean.authenticate(credencials.getUsername(), credencials.getPassword());
            if (user != null) {
                if (user.getEmail() != null) {
                    log.info("Generating JWT for user " + user.getEmail());
                }
                String token = jwtBean.createJwt(user.getEmail(), new
                        String[]{user.getClass().getSimpleName()});
                log.info(user.getClass().getSimpleName());
                return Response.ok(new Jwt(token)).build();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("/admin/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateAdmnin(AuthDTO credencials) {
        try {
            Admin admin = adminBean.authenticate(credencials.getUsername(), credencials.getPassword());
            if (admin != null) {
                if (admin.getEmail() != null) {
                    log.info("Generating JWT for user " + admin.getEmail());
                }
                String token = jwtBean.createJwt(admin.getEmail(), new
                        String[]{admin.getClass().getSimpleName()});
                return Response.ok(new Jwt(token)).build();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @POST
    @Path("/profissionalsaude/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateProfissionalsaude(AuthDTO credencials) {
        try {
            User user = userBean.authenticate(credencials.getUsername(), credencials.getPassword());
            if (user != null) {
                if (user.getEmail() != null) {
                    log.info("Generating JWT for user " + user.getEmail());
                }
                String token = jwtBean.createJwt(user.getEmail(), new
                        String[]{user.getClass().getSimpleName()});

                return Response.ok(new Jwt(token)).build();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/user")
    public Response demonstrateClaims(@HeaderParam("Authorization") String auth) {
        if (auth != null && auth.startsWith("Bearer ")) {
            try {
                JWT j = JWTParser.parse(auth.substring(7));
                log.info(j.getJWTClaimsSet().getClaims().toString());
                return Response.ok(j.getJWTClaimsSet().getClaims()).build();
                //Note: nimbusds converts token expiration time to milliseconds
            } catch (ParseException e) {
                log.warning(e.toString());
                return Response.status(400).build();
            }
        }
        return Response.status(204).build(); //no jwt means no claims to extract
    }
}
