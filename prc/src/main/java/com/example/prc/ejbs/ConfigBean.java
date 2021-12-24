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
            tipoProfissional.create("Oftalmologia");
            tipoProfissional.create("Dermatologista");
            tipoProfissional.create("Genecologista");
            tipoProfissional.create("Urologista");

            tipoDadosBiometricos.create("bpm",60,100,null);
            tipoDadosBiometricos.create("colestrol",5,10,null);
            tipoDadosBiometricos.create("obesidade",0, 0,"cenas que nao vou criar ja mas possivelmente criado em json");

            adminBean.create("teste123","admin teste", "admin@admin.com");

            profissionalSaudeBean.create("teste123","Grayson Arnold", "profissional1@profissional.com",1);
            profissionalSaudeBean.create("teste123","Maria das cruzes", "profissional2@profissional.com",2);
            profissionalSaudeBean.create("teste123","Joaquim Alberto", "profissional3@profissional.com",3);
            profissionalSaudeBean.create("teste123","Kye Amin", "profissional4@profissional.com",4);
            profissionalSaudeBean.create("teste123","Jamaal Gibbons", "profissional5@profissional.com",5);
            profissionalSaudeBean.create("teste123","Odin Booth", "profissional6@profissional.com",2);
            profissionalSaudeBean.create("teste123","Mollie Goff", "profissional7@profissional.com",5);
            profissionalSaudeBean.create("teste123","Sophia Rooney", "profissional8@profissional.com",1);
            profissionalSaudeBean.create("teste123","Joel Tate", "profissional9@profissional.com",1);
            profissionalSaudeBean.create("teste123","Luna Ray", "profissional10@profissional.com",2);

            utenteBean.create("teste123","utente teste", "utente@utente.com", new Date("29/06/1999"),"profissional1@profissional.com");

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }
}
