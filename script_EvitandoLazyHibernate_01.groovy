package glicolog
import org.hibernate.Hibernate

//def resultado = Insulina.findAll()
//println resultado
//def resultado2 = Glicemia.findAll { tipoGlicemia == "Pos" }
//println resultado2

//def res3 = Registro.collect { tipoGlicemia == "Pos" }
//println res3

def lista = Registro.findAll()
Hibernate.initialize lista
println lista