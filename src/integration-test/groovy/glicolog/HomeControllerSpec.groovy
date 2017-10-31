package glicolog

import spock.lang.Specification
import grails.plugins.rest.client.RestBuilder
import grails.testing.mixin.integration.Integration

@SuppressWarnings(['LineLength', 'MethodName'])
@Integration
class HomeControllerFuncSpec extends Specification {

    def 'test validação do saveForm'() {
        given:
        RestBuilder rest = new RestBuilder()

        when:
        def resp = rest.post("http://localhost:${serverPort}/home/saveForm") { 
            accept('application/json') 
            contentType('application/json') 
            json {
                dataRegistro = '01/10/2017'
                horaRegistro = '10:42'
                tipoRegistro = 'Glicemia'
                tipoGlicemia = 'Aspart'
                taxaGlicemia = 120
            }
        }

        then:
        resp.status == 422 
/*
        resp.json.errors.find { it.field == 'tipoRegistro' }.message == 'Tipo Registro fora da especificação'
*/
/*        resp.json.errors.find { it.field == 'tipoAtivFIsica' }.message == 'Property [tipoAtivFisica] of class [class glicolog.Home] cannot be null'
    }
*/    
}
