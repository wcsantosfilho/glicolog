package glicolog

class Refeicao extends Registro {
    String tipoRefeicao
    String observRefeicao

    static constraints = {
        tipoRefeicao inList: ['Cafe','Almoco','Lanche','Jantar','Ceia']
    }
}
