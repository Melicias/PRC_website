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
    @EJB
    PrcBean prcBean;
    @EJB
    UtenteDadosBiometricosBean dadosBiometricosBean;

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

            prescricaoBean.create("Prescricao (descricao 1)","Prescricao 1",1);
            prescricaoBean.create("Prescricao (descricao 2)","Prescricao 2",2);
            prescricaoBean.create("Prescricao (descricao 3)","Prescricao 3",3);

            adminBean.create("teste123","admin teste", "admin@admin.com");
          
            profissionalSaudeBean.create("teste123","profissional teste", "profissional@profissional.com",1);
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

            utenteBean.create("teste123","Sameer Cabrera", "utente@utente.com", new Date("29/06/1999"),"profissional1@profissional.com");
            utenteBean.create("teste123","Kaydee Hodson", "utente2@utente.com", new Date("20/03/1990"),"profissional2@profissional.com");
            utenteBean.create("teste123","Ana Taylor", "utente3@utente.com", new Date("19/08/1989"));
            utenteBean.create("teste123","Jorden Churchill", "utente4@utente.com", new Date("9/10/1979"));
            utenteBean.create("teste123","Peter Barrera", "utente5@utente.com", new Date("2/11/1969"),"profissional9@profissional.com");
            utenteBean.create("teste123","Amina Rivera", "utente6@utente.com", new Date("4/10/1995"));
            utenteBean.create("teste123","Cian Ellis", "utente7@utente.com", new Date("8/01/1984"),"profissional10@profissional.com");
            utenteBean.create("teste123","Delia Walsh", "utente8@utente.com", new Date("18/02/1978"),"profissional1@profissional.com");
            utenteBean.create("teste123","Benjamin Holder", "utente9@utente.com", new Date("24/12/1984"),"profissional2@profissional.com");
            utenteBean.create("teste123","Ari Rosario", "utente10@utente.coidcom", new Date("23/04/1995"),"profissional4@profissional.com");
            utenteBean.create("teste123","Marcia House", "utente11@utente.com", new Date("15/07/1990"));
            utenteBean.create("teste123","Kelan Barrett", "utente12@utente.com", new Date("29/06/2002"),"profissional1@profissional.com");
            utenteBean.create("teste123","Shanna Wilkinson", "utente13@utente.com", new Date("21/04/1978"));
            utenteBean.create("teste123","Jibril Dotson", "utente14@utente.com", new Date("26/03/1969"),"profissional2@profissional.com");
            utenteBean.create("teste123","Killian Higgins", "utente15@utente.com", new Date("11/09/1943"));
            utenteBean.create("teste123","Roman Morgan", "utente16@utente.com", new Date("5/11/1994"),"profissional5@profissional.com");
            utenteBean.create("teste123","Luna Kearney", "utente17@utente.com", new Date("2/12/1998"),"profissional3@profissional.com");
            utenteBean.create("teste123","Weronika Orozco", "utente18@utente.com", new Date("30/05/1980"));
            utenteBean.create("teste123","Duke Magana", "utente19@utente.com", new Date("24/06/1970"),"profissional4@profissional.com");
            utenteBean.create("teste123","Ami Pennington", "utente20@utente.com", new Date("16/08/1975"),"profissional9@profissional.com");
            utenteBean.create("teste123","Adele Farmer", "utente21@utente.com", new Date("22/05/1980"),"profissional10@profissional.com");
            utenteBean.create("teste123","Jordi Greene", "utente22@utente.com", new Date("20/10/1990"));

            prcBean.create("utente@utente.com", "profissional@profissional.com", "Doença", new Date("29/12/2021"), 1);
            prcBean.create("utente@utente.com", "profissional2@profissional.com", "Doença 1", new Date("29/12/2021"), 1);
            prcBean.create("utente@utente.com", "profissional1@profissional.com", "Doença 2", new Date("29/12/2021"), 1);
            prcBean.create("utente@utente.com", "profissional9@profissional.com", "Doença 3", new Date("29/12/2021"), 1);
            prcBean.create("utente@utente.com", "profissional5@profissional.com", "Doença 4", new Date("29/12/2021"), 1);
            prcBean.create("utente@utente.com", "profissional1@profissional.com", "Doença 5", new Date("29/12/2021"), 1);

            dadosBiometricosBean.create(1,new Date("28/02/2020"),"115","utente@utente.com");

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }
}
