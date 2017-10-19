package glicolog

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

class AtivFisicaController {

    /* --------------------------------
     *  Index
       -------------------------------- */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AtivFisica.list(params), model:[ativfisicaCount: AtivFisica.count()]
    }

    /* --------------------------------
     *  Save
       -------------------------------- */
    @Transactional
    def save() {
        def pessoa = Pessoa.findByNome("Carlos Carvalhares")
        if (pessoa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        } 
        
        if (pessoa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pessoa.errors, view:'index'
            return
        }



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
    
    
    /* --------------------------------
     *  notFound
       -------------------------------- */
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), params.id])
                redirect controller: "home", action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
