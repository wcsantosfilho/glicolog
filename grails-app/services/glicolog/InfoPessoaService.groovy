package glicolog

import grails.gorm.transactions.Transactional

/* ------------------------------------------------- *
 * Serviço para prover dados sobre a classe Pessoa   *
 * ------------------------------------------------- */

@Transactional
class InfoPessoaService {

    @Transactional(readOnly = true)
    /* Recebe a string do nome do usuário e retorna o objeto "Pessoa" *
     * correspondente                                                 *
     *                                                                */
    def pessoaDoUsuarioLogado(String nomeUsuario) {
        def usuario = User.findByUsername(nomeUsuario)
        Pessoa.get(usuario.pessoa.id)
    }
}
