package glicolog

class AtivFisica {
    Date dataAtivFisica
    String tipoAtivFisica
    String observAtivFisica
    static belongsTo = [pessoa:Pessoa]

    static constraints = {
        tipoAtivFisica inList: ['Leve','Moderada','Intensa']
    }
    
    String toString() {
        def dt1 = dataAtivFisica.format("dd/MM/yyyy HH:mm:ss")
        "${this.pessoa} : ${dt1}"
    }
}
