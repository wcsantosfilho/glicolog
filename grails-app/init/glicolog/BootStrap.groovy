package glicolog
import glicolog.*
import static java.util.Calendar.*


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

            def userWalter = new Usuario(login:"walter.santosf@gmail.com", password:"www", name: "Walter Santos Filho", tipo: "Comum")
            def pessWalter = new Pessoa(nome: "Walter Santos Filho", idade:25, usuario: userWalter)
                .save(failOnError: true)
            
            def anoReg = 2017
            def mesReg = 05
            def diaReg = 02
            def ctDias = 55
            def dataBase = new Date().parse("dd.mm.yyyy HH:mm:ss", "${diaReg}.${mesReg}.${anoReg} 06:00:00")
            1.upto(ctDias) {
                dataBase = dataBase.plus(1)
                dataBase = dataBase.parse("dd.mm.yyyy HH:mm:ss", "${dataBase[DATE]}.${dataBase[MONTH]+1}.${dataBase[YEAR]} 06:00:00")
                def dataBase2 = dataBase.clone()

                new Glicemia(pessoa: pessWalter, dataRegistro: dataBase2,  tipoGlicemia: "Pre", taxaGlicemia: 80)
                    .save(failOnError: true)
                new Insulina(pessoa: pessWalter, dataRegistro: dataBase2, tipoInsulina: "Aspart", doseInsulina: 9)
                    .save(failOnError: true)
                new Refeicao(pessoa: pessWalter, dataRegistro: dataBase2, tipoRefeicao: "Cafe", observRefeicao: "Bacon com ovos")
                    .save(failOnError: true)
                new Insulina(pessoa: pessWalter, dataRegistro: dataBase2, tipoInsulina: "Glargina", doseInsulina: 13)
                    .save(failOnError: true)
                
                dataBase2 = dataBase2.parse("dd.mm.yyyy HH:mm:ss", "${dataBase[DATE]}.${dataBase[MONTH]+1}.${dataBase[YEAR]} 08:00:00")
                new Glicemia(pessoa: pessWalter, dataRegistro: dataBase2,  tipoGlicemia: "Pos", taxaGlicemia: 78)
                    .save(failOnError: true)

                dataBase2 = dataBase2.parse("dd.mm.yyyy HH:mm:ss", "${dataBase[DATE]}.${dataBase[MONTH]+1}.${dataBase[YEAR]} 10:30:00")
                new AtivFisica(pessoa: pessWalter, dataRegistro: dataBase2, tipoAtivFisica: "Leve", observAtivFisica: "Corridinha")
                    .save(failOnError: true)

                dataBase2 = dataBase2.parse("dd.mm.yyyy HH:mm:ss", "${dataBase[DATE]}.${dataBase[MONTH]+1}.${dataBase[YEAR]} 12:00:00")

                new Glicemia(pessoa: pessWalter, dataRegistro: dataBase2,  tipoGlicemia: "Pre", taxaGlicemia: 120)
                    .save(failOnError: true)
                new Insulina(pessoa: pessWalter, dataRegistro: dataBase2, tipoInsulina: "Glargina", doseInsulina: 12)
                    .save(failOnError: true)
                new Refeicao(pessoa: pessWalter, dataRegistro: dataBase2, tipoRefeicao: "Almoco", observRefeicao: "Salada, arroz, feijão e carne")
                    .save(failOnError: true)

                dataBase2 = dataBase2.parse("dd.mm.yyyy HH:mm:ss", "${dataBase[DATE]}.${dataBase[MONTH]+1}.${dataBase[YEAR]} 19:45:00")
                new Glicemia(pessoa: pessWalter, dataRegistro: dataBase2,  tipoGlicemia: "Pre", taxaGlicemia: 155)
                    .save(failOnError: true)
                new Insulina(pessoa: pessWalter, dataRegistro: dataBase2, tipoInsulina: "Glargina", doseInsulina: 13)
                    .save(failOnError: true)
                new Refeicao(pessoa: pessWalter, dataRegistro: dataBase2, tipoRefeicao: "Jantar", observRefeicao: "Salada, empadão e bolo de carne")
                    .save(failOnError: true)

                dataBase2 = dataBase2.parse("dd.mm.yyyy HH:mm:ss", "${dataBase[DATE]}.${dataBase[MONTH]+1}.${dataBase[YEAR]} 22:15:00")
                new Glicemia(pessoa: pessWalter, dataRegistro: dataBase2,  tipoGlicemia: "Controle", taxaGlicemia: 132)
                    .save(failOnError: true)
            }
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
