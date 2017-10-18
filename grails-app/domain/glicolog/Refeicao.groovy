package glicolog

class Refeicao {
    Date dataRefeicao
    String tipoRefeicao
    String observRefeicao
    static belongsTo = [pessoa:Pessoa]

    static constraints = {
        tipoRefeicao inList: ['Cafe','Almoco','Lanche','Jantar','Ceia']
    }
}
