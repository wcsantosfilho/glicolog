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
            new Pessoa(nome: "Walter", idade: 46).save()
            new Pessoa(nome: "Alfredo", idade: 33).save()
        
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
