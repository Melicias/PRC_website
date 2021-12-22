package com.example.prc.dtos;

import com.example.prc.entities.TipoProfissional;
import com.example.prc.entities.Utente;

import java.util.List;

public class ProfissionaSaudeDTO {
    private TipoProfissional tipoProfissional;
    private List<Utente> utentes;

    public ProfissionaSaudeDTO(TipoProfissional tipoProfissional, List<Utente> utentes) {
        this.tipoProfissional = tipoProfissional;
        this.utentes = utentes;
    }
}
