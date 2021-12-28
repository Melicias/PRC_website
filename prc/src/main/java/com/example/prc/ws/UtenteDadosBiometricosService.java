package com.example.prc.ws;

import com.example.prc.dtos.TipoDadosBiometricosDTO;
import com.example.prc.dtos.UtenteDTO;
import com.example.prc.dtos.UtenteDadosBiometricosDTO;
import com.example.prc.entities.TipoDadosBiometricos;
import com.example.prc.entities.Utente;
import com.example.prc.entities.UtenteDadosBiometricos;

import java.util.List;
import java.util.stream.Collectors;

public class UtenteDadosBiometricosService {

    public UtenteDadosBiometricosDTO toDto(UtenteDadosBiometricos utenteDadosBiometricos) {
        UtenteDadosBiometricosDTO utenteDadosBiometricosDTO = new UtenteDadosBiometricosDTO(
                toDtoTipoDados(utenteDadosBiometricos.getTipoDadosBiometricos()),
                utenteDadosBiometricos.getData_observacao(),
                utenteDadosBiometricos.getValor()
        );
        utenteDadosBiometricosDTO.setUtente(toDtoUtente(utenteDadosBiometricos.getUtente()));
        return  utenteDadosBiometricosDTO;
    }

    public List<UtenteDadosBiometricosDTO> toDtos(List<UtenteDadosBiometricos> utenteDadosBiometricos){
        return utenteDadosBiometricos.stream().map(this::toDto).collect(Collectors.toList());
    }
    private TipoDadosBiometricosDTO toDtoTipoDados(TipoDadosBiometricos tipoDadosBiometricos) {
        TipoDadosBiometricosService tipoDadosBiometricosService = new TipoDadosBiometricosService();
        return tipoDadosBiometricosService.toDTO(tipoDadosBiometricos);
    }
    private UtenteDTO toDtoUtente(Utente utente) {
        UtenteService utenteService= new UtenteService();
        return utenteService.toDTO(utente);
    }
}
