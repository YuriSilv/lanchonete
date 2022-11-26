import java.util.List;
import java.util.ArrayList;

public class Sistema {

    ////////// ATRIBUTOS ////////////////////
    private boolean logado;
    final int numero_funcionarios = 14;
    final int numero_adms = 1;
    private String nivelAcesso;
    /// Talvez 3 niveis de acesso: 1 funcionario; 2 adm; 3 nivel masterblaster pika 
    
    // Funcionario funcionarios[] = new Funcionario[numero_funcionarios];
    // Administrador administradores[] = new Administrador[numero_adms];

    List<Cliente> clientes = new ArrayList<Cliente>();
    List<String> clientesCpf = new ArrayList<String>();  

    List<Pedidos> listaPedidos= new ArrayList<Pedidos>();


    /////// construtor //////////
    public Sistema(){

    }
    public Sistema(Usuario user){
        

        setLogado(true);
        System.out.println("Nivel de acesso recebido: "+user.nivelAcesso);
        

        setNivelAcesso(user.nivelAcesso); 
    }






    //////// METODOS //////////////////////

    ///test
    public void mostrarPoder(){
        
        System.out.println(getNivelAcesso());
        if(getNivelAcesso().equals("120")){
            System.out.println("Vc é um administrador\n");
        }
        if(getNivelAcesso().equals("10")){
            System.out.print("Voce é um funcionario\n");
        }
    }

    public void sair(){
        setLogado(false);
    }


    public void CadastrarCliente(String nome, String cpf,String telefone, String end){

        //erro
        if (clientesCpf.contains(cpf)) {
            System.out.println("Erro. User já cadastrado");
        }else{
            System.out.println("Cadastro feito com sucesso");
            Cliente newCliente = new Cliente(nome, cpf, telefone, end);
            clientes.add(newCliente);
            clientesCpf.add(cpf);
        }

        /*execeção de cliente já cadastrado
        try{
            clientes.contains(newCliente);
            newCliente.getCpf();
            clientes.add(newCliente);
        }
        finally{
            newCliente = null;
        }*/
        
    }

    public void adicionarNovoProduto(){
        /// Adicionando produtos na base de dados
    }
    public void adicionarNovoFuncionario(){
        /// PENSANDO AQUI... Talvez seja melhor fazer tipo com sobrecarga: Assim, ele receberia um usuario e definiria se é funcionario ou adm -So ideia- 
    }
    



    // Aqui vai ser usado quando o array de produtos(pelo menos inicial) já estiver definido pelo usuario na interface
    // daqui o pedido é criado e guardado no array do Sistema--
    // Futuramente vamos definir como guardar essas modificacoes no JSON
    public void incluirPedido(List<Produto> listaProdutos){
        Pedidos p1 = new Pedidos(listaProdutos);
        this.listaPedidos.add(p1);
    }
    

    /**
     * @return int return the nivelAcesso
     */
    public String getNivelAcesso() {
        return nivelAcesso;
    }

    /**
     * @param nivelAcesso the nivelAcesso to set
     */
    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }


    /**
     * @return boolean return the logado
     */
    public boolean isLogado() {
        return logado;
    }

    /**
     * @param logado the logado to set
     */
    public void setLogado(boolean logado) {
        this.logado = logado;
    }

}
