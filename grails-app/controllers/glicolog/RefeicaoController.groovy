package glicolog

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

class RefeicaoController {

    /* --------------------------------
     *  Index
       -------------------------------- */
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Refeicao.list(params), model:[refeicaoCount: Refeicao.count()]
    }

    /* -------------------------------- *
     *  save                        *
     * -------------------------------- */
    @Transactional
    protected void save(Refeicao refeicao) {
        if (refeicao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        } 

        if (refeicao.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond refeicao.errors, view:'index'
            return
        }

        refeicao.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'refeicao.label', default: 'Refeição'), refeicao.id])
                redirect home
            }
            '*' { respond refeicao, [status: CREATED] }
        }

    }
    
    /* -------------------------------- *
     *  notFound                        *
     * -------------------------------- */
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'refeicao.label', default: 'Refeicao'), params.id])
                redirect controller: "home", action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

