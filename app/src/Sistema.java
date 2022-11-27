import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

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
    public void cadastrarCliente(String nome, String cpf, String telefone, String end){
        try {
            proxy.verificarDadosCliente(cpf, telefone);
            Cliente newCliente = new Cliente(nome,cpf,telefone,end);
            clientes.add(newCliente);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removerCliente(String cpf){
        //remover do BD
        Iterator<Cliente> allClientes = clientes.iterator();
        while (allClientes.hasNext()) {
            Cliente c = allClientes.next();
            if (c.getCpf() == cpf) {
                allClientes.remove();
                break;
            }
        }
    }
    //Classe cliente ainda incompleta. Mexer depois
    public void editarCliente(String nome, String cpf, String telefone, String end){
        //acessar no BD e editar
        Iterator<Cliente> allClientes = clientes.iterator();
        while (allClientes.hasNext()) {
            Cliente c = allClientes.next();
            if (c.getCpf() == cpf) {
                allClientes.remove();
                break;
            }
        }
    }

    public void listarClientes(){
        for(Cliente c : clientes){
            System.out.println(c.getNome());
        }
    }

    //Funcionarios
    public void cadastrarEmpregado(String name, String cpf){
        try {
            //Arruma isso aq dps
            if (numero_funcionarios != -1){
                proxy.verificarDadosEmpregado(cpf);
                Funcionario newFuncionario = new Funcionario(name, cpf, Funcionario.getNivelacesso());
                funcionarios[numero_funcionarios] = newFuncionario;
                numero_funcionarios -= 1;    
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    //ADM
    //Uma maneira mais lógica é pegar um funcionário e alterar seu nível de acesso. Seria uma idea de promoção.
    public Administrador cadastrarEmpregado(String name, String cpf, String userName, String senha){
        Administrador newAdministrador = new Administrador(name, cpf, Administrador.getNivelacesso(), userName, senha);
        //dump funcionário para o BD
        return newAdministrador;
    }
  
    // Aqui vai ser usado quando o array de produtos(pelo menos inicial) já estiver definido pelo usuario na interface
    // daqui o pedido é criado e guardado no array do Sistema--
    // Futuramente vamos definir como guardar essas modificacoes no JSON
    public void incluirPedido(List<Produto> listaProdutos){
        Pedidos p1 = new Pedidos(listaProdutos);
        this.listaPedidos.add(p1);
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

}
