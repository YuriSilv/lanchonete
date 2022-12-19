package com.yuri.sistema;

import com.yuri.cliente.*;
import com.yuri.empregados.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;

public class Sistema {
    private final Connection<Administrador> connectionAdmnistrador = new Connection<>();
    private final Connection<Funcionario> connectionFuncionario = new Connection<>();
    private final Connection<Produto> connectionProduto = new Connection<>();
    private final Connection<Pedidos> connectionPedidos = new Connection<>();
    private final Connection<Cliente> connectionCliente = new Connection<>();
    private final Connection<Extrato> connectionExtrato = new Connection<>();
    protected static int contadorCliente;
    private static boolean isAdm;
    private static String userLogged;
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
    
    /**
     * Permite logar no sistema e setar se é um ADM ou funcionário usando o sistema
     * @param userName nome de usuário(cpf)
     * @param senha senha
     * @return se os dados estiverem corretos, retorna true
     * @throws IOException 
     */
    public boolean logar(String userName, String senha) throws IOException{
        Funcionario[] funcionarios = listarEmpregados();
        Proxy p1 = new Proxy();
        String senhaCodificada = p1.codificaSenha(senha);
        for(int i = 0; i < funcionarios.length; i++){
            if(funcionarios[i].getCpf().equals(userName) && funcionarios[i].getSenha().equals(senhaCodificada)){
                if(funcionarios[i].getIsAdm()){
                    setIsAdm(true);
                }else{
                    setIsAdm(false);
                }
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
            contadorCliente+=1;
            Cliente.setContador();
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
                return;
            }
        }
        throw new RuntimeException();
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
                return;
            }
        }
        throw new RuntimeException();
    }
    
    /**
     * Lista todos os clientes da base de dados
     * @return retorna um array de clientes
     * @throws IOException 
     */
    public Cliente[] listarClientes() throws IOException{
        ArrayList<Cliente> clientes = connectionCliente.dadosJson(connectionCliente.getPathClinte(), Cliente[].class);
        Collections.sort(clientes, new ClienteComparator());
        Cliente[] dados = new Cliente[clientes.size()];
        dados = clientes.toArray(dados);
        return dados;
    }
    
    /**
     * Permite pesquisar um cliente específico na base de dados
     * @param cpf define o cpf do cliente a ser pesquisado
     * @return  retorna um objeto do tipo cliente
     * @throws IOException 
     */
    public Cliente pesquisarCliente(String cpf) throws IOException{
        ArrayList<Cliente> clientes = connectionCliente.dadosJson(connectionCliente.getPathClinte(), Cliente[].class);
        Collections.sort(clientes, new ClienteComparator());
        for(Cliente c: clientes){
            if(cpf.equals(c.getCpf())){
                return c;
            }
        }
        return null;
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
            Proxy p1 = new Proxy();
            String senhaCodificada = p1.codificaSenha(senha);
            //proxy.verificarDadosEmpregado(cpf);
            if (isADM == false){
                Funcionario newFuncionario = new Funcionario(name, cpf, senhaCodificada, isADM);
                connectionFuncionario.dump(newFuncionario, connectionFuncionario.getPathEmpregado());
            }
            else{
                Administrador newAdm = new Administrador(name, cpf, senhaCodificada, isADM);
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
                return;
            }
        }
        throw new RuntimeException();
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
        Proxy p1 = new Proxy();

        for (Funcionario f : users) {
            if (f.getCpf().equals(cpf)) {
                if (null != dataType){
                    switch (dataType) {
                    case "senha" -> f.setSenha(p1.codificaSenha(info));
                    case "nome" -> f.setNome(info);
                    case "cpf" -> f.setCpf(info);
                    case "toAdm" -> f.setisAdm(true);
                    case "toFunc" -> f.setisAdm(false);
              
                default -> {
                    }
                
                }
            }
                connectionFuncionario.saveState(connectionFuncionario.getPathEmpregado(), users);
                return;
            }
        }
        throw new RuntimeException();
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
     * Lista os Produtos na base de dados e os retorna
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
            
            Cliente c = pesquisarCliente(cpf);
            Extrato e = new Extrato(valor, cpf, c.getNome());
            e.setId(newPedido.getId());
            gerarExtrato(e);
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
        ArrayList<Extrato> extratos = connectionExtrato.dadosJson(connectionExtrato.getPathExtratos(), Extrato[].class);
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        
        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            if (p.getId() == id) {
                removerExtrato(id);
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
                connectionPedidos.saveState(connectionPedidos.getPathPedidos(), pedidos);
                return;
            }
        }
        throw new RuntimeException();
    }
    
    /**
     * Lista os pedidos da base de dados
     * @return retorna um array com todos os pedidos
     * @throws IOException 
     */
    public Pedidos[] listarPedidos() throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        Collections.sort(pedidos, new PedidoComparator());
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
    public boolean filterDate(String date1, String date2, int horaInicial, int horaFinal, Calendar compareDate, int compareHour){
        String[] stringArray = date1.split("-");

        Calendar dataInicial= Calendar.getInstance();
        dataInicial.clear();
        dataInicial.set(Integer.parseInt(stringArray[0]), Integer.parseInt(stringArray[1])-1, Integer.parseInt(stringArray[2]), horaInicial, 0, 0);
        String[] stringArrayFinal = date2.split("-");
        Calendar dataFinal= Calendar.getInstance();
        dataFinal.clear();
        dataFinal.set(Integer.parseInt(stringArrayFinal[0]), Integer.parseInt(stringArrayFinal[1])-1, Integer.parseInt(stringArrayFinal[2]), horaFinal, 0, 0);
        
        return compareDate.getTimeInMillis()>= dataInicial.getTimeInMillis() && compareDate.getTimeInMillis()<= dataFinal.getTimeInMillis();
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
        Collections.sort(pedidos, new PedidoComparator());
        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            if (p.getCpf().equals(cpf)){
                if(filterDate(date1, date2, hora1, hora2, p.getDataTesteCalendar(), p.getHorarioPedido())){
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
                            if(filterDate(date1, date2,hora1,hora2, 
                                p.getDataTesteCalendar(), p.getHorarioPedido())){
                                pedidosCliente.add(p);
                        }
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
    public float[] getEstatistica(String date1, String date2, int hora1, int hora2) throws IOException{
        ArrayList<Pedidos> pedidos = connectionPedidos.dadosJson(connectionPedidos.getPathPedidos(), Pedidos[].class);
        Iterator<Pedidos> allPedidos = pedidos.iterator();
        ArrayList<Float> valores = new ArrayList<>();
        
        float mean = 0;
        float desvioPadrao = 0;
  
        while (allPedidos.hasNext()) {
            Pedidos p = allPedidos.next();
            
            if(filterDate(date1, date2,hora1,hora2, 
                                p.getDataTesteCalendar(), p.getHorarioPedido())){
                valores.add(p.getValorTotal());
                mean += p.getValorTotal();
            }
        }
        if(valores.isEmpty()) return null;
        mean = mean/valores.size();
        for (Float v : valores){
            desvioPadrao = (float)Math.pow((v-mean),2);
        }
        desvioPadrao = desvioPadrao/(valores.size()-1);
        float[] dados = {mean, desvioPadrao};
        return dados;
    }
    
    /**
     * Salva um extrato gerado no BD
     * @param e objeto extrato
     * @throws IOException 
     */
    public void gerarExtrato(Extrato e) throws IOException{
        connectionExtrato.dump(e, connectionExtrato.getPathExtratos());
    }
    
    /**
     * Remove o Extrato do cliente
     * @param id id do extrato
     * @throws IOException 
     */
    public void removerExtrato(int id) throws IOException{
        ArrayList<Extrato> extratos = connectionExtrato.dadosJson(connectionExtrato.getPathExtratos(), Extrato[].class);
        Iterator<Extrato> allExtratos = extratos.iterator();
        
        while (allExtratos.hasNext()) {
            Extrato e = allExtratos.next();
            if (e.getId() == id) {
                allExtratos.remove();
                connectionExtrato.saveState(connectionExtrato.getPathExtratos(), extratos);
                break;
            }
        }
    }
    
    /**
     * Lista os extratos na base de dados e os retorna
     * @return retorna uma lista de extratos
     * @throws IOException 
     */
    public Extrato[] listarExtrato() throws IOException{
        ArrayList<Extrato> extratos = connectionExtrato.dadosJson(connectionExtrato.getPathExtratos(), Extrato[].class);
        Extrato[] dados = new Extrato[extratos.size()];
        dados = extratos.toArray(dados);
        return dados;
    }
    
    @Override
    public String toString() {
        return "Sistema{" + "connectionAdmnistrador=" + connectionAdmnistrador + ", connectionFuncionario=" + connectionFuncionario + ", connectionProduto=" + connectionProduto + ", connectionPedidos=" + connectionPedidos + ", connectionCliente=" + connectionCliente + ", connectionExtrato=" + connectionExtrato + ", contadorCliente=" + contadorCliente + '}';
    }
    
    /**
     * 
     * @return retorna se é ou não ADM
     */
    public static boolean getIsAdm() {
        return isAdm;
    }
    
    /**
     * 
     * @param isAdm define se é ou não ADM
     */
    public static void setIsAdm(boolean isAdm) {
        Sistema.isAdm = isAdm;
    }
    
    /**
     * Contador estatíco das instâncias de cliente
     * @return Retorna quantos clientes foram criados estatícamente
     */
    public static int contadorCliente(){
        return Cliente.getContador();
    }
    
    /**
     * 
     * @return retorna o CPF do usuário que logou
     */
    public static String getUserLogged() {
        return userLogged;
    }
    
    /**
     * 
     * @param userLogged define o CPF de quem logou
     */
    public static void setUserLogged(String userLogged) {
        Sistema.userLogged = userLogged;
    }
    
}
