package glicolog

class Pessoa {
    
    String nome
    Integer idade
    
    static hasMany = [glicemias: Glicemia, insulinas: Insulina, refeicoes: Refeicao, ativFisicas: AtivFisica]
    
    static constraints = {
    }
    
    String toString() {
        nome
    }
}
