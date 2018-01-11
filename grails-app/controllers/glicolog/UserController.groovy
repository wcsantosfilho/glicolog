package glicolog

class UserController {
 
  def login = {}
 
  def authenticate = {
    def user = User.findByLoginAndPassword(params.login, params.password)
    if(user) {
      session.user = user
      flash.message = "Olá ${user.name}, seja bem-vindo ao Glicolog!"
      redirect(controller:"home", action:"index")
    } else {
      flash.message = "Ops, ${params.login}. Suas credenciais não batem com nossos registros."
      redirect(action:"login")
    }
  }
 
  def logout = {
    flash.message = "Até mais ${session.user.name}!"
    session.user = null
    redirect(controller:"home", action:"index")
  }
}
