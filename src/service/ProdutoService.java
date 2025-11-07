package service;

import interfaces.ICrud;
import model.Produto;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProdutoService implements ICrud<Produto> {

    private Map<Integer, Produto> produtos = new HashMap<>();
    private int proximoCodigo = 1001; //para distinguir do id cliente

    @Override
    public void cadastrar (Produto produto) {
        produto.setCodigo(proximoCodigo++);
        produtos.put(produto.getCodigo(), produto);
        System.out.println("Produto cadastrado! Código gerado: " + produto.getCodigo());
        }


    @Override
    public Produto consultar(int codigo) {
        Produto produto = produtos.get(codigo);
        if (produto == null) System.out.println("Produto não encontrado.");
        return produto;
    }

    @Override
    public void atualizar(Produto produto) {
        if (produtos.containsKey(produto.getCodigo())) {
            produtos.put(produto.getCodigo(), produto);
            System.out.println("Produto atualizado com sucesso!");
        } else {
            System.out.println("Produto não encontrado para atualização.");
        }
    }

    @Override
    public void deletar(int codigo) {
        if (produtos.remove(codigo) != null) {
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    @Override
    public List<Produto> listar() {
        return new ArrayList<>(produtos.values());
    }

}
