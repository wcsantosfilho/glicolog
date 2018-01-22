package glicolog

import grails.testing.gorm.DomainUnitTest
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise
class PessoaSpec extends Specification implements DomainUnitTest<Pessoa> {

    @Shared int id
    
    void "teste de persistencia de Pessoa - mocking"() {
        setup:
            def userWalter = new Usuario(login: "walter@mail.com", password: "aaa", name: "Walter Santos", tipo: "Comum")
            def userAlfredo = new Usuario(login: "alfredo@mail.com", password: "aaa", neme: "Alfredo Santos", tipo: "Comum")

            new Pessoa(nome: "Walter", idade: 46, usuario: userWalter).save()
            new Pessoa(nome: "Alfredo", idade: 33, usuario: userAlfredo).save()
        
        expect:
            Pessoa.count() == 2
    }
    
    void "teste de instância do dominio"() {
        setup:
        id = System.identityHashCode(domain)

        expect:
        domain != null
        domain.hashCode() == id

        when:
        domain.nome = 'Walter'

        then:
        domain.nome == 'Walter'
    }
}
