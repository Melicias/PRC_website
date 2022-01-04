package com.example.prc.ws;
import com.example.prc.dtos.AuthDTO;
import com.example.prc.ejbs.UtenteBean;
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
@Path("/auth")
public class LoginService {
    @EJB
    JwtBean jwtBean;
    @EJB
    UtenteBean utenteBean;
    @EJB
    UserBean userBean;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(AuthDTO credencials) {
        try {
            User user = userBean.authenticate(credencials.getUsername(), credencials.getPassword());
            if (user != null) {
                if(user.getBlocked() == 1)
                    return Response.status(401).entity("This user is blocked! if you think this is a mistake please contact our support").build();
                if(user.getDeleted_at() != null)
                    return Response.status(401).entity("Authorized, something is wrong with username or password!").build();

                String token = jwtBean.createJwt(user.getEmail(), new String[]{user.getClass().getSimpleName()});
                return Response.ok(new Jwt(token)).build();
            }
        } catch (Exception e) {

        }
        return Response.status(401).entity("Authorized, something is wrong with username or password!").build();
    }

    @POST
    @Path("/mobile/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUtente(AuthDTO credencials) {
        try {
            Utente utente = utenteBean.authenticate(credencials.getUsername(), credencials.getPassword());
            if (utente != null) {
                String token = jwtBean.createJwt(utente.getEmail(), new
                        String[]{utente.getClass().getSimpleName()});
                return Response.ok(new Jwt(token)).build();
            }
        } catch (Exception e) {

        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("/user")
    public Response demonstrateClaims(@HeaderParam("Authorization") String auth) {
        if (auth != null && auth.startsWith("Bearer ")) {
            try {
                JWT j = JWTParser.parse(auth.substring(7));
                return Response.ok(j.getJWTClaimsSet().getClaims()).build();
                //Note: nimbusds converts token expiration time to milliseconds
            } catch (ParseException e) {
                return Response.status(400).build();
            }
        }
        return Response.status(204).build(); //no jwt means no claims to extract
    }
}
