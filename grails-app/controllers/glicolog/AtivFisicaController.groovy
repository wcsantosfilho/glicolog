package glicolog

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

class AtivFisicaController {

    
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AtivFisica.list(params), model:[ativfisicaCount: AtivFisica.count()]
    }
    
    def save() {
        def pessoa = Pessoa.findByNome(params.nome)
        //def p1 = new Pessoa(nome: "Irivaldo Abreu", idade: 22)
        //p1.save(flush: true)
        println 111111111
        println params.dataAtivFisica
        println 999999999
        def dataHoraAtiv = Date.parse('dd/MM/yyyy HH:mm:ss', params.dataAtivFisica + " " + params.horaAtivFisica+":00")
        def ativfisica = new AtivFisica (dataAtivFisica: dataHoraAtiv, tipoAtivFisica: params.tipoAtivFisica, observAtivFisica: params.observAtivFisica, pessoa: pessoa )
        ativfisica.save(flush: true)
        
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ativfisica.label', default: 'AtivFisica'), ativfisica.toString()])
                redirect(controller: "AtivFisica")
            }
            '*' { respond a1, [status: CREATED] }
        }
    }

}
