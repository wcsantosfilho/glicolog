package glicolog
AtivFisicaCommand afc1 = new AtivFisicaCommand(dataAtivFisica: new Date("01/12/2015"),horaAtivFisica: "12:22",tipoAtivFisica: "Leve",observAtivFisica: "Musculacao 123")

afc1.validate()

if (afc1.hasErrors()) {
    afc1.errors.allErrors.each {
        println it
        println "--"
    }
}