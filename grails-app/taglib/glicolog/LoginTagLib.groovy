package glicolog

class LoginTagLib {
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    def loginControl = {
        //def user = User.getAuthenticatedUser()
        if(isLoggedIn()) {
            def stringAction = "[Logoff]"
          //  out << "${User.pessoa.nome} "
            out << link(action: 'logoff', controller:'/'){stringAction}
        } else {
            def stringAction = "[Login]"
            out << link(action: 'login', controller:'/'){stringAction}
        }
    }
}
