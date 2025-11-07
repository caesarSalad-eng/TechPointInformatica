package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;

    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
    }

    //Getters
    public int getId() {
        return id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public List<ItemPedido> getItens() {
        return itens;
    }

    //Setter


    public void setId(int id) {
        this.id = id;
    }

    //Funções
    public void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public double calcularTotal() {
        return itens.stream().mapToDouble(ItemPedido::getSubtotal).sum();
    }


    @Override
    public String toString() {
        return "Pedido #" + id + " | Cliente: " + cliente.getNome() + " | Total: R$ " + calcularTotal();
    }
}

//terminado