package com.example.prc.ws;


import com.example.prc.dtos.TipoProfissionalDTO;
import com.example.prc.ejbs.TipoProfissionalBean;
import com.example.prc.entities.TipoProfissional;
import com.example.prc.exceptions.MyConstraintViolationException;
import com.example.prc.exceptions.MyEntityExistsException;
import com.example.prc.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("tipoprofissional")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class TipoProfissionalService {
    @EJB
    private TipoProfissionalBean tipoProfissionalBean;

    @GET
    @Path("/")
    public List<TipoProfissionalDTO> getTipoProfissionalWS() {
        return toDTOs(tipoProfissionalBean.getAllTipoProfissional());
    }

    private TipoProfissionalDTO toDTO(TipoProfissional tipoProfissional) {
        TipoProfissionalDTO tp = new TipoProfissionalDTO(
                tipoProfissional.getId(),
                tipoProfissional.getName()
        );
        return tp;
    }

    private List<TipoProfissionalDTO> toDTOs(List<TipoProfissional> tipoProfissionals) {
        return tipoProfissionals.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @POST
    @Path("/")
    public Response createNewTipoPrescricao (TipoProfissionalDTO tipoProfissionalDTO)
            throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        tipoProfissionalBean.create(
                tipoProfissionalDTO.getName());
        return Response.status(Response.Status.CREATED).build();
    }
}
