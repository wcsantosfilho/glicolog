package glicolog
import glicolog.*

class BootStrap {

    def init = { servletContext ->
        
		System.out.println("Inicio do Boostrap")    
        def admin = Usuario.findByLogin('admin@glicolog.com.br')
        if (!admin) {
            new Usuario(login:"admin@glicolog.com.br", password:"admin", name: "Administrador", tipo: "Admin")
            .save(failOnError: true)
        }
        
        def countPessoas = Pessoa.count()
        if (countPessoas == 0) {
            def userLucas = new Usuario(login:"lucassantos202@gmail.com", password:"lucas", name: "Lucas Santos", tipo: "Comum")
            new Pessoa(nome: "Lucas Santos", idade:18, usuario: userLucas)
                .save(failOnError: true)

            def userBernardino = new Usuario(login:"bernardino.boehringer@gmail.com", password:"aaa", name: "Bernardino Boehringer", tipo: "Comum")
            new Pessoa(nome: "Bernardino Boehringer", idade:25, usuario: userBernardino)
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 06:02:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 80))
                .save(failOnError: true)
        }
    		
    	def countPessoasComGlicemia = Pessoa.findAll {
    		glicemias.size() > 0
		}
    	def countPessoasSemGlicemia = Pessoa.findAll {
    		glicemias.size() == 0
		}
        
		System.out.println("Pessoas com glicemias"+ countPessoasComGlicemia)
		System.out.println("Pessoas sem glicemias"+ countPessoasSemGlicemia)
    		
		System.out.println("Fim do Boostrap")    	
    }
    
    def destroy = {
    }
}
