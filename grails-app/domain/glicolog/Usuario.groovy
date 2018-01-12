package glicolog

class Usuario {

    static constraints = {
        login(unique:true)
        password(password:true)
        name()
        tipo(inList: ["Admin", "Comum"])
    }
    
    String login
    String password
    String name
    String tipo

    String toString(){
    name
    }
}
