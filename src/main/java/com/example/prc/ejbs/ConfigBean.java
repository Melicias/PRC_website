package com.example.prc.ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@Singleton
public class ConfigBean {
    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");

    @PostConstruct
    public void populateDB() {

        System.out.println("Hello Java EE!");

        try{



        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
