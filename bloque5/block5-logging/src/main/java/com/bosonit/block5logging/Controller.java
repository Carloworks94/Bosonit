package com.bosonit.block5logging;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Log4j2
public class Controller {
    //private static final Logger logger = LogManager.getLogger(Controller.class);

    @RequestMapping("/")
    public String index() {
        System.setProperty("log4j.configurationFile", "C:\\Users\\carlos.garrido\\IdeaProjects\\block5-logging\\src\\main\\resources\\log4j2.xml");
        //Configurator.initialize(null, "log4j2.xml");
        System.out.println("Config file: " + System.getProperty("log4j.configurationFile"));
        log.error("MENSAJE DE TIPO ERROR");
        log.debug("MENSAJE DE TIPO DEBUG");
        log.info("MENSAJE DE TIPO INFO");
        log.warn("MENSAJE DE TIPO WARNING");
        return "MENSAJES GENERADOS";
    }
}
