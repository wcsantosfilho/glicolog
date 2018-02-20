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
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-03 08:02:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 78))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-04 08:02:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 120))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-05 07:02:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 99))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-06 06:02:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 280))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-07 08:02:02.00",  tipoGlicemia: "Pre", taxaGlicemia: 114))
                .addToInsulinas(new Insulina(dataRegistro: "2015-05-02 06:02:02.00", tipoInsulina: "Aspart", doseInsulina: 9))
                .addToInsulinas(new Insulina(dataRegistro: "2015-05-03 08:02:02.00", tipoInsulina: "Aspart", doseInsulina: 9))
                .addToInsulinas(new Insulina(dataRegistro: "2015-05-04 07:02:02.00", tipoInsulina: "Aspart", doseInsulina: 9))
                .addToInsulinas(new Insulina(dataRegistro: "2015-05-05 06:02:02.00", tipoInsulina: "Aspart", doseInsulina: 9))
                .addToInsulinas(new Insulina(dataRegistro: "2015-05-06 08:02:02.00", tipoInsulina: "Aspart", doseInsulina: 9))
                .addToInsulinas(new Insulina(dataRegistro: "2015-05-07 06:02:02.00", tipoInsulina: "Aspart", doseInsulina: 9))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-02 08:00:00.00",  tipoGlicemia: "Pos", taxaGlicemia: 118))
                .addToGlicemias(new Glicemia(dataRegistro: "2015-05-03 10:00:00.00",  tipoGlicemia: "Pos", taxaGlicemia: 44))
                .addToRefeicoes(new Refeicao(dataRegistro: "2015-05-02 06:02:02.00", tipoRefeicao: "Cafe", observRefeicao: "Bacon com ovos"))
                .addToRefeicoes(new Refeicao(dataRegistro: "2015-05-03 08:02:02.00", tipoRefeicao: "Cafe", observRefeicao: "Bacon sem ovos"))
                .addToAtivFisicas(new AtivFisica(dataRegistro: "2015-05-02 10:15:00.00", tipoAtivFisica: "Leve", observAtivFisica: "Corridinha"))
                .addToAtivFisicas(new AtivFisica(dataRegistro: "2015-05-03 09:18:00.00", tipoAtivFisica: "Moderada", observAtivFisica: "Musculação"))
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
