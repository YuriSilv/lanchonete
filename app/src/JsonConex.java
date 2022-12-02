import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Iterator;

public class JsonConex {

    public static String clientePath = "src\\main\\java\\data\\clientes.json";
    public static String produtoPath = "src\\main\\java\\data\\produtos.json";
    public static String empregadoPath = "src\\main\\java\\data\\empregados.json";
    
    // abre a base de dados e retorna os dados
    public static ArrayList<String> open(String path){
        File file = new File(path);
        ArrayList<String> dados = new ArrayList<String>();
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONArray jsonArray = new JSONArray(content);
            for(int i = 0; i < jsonArray.length(); i++){
                dados.add(jsonArray.get(i).toString());
            }
            return dados;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<Cliente> dadosClientes(){
        File file = new File(clientePath);
        Gson gson = new Gson();
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            ArrayList<Cliente> dados = gson.fromJson(content, new TypeToken<ArrayList<Cliente>>() {}.getType());
            return dados;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void dump(Cliente cliente) throws IOException{
        FileWriter writeFile = null;
        ArrayList<String> dados = open(clientePath);
        Gson gson = new Gson();
        String jsonDados = gson.toJson(cliente);
        dados.add(jsonDados);
        try {
            writeFile = new FileWriter(clientePath, false);
            writeFile.write(dados.toString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            gson = null;
            writeFile.close();
        }
    }
    
    public static void saveState(String path, ArrayList dados) throws IOException{
        FileWriter writeFile = null;
        Gson gson = new Gson();
        String jsonDados = gson.toJson(dados);
        try {
            writeFile = new FileWriter(path, false);
            writeFile.write(jsonDados);
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            writeFile.close();
        }
    }
    
    public static Cliente SearchCliente(String cpf) throws IOException{
        File file = new File(clientePath);

        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            Gson gson = new Gson();
            ArrayList<Cliente> outputList = gson.fromJson(content, new TypeToken<ArrayList<Cliente>>() {}.getType());
            for(Cliente c : outputList){
                if(c.getCpf().equals(cpf)){
                    return c;
                }
            }
            throw new RuntimeException("cliente não encontrado");
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.getMessage();
        } finally{
     
        }
        return null;
    }
    
    /*----------------------------------------------------------------------------------------------------------*/
    
    public static void dump(Produto produto) throws IOException{
        FileWriter writeFile = null;
        ArrayList<String> dados = open(produtoPath);
        Gson gson = new Gson();
        String jsonDados = gson.toJson(produto);
        dados.add(jsonDados);
        try {
            writeFile = new FileWriter(produtoPath, false);
            writeFile.write(dados.toString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            gson = null;
            writeFile.close();
        }
    }    
    
    public static ArrayList<Produto> dadosProdutos(){
        File file = new File(produtoPath);
        Gson gson = new Gson();
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            ArrayList<Produto> dados = gson.fromJson(content, new TypeToken<ArrayList<Produto>>() {}.getType());
            return dados;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /*----------------------------------------------------------------------------------------------------------*/
    
    public static void dump(Usuario empregado) throws IOException{
        FileWriter writeFile = null;
        ArrayList<String> dados = open(empregadoPath);
        Gson gson = new Gson();
        String jsonDados = gson.toJson(empregado);
        dados.add(jsonDados);
        try {
            writeFile = new FileWriter(empregadoPath, false);
            writeFile.write(dados.toString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            gson = null;
            writeFile.close();
        }
    }    
    
    public static ArrayList<Funcionario> dadosEmpregados(){
        File file = new File(empregadoPath);
        Gson gson = new Gson();
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            ArrayList<Funcionario> dados = gson.fromJson(content, new TypeToken<ArrayList<Funcionario>>() {}.getType());
            return dados;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
