package glicolog
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON
import grails.converters.XML


class HomeController {
    static allowedMethods = [index:'GET',
            saveForm:'POST',
    ]

    /* ------------------------------------------------ *
     *  index                                           *
     * ------------------------------------------------ */
    def index() { 
        try {
            if (!session?.usuario) {
                //respond session.errors, view:'index'
                def flagErro = true
                def textoErro = "Sessão não iniciada. Faça Login"
                def listObject = [errors: textoErro, flagErro: flagErro]
                respond listObject, view:'index'            
                return
            }

            def pessoaParaSearch = Pessoa.findByNome(session?.usuario.name)
            if (pessoaParaSearch == null) {
                respond pessoaParaSearch.errors, view:'index'
                return
            }

            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            params.sort = params.sort ?: 'dataRegistro'
            params.order = params.order ?: 'desc'

            def registroCriteria = Registro.createCriteria()
            def searchResults = registroCriteria.list(params) {
                pessoa {
                    eq 'nome', session?.usuario.name
                }
                projections { 
                    groupProperty ('dataRegistro', 'dataRegistro')
                    min ('tipoGlicemia')
                    min ('taxaGlicemia')
                    min ('tipoInsulina')
                    min ('doseInsulina')
                    min ('tipoRefeicao')
                    min ('observRefeicao')
                    min ('tipoAtivFisica')
                    min ('observAtivFisica')
                }
            }
            
            def registroTotal = searchResults.totalCount
            def listObject = [registroList: searchResults, registroTotal: registroTotal]
            withFormat {
                html { listObject }
                json { render searchResults as JSON }
                xml { render listobject as XML }
            }
        } catch (Exception ex) {
            def errG = new errosGerais(controller: 'home', erroNoCatch: 'Exception', erroException: ex.message)
            respond errG, view:'error'
            return
        }
    }
    
    /* ------------------------------------------------ *
     *  saveForm (input originario do home/index)      *
     * ------------------------------------------------ */
    @Transactional(readOnly = false)
    def saveForm(RegistroInfo info) {
        try {
            def pessoa = Pessoa.findByNome(session.usuario.name)
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
                // flagErro será passado para a GSP. Se for true, não irá montar a paginação (já que a lista não foi montada)
                def flagErro = true
                def listObject = [errors: info.errors, flagErro: flagErro]
                respond listObject, view:'index'
                return
            }
            switch(params.tipoRegistro) {
                case 'Glicemia':
                    // Cria a instância
                    Glicemia glicemia;
                
                    // Monta os atributos com os dados do Command Info
                    def dataHoraAtiv = Date.parse('dd/MM/yyyy HH:mm:ss', info.dataRegistro + " " + info.horaRegistro+":00")
                    glicemia = new Glicemia (dataRegistro: dataHoraAtiv, tipoGlicemia: info.tipoGlicemia, taxaGlicemia: info.taxaGlicemia, pessoa: pessoa )
                
                    // salva
                    if (!glicemia.save(flush: true)) {
                        def mensagemErro = glicemia.errors.allErrors.join(' \n')
                        def errG = new errosGerais(controller: 'saveForm', erroNoCatch:'Erro no Save', erroException: mensagemErro)
                        respond errG, view:'error'
                        return
                    }

                    // request.withFormat => avalia o formato do request para gerar a resposta
                    request.withFormat {
                        // trata o conteúdo submetido por um "form" ou de um "multipartForm"
                        form multipartForm {
                            flash.message = message(code: 'default.created.message', args: [message(code: 'glicemia.label', default: 'glicemia'), glicemia.toString()])
                            redirect(controller: "Home")
                        }
                        // '*' => Trata o conteúdo geral, o que não se aplicar acima.
                        '*' { 
                            respond glicemia, [status: CREATED] 
                        }
                    }

                    break                
                case 'Insulina':
                    // Cria a instância
                    Insulina insulina;
                
                    // Monta os atributos com os dados do Command Info
                    def dataHoraAtiv = Date.parse('dd/MM/yyyy HH:mm:ss', info.dataRegistro + " " + info.horaRegistro+":00")
                    insulina = new Insulina (dataRegistro: dataHoraAtiv, tipoInsulina: info.tipoInsulina, doseInsulina: info.doseInsulina, pessoa: pessoa )
                
                    // salva
                    if (!insulina.save(flush: true)) {
                        def mensagemErro = insulina.errors.allErrors.join(' \n')
                        def errG = new errosGerais(controller: 'saveForm', erroNoCatch:'Erro no Save', erroException: mensagemErro)
                        respond errG, view:'error'
                        return
                    }

                    // request.withFormat => avalia o formato do request para gerar a resposta
                    request.withFormat {
                        // trata o conteúdo submetido por um "form" ou de um "multipartForm"
                        form multipartForm {
                            flash.message = message(code: 'default.created.message', args: [message(code: 'insulina.label', default: 'Insulina'), insulina.toString()])
                            redirect(controller: "Home")
                        }
                        // '*' => Trata o conteúdo geral, o que não se aplicar acima.
                        '*' { 
                            respond insulina, [status: CREATED] 
                        }
                    }

                    break                
                case 'Refeicao':
                    // Cria a instância
                    Refeicao refeicao;
                
                    // Monta os atributos com os dados do Command Info
                    def dataHoraAtiv = Date.parse('dd/MM/yyyy HH:mm:ss', info.dataRegistro + " " + info.horaRegistro+":00")
                    refeicao = new Refeicao (dataRegistro: dataHoraAtiv, tipoRefeicao: info.tipoRefeicao, observRefeicao: info.observRefeicao, pessoa: pessoa )
                
                    // salva
                    if (!refeicao.save(flush: true)) {
                        def mensagemErro = refeicao.errors.allErrors.join(' \n')
                        def errG = new errosGerais(controller: 'saveForm', erroNoCatch:'Erro no Save', erroException: mensagemErro)
                        respond errG, view:'error'
                        return
                    }

                    // request.withFormat => avalia o formato do request para gerar a resposta
                    request.withFormat {
                        // trata o conteúdo submetido por um "form" ou de um "multipartForm"
                        form multipartForm {
                            flash.message = message(code: 'default.created.message', args: [message(code: 'refeicao.label', default: 'Refeicao'), refeicao.toString()])
                            redirect(controller: "Home")
                        }
                        // '*' => Trata o conteúdo geral, o que não se aplicar acima.
                        '*' { 
                            respond refeicao, [status: CREATED] 
                        }
                    }

                    break
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
                            flash.message = message(code: 'default.created.message', args: [message(code: 'ativfisica.label', default: 'Ativfisica'), ativfisica.toString()])
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