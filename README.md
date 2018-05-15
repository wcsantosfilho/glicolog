# glicolog
Projeto Glicolog - grails

Registro de Glicemias, Insulinas aplicadas, refeições e atividades físicas para efeito de controle e registro


* Item ------------------------------------------------------------------| Status |
* Padronizar formulários do lado direito da home                            OK
* Mensagem de erro deve voltar no formulário com dados                      OK
* Voltar no formulario após erro selecinando o "sub" form do lado direito   OK
* Validar formulário selecionado sem campos preenchidos                     OK
* Quando gera erro de validação, o que fazer com o 
| index() - lista de registros? Está gerando erro por falta do campo
| registroTotal - Veja HomeController, linhas 50 a 59 (solução porca!)      OK
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
* Inclusão de Glicemia sem hora dá mensagem de erro, mas zera o form.       OK
* Implementar o Validator no Client-Side                                    OK
* Permitir várias inserções de registros diferentes com a mesma data e hora OK
* Trazer data e hora pré-preenchidas                                        OK
* Validar no Command Info as Constrains do Domain                           OK
* Problemas com visualização do Form de Datas do Relatório no iPhone        None
* Pesquisar Plugins de segurança: Spring Security Core                      +/-
   |- Duração da Session                                                    None
* Layout de impressão para consulta médica                                  +/-
   |- Seleção de datas                                                      None
   |- Dados variáveis no cabeçalho (nome, data ini e data fim)              None
   |- Nova Aba ou Download                                                  OK
   |- HTML e/ou PDF                                                         None
   |- Rodapé ("Gerado por Glicolog") sozinho na página                      None
   |- Mudar layout do relatório para usar menos papel                       None
* Relatório por Intervalo de datas                                          +/-
   |- Discutir com usuário esquema de datas e relatório                     OK
   |- Intervalo de datas padrão (não pode ser "sempre")                     OK
   |- Máscara do dataIni e dataFim                                          OK
* Problemas com formulário submetido com "Enter"                            None
* Testar SendGrid para enviar email pelo Heroku                             None
* Datas Inicio e Fim do Relatório qdo não forem digitadas = Infinito        None
* Login e Senha do Banco de Dados + APIs em arquivo (tirar do codigo fonte) None
* Montar esquema para proteger API SendGrid                                 None
* Aviso por e-mail em caso de não preenchimento                             None
* Avanço de cursor pelo teclado após Data                                   None
* Campo "Tipo Registro" sobrepõe do "Data e Hora" nas views menores         None
* Ocultar Itens de Menu antes do Login                                      None
* Permitir consulta ascendente e descentente                                None
* Testar  http://bootstrapvalidator.com/ para o front-end                   None
* Tratar erros no Controller                                                None
* Estudar mecanismos de mensagem de erro, tanto no controller quanto no GSP +/-
* Pesquisar plugin Google visualization plugin para Charts                  None
* Estudar e Criar casos de testes                                           None
* Colocar em tabelas os tipos (tipo de glicemia, tipo de insulina etc)      None
* Cadastro de novos usuários                                                None
* Eliminar itens inúteis do home/index                                      None
* Criar mecanismo de exclusão de registro                                   None
* Criar ícone no cabeçalho da index para mostrar se é asc ou desc           None
* Criar ícone para a aplicação                                              None
* Seleção de Intervalo de data                                              None
* Implementar Busca (por alguma coisa... taxa, tipo registro)               None
* Consistência para evitar dois registros iguais na mesma data e hora       None
* Criar tipo de registro para Ocorrências (hipoglicemias, gripes, p.ex.)    None