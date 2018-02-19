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
        def formattedGlicemia = "${reg?.tipoGlicemia ?: ''} - ${reg?.taxaGlicemia ?: ''}"
        def formattedInsulina = "${reg?.tipoInsulina ?: ''} - ${reg?.doseInsulina ?: ''}"
        def formattedRefeicao = "${reg?.tipoRefeicao ?: ''} - ${reg?.observRefeicao ?: ''}"
        def formattedAtivFisica = "${reg?.tipoAtivFisica ?: ''} - ${reg?.observAtivFisica ?: ''}"

        def colDataRegistro = "<td data-th=\"Data Registro\" class=\"td-glicolog\">${formattedDataRegistro} </td>"
        out << "${colDataRegistro}<td data-th=\"Glicemia\" class=\"td-glicolog\">${formattedGlicemia}</td><td data-th=\"Insulina\" class=\"td-glicolog\">${formattedInsulina}</td><td data-th=\"Refeição\" class=\"td-glicolog\">${formattedRefeicao}</td><td data-th=\"Ativ.Física\" class=\"td-glicolog\">${formattedAtivFisica}</td>"
    }
}
