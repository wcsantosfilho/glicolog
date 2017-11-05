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

    /* -------------------------------- *
     *  save                        *
     * -------------------------------- */
    @Transactional
    protected void save(AtivFisica ativFisica) {
        if (ativFisica == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        } 

        if (ativFisica.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ativFisica.errors, view:'index'
            return
        }

        ativFisica.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ativFisica.label', default: 'Atividade Fisica'), ativFisica.id])
                redirect home
            }
            '*' { respond ativFisica, [status: CREATED] }
        }

    }
    
    /* -------------------------------- *
     *  notFound                        *
     * -------------------------------- */
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

