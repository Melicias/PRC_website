package com.example.prc.ejbs;

import com.example.prc.entities.TipoPrescricao;
import com.example.prc.entities.TipoProfissional;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@Singleton
public class ConfigBean {
    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");

    @EJB
    TipoPrescricaoBean tipoPrescricao;
    @EJB
    TipoProfissionalBean tipoProfissional;
    @EJB
    TipoDadosBiometricosBean tipoDadosBiometricos;
    @EJB
    PrescricaoBean prescricaoBean;
    @EJB
    AdminBean adminBean;
    @EJB
    UtenteBean utenteBean;
    @EJB
    ProfissionalSaudeBean profissionalSaudeBean;

    @PostConstruct
    public void populateDB() {

        System.out.println("Hello Java EE!");

        try{

            tipoPrescricao.create("Exercicio Fisico");
            tipoPrescricao.create("Comer para emagrecer");
            tipoPrescricao.create("Comer para engordar");

            tipoProfissional.create("Cardiologista");
            tipoProfissional.create("Cirurgião");
            tipoProfissional.create("Clínico geral");

            tipoDadosBiometricos.create("bpm",60,100,null);
            tipoDadosBiometricos.create("colestrol",5,10,null);
            tipoDadosBiometricos.create("obesidade",0, 0,"cenas que nao vou criar ja mas possivelmente criado em json");

            adminBean.create("teste123","admin teste", "admin@admin.com");

            profissionalSaudeBean.create("teste123","profissional teste", "profissional@profissional.com",1);
            profissionalSaudeBean.create("teste123","profissional teste1", "profissional1@profissional1.com",1);

            utenteBean.create("teste123","utente teste", "utente@utente.com", new Date("29/06/1999"),"profissional@profissional.com");

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }
}
