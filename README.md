# glicolog
Projeto Glicolog - grails

Registro de Glicemias, Insulinas aplicadas, refeições e atividades físicas para efeito de controle e registro


* Item ------------------------------------------------------------------| Status |
* Padronizar formulários do lado direito da home                            OK
* Mensagem de erro deve voltar no formulário com dados                      OK
* Voltar no formulario após erro selecinando o "sub" form do lado direito   OK
* Validar formulário selecionado sem campos preenchidos                     OK
* Quando gera erro de validação, o que fazer com o index() - lista de registros? Está gerando erro por falta do campo registroTotal - Veja HomeController, linhas 50 a 59 (solução porca!)                                                                                                   OK
* Implementar o save em cada Tipo de Registro                               OK
* Verificar falta dos scriptse formatação do HTML                           OK
* Acertar URL do TOMCAT no Catalyst                                         OK
* Erro na gravação da Insulina                                              OK
* Erro na gravação da refeição                                              OK
* Layout responsivo para celular                                            OK
* |-> Conteúdo da tabela                                                    OK
* |-> Menu não abre                                                         OK
* Alterar SGBD para MySQL no ambiente de desenvolvimento                    OK
* Acertar permissão no MySQL para gravar com usuário glicolog               OK
* Montar lista de Registros abaixo do form principal da home/index          OK
* Decidir como fazer a lista de itens (Herança no Registro, mas e a saída?) OK
* Arrumar a paginação                                                       OK
* Estudar e fazer deploy no Heroku com WAR                                  OK
* Corrigir erros da Criteria + Paginação                                    OK
* Decidir questão do usuário vs pessoa                                      OK
* Campos taxaGlicemia e doseInsulina devem ser numéricos                    OK
* Unificar a linha de dados para mesma data e hora                          OK
* Refatorar codigo para conceito de services (ver post)                     OK
* Pesquisar Plugins de segurança: Spring Security Core                      +/-
   |- Duração da Session                                                    None
* Permitir várias inserções de registros diferentes com a mesma data e hora None
* Aviso por e-mail em caso de não preenchimento                             None
* Inclusão de Glicemia sem hora dá mensagem de erro, mas zera o form.       None
* Avanço de cursor pelo teclado após Data                                   None
* Campo "Tipo Registro" sobrepõe do "Data e Hora" nas views menores         None
* Permitir consulta ascendente e descentente                                None
* Tratar erros no Controller                                                None
* Estudar mecanismos de mensagem de erro, tanto no controller quanto no GSP +/-
* Pesquisar plugin Google visualization plugin para Charts                  None
* Estudar e Criar casos de testes                                           None
* Trazer data e hora pré-preenchidas                                        None
* Implementar o Validator no Client-Side                                    None
* Validar no Command Info as Constrains do Domain                           None
* Colocar em tabelas os tipos (tipo de glicemia, tipo de insulina etc)      None
* Cadastro de novos usuários                                                None
* Eliminar itens inúteis do home/index                                      None
* Criar mecanismo de exclusão de registro                                   None
* Criar ícone no cabeçalho da index para mostrar se é asc ou desc           None
* Criar ícone para a aplicação                                              None
* Seleção de Intervalo de data                                              None
* Implementar Busca (por alguma coisa... taxa, tipo registro)               None
* Colocar consistência para evitar dois registros iguais na mesma data e hora None
* Layout de impressão para consulta médica                                  None
* Criar tipo de registro para Ocorrências (hipoglicemias, gripes, p.ex.)    None