package glicolog

class Glicemia {
    Date dataGlicemia
    String tipoGlicemia
    Integer taxaGlicemia
    static belongsTo = [pessoa: Pessoa]

    static constraints = {
        tipoGlicemia inList: ['Pre','Pos','Controle']
    }
    
    String toString() {
        Date dd1 = this.dataGlicemia
		String data1 = dd1.getDateTimeString()
		"${data1} : ${this.taxaGlicemia}"
    }
}
