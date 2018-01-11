package glicolog

class User {

  static constraints = {
    login(unique:true)
    password(password:true)
    name()
  }
 
   
  String login
  String password
  String name
 
  String toString(){
    name
  }
}
