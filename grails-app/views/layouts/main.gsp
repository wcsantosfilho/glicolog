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
        <asset:stylesheet href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>


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
                    <li class="nav-item"><a class="nav-link" href="/pessoa/">Pessoa</a></li>
                </ul>
                <form class="form-inline mt-2 mt-md-0">
                    <input class="form-control mr-sm-2" type="text" placeholder="Busca">
                    <button class="btn btn-outline-sucess my-2 my-sm-0" type="submit">Busca</button>
                </form>
                <ul class="navbar-nav d-flex flex-row-reverse">
                    <li class="nav-item nav-link"><g:loginControl /></li>
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
