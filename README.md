# glicolog
Projeto Glicolog - grails

Registro de Glicemias, Insulinas aplicadas, refeições e atividades físicas para efeito de controle e registro


* Item ------------------------------------------------------------------| Status |
* Tratar erros no Controller                                                None
* Estudar mecanismos de mensagem de erro, tanto no controller quanto no GSP +/-
* Padronizar formulários do lado direito da home                            OK
* Mensagem de erro deve voltar no formulário com dados                      OK
* Voltar no formulario após erro selecinando o "sub" form do lado direito   OK
* Validar formulário selecionado sem campos preenchidos                     OK
* Quando gera erro de validação, o que fazer com o index() - lista de registros? Está gerando erro por falta do campo registroTotal - Veja HomeController, linhas 50 a 59 (solução porca!)                                                                                                   OK
* Implementar o save em cada Tipo de Registro                               OK
* Verificar falta dos scriptse formatação do HTML                           OK
* Acertar URL do TOMCAT no Catalyst                                         OK
* Erro na gravação da Insulina                                              OK
* Erro na gravação da refeição                                              None
* Inclusão de Glicemia sem hora dá mensagem de erro, mas zera o form.       None
* Estudar e Criar casos de testes                                           None
* Avanço de cursor pelo teclado após Data                                   None
* Trazer data e hora pré-preenchidas                                        None
* Layout responsivo para celular                                            None
* Implementar o Validator no Client-Side                                    None
* Validar no Command Info as Constrains do Domain                           None
* Colocar em tabelas os tipos (tipo de glicemia, tipo de insulina etc)      None
* Decidir questão do usuário vs pessoa                                      None
* Cadastro de novos usuários                                                None
* Eliminar itens inúteis do home/index                                      None
* Alterar SGBD para MySQL no ambiente de desenvolvimento                    OK
* Acertar permissão no MySQL para gravar com usuário glicolog               OK
* Duração da Session                                                        None
* Montar lista de Registros abaixo do form principal da home/index          OK
* Decidir como fazer a lista de itens (Herança no Registro, mas e a saída?) OK
* Arrumar a paginação                                                       OK
* Criar mecanismo de exclusão de registro                                   None
* Criar ícone no cabeçalho da index para mostrar se é asc ou desc           None
* Refatorar codigo para conceito de services (ver post)                     None
* Estudar e fazer deploy no Heroku com WAR                                  OK
