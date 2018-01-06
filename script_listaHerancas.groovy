

class Animal {
    String especie
    String som
 
 String getSom() {
     return som
 }
}

class Felino extends Animal {
    Integer pintas
    
    String getSom() {
        return "miau"
    }
}

class Equino extends Animal {
    Boolean crina
    
    String getSom() {
        return "uuuuuurrrrrhhh"
    }
}

def f1 = new Felino (especie: "Gato", som: "ronron", pintas: 2)
def e1 = new Equino (especie: "Zebra", som: "brhhhh", crina: false)
def e2 = new Equino (especie: "Muar", som: "riiiihhh", crina: true)

def lista = Animal.list()
println lista