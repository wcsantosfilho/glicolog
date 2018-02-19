package glicolog

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        // Chamada da Aplicação Grails
        GrailsApp.run(Application, args)
    }
}