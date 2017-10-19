package glicolog
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HomeController {

    
    def index() { }
    
    def insereAtivFisica() {
        redirect(controller: "AtivFisica", action: "save")
    }
}
