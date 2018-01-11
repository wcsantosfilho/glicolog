package glicolog
import glicolog.*

class BootStrap {

    def init = { servletContext ->
        
		System.out.println("Inicio do Boostrap")    
        def admin = User.findByLogin('admin@glicolog.com.br')
        if (!admin) {
            new User(login:"admin@glicolog.com.br", password:"admin", name: "Administrador")
            .save(failOnError: true)
        
            new User(login:"arnaldo.mello@gmail.com", password:"aaa", name: "Arnaldo de Sá e Mello")
            .save(failOnError: false)
        }
        
        def countPessoas = Pessoa.count()
        if (countPessoas == 0) {
            new Pessoa(nome: "Arnaldo de Sá e Mello", idade:22)
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 06:02:02.00" , tipoGlicemia: "Pre", taxaGlicemia: 80))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-05 21:02:02.00" ,  tipoGlicemia: "Pos", taxaGlicemia: 114))
                .addToAtivFisicas(new AtivFisica(dataRegistro: "2015-05-02 12:00:00.00", tipoAtivFisica: "Leve", observAtivFisica:"Corridinha leve"))
                .addToAtivFisicas(new AtivFisica(dataRegistro: "2015-05-05 12:00:00.00", tipoAtivFisica: "Moderada", observAtivFisica:"Musculação"))
                .save(failOnError: true)

            new Pessoa(nome: "Bernardino Boehringer", idade:25)
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 06:02:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 80))
                .save(failOnError: true)

            new Pessoa(nome: "Carlos Carvalhares", idade: 44)
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 06:02:02.00",  tipoGlicemia: "Controle", taxaGlicemia: 80))
                .addToInsulinas(new Insulina(dataRegistro: "2015-05-02 06:10:02.00",  tipoInsulina: "Aspart", doseInsulina: 8))
                .save(failOnError: true)

            new Pessoa(nome: "Décio Domingues", idade:12)
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 06:02:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 80))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 12:02:02.00",  tipoGlicemia: "Pos", taxaGlicemia: 120))
                .save(failOnError: true)

            new Pessoa(nome: "Ferdinando Gonçalves", idade:91)
                .save(failOnError: true)

            new Pessoa(nome: "Gabriel Lima", idade:55)
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 06:02:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 80))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 12:02:02.00",  tipoGlicemia: "Pos", taxaGlicemia: 120))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 18:02:02.00",  tipoGlicemia: "Controle", taxaGlicemia: 155))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 21:22:02.00",  tipoGlicemia: "Pos", taxaGlicemia: 202))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-03 05:32:02.00",  tipoGlicemia: "Controle", taxaGlicemia: 180))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-03 10:40:02.00",  tipoGlicemia: "Pos", taxaGlicemia: 150))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-03 12:17:02.00",  tipoGlicemia: "Controle", taxaGlicemia: 155))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-03 18:04:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 92))
                .save(failOnError: true)

            new Pessoa(nome: "Hernesto Alberto", idade:10)
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 06:02:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 80))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 12:02:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 120))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 18:02:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 155))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 21:22:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 202))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-03 05:32:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 180))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-03 10:40:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 150))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-03 12:17:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 155))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-03 18:04:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 92))
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
