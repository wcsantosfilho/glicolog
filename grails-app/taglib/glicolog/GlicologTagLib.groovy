package glicolog
import java.text.SimpleDateFormat

class GlicologTagLib {
    static namespace = 'glicolog'
    static defaultEncodeAs = [taglib:'raw']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    
    /* 
        Tag que gera qualquer coisa para um 
        @attr item Objeto do tipo Registro cujos dados específicos
        serão formatados para serem apresentados
    */
    def listaDetalheRegistro = { attrs ->
        def reg = attrs.item
        Locale localeBR = new Locale("pt", "BR");
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm", localeBR);
        def formattedDataRegistro = fmt.format(reg?.dataRegistro);

        def colDataRegistro = "<td class=\"td-glicolog\">${formattedDataRegistro} </td>"
        
        if (reg?.instanceOf(Glicemia)) {
            out << "${colDataRegistro}<td class=\"td-glicolog\">${reg?.tipoGlicemia} - ${reg?.taxaGlicemia}</td><td class=\"td-glicolog\"></td><td class=\"td-glicolog\"></td><td class=\"td-glicolog\"></td>"
        } else if (reg?.instanceOf(Insulina)) {
            out << "${colDataRegistro}<td class=\"td-glicolog\"></td><td class=\"td-glicolog\">${reg?.tipoInsulina} - ${reg?.doseInsulina}</td><td class=\"td-glicolog\"></td><td class=\"td-glicolog\"></td>"
        } else if (reg?.instanceOf(Refeicao)) {
            out << "${colDataRegistro}<td class=\"td-glicolog\"></td><td class=\"td-glicolog\"></td><td class=\"td-glicolog\">${reg?.tipoRefeicao} - ${reg?.observRefeicao}</td><td class=\"td-glicolog\"></td>" 
        } else if (reg?.instanceOf(AtivFisica)) {
            out << "${colDataRegistro}<td class=\"td-glicolog\"></td><td class=\"td-glicolog\"></td><td class=\"td-glicolog\"></td><td class=\"td-glicolog\">${reg?.tipoAtivFisica} - ${reg?.observAtivFisica}</td>"
        } else {
            out << attrs.item 
        }
    }
}
