package com.example.prc.ws;

import com.example.prc.dtos.TipoPrescricaoDTO;
import com.example.prc.dtos.TipoProfissionalDTO;
import com.example.prc.ejbs.TipoPrescricaoBean;
import com.example.prc.entities.TipoPrescricao;
import com.example.prc.entities.TipoProfissional;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("tipoPrescricao")
@Produces({MediaType.APPLICATION_JSON}) // injects header “Content-Type: application/json”
@Consumes({MediaType.APPLICATION_JSON}) // injects header “Accept: application/json”
public class TipoPrescricaoService {
    @EJB
    TipoPrescricaoBean tipoPrescricaoBean;

    @GET
    @Path("/")
    public List<TipoPrescricaoDTO> getAllTiposPrescricao() {
        return toDTOs(tipoPrescricaoBean.getAllTiposPrescricao());
    }

    public TipoPrescricaoDTO toDTO(TipoPrescricao tipoPrescricao) {
        TipoPrescricaoDTO tp = new TipoPrescricaoDTO(
                tipoPrescricao.getId(),
                tipoPrescricao.getName()
        );
        return tp;
    }

    private List<TipoPrescricaoDTO> toDTOs(List<TipoPrescricao> tipoPrescricoes) {
        return tipoPrescricoes.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
