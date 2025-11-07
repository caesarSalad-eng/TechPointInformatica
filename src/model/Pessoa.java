package model;

//Criei uma classe pessoa para praticar a Herança, onde as classes Cliente,
// PessoaFisica e PessoaJuridica herdarão a classe Pessoa

public abstract class Pessoa {

    protected int id;
    protected String nome;
    protected String telefone;
    protected String email;

    public Pessoa() {}

    //Atributos
    public Pessoa(int id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public abstract void exibirInfo();


    //Getters
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }

    //Setter


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Email: " + email;
    }
}

//terminado