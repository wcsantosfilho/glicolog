package glicolog

class Glicemia {
    Date dataGlicemia
    Integer taxaGlicemia
    static belongsTo = [pessoa: Pessoa]

    static constraints = {
    }
    
    String toString() {
        Date dd1 = this.dataGlicemia
		String data1 = dd1.getDateTimeString()
		"${data1} : ${this.taxaGlicemia}"
    }
}
