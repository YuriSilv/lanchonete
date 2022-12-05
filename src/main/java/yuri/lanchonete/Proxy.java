package yuri.lanchonete;

import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class Proxy{
    //verificar cpf no BD
    List<String> clientesCpf = new ArrayList<String>();
    List<String> funcionariosCpf = new ArrayList<String>(); 
    
    private static int tryCount = 0;
    
    public boolean verificarLogin(String userName, String senha){

        if(userName.equals("juangatao") && senha.equals("123")){
            return true;
        }
        
        if(userName.equals("lordYuri") && senha.equals("senha123")){
            return true;
        }
        //OBS. Se der tempo alterar as exceções para os tipos corretos
        throw new RuntimeException("Usuário ou senha inválido");
    }

    public void verificarDadosCliente(String cpf, String telefone){
        if(cpf.length() != 11 || !(cpf.matches("[0-9]+"))){
            throw new RuntimeException("cpf inválido");
        }

        if(telefone.length() != 8 || !(telefone.matches("[0-9]+"))){
            throw new RuntimeException("número inválido");
        }

        if (clientesCpf.contains(cpf)) {
            throw new RuntimeException("Cliente já cadastrado");
        }
    }

    public void verificarDadosEmpregado(String cpf){
        if(cpf.length() != 11){
            throw new RuntimeException("cpf inválido");
        }

        if (funcionariosCpf.contains(cpf)) {
            throw new RuntimeException("Cliente já cadastrado");
        }
    }
    
    public static void verificarSenha(String senha){
        if(senha.length() < 4){
            throw new RuntimeException("tamanho de senha inválido");
        }
    }
    
    public static boolean validarSenha(String senhaAntiga, String senhaAntigaValida){
        if(!(senhaAntiga.equals(senhaAntigaValida))){
            setTryCount();
            return false;
        }else{
            return true;
        }
    }

    public static int getTryCount() {
        return tryCount;
    }

    public static void setTryCount() {
        Proxy.tryCount++;
    }
    
    
    
}