package glicolog

class Glicemia extends Registro {
    String tipoGlicemia
    Integer taxaGlicemia

    static constraints = {
        tipoGlicemia inList: ['Pre','Pos','Controle']
    }
}
