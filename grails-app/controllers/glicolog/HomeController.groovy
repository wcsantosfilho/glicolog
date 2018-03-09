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
    
    /* ------------------------------------------------ *
     *  index                                           *
     * ------------------------------------------------ */
    @Secured(['ROLE_USER'])
    def index() { 
        try {
            def usuarioLogado = User.findByUsername(principal.username)
            // Busca a pessoa ligada ao usuário da sessão
            def pessoaParaSearch = usuarioLogado.pessoa

            // Verifica se há uma sessão ativa
            if (!isLoggedIn()) {
                //respond session.errors, view:'index'
                def flagErro = true
                def textoErro = "Sessão não iniciada. Faça Login"
                def listObject = [errors: textoErro, flagErro: flagErro]
                respond listObject, view:'index'            
                return
            }

            // Valida se há uma pessoa ligada ao usuário logado
            if (pessoaParaSearch == null) {
                respond pessoaParaSearch.errors, view:'index'
                return
            }

            // Parametros de busca e paginação
            params.max = Math.min(params.max ? params.int('max') : 10, 100)
            params.sort = params.sort ?: 'dataRegistro'
            params.order = params.order ?: 'desc'
            
            // HQL para buscar os registros da pessoa, agrupados pela data e hora em uma mesma linha
            // depois transforma em Collect para virar um MAPA com propriedades nomeadas (a GSP/Taglib usa assim)
            def searchResults = Registro.executeQuery("""
                select dataRegistro, 
                min (tipoGlicemia),
                min (taxaGlicemia),
                min (tipoInsulina),
                min (doseInsulina),
                min (tipoRefeicao),
                min (observRefeicao),
                min (tipoAtivFisica),
                min (observAtivFisica)
                FROM Registro
                WHERE pessoa = :pessoaParaSearch
                GROUP BY dataRegistro
                ORDER BY dataRegistro desc""", [pessoaParaSearch:pessoaParaSearch, offset:params.offset, max:params.max]).collect {
                    [   dataRegistro: it[0],
                        tipoGlicemia: it[1],
                        taxaGlicemia: it[2],
                        tipoInsulina: it[3],
                        doseInsulina: it[4],
                        tipoRefeicao: it[5],
                        observRefeicao: it[6],
                        tipoAtivFisica: it[7],
                        observAtivFisica: it[8]
                    ]
            }
            
            // HQL para contar os registros da pessoa, agrupados pela data e hora em uma mesma linha
            def searchCount = Registro.executeQuery("""
                select count(dataRegistro)
                    FROM Registro
                    WHERE pessoa = :pessoaParaSearch
                    GROUP BY dataRegistro""", [pessoaParaSearch:pessoaParaSearch])
            
            def registroTotal = searchCount.size()
            
            // define a lista para passar para o GSP
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
    @Secured(['ROLE_USER'])
    def saveForm(RegistroInfo info) {
        try {
            def usuarioLogado = User.findByUsername(principal.username)
            // Busca a pessoa ligada ao usuário da sessão
            def pessoa = usuarioLogado.pessoa
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
            
            // Validação do Command Info
            if (info.hasErrors()) {
                println "Erro no Info"
                transactionStatus.setRollbackOnly()
                // flagErro será passado para a GSP. Se for true, não irá montar a paginação (já que a lista não foi montada)
                def flagErro = true
                def listObject = [errors: info.errors, flagErro: flagErro]
                respond listObject, view:'index'
                return
            }
            
            // Valida qual tipo de Registro está sendo inserido para chamar o objeto correspondente
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
                
                    // salva o Registro de Refeicao
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
    
    /* ------------------------------------------------ *
     *  sendmail                                        *
     * ------------------------------------------------ */
    @Secured('ROLE_USER')
    def sendmail() {
        try {
            comunicacaoService.enviarEmail(
                "wcsantosfilho@gmail.com",
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