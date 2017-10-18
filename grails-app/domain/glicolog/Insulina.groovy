package glicolog

class Insulina {
    Date dataInsulina
    String tipoInsulina
    Integer dosesInsulina
    static belongsTo = [pessoa:Pessoa]

    static constraints = {
        tipoInsulina inList: ['Aspart','Glargina']
    }
}
