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
         <g:hasErrors>
            <ul class="errors" role="alert">
                <g:eachError var="error">
                <li  <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
         <div class="row">
            <div class="container">
                <div class="row">
                    <div class="boxMensagem">Ops! Ocorreu um erro.<br> Nossas equipes de suporte vão trabalhar nisso o mais breve possível!</div>
                    <table class="table table-dark">
                        <g:each var="listErros" in="${errosGerais}" status="counter">
                            <g:if test="${counter == 0}">
                                <thead>
                                    <th scope="col">#</th>
                                    <th scope="col">Controller</th>
                                    <th scope="col">Erro</th>
                                    <th scope="col">Exception</th>
                                </thead>
                            </g:if>
                            <tr>
                                <th scope="row">${counter+1}</th>
                                <td>${listErros.controller}</td>
                                <td>${listErros.erroNoCatch}</td>
                                <td>${listErros.erroException}</td>
                            </tr>
                        </g:each>
                    </table>
                </div> <!-- row -->
            </div>
        </div><!-- row -->
    </div>
</body>
</html>