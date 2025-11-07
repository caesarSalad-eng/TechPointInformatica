package model;

public class PessoaFisica extends Cliente{
    private String cpf;

    public PessoaFisica() {
        super();;
    }

    //Atributos
    public PessoaFisica(int id, String nome, String telefone, String email, String endereco, String cpf) {
        super(id, nome, telefone, email, endereco);
        this.cpf = cpf;
    }

    //Getters
    public String getCpf() {
        return cpf;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Pessoa Física: " + nome + " | CPF: " + cpf + " | Endereço: " + endereco);
    }
}

//terminado