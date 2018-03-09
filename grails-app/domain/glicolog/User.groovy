package glicolog

class User extends SecUser {
    Pessoa pessoa

    static constraints = {
        pessoa(nullable: true, unique: true)
    }
}
