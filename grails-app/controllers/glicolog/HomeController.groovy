package glicolog
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HomeController {

    
    def index() { }
    
    /* --------------------------------
     *  Save Form (input originario do home/index)
       -------------------------------- */
    @Transactional
    def saveForm(RegistroInfo info) {
        
        def pessoa = Pessoa.findByNome("Carlos Carvalhares")
        println 11111
        println pessoa.nome
        println 33333
        println params
        println 55555
        println info
        println 99999
        if (pessoa == null) {
            transactionStatus.setRollbackOnly()
            respond pessoa.errors, view:'index'
            // notFound()
            return
        }
        
        if (pessoa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pessoa.errors, view:'index'
            return
        }

        //info.validate()
        if (info.hasErrors()) {
            println "Erro no Info"
            transactionStatus.setRollbackOnly()
            respond info.errors, view:'index'
            return
        }
        
    /*    def dataHoraAtiv = Date.parse('dd/MM/yyyy HH:mm:ss', info.dataAtivFisica + " " + info.horaAtivFisica+":00")
        def ativfisica = new AtivFisica (dataAtivFisica: dataHoraAtiv, tipoAtivFisica: info.tipoAtivFisica, observAtivFisica: info.observAtivFisica, pessoa: pessoa )
        ativfisica.save(flush: true)
*/
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ativfisica.label', default: 'AtivFisica'), ativfisica.toString()])
                redirect(controller: "AtivFisica")
            }
            '*' { respond a1, [status: CREATED] }
        }
    }
}



class RegistroInfo implements grails.validation.Validateable {
    String dataRegistro
    String horaRegistro
    String tipoRegistro
    String tipoGlicemia
    String taxaGlicemia
    String tipoInsulina
    String doseInsulina
    String tipoRefeicao
    String observRefeicao
    String tipoAtivFisica
    String observAtivFisica

    static constraints = {
        tipoGlicemia nullable: true
        taxaGlicemia nullable: true
        tipoInsulina nullable: true
        doseInsulina nullable: true
        tipoRefeicao nullable: true
        observRefeicao nullable: true
        tipoAtivFisica nullable: true
        observAtivFisica nullable: true
        tipoAtivFisica (validator:{ value, object ->
            if (!['Leve','Moderada','Intensa'].contains(value) || object.tipoRegistro == 'AtivFisica') {
                return ['tipoAtivFisicaMissing']
			}
        })

    }
}