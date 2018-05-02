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
                    <li class="nav-item"><a class="nav-link" href="/home/relatorioPessoas">Relatório P</a></li>
                    <li class="nav-item"><a class="nav-link" href="/home/reportRegistros">Relatório R</a></li>
                </ul>
                <g:form class="form-inline mt-2 mt-md-0" controller="home" action="index" method="get">
                    <label for="dataIni" class="label-control">De:</label>
                    <div class="input-group input-group-sm">
                        <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
                        <input name="dataIni" id="dataIni" value="${params.dataIni}" class="dataGlico" />
                        <div class="errorField" aria-live="polite"></div>
                    </div>
                    <label for="dataFim" class="label-control">Até:</label>
                    <div class="input-group input-group-sm">
                        <input name="dataFim" id="dataFim" value="${params.dataFim}" class="dataGlico" />
                        <div class="errorField" aria-live="polite"></div>
                    </div>
                    <span> | </span>
                    <button class="btn btn-outline-sucess btn-sm" type="submit">Vai!</button>
                </g:form>
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
