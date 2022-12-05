package yuri.lanchonete;

import java.io.IOException;
import java.util.ArrayList;

public class Lanchonete {

    public static void main(String[] args) throws IOException {
        Sistema s1 = new Sistema();
        //s1.cadastrarCliente("Yuri", "12345678900", "12345678", "Cidade Nova");
        //s1.cadastrarCliente("Ruan", "12345678911", "12345678", "Jardim Imp");
        //JsonConex connection = new JsonConex();
        
        //Cliente c = new Cliente("Yuri", "12345678910", "12345678", "DTNA");
        
        //connection.dumpCliente("app\\data\\clientes.json", c);
        //s1.logar("lordYuri", "senha123");
        //s1.cadastrarCliente("Jhon", "12345678900", "12345678", "DTNA");
        //s1.cadastrarCliente("Yuri", "12345678900", "12345678", "DTNA");
        //s1.cadastrarCliente("Juan", "00987654321", "87654321", "DTNA");
        //s1.cadastrarCliente("Silvino", "12345678911", "67812345", "MG");
        //s1.editarCliente("12345678911", "nome");
        //s1.listarClientes();
        //s1.listarClientes();
        
        //s1.cadastrarProduto("Hamburguer", "Pão padrão", 1.5F);
        //s1.cadastrarProduto("Coca", "Queijo fatiado", 2.5F);
        //s1.cadastrarProduto("Torta", "", 0.6F);
        //s1.removerProduto("Mussarela");
        
        //s1.editarProduto("Pão de sal", "valor");
        //s1.cadastrarEmpregado("dos Santos", "00456789321", Administrador.getNivelAcesso(), "4040");
        //s1.removerEmpregado("00123456789");
        //s1.alterarSenhaAdm("12345678999");
        //adicionar func de remover, editar e listar
        /*ArrayList<String> p = new ArrayList<String>();
        p.add("Torta");
        s1.cadastrarPedidos("12345678900", p);*/
        //s1.editarPedido("12345678911", 0, "produto");
        s1.listarEmpregados();
    }
}
