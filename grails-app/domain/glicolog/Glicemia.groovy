package glicolog

class Glicemia extends Registro {
    String tipoGlicemia
    Integer taxaGlicemia

    static constraints = {
        tipoGlicemia inList: ['Pre','Pos','Controle']
    }
    
    String toString() {
        Date dd1 = this.dataRegistro
		String data1 = dd1.getDateTimeString()
		"${data1} : ${this.taxaGlicemia}"
    }
}
