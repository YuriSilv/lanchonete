package com.yuri.sistema;

import com.yuri.cliente.*;
import com.yuri.empregados.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Sistema {
    private final Connection<Administrador> connectionAdmnistrador = new Connection<>();
    private final Connection<Funcionario> connectionFuncionario = new Connection<>();
    private final Connection<Produto> connectionProduto = new Connection<>();
    private final Connection<Pedidos> connectionPedidos = new Connection<>();
    private final Connection<Cliente> connectionCliente = new Connection<>();
    
    Funcionario funcionarios[] = new Funcionario[15];
    
    String nomes[] = {"Vicente Pires", "Davi Moreira", "Daniela Pires", "Emanuelly Aragão", "Mirella da Cunha",
                      "Evelyn da Luz", "Bernardo Gonçalves", "Pedro Aragão", "Rodrigo Vieira", "Maria Alice", "Gabriel Novaes",
                      "Laura da Paz", "Raquel da Luz", "Bruno Cunha", "Marcela Lopes"};
    
    String cpfs[] = {"41334161046", "54765983080", "21539673090", "43970882095", "53986071032", "05538463028", "30504563025",
                    "42061623018", "56296427077", "30544141083", "21494379023", "57994467040", "60657174017", "98942706037",
                    "64298908054"};

    
    /**
     * Cadastra os 15 colaboradores iniciais
     * @throws IOException 
     */
    public void CadastrarEstaticoColaborador() throws IOException{
        for(int i = 0; i < 15; i++){
            funcionarios[i] = new Funcionario(nomes[i], cpfs[i], "1234", false);
            connectionFuncionario.dump(funcionarios[i], connectionFuncionario.getPathEmpregado());
            funcionarios[i] = null;
        }
    }
    
    public boolean logar(String userName, String senha) throws IOException{
        Funcionario[] funcionarios = listarEmpregados();
        
        for(int i = 0; i < funcionarios.length; i++){
            if(funcionarios[i].getCpf().equals(userName) && funcionarios[i].getSenha().equals(senha)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Cadastra um cliente na base de dados
     * @param nome define o nome do cliente à ser cadastrado
     * @param cpf define o cpf à ser cadastrado
     * @param telefone define o telefone à ser cadastrado
     * @param end define o endereço à ser cadastrado
     * @throws IOException 
     */
    public void cadastrarCliente(String nome, String cpf, String telefone, String end) throws IOException{
        try {
            //proxy.verificarDadosCliente(cpf, telefone);
            Cliente newCliente = new Cliente(nome,cpf,telefone,end);
            connectionCliente.dump(newCliente, connectionCliente.getPathClinte());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Remove um cliente da base de dados
     * @param cpf define o cpf do cliente que será removido
     * @throws IOException 
     */
    public void removerCliente(String cpf) throws IOException{
      
        ArrayList<Cliente> clientes = connectionCliente.dadosJson(connectionCliente.getPathClinte(), Cliente[].class);
        Iterator<Cliente> allClientes = clientes.iterator();
        while (allClientes.hasNext()) {
            Cliente c = allClientes.next();
            if (c.getCpf().equals(cpf)) {
                System.out.println(c.getNome());
                allClientes.remove();
                connectionCliente.saveState(connectionCliente.getPathClinte(), clientes);
            }
        }
    }
    
    /**
     * Permite editar os dados de um cliente
     * @param cpf define o cpf do cliente que será editado
     * @param dataType define o dado que se deseja alterar
     * @param info o novo dado que será passado
     * @throws IOException 
     */
    public void editarCliente(String cpf, String dataType, String info) throws IOException{        
        ArrayList<Cliente> clientes = connectionCliente.dadosJson(connectionCliente.getPathClinte(), Cliente[].class);
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                System.out.println(c.getNome());
                if (null != dataType)switch (dataType) {
                    case "end" -> c.setEndereco(info);
                    case "nome" -> c.setNome(info);
                    case "telefone" -> c.setTelefone(info);
                    case "cpf" -> c.setCpf(info);
                    default -> {
                    }
                }
                connectionCliente.saveState(connectionCliente.getPathClinte(), clientes);
                break;
            }
        }
    }
    
    /**
     * Lista todos os clientes da base de dados
     * @return retorna um array de clientes
     * @throws IOException 
     */
    public Cliente[] listarClientes() throws IOException{
        ArrayList<Cliente> clientes = connectionCliente.dadosJson(connectionCliente.getPathClinte(), Cliente[].class);
        Cliente[] dados = new Cliente[clientes.size()];
        dados = clientes.toArray(dados);
        return dados;
    }
    
    /**
     * Cadastra um empregado (funcionário ou administrador)
     * @param name define o nome do funcionário
     * @param cpf define o cpf do funcionário
     * @param senha define a senha do funcionário
     * @param isADM define se o tipo de funcionário é administrador ou não
     * @throws IOException 
     */
    public void cadastrarEmpregado(String name, String cpf, String senha, boolean isADM) throws IOException{
        try {
            //proxy.verificarDadosEmpregado(cpf);
            if (isADM == false){
                Funcionario newFuncionario = new Funcionario(name, cpf, senha, isADM);
                connectionFuncionario.dump(newFuncionario, connectionFuncionario.getPathEmpregado());
            }
            else{
                Administrador newAdm = new Administrador(name, cpf, senha, isADM);
                connectionAdmnistrador.dump(newAdm, connectionAdmnistrador.getPathEmpregado());
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Remove um empregado da base de dados
     * @param cpf define o cpf do empregado que será removido
     * @throws IOException 
     */
    public void removerEmpregado(String cpf) throws IOException{
        //remover do BD
        ArrayList<Funcionario> users = connectionFuncionario.dadosJson(connectionFuncionario.getPathEmpregado(), Funcionario[].class);
        Iterator<Funcionario> allUsers = users.iterator();
        while (allUsers.hasNext()) {
            Usuario u = allUsers.next();
            if (u.getCpf().equals(cpf)) {
                allUsers.remove();
                connectionFuncionario.saveState(connectionFuncionario.getPathEmpregado(), users);
                break;
            }
        }
    }
    
    /**
     * Permite editar os dados de um empregado
     * @param cpf define o cpf do empregado que será editado
     * @param dataType define o dado que se deseja alterar
     * @param info o novo dado que será passado
     * @throws IOException 
     */
    public void editarEmpregado(String cpf, String dataType, String info) throws IOException{
        ArrayList<Funcionario> users = connectionFuncionario.dadosJson(connectionFuncionario.getPathEmpregado(), Funcionario[].class);
        for (Funcionario f : users) {
            if (f.getCpf().equals(cpf)) {
                if (null != dataType)switch (dataType) {
                    case "senha" -> f.setSenha(info);
                    case "nome" -> f.setNome(info);
                    case "cpf" -> f.setCpf(info);
                    case "toAdm" -> f.setisAdm(true);
                    case "toFunc" -> f.setisAdm(false);
                    default -> {
                    }
                }
                connectionFuncionario.saveState(connectionFuncionario.getPathEmpregado(), users);
                break;
            }
        }
    }
    
    /**
     * Lista todos os empregados da base de dados
     * @return retorna um array de empregados
     * @throws IOException 
     */
    public Funcionario[] listarEmpregados() throws IOException{
        ArrayList<Funcionario> users = connectionFuncionario.dadosJson(connectionFuncionario.getPathEmpregado(), Funcionario[].class);
        Funcionario[] dados = new Funcionario[users.size()];
        dados = users.toArray(dados);
        return dados;
    }
    
    /**
     * Função que verifica o maior ID da base de dados a fim de gerar um id único
     * @param type define o nome do objeto que será criado um id de controle
     * @return retorna o maior ID da base de dados +1
     * @throws IOException 
     */
    public final int defIdUnico(String type) throws IOException{
        if(type.equals(Produto.class.getName())){
            int max = 0;
            ArrayList<Produto> produtos = connectionProduto.dadosJson(connectionProduto.getPathProdutos(), Produto[].class);
            for (Produto p: produtos){
                if(p.getId() > max){
                    max = p.getId();
                }
            }
            return max+1;
            
        }else if (type.equals(Pedidos.class.getName())){
            int max = 0;
            ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
            for (Pedidos p: pedidos){
                if(p.getId() > max){
                    max = p.getId();
                }
            }
            return max+1;
        }else{
            return 0;
        }
    }
    
    /**
     * Cadastra um novo produto na base de dados
     * @param nome define o nome do produto
     * @param descricao define a descrição do produto
     * @param valor define o valor do produto
     * @throws IOException 
     */
    public void cadastrarProduto(String nome, String descricao, float valor) throws IOException{
        try {
            Produto newProduto = new Produto(nome,descricao,valor);
            newProduto.setId(defIdUnico(Produto.class.getName()));
            connectionProduto.dump(newProduto, connectionProduto.getPathProdutos());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Remove um produto da base de dados
     * @param id descreve o id do produto que será removido
     * @throws IOException 
     */
    public void removerProduto(int id) throws IOException{
        ArrayList<Produto> produtos = connectionProduto.dadosJson(connectionProduto.getPathProdutos(), Produto[].class);
        Iterator<Produto> allProduto = produtos.iterator();
        while (allProduto.hasNext()) {
            Produto p = allProduto.next();
            if (p.getId() == id) {
                allProduto.remove();
                connectionProduto.saveState(connectionProduto.getPathProdutos(), produtos);
                break;
            }
        }
    }
    
    /**
     * Edita algum dado sobre o produto
     * @param id se refere ao id do produto à ser editado
     * @param dataType define o tipo de dado que irá alterar, podendo ser "nome", "descrição" e "valor"
     * @param info define a nova informação
     * @throws IOException 
     */
    public void editarProduto(int id, String dataType, String info) throws IOException{
        //acessar no BD e editar
        ArrayList<Produto> produtos = connectionProduto.dadosJson(connectionProduto.getPathProdutos(), Produto[].class);
        for (Produto p : produtos) {
            if (p.getId() == id) {
                if (null != dataType)switch (dataType) {
                    case "nome" -> p.setNome(info);
                    case "descrição" -> p.setDescricao(info);
                    case "valor" -> p.setValor(Float.parseFloat(info));
                    default -> {
                    }
                }
                connectionProduto.saveState(connectionProduto.getPathProdutos(), produtos);
                break;
            }
        }
    }
    
    /**
     *
     * @return retorna um array com todos os produtos da base de dados
     * @throws IOException 
     */
    public Produto[] listarProduto() throws IOException{
        ArrayList<Produto> produtos = connectionProduto.dadosJson(connectionProduto.getPathProdutos(), Produto[].class);
        Produto[] dados = new Produto[produtos.size()];
        dados = produtos.toArray(dados);
        return dados;
    }
    
    /**
     * Cadastra um pedido na base de dados
     * @param cpf cpf do cliente que terá o pedido cadastrado
     * @param produtos lista com os ids dos produtos que serão cadastrados
     * @throws IOException 
     */
    public void cadastrarPedidos(String cpf, int[] produtos) throws IOException{
        try {
            float valor = 0;
            
            ArrayList<Produto> allProdutos = connectionProduto.dadosJson(connectionProduto.getPathProdutos(), Produto[].class);
            ArrayList<Produto> produtosPedido = new ArrayList<>();

            for(Produto p: allProdutos){
                for(int i = 0; i < produtos.length; i++){
                    if(p.getId() == produtos[i]){
                        produtosPedido.add(p);
                        valor += p.getValor();
                    }
                }
            }
            Pedidos newPedido = new Pedidos(valor, cpf);
            newPedido.setId(defIdUnico(Pedidos.class.getName()));
            newPedido.setProdutos(produtosPedido);
            connectionPedidos.dump(newPedido, connectionPedidos.getPathPedidos());

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Remove um pedido da base de dados
     * @param id define o id do pedido que será removido
     * @throws IOException 
     */
    public void removerPedido(int id) throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            if (p.getId() == id) {
                allPedidos.remove();
                connectionPedidos.saveState(connectionPedidos.getPathPedidos(), pedidos);
                break;
            }
        }
    }
    
    /**
     * Permite editar um determinado pedido
     * @param id define o id do pedido que será editado
     * @param dataType define qual dado será alterado, podendo ser o status, cpf, valor ou horarioEntrega
     * @param info define a nova informação
     * @throws IOException 
     */
    public void editarPedido(int id, String dataType, String info) throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        for (Pedidos p : pedidos) {
            if (p.getId() == id) {
                if (null != dataType)switch (dataType) {
                    case "status" -> p.setStatus(info);
                    case "cpf" -> p.setCpf(info);
                    case "horarioEntrega" -> p.setHorarioEntrega(Integer.parseInt(info));
                    default -> {
                    }
                }
                connectionPedidos.saveState(connectionPedidos.getPathPedidos(), pedidos);
                break;
            }
        }
    }
    
    /**
     * Função que permite editar somente os produtos dentro de um pedido e recalcula o valor
     * @param id id do pedido
     * @param idProdutos ids dos novos produtos
     * @throws IOException 
     */
    public void editarProdutoInPedido(int id, int[] idProdutos) throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        ArrayList<Produto> allProdutos = connectionProduto.dadosJson(connectionProduto.getPathProdutos(), Produto[].class);
        ArrayList<Produto> produtos = new ArrayList<>();
        float newValue = 0;
        for (Pedidos p : pedidos) {
            if (p.getId() == id) {
                for(Produto listProdutos: allProdutos){
                    for(int j = 0; j < idProdutos.length; j++){
                        if(idProdutos[j] == listProdutos.getId()){
                            produtos.add(listProdutos);
                            newValue += listProdutos.getValor();
                        }
                    }
                }
                
                p.setProdutos(produtos);
                p.setValorTotal(newValue);
                break;
            }
        }
        connectionPedidos.saveState(connectionPedidos.getPathPedidos(), pedidos);
    }
    
    /**
     * Lista os pedidos da base de dados
     * @return retorna um array com todos os pedidos
     * @throws IOException 
     */
    public Pedidos[] listarPedidos() throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        Pedidos[] dados = new Pedidos[pedidos.size()];
        dados = pedidos.toArray(dados);
        return dados;
    }
    
    /**
     * Filtra a data em um intervalo de tempo
     * @param date1 data inicial
     * @param date2 data final
     * @param horaInicial horário inicial
     * @param horaFinal horário final
     * @param compareDate define a data que será comparada, ou seja, a passada pelo usuário
     * @param compareHour define a hora que será comparada, ou seja, a passada pelo usuário
     * @return retorna se a data passada está no intervalo(true) ou não(false)
     */
    public boolean filterDate(String date1, String date2, int horaInicial, int horaFinal, String compareDate, int compareHour){
        LocalDate t1 = LocalDate.parse(date1);
        LocalDate t2 = LocalDate.parse(date2);
        LocalDate compareTime = LocalDate.parse(compareDate);
        
        return (compareTime.getYear()>= t1.getYear() & compareTime.getMonthValue()>= t1.getMonthValue() 
                & compareTime.getDayOfMonth() >= t1.getDayOfMonth() & compareHour >= horaInicial) &
                (compareTime.getYear()<= t1.getYear() & compareTime.getMonthValue() <= t2.getMonthValue()
                & compareTime.getDayOfMonth() <= t2.getDayOfMonth() & compareHour <= horaFinal);
        
    }
    
    /**
     * Pesquisa um produto vendido em um intervalo de tempo
     * @param date1 data inicial
     * @param date2 data final
     * @param hora1 horário inicial
     * @param hora2 horário final
     * @param cpf cpf do cliente que será pesquisado
     * @return retorna um ArrayList com todos os produtos vendidos para um determinado cliente
     * @throws IOException 
     */
    public ArrayList<Produto> pesquisarProdutos(String date1, String date2, int hora1, int hora2, String cpf) throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        ArrayList<Produto> produtos = new ArrayList<>();

        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            if (p.getCpf().equals(cpf)){
                if(filterDate(date1, date2, hora1, hora2, p.getDataPedido(), p.getHorarioPedido())){
                    for (Produto pr : p.getProdutos()){
                        produtos.add(pr);
                    }
                }
            }
        }
        return produtos;
    }
    
    /**
     * Pesquisa os pedidos de um cliente em um intervalo de tempo
     * @param cpf cpf do cliente que será pesquisado
     * @param date1 data inicial
     * @param date2 data final
     * @param hora1 hora inicial
     * @param hora2 hora final
     * @return retorna um ArrayList com todos os pedidos vendidos para um determinado cliente
     * @throws IOException 
     */
    public Pedidos[] getClientePedidos(String cpf, String date1, String date2, int hora1, int hora2) throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        ArrayList<Cliente> clientes = connectionCliente.dadosJson(connectionCliente.getPathClinte(), Cliente[].class);
        ArrayList<Pedidos> pedidosCliente = new ArrayList<>();
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                for(Pedidos p: pedidos){
                    if(p.getCpf().equals(cpf)){
                        pedidosCliente.add(p);
                    }
                }
                break;
            }
        }
        Pedidos[] dados = new Pedidos[pedidosCliente.size()];
        dados = pedidosCliente.toArray(dados);
        return dados;
    }
    
    /**
     * Define as estatística em um determinado período de tempo
     * @param date1 data inicial
     * @param date2 data final
     * @param hora1 hora inicial
     * @param hora2 hora final
     * @return retorna a array com a média e o desvio padrão, respectivamente
     * @throws IOException 
     */
    public double[] getEstatistica(String date1, String date2, int hora1, int hora2) throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        ArrayList<Float> valores = new ArrayList<>();
        
        float mean = 0;
        float desvioPadrao = 0;
     
        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            if(filterDate(date1, date2, hora1, hora2, p.getDataPedido(), p.getHorarioPedido())){
                valores.add(p.getValorTotal());
                mean += p.getValorTotal();
            }
        }
        mean = mean/valores.size();
        for (Float v : valores){
            desvioPadrao = (float)Math.pow((v-mean),2);
        }
        desvioPadrao = desvioPadrao/(valores.size()-1);
        double[] dados = {mean, desvioPadrao};
        return dados;
    }
}