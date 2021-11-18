package com.example.prc.ws;
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
    UserBean userBean;
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("email") String email,
                                     @FormParam("password") String password) {
        try {
            User user = userBean.authenticate(email, password);
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
