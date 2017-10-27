class LoginCommand implements grails.validation.Validateable { 
groovy> ng username 
    String password 
    static constraints = { 
        username(blank: false, minSize: 6) 
        password(blank: false, minSize: 6) 
    } 
} 
LoginCommand lc1 = new LoginCommand(username: "Walter", password: "123456") 
lc1.validate() 
if (lc1.hasErrors()) { 
  lc1.errors.allErrors.each { 
        println it 
    } 
} else { return lc1 } 