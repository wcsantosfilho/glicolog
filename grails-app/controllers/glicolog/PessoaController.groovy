package glicolog

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PessoaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Pessoa.list(params), model:[pessoaCount: Pessoa.count()]
    }

    def show(Pessoa pessoa) {
        respond pessoa
    }

    def create() {
        respond new Pessoa(params)
    }

    @Transactional
    def save(Pessoa pessoa) {
        if (pessoa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pessoa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pessoa.errors, view:'create'
            return
        }

        pessoa.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), pessoa.id])
                redirect pessoa
            }
            '*' { respond pessoa, [status: CREATED] }
        }
    }

    def edit(Pessoa pessoa) {
        respond pessoa
    }

    @Transactional
    def update(Pessoa pessoa) {
        if (pessoa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pessoa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pessoa.errors, view:'edit'
            return
        }

        pessoa.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), pessoa.id])
                redirect pessoa
            }
            '*'{ respond pessoa, [status: OK] }
        }
    }

    @Transactional
    def delete(Pessoa pessoa) {

        if (pessoa == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        pessoa.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), pessoa.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pessoa.label', default: 'Pessoa'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
