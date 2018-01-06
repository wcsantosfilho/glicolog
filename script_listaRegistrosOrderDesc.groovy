package glicolog
import org.hibernate.Hibernate

def lista = Registro.findAll(max:10, sord:'dataRegistro', order:'desc')
Hibernate.initialize lista
lista.each {
println "==> ${it}"
}