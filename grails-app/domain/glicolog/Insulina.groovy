package glicolog

class Insulina extends Registro {
    String tipoInsulina
    Integer dosesInsulina

    static constraints = {
        tipoInsulina inList: ['Aspart','Glargina']
    }
}
