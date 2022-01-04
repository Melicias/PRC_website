package com.example.prc.ws;

import com.example.prc.dtos.AdminDTO;
import com.example.prc.ejbs.AdminBean;
import com.example.prc.ejbs.ProfissionalSaudeBean;
import com.example.prc.ejbs.UtenteBean;
import com.example.prc.entities.*;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

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

    @GET
    @Path("statistics")
    public Response getStatisticsAdmin() {
        return adminBean.getStatistics(utenteBean.getAllUtentes(), profissionalSaudeBean.getAllProfissionalSaude());
    }

    @GET
    @Path("/")
    public List<AdminDTO> getAdmins() {
        return toDTOs(adminBean.getAllAdmins());
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAdmin(AdminDTO adminDTO) {
        try {
            adminBean.create(adminDTO.getPassword(),
                    adminDTO.getName(),
                    adminDTO.getEmail());
        } catch (Exception e) {
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok(adminDTO).build();
    }

    @PUT
    @Path("/block/{email}")
    public Response blockProfissionalSaude(@PathParam("email") String email){
        try{
            Admin admin = adminBean.blockAdmin(email);
            return Response.ok(toDTO(admin)).build();
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("{emailadmin}")
    public Response updateProfissionalSaudePassword (@PathParam("emailadmin") String emailadmin, AdminDTO adminDTO)
            throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        try{
            adminBean.updatePassword(
                    emailadmin,
                    adminDTO.getPassword(),
                    adminDTO.getNewPassword(),
                    adminDTO.getConfirmNewPassword());
        }catch (Exception e){
            return Response.status(400).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    public AdminDTO toDTO(Admin admin) {
        return new AdminDTO(
                admin.getEmail(),
                "",
                admin.getName(),
                admin.getDeleted_at(),
                admin.getBlocked()
        );
    }

    private List<AdminDTO> toDTOs(List<Admin> admins) {
        return admins.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
