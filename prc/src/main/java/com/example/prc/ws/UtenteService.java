package com.example.prc.ws;

import com.example.prc.dtos.TipoProfissionalDTO;
import com.example.prc.dtos.UtenteDTO;
import com.example.prc.entities.TipoProfissional;
import com.example.prc.entities.Utente;

import java.util.List;
import java.util.stream.Collectors;

public class UtenteService {


    private UtenteDTO toDTO(Utente utente) {
        UtenteDTO ut = new UtenteDTO(
                utente.getEmail(),
                utente.getPassword(),
                utente.getName(),
                null,
                0,
                utente.getDataNasc(),
                utente.getProfissionalSaude(),
                utente.getDadosBiometricos(),
                utente.getPrcs()
        );
        return ut;
    }

    private List<UtenteDTO> toDTOs(List<Utente> utentes) {
        return utentes.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
