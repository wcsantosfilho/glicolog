package glicolog
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.converters.JSON
import grails.converters.XML
import org.hibernate.criterion.CriteriaSpecification
import grails.plugin.springsecurity.annotation.Secured

class HomeController {
    static allowedMethods = [index:'GET',
            saveForm:'POST', sendmail:'GET'
    ]

    def comunicacaoService
    def infoPessoaService
    def infoRegistroService
    
    /* ------------------------------------------------ *
     *  index                                           *
     * ------------------------------------------------ */
    @Secured(['ROLE_USER'])
    def index() { 
        try {
            // Verifica se há uma sessão ativa
            if (!isLoggedIn()) {
                def flagErro = true
                def textoErro = "Sessão não iniciada. Faça Login"
                def listObject = [errors: textoErro, flagErro: flagErro]
                respond listObject, view:'index'            
                return
            }

            // Busca a pessoa ligada ao usuário da sessão
            def pessoaParaSearch = infoPessoaService.pessoaDoUsuarioLogado(getPrincipal().username)
            
            // Valida se há uma pessoa ligada ao usuário logado
            if (pessoaParaSearch == null) {
                respond pessoaParaSearch.errors, view:'index'
                return
            }

            // Parametros de busca e paginação
            params.offset = params.offset ? params.int('offset') : 0
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            params.order = params.order ?: 'desc'
            
            // define a lista para passar para o GSP 
            def listObject = infoRegistroService.consultaRegistrosDaPessoa(pessoaParaSearch, params.offset, params.max, params.order)
            withFormat {
                html { listObject }
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
    @Secured(['ROLE_USER'])
    def saveForm(RegistroInfo info) {
        try {
            // Busca a pessoa ligada ao usuário da sessão
            def pessoa = infoPessoaService.pessoaDoUsuarioLogado(getPrincipal().username)
            
            if (pessoa == null) {
                respond pessoa.errors, view:'index'
                return
            }
            
            if (pessoa.hasErrors()) {
                respond pessoa.errors, view:'index'
                return
            }
            
            // Validação do Command Info
            if (info.hasErrors()) {
                // flagErro será passado para a GSP. Se for true, não irá montar a paginação (já que a lista não foi montada)
                println "Erro na validacao do Command Info (info.hasErrors())"
                println info.tipoAtivFisica
                println "-----------------"
                def flagErro = true
                def listObject = [registroInfo: info, errors: info.errors, flagErro: flagErro]
                respond listObject, view:'index'
                return
            }
            
            // Valida qual tipo de Registro está sendo inserido para chamar o objeto correspondente
            switch(params.tipoRegistro) {
                case 'Glicemia':
                    try {
                        // Chama o Service para Salvar a Glicemia
                        infoRegistroService.salvaRegistroGlicemia(pessoa, info)
                    }
                    catch (Exception ex) {
                        def mensagemErro = info.errors.allErrors.join(' \n')
                        def errG = new errosGerais(controller: 'saveForm', erroNoCatch:'Erro no Save', erroException: mensagemErro)
                        respond errG, view:'error'
                        return
                    }

                    // request.withFormat => avalia o formato do request para gerar a resposta
                    request.withFormat {
                        // trata o conteúdo submetido por um "form" ou de um "multipartForm"
                        form multipartForm {
                            flash.message = message(code: 'default.created.message', args: [message(code: 'glicemia.label', default: 'glicemia'), info.dataRegistro.toString(), info.horaRegistro.toString()])
                            redirect(controller: "Home")
                        }
                        // '*' => Trata o conteúdo geral, o que não se aplicar acima.
                        '*' { 
                            respond info, [status: CREATED] 
                        }
                    }

                    break                
                case 'Insulina':
                    try {
                        // Chama o Service para Salvar a Insulina
                        infoRegistroService.salvaRegistroInsulina(pessoa, info)
                    }
                    catch (Exception ex) {
                        def mensagemErro = info.errors.allErrors.join(' \n')
                        def errG = new errosGerais(controller: 'saveForm', erroNoCatch:'Erro no Save', erroException: mensagemErro)
                        respond errG, view:'error'
                        return
                    }

                    // request.withFormat => avalia o formato do request para gerar a resposta
                    request.withFormat {
                        // trata o conteúdo submetido por um "form" ou de um "multipartForm"
                        form multipartForm {
                            flash.message = message(code: 'default.created.message', args: [message(code: 'insulina.label', default: 'Insulina'), info.dataRegistro.toString()])
                            redirect(controller: "Home")
                        }
                        // '*' => Trata o conteúdo geral, o que não se aplicar acima.
                        '*' { 
                            respond info, [status: CREATED] 
                        }
                    }

                    break                
                case 'Refeicao':
                    try {
                        // Chama o Service para Salvar a Refeição
                        infoRegistroService.salvaRegistroRefeicao(pessoa, info)
                    }
                    catch (Exception ex) {
                        def mensagemErro = info.errors.allErrors.join(' \n')
                        def errG = new errosGerais(controller: 'saveForm', erroNoCatch:'Erro no Save', erroException: mensagemErro)
                        respond errG, view:'error'
                        return
                    }


                    // request.withFormat => avalia o formato do request para gerar a resposta
                    request.withFormat {
                        // trata o conteúdo submetido por um "form" ou de um "multipartForm"
                        form multipartForm {
                            flash.message = message(code: 'default.created.message', args: [message(code: 'refeicao.label', default: 'Refeicao'), info.dataRegistro.toString()])
                            redirect(controller: "Home")
                        }
                        // '*' => Trata o conteúdo geral, o que não se aplicar acima.
                        '*' { 
                            respond info, [status: CREATED] 
                        }
                    }

                    break
                case 'AtivFisica':
                    try {
                        // Chama o Service para Salvar a Atividade Fisica
                        infoRegistroService.salvaRegistroAtivFisica(pessoa, info)
                    }
                    catch (Exception ex) {
                        println 'Erro de Exception da chamada da Service'
                        if ( ex.getCause() != null ) { println ex.getCause() } else { println 'sem getCause()'}
                        if ( ex.message != null ) { println ex.message } else { println 'sem mensagens' }
                        println '---------------------------------------'
                        def mensagemErro = info.errors.allErrors.join(' \n')
                        def errG = new errosGerais(controller: 'saveForm', erroNoCatch:'Erro no Save', erroException: mensagemErro)
                        respond errG, view:'error'
                        return
                    }
                    // request.withFormat => avalia o formato do request para gerar a resposta
                    request.withFormat {
                        // trata o conteúdo submetido por um "form" ou de um "multipartForm"
                        form multipartForm {
                            flash.message = message(code: 'default.created.message', args: [message(code: 'ativfisica.label', default: 'Ativfisica'), info.dataRegistro.toString()])
                            redirect(controller: "Home")
                        }
                        // '*' => Trata o conteúdo geral, o que não se aplicar acima.
                        '*' { 
                            respond info, [status: CREATED] 
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
    
    /* ------------------------------------------------ *
     *  sendmail                                        *
     * ------------------------------------------------ */
    @Secured('ROLE_USER')
    def sendmail() {
        try {
            comunicacaoService.enviarEmail(
                "wcsantosfilho@gmail.com",
                "Walter Santos Filho",
                "Novo Login no Glicolog",
                "O usuario acaba de fazer Login no Glicolog")
            /* RETIREI DA LINHA ACIMA: ${session?.usuario.name} */
        } catch (Exception ex) {
            def errG = new errosGerais(controller: 'sendmail', erroNoCatch: 'Exception', erroException: ex.message)
            respond errG, view:'error'
            return
        }   
        redirect(controller:"home", action:"index")
    }
}

class errosGerais {
    String controller
    String erroNoCatch
    String erroException;
}

/* ---------------------------------------------------------- *
 * CommandInfo para validar dados de entrda pelas constraints *
 * ---------------------------------------------------------- */
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
        // Valida o tipo de Registro
        tipoRegistro (validator:{ value, object ->
            if (!['Glicemia','Insulina','Refeicao','AtivFisica'].contains(value) ) {
                    return 'validation.tipoRegistroErrado'
			}    
        })
        
        // Valida o tipo de Glicemia
        tipoGlicemia nullable: true
        tipoGlicemia (validator:{ value, object ->
            if (object.tipoRegistro == 'Glicemia' &&
                !['Pre','Pos','Controle'].contains(value) ) {
                    return 'validation.tipoGlicemiaErrado'
			}    
        })
        
        // Valida a taxa de Glicemia
        taxaGlicemia nullable: true
        taxaGlicemia (validator:{ value, object ->
            if (object.tipoRegistro == 'Glicemia' &&
                value == null ) {
                    return 'validation.taxaGlicemiaNulo'
			}    
        })
        
        // Valida o tipo de Insulina
        tipoInsulina nullable: true
        tipoInsulina (validator:{ value, object ->
            if (object.tipoRegistro == 'Insulina' &&
                !['Aspart','Glargina'].contains(value) ) {
                    return 'validation.tipoInsulinaErrado'
			}    
        })
        
        // Valida a dose de insulina
        doseInsulina nullable: true
        doseInsulina (validator:{ value, object ->
            if (object.tipoRegistro == 'Insulina' &&
                value == null ) {
                    return 'validation.doseInsulinaNulo'
			}    
        })
        
        // Valida o tipo de refeição
        tipoRefeicao nullable: true
        tipoRefeicao (validator:{ value, object ->
            if (object.tipoRegistro == 'Refeicao' &&
                !['Cafe','Almoco','Lanche','Jantar','Ceia'].contains(value) ) {
                    return 'validation.tipoRefeicaoErrado'
			}    
        })
        
        // Validação da Observação de Refeição
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