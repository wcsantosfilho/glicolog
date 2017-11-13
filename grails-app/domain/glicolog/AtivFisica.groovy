package glicolog

class AtivFisica extends Registro {
    String tipoAtivFisica
    String observAtivFisica

    static constraints = {
        tipoAtivFisica inList: ['Leve','Moderada','Intensa']
    }
    
    String toString() {
        def dt1 = dataRegistro.format("dd/MM/yyyy HH:mm:ss")
        "${this.pessoa} : ${dt1}"
    }
}
