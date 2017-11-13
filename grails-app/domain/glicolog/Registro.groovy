package glicolog

class Registro {
    Date dataRegistro
    static belongsTo = [pessoa: Pessoa]
}