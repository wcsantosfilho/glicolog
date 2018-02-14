package glicolog

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        // Testes com Logger
        Logger logger = LoggerFactory.getLogger("glicolog.HelloWorld1");
        logger.debug("Hello world.");
        
        // print internal state
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
        
        // Chamada da Aplicação Grails
        GrailsApp.run(Application, args)
    }
}