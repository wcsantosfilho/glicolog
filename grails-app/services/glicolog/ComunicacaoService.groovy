package glicolog

import grails.gorm.transactions.Transactional
import grails.util.Environment

@Transactional
class ComunicacaoService {

    def enviarEmail(String emailDestinatario,
                    String emailAssunto,
                    String emailMensagem) {
        if (Environment.current == Environment.DEVELOPMENT) {
            def mailService
            sendMail {
                to emailDestinatario
                subject emailAssunto
                text emailMensagem
            }
        }
        if (Environment.current == Environment.PRODUCTION) {
            
        }
    }
}
