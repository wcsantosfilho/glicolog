package glicolog

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)

class InsulinaController {
    /* -------------------------------
     * Index
       ------------------------------- */
    def index(Integer max) {
        param.max = Math.min(max ?: 10, 100)
        respond Insulina.list(params), model:[insulinaCount: Insulina.count()]
    }

    /* -------------------------------
     * save
     * ------------------------------- */
    @Transactional
    protected void save(Insulina insulina) {
        if (insulina == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        
        if (insulina.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond insulina.errors, view:'index'
            return
        }
        
        insulina.save flush:true
        
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code:'insulina.label', default: 'Insulina'), insulina.id])
                redirect home
            }
            '*' { respond insulina, [status: CREATED]}
        }
    }
    
    /* ------------------------------- *
     * notFound                        *
     * ------------------------------- */
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code:'insulina.label', default: 'Insulina'), params.id])
                redirect controller: "home", action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
