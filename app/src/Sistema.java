import java.util.List;
import java.util.ArrayList;

public class Sistema {

    ////////// ATRIBUTOS ////////////////////
    final int numero_funcionarios = 14;
    final int numero_adms = 1;
    
    Funcionario funcionarios[] = new Funcionario[numero_funcionarios];
    Administrador administradores[] = new Administrador[numero_adms];

    List<Cliente> clientes = new ArrayList<Cliente>();
    List<String> clientesCpf = new ArrayList<String>();
    
    //////// METODOS //////////////////////
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
}
