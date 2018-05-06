package glicolog
import glicolog.*
import static java.util.Calendar.*
import grails.util.Environment


class BootStrap {

    def init = { servletContext ->
        
		System.out.println("Inicio do Boostrap")
        
        /* ------------------------------------------------- *
         * Criação de usuário no Spring Security plugin      *
         * ------------------------------------------------- */
        
        def userRole = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN').save(failOnError: true)
        
        def adminUser = User.findByUsername('Admin') ?: new User(username: 'Admin', password: 'Admin').save(failOnError: true)
        
        def secUandR = SecUserSecRole.findAllBySecUserAndSecRole(adminUser, adminRole) ?: SecUserSecRole.create (adminUser, adminRole)
        
        SecUserSecRole.withSession {
            it.flush()
            it.clear()
        }
        
        /* ------------------------------------------------- */
        def lucasPessoa = Pessoa.findByNome('Lucas Santos') ?: new Pessoa(nome: "Lucas Santos", idade:18)
            .save(failOnError: true)

        def lucasUser = User.findByUsername('lucassantos') ?: new User(username: 'lucassantos', password: 'Lucas', pessoa: lucasPessoa).save(failOnError: true)
        def secUandR2 = SecUserSecRole.findAllBySecUserAndSecRole(lucasUser, userRole) ?: SecUserSecRole.create (lucasUser, userRole)
        SecUserSecRole.withSession {
            it.flush()
            it.clear()
        }

        /* ------------------------------------------------- */
        def pessWalter = Pessoa.findByNome('Walter Santos Filho') ?: new Pessoa(nome: "Walter Santos Filho", idade:47)
            .save(failOnError: true)

        def walterUser = User.findByUsername('wfilho') ?: new User(username: 'wfilho', password: 'www', pessoa: pessWalter).save(failOnError: true)
        def secUandR3 = SecUserSecRole.findAllBySecUserAndSecRole(walterUser, userRole) ?: SecUserSecRole.create (walterUser, userRole)
        SecUserSecRole.withSession {
            it.flush()
            it.clear()
        }
        if (Environment.current == Environment.DEVELOPMENT) {
            
            def dataAtual = new Date()
            def anoReg = dataAtual[Calendar.YEAR]
            def mesReg = dataAtual[Calendar.MONTH] + 1
            def diaReg = dataAtual[Calendar.DATE]-55
            def ctDias = 55
            def dataBase = new Date().parse("dd.MM.yyyy HH:mm:ss", "${diaReg}.${mesReg}.${anoReg} 06:00:00")
            println ("dataBase: ${dataBase}")
            1.upto(ctDias) {
                dataBase = dataBase.plus(1)
                dataBase = dataBase.parse("dd.MM.yyyy HH:mm:ss", "${dataBase[DATE]}.${dataBase[MONTH]+1}.${dataBase[YEAR]} 06:00:00")
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
            
            def pessAntonio = Pessoa.findByNome('Antonio de Almeida') ?: new Pessoa(nome: "Antonio de Almeida", idade:32)
            .save(failOnError: true)
            def pessArthur = Pessoa.findByNome('Arthur Alencar') ?: new Pessoa(nome: "Arthur Alencar", idade:18)
            .save(failOnError: true)
            def pessArnaldo = Pessoa.findByNome('Arnaldo Albuquerque') ?: new Pessoa(nome: "Arnaldo Albuquerque", idade:12)
            .save(failOnError: true)
            def pessAstolfo = Pessoa.findByNome('Astolfo Andreoli') ?: new Pessoa(nome: "Astolfo Andreoli", idade:55)
            .save(failOnError: true)
            def pessAloa = Pessoa.findByNome('Aloá Arbacaredo') ?: new Pessoa(nome: "Aloá Arbacaredo", idade:82)
            .save(failOnError: true)

            def pessMariana = Pessoa.findByNome('Mariana Maia') ?: new Pessoa(nome: "Mariana Maia", idade:11)
            .save(failOnError: true)
            def pessMirlene = Pessoa.findByNome('Mirlene Mattos') ?: new Pessoa(nome: "Mirlene Mattos", idade:51)
            .save(failOnError: true)
            def pessMirian = Pessoa.findByNome('Mirian Matuelsen') ?: new Pessoa(nome: "Mirian Matuelsen", idade:35)
            .save(failOnError: true)
            def pessMonica = Pessoa.findByNome('Mônica Mascarenhas de Moraes') ?: new Pessoa(nome: "Mônica Mascarenhas de Moraes", idade:5)
            .save(failOnError: true)
            def pessMarcelo = Pessoa.findByNome('Marcelo Mercer') ?: new Pessoa(nome: "Marcelo Mercer", idade:22)
            .save(failOnError: true)

            
        }
    		
    	def countPessoasComGlicemia = Pessoa.findAll {
    		glicemias.size() > 0
		}
    	def countPessoasSemGlicemia = Pessoa.findAll {
    		glicemias.size() == 0
		}
        
        def countUsuariosComRole = User.findAll {
            pessoa != null
        }
        
        
		System.out.println("Pessoas com glicemias"+ countPessoasComGlicemia)
		System.out.println("Pessoas sem glicemias"+ countPessoasSemGlicemia)
		System.out.println("Usuários com Role"+ countUsuariosComRole)
        println "Usuarios:"
        def listaUsuarios = User.executeQuery("""
        SELECT username, password, pessoa.nome FROM User ORDER BY username""").collect {
            println "${it[0]} | ${it[1]} - ${it[2]}"
        }
    		
		System.out.println("Fim do Boostrap")    	
    }
    
    def destroy = {
    }
}
