package glicolog

import grails.gorm.transactions.Transactional

@Transactional
class ComunicacaoService {

    def enviarEmail(String emailDestinatario,
                    String emailAssunto,
                    String emailMensagem) {
        
        def mailService
        sendMail {
            to emailDestinatario
            subject emailAssunto
            text emailMensagem
        }
    }
}
