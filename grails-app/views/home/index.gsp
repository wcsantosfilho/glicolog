<html>
<head>
    <meta name="layout" content="main"/>
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
                            data-field-id="${error.field}"
                        </g:if>
                        <li><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
            </g:hasErrors>
         </div>
         <div class="row">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-xs-6"> <!-- lado esquerdo do form -->
                        <div class="row">
                            <div class="col-md-6 col-xs-3 colunaDoForm"> <!-- 1a subdivisão do lado esquerdo do form -->

                                <label class="label-control">Data e Hora:</label>
                                <div class="input-group input-group-md">
                                    <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                                    <g:field type="text" class="form-control" id="dataRegistro" name="dataRegistro" placeholder="dd/mm/yyyy" value="${registroInfo?.dataRegistro}" />
                                </div>
                                <br>    
                                <div class="input-group input-group-md">
                                    <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                    <g:field type="text" class="form-control" id="horaRegistro" name="horaRegistro"  placeholder="hh:mm" value="${registroInfo?.horaRegistro}" />
                                </div>
                            </div> <!-- /1a subdivisão do lado esquerdo do form -->
                            <div class="col-md-6 col-xs-3 colunaDoForm"> <!-- 2a subdivisão do lado esquerdo do form -->
                                <label class="label-control">Tipo Registro:</label>
                                <br>
                                <g:radioGroup name="tipoRegistro" id="tipoRegistro"
                                              values="['Glicemia', 'Insulina', 'Refeicao', 'AtivFisica']"
                                              labels="['Glicemia', 'Insulina', 'Refeição', 'Atividade Fisica']" 
                                              value="${registroInfo?.tipoRegistro}" >
                                    <li class="listRadio">${it.radio} <g:message code="${it.label}" /></li>
                                </g:radioGroup>
                            </div> <!-- /2a subdivisão do lado esquerdo do form -->
                        </div> <!-- row -->
                    </div> <!-- lado esquerdo do form -->
                    <div class="col-md-6 col-xs-6"> <!-- lado direito do form -->
                        <!-- ============================================================ -->
                        <!--     FORM DA GLICEMIA                                         -->
                        <!-- ============================================================ -->
                        <g:form name="formGlicemia"  resource="${this.registroInfo}" url="[action:'saveForm',controller:'home']" class="container"> <!-- FORM DA GLICEMIA -->
                            <div class="grupoDeCampos" id="grupoGlicemia">
                                <g:textField type="text" class="formDataLadoDireito" name="dataRegistro" hidden="true" value="${registroInfo?.dataRegistro}"/>
                                <g:textField type="text" class="formHoraLadoDireito" name="horaRegistro" hidden="true" value ="${registroInfo?.horaRegistro}"/>
                                <g:textField type="text" class="formTipoLadoDireito" name="tipoRegistro" hidden="true" value ="${registroInfo?.tipoRegistro}"/>
                                <div class="input-group input-group-sm">
                                    <span class="input-group-addon" id="basic-addon3">Tipo Glicemia</span>
                                    <select class="form-control" id="tipoGlicemia" name="tipoGlicemia" value="${registroInfo?.tipoGlicemia}">
                                        <option value="Pre">Pré prandial</option>
                                        <option value="Pos">Pós prandial</option>
                                        <option value="Controle">Controle</option>
                                    </select>
                                </div>
                                <br>
                                <div class="input-group input-group-sm"> 
                                    <span class="input-group-addon" id="basic-addon3">Taxa Glicemia</span>
                                    <input type="text" id="taxaGlicemia" class="form-control" name="taxaGlicemia" value="${registroInfo?.taxaGlicemia}">
                                </div> 
                                <br>
                                <button type="submit" class="btn btn-primary btn-sm botaoFormLadoDireito">Gravar</button>
                            </div>
                        </g:form>
                        <!-- ============================================================ -->
                        <!--     FORM DA INSULINA                                         -->
                        <!-- ============================================================ -->
                        <g:form name="formInsulina"  resource="${this.registroInfo}" url="[action:'saveForm',controller:'home']" class="container"> <!-- FORM DA INSULINA -->
                            <div class="grupoDeCampos" id="grupoInsulina">
                                <g:textField type="text" class="formDataLadoDireito" name="dataRegistro" hidden="true" value="${registroInfo?.dataRegistro}"/>
                                <g:textField type="text" class="formHoraLadoDireito" name="horaRegistro" hidden="true" value ="${registroInfo?.horaRegistro}"/>
                                <g:textField type="text" class="formTipoLadoDireito" name="tipoRegistro" hidden="true" value ="${registroInfo?.tipoRegistro}"/>
                                <label for="tipoInsulina">Tipo Insulina</label>
                                <select class="form-control" id="tipoInsulina" name="tipoInsulina" value="{registroInfo?.tipoInsulina}">
                                    <option value="Aspart">Rápida - Aspart</option>
                                    <option value="Glargina">Lenta - Glargina</option>
                                </select>
                                <div class="form-group">
                                    <label for="doseInsulina">Insulina (doses)</label>
                                    <input type="text" id="doseInsulina" class="form-control" name="doseInsulina" value="${registroInfo?.doseInsulina}">
                                </div>
                                <button type="submit" class="btn btn-warning botaoFormLadoDireito">Gravar</button>
                            </div>
                        </g:form>
                        <!-- ============================================================ -->
                        <!--       FORM DA REFEICAO                                      -->
                        <!-- ============================================================ -->
                        <g:form name="formRefeicao"  resource="${this.registroInfo}" url="[action:'saveForm',controller:'home']" class="container"> <!-- FORM DA REFEIÇÃO -->
                            <div class="grupoDeCampos" id="grupoRefeicao">
                                <g:textField type="text" class="formDataLadoDireito" name="dataRegistro" hidden="true" value="${registroInfo?.dataRegistro}"/>
                                <g:textField type="text" class="formHoraLadoDireito" name="horaRegistro" hidden="true" value ="${registroInfo?.horaRegistro}"/>
                                <g:textField type="text" class="formTipoLadoDireito" name="tipoRegistro" hidden="true" value ="${registroInfo?.tipoRegistro}"/>
                                <div class="form-group">
                                    <label for="tipoRefeicao">Tipo Refeição</label>
                                    <select class="form-control" id="tipoRefeicao" name="tipoRefeicao" value="${registroInfo?.tipoRefeicao}">
                                        <option value="Cafe">Café da manhã</option>
                                        <option value="Almoco">Almoço</option>
                                        <option value="Lanche">Lanche da tarde</option>
                                        <option value="Jantar">Jantar</option>
                                        <option value="Ceia">Ceia</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="observRefeicao">Observação Refeição (opcional)</label>
                                    <input type="text" id="observRefeicao" class="form-control" name="observRefeicao" value="${registroInfo?.observRefeicao}">
                                </div>
                                <button type="submit" class="btn btn-warning botaoFormLadoDireito">Gravar</button>
                            </div>
                        </g:form>
                        <!-- ============================================================ -->
                        <!--        FORM DA ATIVIDADE FISICA                          -->
                        <!-- ============================================================ -->
                        <div class="grupoDeCampos" id="grupoAtivFisica">
                            <g:form name="formAtivFisica" resource="${this.registroInfo}" url="[action:'saveForm',controller:'home']" class="container"> <!-- FORM DA ATIVIDADE FISICA -->
                                <g:textField type="text" class="formDataLadoDireito" name="dataRegistro" hidden="true" value="${registroInfo?.dataRegistro}"/>
                                <g:textField type="text" class="formHoraLadoDireito" name="horaRegistro" hidden="true" value ="${registroInfo?.horaRegistro}"/>
                                <g:textField type="text" class="formTipoLadoDireito" name="tipoRegistro" hidden="true" value ="${registroInfo?.tipoRegistro}"/>
                                <div class="form-group">
                                    <label for="tipoAtivFisica">Atividade Física</label>
                                    <g:select class="form-control" name="tipoAtivFisica" from="${['Leve','Moderada','Intensa']}" value="${registroInfo?.tipoAtivFisica}"
          noSelection="['':'-Grau de Atividade-']" />
                                </div>
                                <div class="form-group fieldcontain required">
                                    <label for="observAtivFisica">Observação Atividade Física (opcional)</label>
                                    <g:textField type="text" id="observAtivFisica" class="form-control" name="observAtivFisica" value="${registroInfo?.observAtivFisica}"/>
                                </div>
                                <button type="submit"  class="btn btn-warning botaoFormLadoDireito">Gravar</button>
                            </g:form> <!-- / FORM DA ATIVIDADE FISICA -->
                        </div>
                    </div>    <!-- lado direito do form -->
                </div> <!-- row -->
            </div>
        </div><!-- row -->
        <div class="row">
            <div id="list-registros" role="main">
                <h1><g:message code="default.list.label" args="[entityName]" /></h1>
                <h2>${registroTotal}</h2>
                <g:each in="${registroList}">
                    <p>${it}</p>
                </g:each>
                <div class="pagination">
                    <g:paginate total="${registroTotal ?: 0}" />
                </div>
            </div>
        </div>
    </div> <!-- row -->
</body>
</html>