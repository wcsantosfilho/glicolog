package glicolog

class UsuarioController {
 
  def login = {}
 
  def authenticate = {
    def usuario = Usuario.findByLoginAndPassword(params.login, params.password)
    if(usuario) {
      session.usuario = usuario
      flash.message = "Olá ${usuario.name}, seja bem-vindo ao Glicolog!"
      redirect(controller:"home", action:"index")
    } else {
      flash.message = "Ops, ${params.login}. Suas credenciais não batem com nossos registros."
      redirect(action:"login")
    }
  }
 
  def logout = {
    flash.message = "Até mais ${session.usuario.name}!"
    session.usuario = null
    redirect(controller:"home", action:"index")
  }
}
