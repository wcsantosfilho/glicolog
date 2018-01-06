package glicolog

class Insulina extends Registro {
    String tipoInsulina
    Integer doseInsulina

    static constraints = {
        tipoInsulina inList: ['Aspart','Glargina']
    }
}
