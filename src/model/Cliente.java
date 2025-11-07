package model;

public class Cliente extends Pessoa{
    protected String endereco;

    public Cliente() {
        super();
    }

    public Cliente(int id, String nome, String telefone, String email, String endereco) {
        super(id, nome, telefone, email);
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Cliente: " + nome + "Id: " + id + " | Endereço: " + endereco);
    }
}

//terminado