package yuri.lanchonete;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Sistema {

    ////////// ATRIBUTOS ////////////////////
    private boolean logado;
    Funcionario funcionarios[] = new Funcionario[15];
    Proxy proxy = new Proxy();
    
    //cadastrar 15 colaboradores
    String nomes[] = {"Vicente Pires", "Davi Moreira", "Daniela Pires", "Emanuelly Aragão", "Mirella da Cunha",
                      "Evelyn da Luz", "Bernardo Gonçalves", "Pedro Aragão", "Rodrigo Vieira", "Maria Alice", "Gabriel Novaes",
                      "Laura da Paz", "Raquel da Luz", "Bruno Cunha", "Marcela Lopes"};
    
    
    String cpfs[] = {"41334161046", "54765983080", "21539673090", "43970882095", "53986071032", "05538463028", "30504563025",
                    "42061623018", "56296427077", "30544141083", "21494379023", "57994467040", "60657174017", "98942706037",
                    "64298908054"};
    

    public void Cadastrar15() throws IOException{
        for(int i = 0; i < 15; i++){
            funcionarios[i] = new Funcionario(nomes[i], cpfs[i], Funcionario.getNivelAcesso(),"1234");
            JsonConex.dump(funcionarios[i]);
            funcionarios[i] = null;
        }
    }
    
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
            Produto.setIdControl(JsonConex.dadosProdutos().size());
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
    
    public boolean searchPedido(String nome){
         ArrayList<Produto> produtosJson = JsonConex.dadosProdutos();
         for(Produto p: produtosJson){
             if(p.getNome().equals(nome)){
                 return true;
             }
         }
         System.out.println("Erro");
         return false;
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
    
    //Pedidos
    
    public ArrayList<Produto> checkPedido(ArrayList<String> produtos){
        ArrayList<Produto> produtosJson = JsonConex.dadosProdutos();
            ArrayList<Produto> produtosPedido = new ArrayList<Produto>();
            for(int i = 0; i < produtos.size(); i++){
                for(Produto p: produtosJson){
                    if(p.getNome().equals(produtos.get(i))){
                        produtosPedido.add(p);
                        break;
                    }     

                }    
            }
            return produtosPedido;
    }
    
    public void cadastrarPedidos(String cpf, ArrayList<String> produtos) throws IOException{
        try {
            //proxy.verificarDadosCliente(cpf, telefone);
            float valor = 0;
            
            ArrayList<Produto> produtosPedido = checkPedido(produtos);
            for(Produto p: produtosPedido){
                valor += p.getValor();
            }
            Pedidos.setControlID(JsonConex.dadosPedidos().size());
            Pedidos newPedido = new Pedidos(valor,cpf);
            newPedido.setProdutos(produtosPedido);
            JsonConex.dump(newPedido);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removerPedido(String cpf, int id) throws IOException{
        //remover do BD
        ArrayList<Pedidos> pedidos = JsonConex.dadosPedidos();
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            if (p.getCpf().equals(cpf) & p.getId() == id) {
                allPedidos.remove();
                JsonConex.saveState(JsonConex.pedidosPath, pedidos);
            }
        }
    }
        
    public void editarPedido(String cpf, int id, String dataType) throws IOException{
        //acessar no BD e editar
        ArrayList<Pedidos> pedidos = JsonConex.dadosPedidos();
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        Scanner ler = new Scanner(System.in);
        //System.out.printf("Informe o tipo de dado que se deseja alterar(1 - end, 2 - nome, 3 - cpf, 4 - telefone):\n");
        //int key = ler.nextInt();
        
        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            if (p.getCpf().equals(cpf) & p.getId() == id) {
                if (dataType == "cpf"){
                    System.out.printf("Informe o novo cpf:\n");
                    String palavra = ler.next();
                    p.setCpf(palavra);
                }
                else if (dataType == "status"){
                    System.out.printf("Informe o status:\n");
                    int status = ler.nextInt();
                    p.atualizarStatus(status);
                }
                else if (dataType == "hora"){
                    System.out.printf("Informe a nova hora:\n");
                    int hora = ler.nextInt();
                    p.setHorarioEntrega(hora);
                }
                else if (dataType == "produto"){
                    ArrayList<String> produtosNome = new ArrayList<String>();
                    System.out.printf("Informe o novo cpf:\n");
                    String palavra = ler.next();
                    produtosNome.add(palavra);
                    ArrayList<Produto> produtos = checkPedido(produtosNome);
                    float newVal = 0;
                    p.setProdutos(produtos);
                    for (Produto pr : produtos){
                        newVal += pr.getValor();
                    }
                    p.setValor(newVal);
                }
                JsonConex.saveState(JsonConex.pedidosPath, pedidos);
                ler.close();
                break;
            }
        }
        ler.close();
    }

    public void listarPedidos(){
        ArrayList<Pedidos> pedidos = JsonConex.dadosPedidos();
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            for (Produto pr : p.getProdutos()){
                System.out.println("Produtos: " + pr.getNome());
            }
            System.out.println("Valor: " + p.getValor() + "\n");
        }
    }
    

    public ArrayList<Produto> pesquisarProdutos(String date1, String date2, int hora1, int hora2){
        ArrayList<Pedidos> pedidos = JsonConex.dadosPedidos();
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        ArrayList<Produto> produtos = new ArrayList<Produto>();

        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            if(filterDate(date1, date2, hora1, hora2, p.getDataPedido(), p.getHorarioPedido())){
                for (Produto pr : p.getProdutos()){
                    produtos.add(pr);
                }
            }
        }
        return produtos;
    }
    
    public boolean filterDate(String date1, String date2, int horaInicial, int horaFinal, String compareDate, int compareHour){
        LocalDate t1 = LocalDate.parse(date1);
        LocalDate t2 = LocalDate.parse(date2);
        LocalDate compareTime = LocalDate.parse(compareDate);
        
        if((compareTime.getYear()>= t1.getYear() & compareTime.getMonthValue()>= t1.getMonthValue() 
                & compareTime.getDayOfMonth() >= t1.getDayOfMonth() & compareHour >= horaInicial) & 
           (compareTime.getYear()<= t1.getYear() & compareTime.getMonthValue() <= t2.getMonthValue() 
                & compareTime.getDayOfMonth() <= t2.getDayOfMonth() & compareHour <= horaFinal)) {
                    return true;    
        }
        else{
            return false;
        }
        
    }
    
    public void getEstatistica(String date1, String date2, int hora1, int hora2){
        ArrayList<Pedidos> pedidos = JsonConex.dadosPedidos();
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        ArrayList<Float> valores = new ArrayList<Float>();
        float mean = 0;
        int sizeMean = 0;
        float desvioPadrao = 0;
     
        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            if(filterDate(date1, date2, hora1, hora2, p.getDataPedido(), p.getHorarioPedido())){
                valores.add(p.getValor());
                mean += p.getValor();
            }
        }
        mean = mean/valores.size();
        for (Float v : valores){
            desvioPadrao = (float)Math.pow((v-mean),2);
        }
        desvioPadrao = desvioPadrao/(valores.size()-1);
        
    }
}

