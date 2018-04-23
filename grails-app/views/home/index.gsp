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
            <div class="row">
                <p id="errorMessage"></p>
            </div>
            <div class="row">
                <g:form name="formRegistro" resource="${this?.registroInfo}" url="[action:'saveForm',controller:'home']" class="container formGlicolog" id="formRegistro">
                    <div class="row">
                        <!-- Lado Esquerdo do Form (Data e Hora) -->
                        <div class="col-md-6 col-xs-6">
                            <label for="dataRegistro" class="label-control">Data e Hora:</label>
                            <div class="input-group input-group-sm" id="divDataRegistro">
                                <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                                <input name="dataRegistro" value="${registroInfo?.dataRegistro}" id="dataRegistro" />
                                <div class="errorField" aria-live="polite"></div>
                            </div>

                            <br>

                            <div class="input-group input-group-sm" id="divHoraRegistro">
                                <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                <input name="horaRegistro" value="${registroInfo?.horaRegistro}" id="horaRegistro" />
                                <div class="errorField" aria-live="polite"></div>
                            </div>
                            <br>
                            <div class="input-group input-group-sm glicoHidden" id="divtipoRegistro">
                                <span class="input-group-addon" id="basic-addon3">tipoRegistro</span>
                                <input type="text" id="tipoRegistro" class="form-control" name="tipoRegistro" value="${registroInfo?.tipoRegistro}" />
                                <div class="errorField" aria-live="polite"></div>
                            </div>

                            <ul class="ulTabs">
                                <li class="listTabs" id="iconeGlicemia"><span class="fa fa-tachometer"> Glicemia</span></li>
                                <li class="listTabs" id="iconeInsulina"><span class="fa fa-thermometer"> Insulina</span></li>
                                <li class="listTabs" id="iconeRefeicao"><span class="fa fa-cutlery"> Refeição</span></li>
                                <li class="listTabs" id="iconeAtivFisica"><span class="fa fa-bicycle"> Atividade Física</span></li>
                            </ul>
                            <br>

                        </div>
                        <!-- Fim Lado Esquerdo -->
                        <!-- Lado Direito do Form (Tabs c/ Tipos de Registro) -->
                        <div class="col-md-6 col-xs-6">
                            <div class="container">
                                <ul class="nav nav-tabs" id="regTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link" id="glicemiaTab" data-toggle="tab" href="#Glicemia" role="tab">Glicemia</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="insulinaTab" data-toggle="tab" href="#Insulina" role="tab">Insulina</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="refeicaoTab" data-toggle="tab" href="#Refeicao" role="tab">Refeição</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="ativFisicaTab" data-toggle="tab" href="#AtivFisica" role="tab">Atividade Física</a>
                                    </li>
                                </ul>
                                <div class="tab-content" id="regTabContent">
                                    <div class="tab-pane fade show active" id="Glicemia" role="tabpanel" aria-labelledby="glicemia-tab">
                                        <h4>glicemia</h4>
                                        <div class="input-group input-group-sm" id="divTipoGlicemia">
                                            <span class="input-group-addon" id="basic-addon3">Tipo Glicemia</span>
                                            <g:select class="form-control glicoInput" id="tipoGlicemia" name="tipoGlicemia" value="${registroInfo?.tipoGlicemia}" from="${['Pré prandial','Pós prandial','Controle']}" keys="${['Pre','Pos','Controle']}" noSelection="['':'-Tipo de Glicemia-']" />
                                            <div class="errorField" aria-live="polite"></div>
                                        </div>
                                        <br>
                                        <div class="input-group input-group-sm" id="divTaxaGlicemia">
                                            <span class="input-group-addon" id="basic-addon3">Taxa Glicemia</span>
                                            <input type="number" id="taxaGlicemia" class="form-control glicoInput" name="taxaGlicemia" value="${registroInfo?.taxaGlicemia}" />
                                            <div class="errorField" aria-live="polite"></div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="Insulina" role="tabpanel" aria-labelledby="insulina-tab">
                                        <h4>insulina</h4>
                                        <div class="input-group input-group-sm" id="divTipoInsulina">
                                            <span class="input-group-addon" id="basic-addon3">Tipo Insulina</span>
                                            <g:select class="form-control glicoInput" id="tipoInsulina" name="tipoInsulina" value="${registroInfo?.tipoInsulina}" from="${['Rápida - Aspart','Lenta - Glargina']}" keys="${['Aspart', 'Glargina']}" noSelection="['':'-Tipo de Insulina']" />
                                            <div class="errorField" aria-live="polite"></div>
                                        </div>
                                        <br>
                                        <div class="input-group input-group-sm" id="divDoseInsulina">
                                            <span class="input-group-addon" id="basic-addon3">Insulina (doses)</span>
                                            <input type="number" id="doseInsulina" class="form-control glicoInput" name="doseInsulina" value="${registroInfo?.doseInsulina}" />
                                            <div class="errorField" aria-live="polite"></div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="Refeicao" role="tabpanel" aria-labelledby="refeicao-tab">
                                        <h4>refeicao</h4>
                                        <div class="input-group input-group-sm" id="divTipoRefeicao">
                                            <span class="input-group-addon" id="basic-addon3">Tipo Refeição</span>
                                            <g:select class="form-control glicoInput" id="tipoRefeicao" name="tipoRefeicao" value="${registroInfo?.tipoRefeicao}" from="${['Café','Almoço','Lanche','Jantar','Ceia']}" keys="${['Cafe', 'Almoco', 'Lanche', 'Jantar', 'Ceia']}" noSelection="['':'-Tipo de Refeição-']" />
                                            <div class="errorField" aria-live="polite"></div>
                                        </div>
                                        <br>
                                        <div class="input-group input-group-sm" id="divObservRefeicao">
                                            <span class="input-group-addon" id="basic-addon3">Obs Refeição</span>
                                            <input type="text" id="observRefeicao" class="form-control glicoInput" name="observRefeicao" value="${registroInfo?.observRefeicao}" />
                                            <div class="errorField" aria-live="polite"></div>
                                        </div>
                                    </div>
                                    <div class="tab-pane fade" id="AtivFisica" role="tabpanel" aria-labelledby="ativfisica-tab">
                                        <h4>ativfisica</h4>
                                        <div class="input-group input-group-sm" id="divGrauAtivFisica">
                                            <span class="input-group-addon" id="basic-addon3">Grau Ativ Física</span>
                                            <g:select class="form-control glicoInput" name="tipoAtivFisica" from="${['Leve','Moderada','Intensa']}" value="${registroInfo?.tipoAtivFisica}" noSelection="['':'-Grau de Atividade-']" id="tipoAtivFisica" />
                                            <div class="errorField" aria-live="polite"></div>
                                        </div>
                                        <br>
                                        <div class="input-group input-group-sm" id="divObservAtivFisica">
                                            <span class="input-group-addon" id="basic-addon3">Obs Ativ Física</span>
                                            <input type="text" class="form-control glicoInput" name="observAtivFisica" value="${registroInfo?.observAtivFisica}" id="observAtivFisica" />
                                            <div class="errorField" aria-live="polite"></div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Fim da Div das Tabs -->
                            </div>
                            <!-- Fim do Container -->
                        </div>
                        <!-- Fim Coluna Lado Direito -->
                        <br>
                        <div class="row">
                            <button type="submit" class="btn btn-primary btn-sm botaoFormLadoDireito">Gravar</button>
                        </div>
                        <!-- row do botão abaixo do form -->
                    </div>
                    <!-- row do form como um todo -->
                </g:form>
                <!-- g form -->
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
        <!-- container -->
    </body>

    </html>
