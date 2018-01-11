package glicolog

class LoginTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def loginControl = {
        if(session.user) {
            def stringAction = "[Logout]"
            out << "${session.user.name} "
            out << link(action: 'logout', controller:'user'){stringAction}
        } else {
            def stringAction = "[Login]"
            out << link(action: 'login', controller:'user'){stringAction}
        }
    }
}
