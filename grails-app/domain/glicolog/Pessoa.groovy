package glicolog

class Pessoa {
    
    String nome
    Integer idade
    static hasMany = [glicemias: Glicemia]
    
    static constraints = {
    }
    
    String toString() {
        nome
    }
}
