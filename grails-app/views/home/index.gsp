<%@ page import="glicolog.Pessoa" contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta name="layout" content="main" />
    <title>Home Page</title>
</head>

<body>
    <div class="container">
        <div class="row">
            <g:if test="${flash.message}">
                <div class="alert alert-danger" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors>
                <ul class="errors" role="alert">
                    <g:eachError var="error">
                        <g:if test="${error in org.springframework.validation.FieldError}">
                            <div class="alert alert-danger">
                                <g:message error="${error}" />
                            </div>
                        </g:if>
                    </g:eachError>
                </ul>
            </g:hasErrors>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-xs-6">
                    <!-- lado esquerdo do form -->
                    <div class="row">
                        <div class="col-md-6 col-xs-3 colunaDoForm">
                            <!-- 1a subdivisão do lado esquerdo do form -->
                            <label for="dataRegistro" class="label-control">Data e Hora:</label>
                            <div class="input-group input-group-sm" id="divDataRegistro">
                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                                <input name="dataRegistro" value="${registroInfo?.dataRegistro}" id="dataRegistro"/>
                                <div class="errorField" aria-live="polite"></div>
                            </div>
                            
                            <br>
                            <div class="input-group input-group-sm" id="divHoraRegistro">
                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                <input name="horaRegistro"  value="${registroInfo?.horaRegistro}" id="horaRegistro"/>
                                <div class="errorField" aria-live="polite"></div>
                            </div>
                            <br>
                        </div>
                        <!-- /1a subdivisão do lado esquerdo do form -->
                        <div class="col-md-6 col-xs-3 colunaDoForm">
                            <!-- 2a subdivisão do lado esquerdo do form -->
                            <label class="label-control">Tipo Registro:</label>
                            <br>
                            <g:radioGroup name="tipoRegistro" id="tipoRegistro" values="['Glicemia', 'Insulina', 'Refeicao', 'AtivFisica']" labels="['Glicemia', 'Insulina', 'Refeição', 'Atividade Fisica']" value="${registroInfo?.tipoRegistro}">
                                <li class="listRadio">${it.radio}
                                    <g:message code="${it.label}" />
                                </li>
                            </g:radioGroup>
                        </div>
                        <!-- /2a subdivisão do lado esquerdo do form -->
                    </div>
                    <!-- row -->
                </div>
                <!-- lado esquerdo do form -->
                <div class="col-md-6 col-xs-6">
                    <!-- lado direito do form -->
                    <!-- ============================================================ -->
                    <!--     FORM DA GLICEMIA                                         -->
                    <!-- ============================================================ -->
                    <g:form name="formGlicemia" resource="${this.registroInfo}" url="[action:'saveForm',controller:'home']" class="container formGlicolog" id="formGlicemia">
                        <!-- FORM DA GLICEMIA -->
                        <div class="grupoDeCampos" id="grupoGlicemia">
                            <g:textField type="text" class="formDataLadoDireito" name="dataRegistro" hidden="true" value="${registroInfo?.dataRegistro}" />
                            <g:textField type="text" class="formHoraLadoDireito" name="horaRegistro" hidden="true" value="${registroInfo?.horaRegistro}" />
                            <g:textField type="text" class="formTipoLadoDireito" name="tipoRegistro" hidden="true" value="${registroInfo?.tipoRegistro}" />
                            <div class="input-group input-group-sm" id="divTipoGlicemia">
                                <span class="input-group-addon" id="basic-addon3">Tipo Glicemia</span>
                                <g:select class="form-control" id="tipoGlicemia" name="tipoGlicemia" value="${registroInfo?.tipoGlicemia}" from="${['Pré prandial','Pós prandial','Controle']}" keys="${['Pre','Pos','Controle']}" noSelection="['':'-Tipo de Glicemia-']" />
                                <div class="errorField" aria-live="polite"></div>
                            </div>
                            <br>
                            <div class="input-group input-group-sm" id="divTaxaGlicemia">
                                <span class="input-group-addon" id="basic-addon3">Taxa Glicemia</span>
                                <input type="number" id="taxaGlicemia" class="form-control" name="taxaGlicemia" value="${registroInfo?.taxaGlicemia}" />
                                <div class="errorField" aria-live="polite"></div>
                            </div>
                            <br>
                            <button type="submit" class="btn btn-primary btn-sm botaoFormLadoDireito">Gravar</button>
                        </div>
                    </g:form>
                    <!-- ============================================================ -->
                    <!--     FORM DA INSULINA                                         -->
                    <!-- ============================================================ -->
                    <g:form name="formInsulina" resource="${this.registroInfo}" url="[action:'saveForm',controller:'home']" class="container formGlicolog" id="formInsulina">
                        <!-- FORM DA INSULINA -->
                        <div class="grupoDeCampos" id="grupoInsulina">
                            <g:textField type="text" class="formDataLadoDireito" name="dataRegistro" hidden="true" value="${registroInfo?.dataRegistro}" />
                            <g:textField type="text" class="formHoraLadoDireito" name="horaRegistro" hidden="true" value="${registroInfo?.horaRegistro}" />
                            <g:textField type="text" class="formTipoLadoDireito" name="tipoRegistro" hidden="true" value="${registroInfo?.tipoRegistro}" />
                            <div class="input-group input-group-sm" id="divTipoInsulina">
                                <span class="input-group-addon" id="basic-addon3">Tipo Insulina</span>
                                <g:select class="form-control" id="tipoInsulina" name="tipoInsulina" value="${registroInfo?.tipoInsulina}" from="${['Rápida - Aspart','Lenta - Glargina']}" keys="${['Aspart', 'Glargina']}" noSelection="['':'-Tipo de Insulina']" />
                                <div class="errorField" aria-live="polite"></div>
                            </div>
                            <br>
                            <div class="input-group input-group-sm" id="divDoseInsulina">
                                <span class="input-group-addon" id="basic-addon3">Insulina (doses)</span>
                                <input type="number" id="doseInsulina" class="form-control" name="doseInsulina" value="${registroInfo?.doseInsulina}" />
                                <div class="errorField" aria-live="polite"></div>
                            </div>
                            <br>
                            <button type="submit" class="btn btn-primary btn-sm botaoFormLadoDireito">Gravar</button>
                        </div>
                    </g:form>
                    <!-- ============================================================ -->
                    <!--       FORM DA REFEICAO                                      -->
                    <!-- ============================================================ -->
                    <g:form name="formRefeicao" resource="${this.registroInfo}" url="[action:'saveForm',controller:'home']" class="container formGlicolog" id="formRefeicao">
                        <!-- FORM DA REFEIÇÃO -->
                        <div class="grupoDeCampos" id="grupoRefeicao">
                            <g:textField type="text" class="formDataLadoDireito" name="dataRegistro" hidden="true" value="${registroInfo?.dataRegistro}" />
                            <g:textField type="text" class="formHoraLadoDireito" name="horaRegistro" hidden="true" value="${registroInfo?.horaRegistro}" />
                            <g:textField type="text" class="formTipoLadoDireito" name="tipoRegistro" hidden="true" value="${registroInfo?.tipoRegistro}" />

                            <div class="input-group input-group-sm" id="divTipoRefeicao">
                                <span class="input-group-addon" id="basic-addon3">Tipo Refeição</span>
                                <g:select class="form-control" id="tipoRefeicao" name="tipoRefeicao" value="${registroInfo?.tipoRefeicao}" from="${['Café','Almoço','Lanche','Jantar','Ceia']}" keys="${['Cafe', 'Almoco', 'Lanche', 'Jantar', 'Ceia']}" noSelection="['':'-Tipo de Refeição-']" />
                                <div class="errorField" aria-live="polite"></div>
                            </div>
                            <br>
                            <div class="input-group input-group-sm" id="divObservRefeicao">
                                <span class="input-group-addon" id="basic-addon3">Obs Refeição</span>
                                <input type="text" id="observRefeicao" class="form-control" name="observRefeicao" value="${registroInfo?.observRefeicao}" />
                                <div class="errorField" aria-live="polite"></div>
                            </div>
                            <br>
                            <button type="submit" class="btn btn-primary btn-sm botaoFormLadoDireito">Gravar</button>
                        </div>
                    </g:form>
                    <!-- ============================================================ -->
                    <!--        FORM DA ATIVIDADE FISICA                          -->
                    <!-- ============================================================ -->
                    <div class="grupoDeCampos" id="grupoAtivFisica">
                        <g:form name="formAtivFisica" resource="${this.registroInfo}" url="[action:'saveForm',controller:'home']" id="formAtivFisica" class="container formGlicolog">
                            <!-- FORM DA ATIVIDADE FISICA -->
                            <g:textField type="text" class="formDataLadoDireito" name="dataRegistro" hidden="true" value="${registroInfo?.dataRegistro}" />
                            <g:textField type="text" class="formHoraLadoDireito" name="horaRegistro" hidden="true" value="${registroInfo?.horaRegistro}" />
                            <g:textField type="text" class="formTipoLadoDireito" name="tipoRegistro" hidden="true" value="${registroInfo?.tipoRegistro}" />

                            <div class="input-group input-group-sm" id="divGrauAtivFisica">
                                <span class="input-group-addon" id="basic-addon3">Grau Ativ Física</span>
                                <g:select class="form-control" name="tipoAtivFisica" from="${['Leve','Moderada','Intensa']}" value="${registroInfo?.tipoAtivFisica}" noSelection="['':'-Grau de Atividade-']" id="tipoAtivFisica" />
                                <div class="errorField" aria-live="polite"></div>
                            </div>
                            <br>
                            <div class="input-group input-group-sm" id="divObservAtivFisica">
                                <span class="input-group-addon" id="basic-addon3">Obs Ativ Física</span>
                                <input type="text" class="form-control" name="observAtivFisica" value="${registroInfo?.observAtivFisica}" id="observAtivFisica" />
                                <div class="errorField" aria-live="polite"></div>
                            </div>
                            <br>
                            <button type="submit" class="btn btn-primary btn-sm botaoFormLadoDireito">Gravar</button>
                        </g:form>
                        <!-- / FORM DA ATIVIDADE FISICA -->
                    </div>
                </div>
                <!-- lado direito do form -->
            </div>
            <!-- row -->
            <hr>
            <div class="row">
                <div class="container">
                    <div id="list-registros" role="main">
                        <table class="table table-sm table-striped table-bordered table-glicolog">
                            <tr>
                                <g:sortableColumn property="dataRegistro" title="Data Registro" />
                                <th>Glicemia</th>
                                <th>Insulina</th>
                                <th>Refeição</th>
                                <th>Ativ.Física</th>
                            </tr>
                            <g:each in="${registroList}">
                                <tr>
                                    <!-- a Taglib listaDetalheRegistro já monta as tags <td></td> para cada coluna conforme o tipo do Registro -->
                                    <glicolog:listaDetalheRegistro item="${it}" />
                                </tr>
                            </g:each>
                        </table>
                        <g:if test="${!flagErro}">
                            <nav aria-label="Page navigation records">
                                Registros: ${registroTotal}
                                <ul class="pagination">
                                    <g:paginate next="Avançar" prev="Voltar" maxsteps="0" controller="home" action="index" total="${registroTotal}" />
                                </ul>
                            </nav>
                        </g:if>
                    </div>
                </div>
                <!-- container -->
            </div>
            <!-- row -->
        </div>
        <!-- row -->
    </div>
    <!-- container -->
</body>

</html>
