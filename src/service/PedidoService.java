package service;

import model.Pedido;
import interfaces.ICrud;

import java.util.*;
import java.util.HashMap;

public class PedidoService implements ICrud<Pedido>{

    private Map<Integer, Pedido> pedidos = new HashMap<>();
    private int proximoId = 1;

    @Override
    public void cadastrar(Pedido pedido) {
            pedido.setId(proximoId++);
            pedidos.put(pedido.getId(), pedido);
            System.out.println("Pedido cadastrado com sucesso! ID: " + pedido.getId());
    }

    @Override
    public Pedido consultar(int id) {
        Pedido pedido = pedidos.get(id);
        if (pedido == null) System.out.println("Pedido não encontrado.");
        return pedido;
    }

    @Override
    public void atualizar(Pedido pedido) {
        if (pedidos.containsKey(pedido.getId())) {
            pedidos.put(pedido.getId(), pedido);
            System.out.println("Pedido atualizado com sucesso!");
        } else {
            System.out.println("Pedido não encontrado para atualização.");
        }
    }

    @Override
    public void deletar(int id) {
        if (pedidos.remove(id) != null) {
            System.out.println("Pedido removido com sucesso!");
        } else {
            System.out.println("Pedido não encontrado.");
        }
    }

    @Override
    public List<Pedido> listar() {
        return new ArrayList<>(pedidos.values());
    }
}
