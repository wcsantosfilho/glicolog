package glicolog

class LoginTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def loginControl = {
        if(session.usuario) {
            def stringAction = "[Logout]"
            out << "${session.usuario.name} "
            out << link(action: 'logout', controller:'usuario'){stringAction}
        } else {
            def stringAction = "[Login]"
            out << link(action: 'login', controller:'usuario'){stringAction}
        }
    }
}
