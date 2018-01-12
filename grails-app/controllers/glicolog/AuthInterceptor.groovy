package glicolog


class AuthInterceptor {
    
    public AuthInterceptor() {
        // intercepta todas os 'requests' exceto 
        // requests para o controler "Usuario"
        matchAll()
            .excludes(controller:"usuario")
    }
    
    boolean before() { 
        // Se o usuário não está autenticado
        // redireciona para /user/login/
        if(!session.usuario) {
            redirect controller: 'usuario', action: 'login'
            return false
        }
        true
    }
    
    boolean after() { true }

    void afterView() {
        // no-op
    }
}
