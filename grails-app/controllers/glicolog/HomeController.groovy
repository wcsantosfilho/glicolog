package glicolog
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON
import grails.converters.XML

@Transactional(readOnly = true)
class HomeController {
    static allowedMethods = [index:'GET',
            saveForm:'POST',
    ]
    
    /* ------------------------------------------------ *
     *  index                                           *
     * ------------------------------------------------ */
    def index() { 
        params.max = Math.min(params.max ? params.int('max') : 50, 200)
        def list = Registro.list(params)
        def listObject = [registroList: list, registroTotal: Registro.count()]
        withFormat {
            html { listObject }
            json { render list as JSON }
            xml { render listObject as XML }
        }
    }
    
    /* ------------------------------------------------ *
     *  saveForm (input originario do home/index)      *
     * ------------------------------------------------ */
    @Transactional
    def saveForm(RegistroInfo info) {
        try {
            def pessoa = Pessoa.findByNome("Carlos Carvalhares")
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
            switch(params.tipoRegistro) {
                case 'AtivFisica':
                    // Cria a instância
                    AtivFisica ativfisica;
                
                    // Monta os atributos com os dados do Command Info
                    def dataHoraAtiv = Date.parse('dd/MM/yyyy HH:mm:ss', info.dataRegistro + " " + info.horaRegistro+":00")
                    ativfisica = new AtivFisica (dataRegistro: dataHoraAtiv, tipoAtivFisica: info.tipoAtivFisica, observAtivFisica: info.observAtivFisica, pessoa: pessoa )
                
                    // salva
                    if (!ativfisica.save(flush: true)) {
                        def mensagemErro = ativFisica.errors.allErrors.join(' \n')
                        def errG = new errosGerais(controller: 'saveForm', erroNoCatch:'Erro no Save', erroException: mensagemErro)
                        respond errG, view:'error'
                        return
                    }

                    // request.withFormat => avalia o formato do request para gerar a resposta
                    request.withFormat {
                        // trata o conteúdo submetido por um "form" ou de um "multipartForm"
                        form multipartForm {
                            flash.message = message(code: 'default.created.message', args: [message(code: 'ativfisica.label', default: 'ativfisica'), ativfisica.toString()])
                            redirect(controller: "Home")
                        }
                        // '*' => Trata o conteúdo geral, o que não se aplicar acima.
                        '*' { 
                            respond ativfisica, [status: CREATED] 
                        }
                    }

                    break
                default:
                    println("Nenhum tipo de registro escolhido")
                    respond info.errors, view: 'index'
                    break
            }
        } catch(NullPointerException ex) {
            def errG = new errosGerais(controller: 'saveForm', erroNoCatch:'NullPointerException', erroException: ex.message)
            respond errG, view:'error'
            return
        } catch(MissingPropertyException ex) {
            def errG = new errosGerais(controller: 'saveForm', erroNoCatch:'MissingPropertyException', erroException: ex.message)
            respond errG, view:'error'
            return
        } catch (Exception ex) {
            def errG = new errosGerais(controller: 'saveForm', erroNoCatch: 'Exception', erroException: ex.message)
            respond errG, view:'error'
            return
        }
    }
}

class errosGerais {
    String controller
    String erroNoCatch
    String erroException;
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
        tipoRegistro (validator:{ value, object ->
            if (!['Glicemia','Insulina','Refeicao','AtivFisica'].contains(value) ) {
                    return 'validation.tipoRegistroErrado'
			}    
        })
        tipoGlicemia nullable: true
        tipoGlicemia (validator:{ value, object ->
            if (object.tipoRegistro == 'Glicemia' &&
                !['Pre','Pos','Controle'].contains(value) ) {
                    return 'validation.tipoGlicemiaErrado'
			}    
        })
        taxaGlicemia nullable: true
        taxaGlicemia (validator:{ value, object ->
            if (object.tipoRegistro == 'Glicemia' &&
                value == null ) {
                    return 'validation.taxaGlicemiaNulo'
			}    
        })
        tipoInsulina nullable: true
        tipoInsulina (validator:{ value, object ->
            if (object.tipoRegistro == 'Insulina' &&
                !['Aspart','Glargina'].contains(value) ) {
                    return 'validation.tipoInsulinaErrado'
			}    
        })
        doseInsulina nullable: true
        doseInsulina (validator:{ value, object ->
            if (object.tipoRegistro == 'Insulina' &&
                value == null ) {
                    return 'validation.doseInsulinaNulo'
			}    
        })
        tipoRefeicao nullable: true
        tipoRefeicao (validator:{ value, object ->
            if (object.tipoRegistro == 'Refeicao' &&
                !['Cafe','Almoco','Lanche','Jantar','Ceia'].contains(value) ) {
                    return 'validation.tipoRefeicaoErrado'
			}    
        })
        observRefeicao nullable: true
        tipoAtivFisica nullable: true
        tipoAtivFisica (validator:{ value, object ->
            if (object.tipoRegistro == 'AtivFisica' &&
                !['Leve','Moderada','Intensa'].contains(value) ) {
                    return 'validation.tipoAtivFisicaErrado'
			}    
        })
        observAtivFisica nullable: true
    }
}