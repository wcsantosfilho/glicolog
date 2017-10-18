package glicolog
import glicolog.*

class BootStrap {

    def init = { servletContext ->
        
		System.out.println("Inicio do Boostrap")    	
     	new Pessoa(nome: "Arnaldo de Sá e Mello", idade:22)
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 06:02:02.00" , taxaGlicemia: 80))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-05 21:02:02.00" , taxaGlicemia: 114))
    		.save(flush: true)
    		
    	new Pessoa(nome: "Bernardino Boehringer", idade:25)
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 06:02:02.00", taxaGlicemia: 80))
    		.save(flush: true)
    		
    	new Pessoa(nome: "Carlos Carvalhares", idade: 44)
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 06:02:02.00", taxaGlicemia: 80))
    		.save(flush: true)
    		
    	new Pessoa(nome: "Décio Domingues", idade:12)
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 06:02:02.00", taxaGlicemia: 80))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 12:02:02.00", taxaGlicemia: 120))
    		.save(flush: true)
        
        new Pessoa(nome: "Ferdinando Gonçalves", idade:91)
            .save(flush: true)
        
        new Pessoa(nome: "Gabriel Lima", idade:55)
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 06:02:02.00", taxaGlicemia: 80))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 12:02:02.00", taxaGlicemia: 120))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 18:02:02.00", taxaGlicemia: 155))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 21:22:02.00", taxaGlicemia: 202))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-03 05:32:02.00", taxaGlicemia: 180))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-03 10:40:02.00", taxaGlicemia: 150))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-03 12:17:02.00", taxaGlicemia: 155))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-03 18:04:02.00", taxaGlicemia: 92))
    		.save(flush: true)
    		
        new Pessoa(nome: "Hernesto Alberto", idade:10)
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 06:02:02.00", taxaGlicemia: 80))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 12:02:02.00", taxaGlicemia: 120))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 18:02:02.00", taxaGlicemia: 155))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-02 21:22:02.00", taxaGlicemia: 202))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-03 05:32:02.00", taxaGlicemia: 180))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-03 10:40:02.00", taxaGlicemia: 150))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-03 12:17:02.00", taxaGlicemia: 155))
    		.addToGlicemias(new Glicemia(dataGlicemia: "2015-05-03 18:04:02.00", taxaGlicemia: 92))
    		.save(flush: true)
    		
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



