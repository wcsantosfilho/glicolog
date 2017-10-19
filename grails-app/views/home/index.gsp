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
         </div>
         <div class="row">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-xs-6"> <!-- lado esquerdo do form -->
                        <div class="row">
                            <div class="col-md-6 col-xs-3 colunaDoForm"> <!-- 1a subdivisão do lado esquerdo do form -->

                                <label class="label-control">Data e Hora:</label>
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                                    <input type="text" class="form-control input-xs" id="formData" name="formData" placeholder="dd/mm/yyyy">
                                </div>
                                <div class="input-group">
                                    <span class="input-group-addon"><span class="fa fa-clock-o"></span></span>
                                    <input type="text" id="formHora" name="formHora" class="form-control input-xs" placeholder="hh:mm">
                                </div>
                            </div> <!-- /1a subdivisão do lado esquerdo do form -->
                            <div class="col-md-6 col-xs-3 colunaDoForm"> <!-- 2a subdivisão do lado esquerdo do form -->
                                <label class="label-control">Tipo Registro:</label>
                                <br>
                                <div class="radio">
                                    <label>
                                        <input type="radio" name="formOpcAtiv" value="Glicemia"> Glicemia
                                    </label>
                                    <label>
                                        <input type="radio" name="formOpcAtiv" value="Insulina"> Insulina
                                    </label>
                                    <br>
                                    <label>
                                        <input type="radio" name="formOpcAtiv" value="Refeicao"> Refeição
                                    </label>
                                    <label>
                                        <input type="radio" name="formOpcAtiv" value="AtivFisica"> Atividade Física
                                    </label>
                                </div>
                            </div> <!-- /2a subdivisão do lado esquerdo do form -->
                        </div> <!-- row -->
                    </div> <!-- lado esquerdo do form -->
                    <div class="col-md-6 col-xs-6"> <!-- lado direito do form -->
                        <!-- ============================================================ -->
                        <!--                                                              -->
                        <!-- ============================================================ -->
                        <g:form name="formGlicemia" url="[action:'save',controller:'glicemia']" class="container"> <!-- FORM DA GLICEMIA -->
                            <div class="grupoDeCampos" id="grupoGlicemia">
                                <g:textField class="formDataLadoDireito" name="dataAtivFisica" hidden="true" />
                                <g:textField type="text" class="formHoraLadoDireito" name="horaAtivFisica" hidden="true" />
                                <label for="formTipoGlicemia">Tipo Glicemia</label>
                                <select class="form-control" id="formTipoGlicemia" name="formTipoGlicemia">
                                    <option>Pré prandial</option>
                                    <option>Pós prandial</option>
                                    <option>Controle</option>
                                </select>
                                <div class="form-group"> 
                                    <label for="formGlicemia">Glicemia</label>
                                    <input type="text" id="formGlicemia" class="form-control" name="formGlicemia">
                                </div> 
                                <button type="submit" class="btn btn-warning botaoFormLadoDireito">Gravar</button>
                            </div>
                        </g:form>
                        <!-- ============================================================ -->
                        <!--                                                              -->
                        <!-- ============================================================ -->
                        <g:form name="formInsulina" url="[action:'save',controller:'insulina']" class="container"> <!-- FORM DA INSULINA -->
                            <div class="grupoDeCampos" id="grupoInsulina">
                                <g:textField class="formDataLadoDireito" name="dataAtivFisica" hidden="true" />
                                <g:textField type="text" class="formHoraLadoDireito" name="horaAtivFisica" hidden="true" />
                                <label for="formTipoInsulina">Tipo Insulina</label>
                                <select class="form-control" id="formTipoInsulina" name="formTipoInsulina">
                                    <option>Rápida - Aspart</option>
                                    <option>Lenta - Glargina</option>
                                </select>
                                <div class="form-group">
                                    <label for="formInsulina">Insulina (doses)</label>
                                    <input type="text" id="formInsulina" class="form-control" name="formInsulina">
                                </div>
                                <button type="submit" class="btn btn-warning botaoFormLadoDireito">Gravar</button>
                            </div>
                        </g:form>
                        <!-- ============================================================ -->
                        <!--                                                              -->
                        <!-- ============================================================ -->
                        <g:form name="formRefeicao" url="[action:'save',controller:'refeicao']" class="container"> <!-- FORM DA REFEIÇÃO -->
                            <div class="grupoDeCampos" id="grupoRefeicao">
                                <g:textField class="formDataLadoDireito" name="dataAtivFisica" hidden="true" />
                                <g:textField type="text" class="formHoraLadoDireito" name="horaAtivFisica" hidden="true" />
                                <div class="form-group">
                                    <label for="formTipoRefeicao">Tipo Refeição</label>
                                    <select class="form-control" id="formTipoRefeicao" name="formRefeicao">
                                        <option>Café da manhã</option>
                                        <option>Almoço</option>
                                        <option>Lanche da tarde</option>
                                        <option>Jantar</option>
                                        <option>Ceia</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="formObsRefeicao">Observação Refeição (opcional)</label>
                                    <input type="text" id="formObsRefeicao" class="form-control" name="formObsRefeicao">
                                </div>
                                <button type="submit" class="btn btn-warning botaoFormLadoDireito">Gravar</button>
                            </div>
                        </g:form>
                        <!-- ============================================================ -->
                        <!--                                                              -->
                        <!-- ============================================================ -->
                        <g:form name="formAtivFisica" url="[action:'save',controller:'ativFisica']" class="container"> <!-- FORM DA ATIVIDADE FISICA -->
                            <div class="grupoDeCampos" id="grupoAtivFisica">
                                <g:textField class="formDataLadoDireito" name="dataAtivFisica" hidden="true" />
                                <g:textField type="text" class="formHoraLadoDireito" name="horaAtivFisica" hidden="true" />
                                <div class="form-group">
                                    <label for="tipoAtivFisica">Atividade Física</label>
                                    <g:select class="form-control" name="tipoAtivFisica" from="${['Leve','Moderada','Intensa']}" value="${grauAtivFisica}"
          noSelection="['':'-Grau de Atividade-']" />
                                </div>
                                <div class="form-group">
                                    <label for="observAtivFisica">Observação Atividade Física (opcional)</label>
                                    <g:textField type="text" id="observAtivFisica" class="form-control" name="observAtivFisica" />
                                </div>
                                <button type="submit"  class="btn btn-warning botaoFormLadoDireito">Gravar</button>
                            </div>
                        </g:form> <!-- / FORM DA ATIVIDADE FISICA -->
                        <!-- ============================================================ -->
                        <!--                                                              -->
                        <!-- ============================================================ -->
                    </div>    <!-- lado direito do form -->
                </div> <!-- row -->
            </div>
        </div><!-- row -->
    </div>
</body>
</html>