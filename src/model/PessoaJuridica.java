package model;

public class PessoaJuridica extends Cliente{
    private String cnpj;
    private String razaoSocial;

    public PessoaJuridica(int id, String nome, String telefone, String email, String endereco, String cnpj) {
        super();
    }

    //Atributos
    public PessoaJuridica(int id, String nome, String telefone, String email, String endereco, String cnpj, String razaoSocial) {
        super(id, nome, telefone, email, endereco);;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    //Getters
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public String getCnpj() {
        return cnpj;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Pessoa Jurídica: " + razaoSocial + " | CNPJ: " + cnpj);
    }
}

//terminado