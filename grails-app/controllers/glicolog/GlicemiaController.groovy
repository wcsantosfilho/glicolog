package glicolog

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class GlicemiaController {
    /* -------------------------------
     * Index
       ------------------------------- */
    def index(Integer max) {
        param.max = Math.min(max ?: 10, 100)
        respond Glicemia.list(params), model:[glicemiaCount: Glicemia.count()]
    }

    /* -------------------------------
     * save
     * ------------------------------- */
    @Transactional(readOnly = false)
    protected void save(Glicemia glicemia) {
        if (glicemia == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        
        if (glicemia.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond glicemia.errors, view:'index'
            return
        }
        
        glicemia.save flush:true
        
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code:'glicemia.label', default: 'Glicemia'), glicemia.id])
                redirect home
            }
            '*' { respond glicemia, [status: CREATED]}
        }
    }
    
    /* ------------------------------- *
     * notFound                        *
     * ------------------------------- */
    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code:'glicemia.label', default: 'Glicemia'), params.id])
                redirect controller: "home", action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
