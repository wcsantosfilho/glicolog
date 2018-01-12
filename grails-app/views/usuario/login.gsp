<html>
<head>
    <meta name="layout" content="main" />
    <title>Login</title>
</head>
    
<body>
    <div class="container">
        <div class="row">
            <g:if test="${flash.message}">
                <div class="alert alert-danger" role="status">${flash.message}</div>
            </g:if>
            <g:form action="authenticate" url="[action:'authenticate',controller:'usuario']" class="container">
                <div class="input-group input-group-sm">
                    <span class="input-group-addon" id="basic-addon3">Login</span>
                    <g:field type="email" id="login" name="login" class="form-control"  value="${usuario?.login}" />
                </div>
                <br>
                <div class="input-group input-group-sm">
                    <span class="input-group-addon" id="basic-addon3">Password</span>
                    <g:field type="password" id="password" name="password" class="form-control"  value="${usuario?.password}" />
                </div>
                <br>
                <button type="submit" class="btn btn-primary btn-sm">Login</button>
            </g:form>
        </div>
    </div>
</body>

</html>