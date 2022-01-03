package com.example.prc.ejbs;

import com.example.prc.dtos.TipoDadosBiometricosQuantitativoDTO;
import com.example.prc.entities.TipoDadosBiometricosQuantitativo;
import com.example.prc.entities.TipoPrescricao;
import com.example.prc.entities.TipoProfissional;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
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
    @EJB
    TipoDadosBiometricosQuantitativoBean tipoDadosBiometricosQuantitativo;

    @PostConstruct
    public void populateDB() {

        System.out.println("Hello Java EE!");

        try{

            tipoPrescricao.create("Mediquentosa");
            tipoPrescricao.create("Dieta");
            tipoPrescricao.create("Exercicio fisico");

            tipoProfissional.create("Cardiologista");
            tipoProfissional.create("Cirurgião");
            tipoProfissional.create("Clínico geral");
            tipoProfissional.create("Oftalmologia");
            tipoProfissional.create("Dermatologista");
            tipoProfissional.create("Genecologista");
            tipoProfissional.create("Urologista");

            tipoDadosBiometricos.create("bpm",60,100,true,new ArrayList<>());
            tipoDadosBiometricos.create("Oxigenio no sangue",95,100,true,new ArrayList<>());
            tipoDadosBiometricos.create("colestrol",5,10,true,new ArrayList<>());
            tipoDadosBiometricos.create("IMC",0, 0,false,new ArrayList<>());
            tipoDadosBiometricos.create("Tensão arterial",0,0,false,new ArrayList<>());
            tipoDadosBiometricosQuantitativo.create("Otima",0,119.9,5);
            tipoDadosBiometricosQuantitativo.create("Normal",120,129.9,5);
            tipoDadosBiometricosQuantitativo.create("Normal Alta",130,139.9,5);
            tipoDadosBiometricosQuantitativo.create("Hipertensão grau I",140,159.9,5);
            tipoDadosBiometricosQuantitativo.create("Hipertensão grau II",160,179.9,5);
            tipoDadosBiometricosQuantitativo.create("Hipertensão grau III",180,0,5);
            tipoDadosBiometricosQuantitativo.create("Lower",0,18.5,4);
            tipoDadosBiometricosQuantitativo.create("Normal",18.5,24.9,4);
            tipoDadosBiometricosQuantitativo.create("Overweight",25,29.9,4);
            tipoDadosBiometricosQuantitativo.create("Obesity I",30,34.9,4);
            tipoDadosBiometricosQuantitativo.create("Obesity II",35,39.9,4);
            tipoDadosBiometricosQuantitativo.create("Obesity III",40,0,4);


            prescricaoBean.create("Tomar os medicamentos todos os dias","Prescricao medicamentos diario",1);
            prescricaoBean.create("Tomar os medicamentos a noite","Prescricao medicamentos noturnos",1);
            prescricaoBean.create("Cuidado com a alimentação, apenas comer arroz e carnes","Prescricao de arroz e carnes",2);
            prescricaoBean.create("Não comer carnes vermelhas","Prescricao evitar carnes vermelhas",2);
            prescricaoBean.create("Apenas comer comidas não processadas","Prescricao de cuidado com processados",2);
            prescricaoBean.create("Fazer cardio pelo menos 1 vez por dia","Prescricao cardio diario",3);
            prescricaoBean.create("Fazer Levantamento de pesos","Prescricao ginasio",3);

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

            prcBean.create("utente@utente.com", "profissional@profissional.com", "Doença", new Date("2021/12/29"), 1,true);
            prcBean.create("utente@utente.com", "profissional2@profissional.com", "Doença 1", new Date("2021/12/22"), 1,true);
            prcBean.create("utente@utente.com", "profissional1@profissional.com", "Doença 2", new Date("2021/12/27"), 1,true);
            prcBean.create("utente@utente.com", "profissional9@profissional.com", "Doença 3", new Date("2021/12/29"), 1,true);
            prcBean.create("utente@utente.com", "profissional5@profissional.com", "Doença 4", new Date("2021/12/29"), 1,true);
            prcBean.create("utente@utente.com", "profissional1@profissional.com", "Doença 5", new Date("2021/12/30"), 1,true);
            prcBean.create("utente2@utente.com", "profissional2@profissional.com", "Doença rara", new Date("2022/01/05"), 6,true);
            prcBean.create("utente5@utente.com", "profissional1@profissional.com", "Doença teste", new Date("2022/02/10"), 1,true);
            prcBean.create("utente3@utente.com", "profissional1@profissional.com", "Doença teste 2.0", new Date("2022/02/01"), 7,true);
            prcBean.create("utente6@utente.com", "profissional1@profissional.com", "Doença random", new Date("2022/12/10"), 2,true);
            prcBean.create("utente9@utente.com", "profissional1@profissional.com", "Doença que nao se sabe", new Date("2022/01/10"), 4,true);

            dadosBiometricosBean.create(1,new Date("01/01/2022"),70,"utente@utente.com");
            dadosBiometricosBean.create(3,new Date("01/01/2022"),94,"utente@utente.com");
            dadosBiometricosBean.create(3,new Date("03/01/2022"),95,"utente@utente.com");
            dadosBiometricosBean.create(4,new Date("20/10/2021"),20,"utente@utente.com");
            dadosBiometricosBean.create(5,new Date("20/10/2021"),119,"utente@utente.com");
            dadosBiometricosBean.create(2,new Date("03/01/2022"),60,"utente@utente.com");
            dadosBiometricosBean.create(5,new Date("03/01/2022"),140,"utente@utente.com");
            dadosBiometricosBean.create(1,new Date("22/12/2021"),75,"utente@utente.com");
            dadosBiometricosBean.create(4,new Date("21/12/2021"),25,"utente@utente.com");
            dadosBiometricosBean.create(1,new Date("21/10/2021"),74,"utente@utente.com");
            dadosBiometricosBean.create(1,new Date("04/01/2022"),85,"utente@utente.com");
            dadosBiometricosBean.create(4,new Date("20/12/2021"),30,"utente21@utente.com");
            dadosBiometricosBean.create(1,new Date("25/12/2021"),80,"utente21@utente.com");
            dadosBiometricosBean.create(2,new Date("26/11/2021"),70,"utente21@utente.com");
            dadosBiometricosBean.create(1,new Date("03/01/2022"),75,"utente21@utente.com");
            dadosBiometricosBean.create(4,new Date("28/11/2021"),27.5,"utente21@utente.com");
            dadosBiometricosBean.create(2,new Date("05/12/2021"),70,"utente21@utente.com");
            dadosBiometricosBean.create(5,new Date("05/12/2021"),125,"utente21@utente.com");
            dadosBiometricosBean.create(1,new Date("14/12/2021"),60,"utente21@utente.com");
            dadosBiometricosBean.create(1,new Date("22/12/2021"),70,"utente21@utente.com");
            dadosBiometricosBean.create(4,new Date("26/12/2021"),29,"utente21@utente.com");
            dadosBiometricosBean.create(1,new Date("04/01/2022"),59,"utente21@utente.com");
            dadosBiometricosBean.create(2,new Date("02/01/2022"),70,"utente21@utente.com");
            dadosBiometricosBean.create(2,new Date("27/12/2021"),70,"utente21@utente.com");


        } catch (Exception e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }
}
