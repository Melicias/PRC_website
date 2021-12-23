package com.example.prc.dtos;

import com.example.prc.entities.Prescricao;
import com.example.prc.entities.ProfissionalSaude;
import com.example.prc.entities.UtenteDadosBiometricos;

import java.util.Date;
import java.util.List;

public class UtenteDTO {
    private String email;
    private String password;
    private String name;
    private Date deleted_at;
    private int blocked;
    private Date dataNasc;
    private List<ProfissionaSaudeDTO> profissionalSaude;
    private List<UtenteDadosBiometricos> dadosBiometricos;
    //private List<PRC> prescricoes;
}
