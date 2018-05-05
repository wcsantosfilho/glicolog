<!DOCTYPE html>
    <html lang="pt-br">
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <title>
            <g:layoutTitle default="Glicolog"/>
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <!-- "meta" acima deve ser os primeiros -->

        <!-- Bootstrap CSS -->
        <asset:stylesheet src="application.css"/>
        <asset:stylesheet src="datepicker/classic.css" id="theme_base"/>
        <asset:stylesheet src="datepicker/classic.date.css" id="theme_date"/>
        <asset:stylesheet src="datepicker/classic.time.css" id="theme_time"/>

        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" /> 
        
        <g:layoutHead/>
    </head>

    <body>
        <nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
            <!-- Brand and toggle get grouped for better mobile display -->
            <button type="button" class="navbar-toggler navbar-toggler-right" data-toggle="collapse" data-target="#Colapsable">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- Collect the nav links, forms, and other content for toggling -->

            <a class="navbar-brand" href="/home/">
                    Glicolog
            </a>
            <div class="collapse navbar-collapse" id="Colapsable">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active"><a class="nav-link" href="/home/">Home <span class="sr-only">(current)</span></a></li>
                    <li class="nav-item"><a class="nav-link" href="/home/sendmail">Pessoa</a></li>
                    <li class="nav-item"><a class="nav-link" href="/home/relatorioPessoas">Relatório Pessoas</a></li>
                </ul>
                <div class="btn-group">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Relatórios:</button>
                    <div class="dropdown-menu dropdown-menu-left" aria-labelledby="dropdownMenuButton">
                        <button class="dropdown-item" type="button" id="button3Meses">Ult. 3 meses</button>
                        <button class="dropdown-item" type="button" id="buttonUltMes">Ult. mês</button>
                        <div class="dropdown-divider"></div>
                        <h6 class="dropdown-header">Personalize:</h6>
                        <g:form class="px-4 py-0" url="[action:'reportRegistros',controller:'home']" method="get" id="formRelatorios">
                            <div class="form-group py-0 mt-0">
                                <label for="dataIni">Data Inicial</label>
                                <input name="dataIni" id="dataIni" class="dataGlico"/>
                            </div>
                            <div class="form-group py-0 mt-0">
                                <label for="dataFim">Data Final</label>
                                <input name="dataFim" id="dataFim" class="dataGlico"/>
                            </div>
                            <p id="erroFormData"></p>
                            <button type="submit" class="btn btn-primary" id="botaoFormData">Vai!</button>
                        </g:form>
                    </div>
                </div>
                <ul class="navbar-nav d-flex flex-row-reverse">
                    <li class="nav-item nav-link">
                        <sec:ifLoggedIn>Olá <sec:username/>. <g:link controller='logoff'>Sair</g:link>
                        </sec:ifLoggedIn>
                        <sec:ifNotLoggedIn>
                        <g:link controller='login' action='auth'>Login</g:link></sec:ifNotLoggedIn>
                    </li>
                </ul>
            </div> <!-- Collapse navbar -->
        </nav> <!-- navbar -->

        <g:layoutBody/>

        <div class="footer" role="contentinfo"></div>

        <div id="spinner" class="spinner" style="display:none;">
            <g:message code="spinner.alt" default="Loading&hellip;"/>
        </div>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <asset:javascript src="application.js"/>


    </body>
</html>
