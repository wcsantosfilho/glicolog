import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

// See http://logback.qos.ch/manual/groovy.html for details on configuration

appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')
        pattern =
                '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        '%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
    }
}

appender("FILE", FileAppender) {
    file= "testFile02.log"
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}) %level %logger - %msg%n"
    }
}

def targetDir = BuildSettings.TARGET_DIR


if (Environment.isDevelopmentMode() && targetDir != null) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger("StackTrace", ERROR, ['FULL_STACKTRACE'], false)
    logger 'grails.plugin.springsecurity.web.filter.DebugFilter', DEBUG, ['FILE'], false
    // logger 'org.springframework.security', DEBUG, ['FILE'], false
    // logger 'grails.plugin.springsecurity', DEBUG, ['FILE'], false
    logger 'grails.app.controllers.glicolog.HomeController', INFO, ['FILE'], false
    // logger("org.hibernate.SQL", DEBUG, ['FILE'], false)
    // logger("org.hibernate.type.descriptor.sql.BasicBinder", TRACE, ['FILE'], false)
}

if (Environment.current == Environment.PRODUCTION && targetDir != null) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}) %level %logger - %msg%n"
        }
    }
    logger 'grails.plugin.springsecurity.web.filter.DebugFilter', INFO, ['STDOUT'], false
    logger 'org.springframework.security', DEBUG, ['FILE'], false
    logger 'grails.plugin.springsecurity', DEBUG, ['FILE'], false
    logger 'grails.app.controllers.glicolog.HomeController', INFO, ['STDOUT'], false
}

root(ERROR, ['STDOUT'])
//root(INFO, ['FILE'])
//root(WARN, ['STDOUT'])
