import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Sistema {

    ////////// ATRIBUTOS ////////////////////
    private boolean logado;
    private int numero_funcionarios = 15;
    final int numero_adms = 1;
    private String nivelAcesso;
    
    Funcionario funcionarios[] = new Funcionario[numero_funcionarios+1];
    Administrador administradores[] = new Administrador[numero_adms];

    List<Cliente> clientes = new ArrayList<Cliente>();

    List<Pedidos> listaPedidos= new ArrayList<Pedidos>();
    
    Proxy proxy = new Proxy();
    JsonConex connection = new JsonConex();

    /////// construtor //////////
    public Sistema(){

    }

    //////// METODOS //////////////////////

    //login
    public void logar(String email, String senha) {
        try {
            setLogado(proxy.verificarLogin(email, senha));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void logout(){
        setLogado(false);
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    //clientes
    public void cadastrarCliente(String nome, String cpf, String telefone, String end) throws IOException{
        try {
            proxy.verificarDadosCliente(cpf, telefone);
            Cliente newCliente = new Cliente(nome,cpf,telefone,end);
            JsonConex.dump(newCliente);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removerCliente(String cpf) throws IOException{
        //remover do BD
        ArrayList<Cliente> clientes = JsonConex.dadosClientes();
        Iterator<Cliente> allClientes = clientes.iterator();
        while (allClientes.hasNext()) {
            Cliente c = allClientes.next();
            if (c.getCpf().equals(cpf)) {
                System.out.println(c.getNome());
                allClientes.remove();
                JsonConex.saveState(JsonConex.clientePath, clientes);
            }
        }
    }
    //Classe cliente ainda incompleta. Mexer depois
    public void editarCliente(String cpf, String dataType) throws IOException{
        //acessar no BD e editar
        ArrayList<Cliente> clientes = JsonConex.dadosClientes();
        Iterator<Cliente> allClientes = clientes.iterator();
        Scanner ler = new Scanner(System.in);
        //System.out.printf("Informe o tipo de dado que se deseja alterar(1 - end, 2 - nome, 3 - cpf, 4 - telefone):\n");
        //int key = ler.nextInt();
        
        while (allClientes.hasNext()) {
            Cliente c = allClientes.next();
            if (c.getCpf().equals(cpf)) {
                System.out.println(c.getNome());
                if (dataType == "end"){
                    System.out.printf("Informe o novo endereço:\n");
                    String palavra = ler.next();
                    c.setEnd(palavra);
                }
                else if (dataType == "nome"){
                    System.out.printf("Informe o novo nome:\n");
                    String palavra = ler.next();
                    c.setNome(palavra);
                }
                else if (dataType == "telefone"){
                    System.out.printf("Informe o novo telefone:\n");
                    String palavra = ler.next();
                    c.setTelefone(palavra);
                }
                else if (dataType == "cpf"){
                    System.out.printf("Informe o novo cpf:\n");
                    String palavra = ler.next();
                    c.setCpf(palavra);
                }
                JsonConex.saveState(JsonConex.clientePath, clientes);
            }
        }
    }
    
    public void listarClientes(){
        ArrayList<Cliente> clientes = JsonConex.dadosClientes();
        Iterator<Cliente> allClientes = clientes.iterator();
        while (allClientes.hasNext()) {
            Cliente c = allClientes.next();
            System.out.println("Nome: " + c.getNome() + "| Telefone: " + c.getTelefone());
        }
    }

    //produtos
    public void cadastrarProduto(String nome, String descricao, float valor) throws IOException{
        try {
            //proxy.verificarDadosCliente(cpf, telefone);
            Produto newProduto = new Produto(nome,descricao,valor);
            JsonConex.dump(newProduto);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removerProduto(String nome) throws IOException{
        //remover do BD
        ArrayList<Produto> produtos = JsonConex.dadosProdutos();
        Iterator<Produto> allProduto = produtos.iterator();
        while (allProduto.hasNext()) {
            Produto p = allProduto.next();
            if (p.getNome().equals(nome)) {
                System.out.println(p.getNome());
                allProduto.remove();
                JsonConex.saveState(JsonConex.produtoPath, produtos);
            }
        }
    }
    
    public void editarProduto(String nome, String dataType) throws IOException{
        //acessar no BD e editar
        ArrayList<Produto> produtos = JsonConex.dadosProdutos();
        Iterator<Produto> allProdutos = produtos.iterator();
        Scanner ler = new Scanner(System.in);
        //System.out.printf("Informe o tipo de dado que se deseja alterar(1 - end, 2 - nome, 3 - cpf, 4 - telefone):\n");
        //int key = ler.nextInt();
        
        while (allProdutos.hasNext()) {
            Produto p = allProdutos.next();
            if (p.getNome().equals(nome)) {
                if (dataType == "descrição"){
                    System.out.printf("Informe a nova descrição:\n");
                    String palavra = ler.next();
                    p.setDescricao(palavra);
                }
                else if (dataType == "nome"){
                    System.out.printf("Informe o novo nome:\n");
                    String palavra = ler.next();
                    p.setNome(palavra);
                }
                else if (dataType == "valor"){
                    System.out.printf("Informe o novo valor:\n");
                    float palavra = ler.nextFloat();
                   p.setValor(palavra);
                }
                JsonConex.saveState(JsonConex.produtoPath, produtos);
                ler.close();
                break;
            }
        }
        ler.close();
    }
    
    public void listarProdutos(){
        ArrayList<Produto> produtos = JsonConex.dadosProdutos();
        Iterator<Produto> allProdutos = produtos.iterator();
        while (allProdutos.hasNext()) {
            Produto p = allProdutos.next();
            System.out.println("Nome: " + p.getNome() + "| Valor: " + p.getValor());
        }
    }
    
    //Funcionarios
    public void cadastrarEmpregado(String name, String cpf, String nivelAcesso, String senha) throws IOException{
        try {
            //Arruma isso aq dps
            proxy.verificarDadosEmpregado(cpf);
            if (nivelAcesso.equals(Funcionario.getNivelAcesso())){
                Funcionario newFuncionario = new Funcionario(name, cpf, Funcionario.getNivelAcesso(), senha);
                JsonConex.dump(newFuncionario);
            }
            else if (nivelAcesso.equals(Administrador.getNivelAcesso())){
                Administrador newAdm = new Administrador(name, cpf, Administrador.getNivelAcesso(), senha);
                JsonConex.dump(newAdm);
            }
            else{
                throw new RuntimeException("Nivel de acesso inválido");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void removerEmpregado(String cpf) throws IOException{
        //remover do BD
        ArrayList<Funcionario> users = JsonConex.dadosEmpregados();
        Iterator<Funcionario> allUsers = users.iterator();
        while (allUsers.hasNext()) {
            Usuario u = allUsers.next();
            if (u.getCpf().equals(cpf)) {
                allUsers.remove();
                JsonConex.saveState(JsonConex.empregadoPath, users);
            }
        }
    }
    
    public void editarEmpregado(String cpf, String dataType) throws IOException{
        //acessar no BD e editar
        ArrayList<Funcionario> funcionarios = JsonConex.dadosEmpregados();
        Iterator<Funcionario> allfuncionarios = funcionarios.iterator();
        Scanner ler = new Scanner(System.in);
        //System.out.printf("Informe o tipo de dado que se deseja alterar(1 - end, 2 - nome, 3 - cpf, 4 - telefone):\n");
        //int key = ler.nextInt();
        
        while (allfuncionarios.hasNext()) {
            Funcionario c = allfuncionarios.next();
            if (c.getCpf().equals(cpf)) {
                System.out.println(c.getNome());
                if (dataType == "nome"){
                    System.out.printf("Informe o novo nome:\n");
                    String palavra = ler.next();
                    c.setNome(palavra);
                }
                else if (dataType == "cpf"){
                    System.out.printf("Informe o novo cpf:\n");
                    String palavra = ler.next();
                    c.setCpf(palavra);
                }else if (dataType == "940671MN01BA"){ //adptar o editar senha
                    System.out.printf("Informe a senha antiga:\n");
                    String palavra = ler.next();
                    if (Proxy.validarSenha(c.getSenha(), palavra)){
                        System.out.printf("Informe a nova senha:\n");
                        String palavra2 = ler.next();
                        c.setSenha(palavra2);
                    }
                }
                JsonConex.saveState(JsonConex.empregadoPath, funcionarios);
                ler.close();
                break;
            }
        }
    }
    
    public void alterarSenhaAdm(String cpf) throws IOException{
        editarEmpregado(cpf, "940671MN01BA");
    }
    
    public void listarEmpregados(){
        ArrayList<Funcionario> users = JsonConex.dadosEmpregados();
        Iterator<Funcionario> allUsers = users.iterator();
        while (allUsers.hasNext()) {
            Usuario u = allUsers.next();
            System.out.println("Nome: " + u.getNome() + "\nCpf: " + u.getCpf());
            /*if (nivelAcesso.equals(Funcionario.getNivelAcesso())){
                System.out.println("Nome: " + u.getNome() + "\nCpf: " + u.getCpf());
            }
            else if (nivelAcesso.equals(Administrador.getNivelAcesso())){
                System.out.println("Nome: " + u.getNome() + "\nCpf: " + u.getCpf());
            }
            else{
                throw new RuntimeException("Nivel de acesso inválido");
            }*/
        }
    }

}

