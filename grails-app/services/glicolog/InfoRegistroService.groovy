package glicolog

import grails.gorm.transactions.Transactional

/* ------------------------------------------------- *
 * Serviço para prover dados sobre a class Registro  *
 * ------------------------------------------------- */

@Transactional
class InfoRegistroService {
    // Definindo o Escopo
    static scope = "prototype"
    
    /* -----------------------------------------------------------------------*
     * Consulta Registros da Pessoa                                           *
     * -----------------------------------------------------------------------*/
    @Transactional(readOnly = true)
    def consultaRegistrosDaPessoa(Pessoa pessoaParaConsulta, Integer offset, Integer max, String order) {
        // HQL para buscar os registros da pessoa, agrupados pela data e hora em uma mesma linha
        // depois transforma em Collect para virar um MAPA com propriedades nomeadas (a GSP/Taglib usa assim)
        def searchResults = Registro.executeQuery("""
            select dataRegistro, 
            min (tipoGlicemia),
            min (taxaGlicemia),
            min (tipoInsulina),
            min (doseInsulina),
            min (tipoRefeicao),
            min (observRefeicao),
            min (tipoAtivFisica),
            min (observAtivFisica)
            FROM Registro
            WHERE pessoa = :pessoaParaSearch
            GROUP BY dataRegistro
            ORDER BY dataRegistro desc""", [pessoaParaSearch:pessoaParaConsulta, offset:offset, max:max]).collect {
                [   dataRegistro: it[0],
                    tipoGlicemia: it[1],
                    taxaGlicemia: it[2],
                    tipoInsulina: it[3],
                    doseInsulina: it[4],
                    tipoRefeicao: it[5],
                    observRefeicao: it[6],
                    tipoAtivFisica: it[7],
                    observAtivFisica: it[8]
                ]
        }

        // HQL para contar os registros da pessoa, agrupados pela data e hora em uma mesma linha
        def searchCount = Registro.executeQuery("""
            select count(dataRegistro)
                FROM Registro
                WHERE pessoa = :pessoaParaSearch
                GROUP BY dataRegistro""", [pessoaParaSearch:pessoaParaConsulta])

        def registroTotal = searchCount.size()

        // define a lista para passar para o GSP
        def listObject = [registroList: searchResults, registroTotal: registroTotal]
    }
    
    /* -----------------------------------------------------------------------*
     * Todos os Registros da Pessoa                                           *
     * -----------------------------------------------------------------------*/
    @Transactional(readOnly = true)
    def consultaTodosRegistrosDaPessoa(Pessoa pessoaParaConsulta) {
        // HQL para buscar os todos os registros da pessoa, agrupados pela data e hora em uma mesma linha
        def searchResults = Registro.executeQuery("""
            select dataRegistro, 
            min (tipoGlicemia),
            min (taxaGlicemia),
            min (tipoInsulina),
            min (doseInsulina),
            min (tipoRefeicao),
            min (observRefeicao),
            min (tipoAtivFisica),
            min (observAtivFisica)
            FROM Registro
            WHERE pessoa = :pessoaParaSearch
            GROUP BY dataRegistro
            ORDER BY dataRegistro asc""", [pessoaParaSearch:pessoaParaConsulta]).collect {
                [   dataRegistro: it[0],
                    tipoGlicemia: it[1],
                    taxaGlicemia: it[2],
                    tipoInsulina: it[3],
                    doseInsulina: it[4],
                    tipoRefeicao: it[5],
                    observRefeicao: it[6],
                    tipoAtivFisica: it[7],
                    observAtivFisica: it[8]
                ]
        }
    }


    
    /* -----------------------------------------------------------------------*
     * Salva Registros da Pessoa (Glicemia)                                   *
     * -----------------------------------------------------------------------*/
    def salvaRegistroGlicemia(Pessoa pessoa, RegistroInfo info) {
        // Cria a instância
        Glicemia glicemia;

        // Monta os atributos com os dados do Command Info
        def dataHoraAtiv = Date.parse('dd/MM/yyyy HH:mm:ss', info.dataRegistro + " " + info.horaRegistro+":00")
        glicemia = new Glicemia (dataRegistro: dataHoraAtiv, tipoGlicemia: info.tipoGlicemia, taxaGlicemia: info.taxaGlicemia, pessoa: pessoa )
        if (!glicemia.save(flush: true)) {
            throw new Exception("Erro no save", glicemia.errors)
        }
    }

    /* -----------------------------------------------------------------------*
     * Salva Registros da Pessoa (Insulina)                                   *
     * -----------------------------------------------------------------------*/
    def salvaRegistroInsulina(Pessoa pessoa, RegistroInfo info) {
        // Cria a instância
        Insulina insulina;

        // Monta os atributos com os dados do Command Info
        def dataHoraAtiv = Date.parse('dd/MM/yyyy HH:mm:ss', info.dataRegistro + " " + info.horaRegistro+":00")
        insulina = new Insulina (dataRegistro: dataHoraAtiv, tipoInsulina: info.tipoInsulina, doseInsulina: info.doseInsulina, pessoa: pessoa )
        if (!insulina.save(flush: true)) {
            throw new Exception("Erro no save", insulina.errors)
        }
    }
    
    /* -----------------------------------------------------------------------*
     * Salva Registros da Pessoa (Refeição)                                   *
     * -----------------------------------------------------------------------*/
    def salvaRegistroRefeicao(Pessoa pessoa, RegistroInfo info) {
        // Cria a instância
        Refeicao refeicao;

        // Monta os atributos com os dados do Command Info
        def dataHoraAtiv = Date.parse('dd/MM/yyyy HH:mm:ss', info.dataRegistro + " " + info.horaRegistro+":00")
        refeicao = new Refeicao (dataRegistro: dataHoraAtiv, tipoRefeicao: info.tipoRefeicao, observRefeicao: info.observRefeicao, pessoa: pessoa )
        if (!refeicao.save(flush: true)) {
            throw new Exception("Erro no save", refeicao.errors)
        }
    }
    
    /* -----------------------------------------------------------------------*
     * Salva Registros da Pessoa (Atividade Fisica)                           *
     * -----------------------------------------------------------------------*/
    def salvaRegistroAtivFisica(Pessoa pessoa, RegistroInfo info) {
        // Cria a instância
        AtivFisica ativfisica;

        // Monta os atributos com os dados do Command Info
        def dataHoraAtiv = Date.parse('dd/MM/yyyy HH:mm:ss', info.dataRegistro + " " + info.horaRegistro+":00")
        ativfisica = new AtivFisica (dataRegistro: dataHoraAtiv, tipoAtivFisica: info.tipoAtivFisica, observAtivFisica: info.observAtivFisica, pessoa: pessoa )
                
        if (!ativfisica.save(flush: true)) {
            throw new Exception("Erro no save", ativfisica.errors)
        }
    }
    
}
