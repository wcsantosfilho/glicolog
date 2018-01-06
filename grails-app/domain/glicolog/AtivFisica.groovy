package glicolog
import groovy.json.*

class AtivFisica extends Registro {
    String tipoAtivFisica
    String observAtivFisica

    static constraints = {
        tipoAtivFisica inList: ['Leve','Moderada','Intensa']
    }
}
