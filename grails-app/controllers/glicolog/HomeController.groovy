package glicolog
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HomeController {

    
    /* ------------------------------------------------ *
     *  index                                           *
     * ------------------------------------------------ */
    def index() { }
    
    /* ------------------------------------------------ *
     *  saveForm (input originario do home/index)      *
     * ------------------------------------------------ */
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
            return
        }
        
        if (pessoa.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pessoa.errors, view:'index'
            return
        }

        if (info.hasErrors()) {
            println "Erro no Info"
            transactionStatus.setRollbackOnly()
            respond info.errors, view:'index'
            return
        }
        
        AtivFisica ativfisica;
        switch(params.tipoRegistro) {
            case 'AtivFisica':
                def dataHoraAtiv = Date.parse('dd/MM/yyyy HH:mm:ss', info.dataRegistro + " " + info.horaRegistro+":00")
                ativfisica = new AtivFisica (dataAtivFisica: dataHoraAtiv, tipoAtivFisica: info.tipoAtivFisica, observAtivFisica: info.observAtivFisica, pessoa: pessoa )
                ativfisica.save(flush: true)

                break
            default:
                println("Nenhum tipo de registro escolhido");
                break;
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ativfisica.label', default: 'ativfisica'), ativfisica.toString()])
                redirect(controller: "AtivFisica")
            }
            '*' { respond ativfisica, [status: CREATED] }
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
        horaRegistro (blank: false, validator: {value, object ->
                if (!value.startsWith('12')) return 'validation.horaRegistroNot12';
        })
        tipoGlicemia nullable: true
        taxaGlicemia nullable: true
        tipoInsulina nullable: true
        doseInsulina nullable: true
        tipoRefeicao nullable: true
        observRefeicao nullable: true
        tipoAtivFisica nullable: true
        observAtivFisica nullable: true
        tipoAtivFisica (validator:{ value, object ->
            if (object.tipoRegistro == 'AtivFisica' &&
                !['Leve','Moderada','Intensa'].contains(value) ) {
                    return 'validation.tipoAtivFisicaErrado'
			}    
        })

    }
}