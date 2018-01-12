package glicolog

class Pessoa {
    
    String nome
    Integer idade
    Usuario usuario
    
    static hasMany = [glicemias: Glicemia, insulinas: Insulina, refeicoes: Refeicao, ativFisicas: AtivFisica]
    
    static constraints = {
        usuario nullable: false
    }
    
    String toString() {
        nome
    }
}
