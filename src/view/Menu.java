package view;
import model.*;
import service.ClienteService;
import service.ProdutoService;
import service.PedidoService;
import java.util.Scanner;

public class Menu {
    //
    private static final Scanner sc = new Scanner(System.in);
    private static final ClienteService clienteService = new ClienteService();
    private static final ProdutoService produtoService = new ProdutoService();
    private static final PedidoService pedidoService = new PedidoService();

    //Menu
    public static void exibirMenu() {
        int opcao = -1;

        do {
            System.out.println("\n=== TechPoint Informática ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Produto");
            System.out.println("3. Realizar Pedido");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine(); // limpa o buffer do teclado

                switch (opcao) {
                    case 1:
                        cadastrarCliente();
                        break;
                    case 2:
                        cadastrarProduto();
                        break;
                    case 3:
                        realizarPedido();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }

            } catch (Exception e) {
                System.out.println("Entrada inválida! Digite apenas números correspondentes às opções.");
                sc.nextLine(); // limpa o buffer pra evitar loop infinito
            }
        } while (opcao != 0);
    }

    //Métodos com nome autoexplicativo
    private static void cadastrarCliente() {
        System.out.println("==Cadastro de cliente==");
        System.out.print("É pessoa física (1) ou jurídica (2)? ");
        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Endereço: ");
        String endereco = sc.nextLine();

        if (tipo == 1) {
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            PessoaFisica pf = new PessoaFisica(0, nome, telefone, email, endereco, cpf);
            clienteService.cadastrar(pf);
        } else if (tipo == 2) {
            System.out.print("CNPJ: ");
            String cnpj = sc.nextLine();
            PessoaJuridica pj = new PessoaJuridica(0, nome, telefone, email, endereco, cnpj);
            clienteService.cadastrar(pj);
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static void cadastrarProduto() {
        System.out.println("==Cadastro de produto==");
        System.out.print("Nome do produto: ");
        String nome = sc.nextLine();

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        System.out.print("Preço: ");
        double preco = sc.nextDouble();

        System.out.print("Estoque inicial: ");
        int estoque = sc.nextInt();

        Produto produto = new Produto(0, nome, categoria, preco, estoque);
        produtoService.cadastrar(produto);
    }

    private static void realizarPedido() {
        System.out.println("==Realizar pedido==");

        System.out.print("ID do cliente: ");
        int idCliente = sc.nextInt();
        sc.nextLine();

        Cliente cliente = clienteService.consultar(idCliente);
        if (cliente == null) {
            System.out.print("Cliente não encontrado. Deseja cadastrá-lo agora? (S/N): ");
            String resposta = sc.nextLine().trim().toUpperCase();

            if (resposta.equals("S")) {
                cadastrarClienteRapido(); // o ID é gerado automaticamente no service
                cliente = clienteService.listar()
                        .stream()
                        .reduce((first, second) -> second) // pega o último cliente cadastrado
                        .orElse(null);
            } else {
                System.out.println("Operação cancelada. Retornando ao menu...");
                return;
            }
        }


        Pedido pedido = new Pedido(0, cliente);

        int codigoProduto;
        do {
            System.out.print("Código do produto (0 para finalizar): ");
            codigoProduto = sc.nextInt();
            sc.nextLine();

            if (codigoProduto != 0) {
                Produto produto = produtoService.consultar(codigoProduto);

                if (produto == null) {
                    System.out.print("Produto não encontrado. Deseja cadastrá-lo agora? (S/N): ");
                    String resposta = sc.nextLine().trim().toUpperCase();

                    if (resposta.equals("S")) {
                        cadastrarProdutoRapido();
                        produto = produtoService.listar()
                                .stream()
                                .reduce((first, second) -> second)
                                .orElse(null);
                    } else {
                        System.out.println("Produto ignorado. Voltando...");
                        continue;
                    }
                }

                if (produto != null) {
                    System.out.print("Quantidade: ");
                    int qtd = sc.nextInt();
                    sc.nextLine();

                    if (qtd <= produto.getEstoque()) {
                        produto.atualizarEstoque(qtd);
                        pedido.adicionarItem(new ItemPedido(produto, qtd));
                    } else {
                        System.out.println("Estoque insuficiente!");
                    }
                }
            }

        } while (codigoProduto != 0);

        pedidoService.cadastrar(pedido);
        System.out.println("\nPedido realizado com sucesso!");
        System.out.println(pedido);
        System.out.println("Itens do pedido:");
        pedido.getItens().forEach(System.out::println);
        System.out.println("Total: R$ " + pedido.calcularTotal());
    }


    private static void cadastrarClienteRapido() {
        System.out.println("==Cadastro rápido de cliente==");
        System.out.print("É pessoa física (1) ou jurídica (2)? ");
        int tipo = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Endereço: ");
        String endereco = sc.nextLine();

        if (tipo == 1) {
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            PessoaFisica pf = new PessoaFisica(0, nome, telefone, email, endereco, cpf);
            clienteService.cadastrar(pf);
        } else if (tipo == 2) {
            System.out.print("CNPJ: ");
            String cnpj = sc.nextLine();
            PessoaJuridica pj = new PessoaJuridica(0, nome, telefone, email, endereco, cnpj);
            clienteService.cadastrar(pj);
        } else {
            System.out.println("Tipo inválido! Cadastro cancelado.");
        }
    }

    private static void cadastrarProdutoRapido() {
        System.out.println("\n--- Cadastro Rápido de Produto ---");

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Categoria: ");
        String categoria = sc.nextLine();

        double preco = 0;
        while (true) {
            try {
                System.out.print("Preço (R$): ");
                preco = sc.nextDouble();
                sc.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Valor inválido! Digite um número válido para o preço.");
                sc.nextLine(); // limpar buffer
            }
        }

        System.out.print("Estoque inicial: ");
        int estoque = sc.nextInt();
        sc.nextLine();

        Produto novo = new Produto(0, nome, categoria, preco, estoque);
        produtoService.cadastrar(novo);
    }



}


