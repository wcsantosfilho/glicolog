package glicolog

import grails.gorm.transactions.Transactional
import grails.util.Environment
import grails.plugins.rest.client.RestBuilder
import groovy.json.JsonBuilder

@Transactional
class ComunicacaoService {

    def enviarEmail(String emailDestinatario,
                    String nomeDestinatario,
                    String emailAssunto,
                    String emailMensagem) {
        if (Environment.current == Environment.DEVELOPMENT) {
            /* teste com API do SendGrid - Serviço de envio de emails */
            println "Enviar email DEVELOPMENT"
            
            // Cria instancia da classe para envio via SendGrid
            SendGrid sendGrid = new SendGrid()
            
            // Cria instancia da class Pers = Personalizations
            Pers pers = new Pers()
            
            // Atribui emails para a lista
            ToEmail toEmail = new ToEmail(emailAddressTo: emailDestinatario, emailNameTo: nomeDestinatario)
            
            // Remetente do e-mail é a applicação no Heroku
            FromEmail fromEmail = new FromEmail(emailAddressFrom: ${buscar no database}, emailNameFrom: "Glicolog")
            
            // Formata o conteúdo do email a ser enviado
            ContentEmail contentEmail = new ContentEmail()
            contentEmail.typeContent = "text/html"
            contentEmail.valueContent = emailMensagem

            // Monta a lista de endereços de email para envio no Personalizations
            pers.toEmailList.add(toEmail)
            pers.persSubject = emailAssunto

            // Adiciona a lista de Personalizatins no SendGrid
            sendGrid.persList.add(pers)

            // Associa propriedades do objeto SendGrid
            sendGrid.fromEmail = fromEmail
            sendGrid.contentEmailList.add(contentEmail)
            sendGrid.subject = emailAssunto

            // Cria instancia do JSON que será enviado para a API do SendGrid
            def jsonSendGrid = new JsonBuilder()

            // Constroi o JSON conforme documentação da API
            def root = jsonSendGrid {
                A: { 
                    personalizations ( sendGrid.persList.collect {
                         Pers p1 -> [to: p1.toEmailList.collect {
                            ToEmail te -> [email: te.emailAddressTo, name: te.emailNameTo ] }, subject: p1.persSubject ]
                         } )
                    from ( email: sendGrid.fromEmail.emailAddressFrom, name: sendGrid.fromEmail.emailNameFrom )
                    subject ( sendGrid.subject )
                    content ( sendGrid.contentEmailList.collect {
                        ContentEmail ce -> [type: ce.typeContent, value: ce.valueContent]
                        } )
                    } 
            }

            // Cria instancia da chamada da REST
            def restSendGrid = new RestBuilder()

            def respSendGrid = restSendGrid.post("https://api.sendgrid.com/v3/mail/send") {
                header 'authorization', ${Buscar no Database}
                accept('application/json')
                contentType('application/json')
                body(jsonSendGrid.toString())
            }
            
            // Precisa testar retorno da API


            
        }
        if (Environment.current == Environment.PRODUCTION) {
            /* teste com SendGrid */
            println "Enviar email PRODUCTION"
        } 
    } // def EnviarEmail
} // Class



class SendGrid {
    Collection<Pers> persList = []
    FromEmail fromEmail
    String subject
    Collection<ContentEmail> contentEmailList = []
}

class Pers {
    Collection<ToEmail> toEmailList = []
    String persSubject
}

class ToEmail {
    String emailAddressTo
    String emailNameTo
}

class FromEmail {
    String emailAddressFrom
    String emailNameFrom
}

class ContentEmail {
    String typeContent
    String valueContent
}


