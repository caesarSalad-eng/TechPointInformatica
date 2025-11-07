package service;
import model.Cliente;
import interfaces.ICrud;
import java.util.*;

public class ClienteService implements ICrud<Cliente> {
    private Map<Integer, Cliente> clientes = new HashMap<>();
    private int proximoId = 1;

    @Override
    public void cadastrar(Cliente cliente) {

        cliente.setId(proximoId++); // define ID automaticamente
        clientes.put(cliente.getId(), cliente);
        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println("Nome: " + cliente.getNome() + " | ID gerado: " + cliente.getId());

    }

    @Override
    public Cliente consultar(int id) {
        Cliente cliente = clientes.get(id);

        if (cliente == null)
            System.out.println("Cliente não encontrado.");
        return cliente;
    }

    @Override
    public void atualizar(Cliente cliente) {
        if (clientes.containsKey(cliente.getId())) {
            clientes.put(cliente.getId(), cliente);
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado para atualização.");
        }
    }

    @Override
    public void deletar(int id) {
        if (clientes.remove(id) != null) {
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    @Override
    public List<Cliente> listar() {
        return new ArrayList<>(clientes.values());
    }


}