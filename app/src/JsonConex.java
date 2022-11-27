import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

public class JsonConex {

    private FileWriter writeFile = null;

    public JSONArray open(String path){
        File file = new File(path);
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JSONArray jsonArray = new JSONArray(content);
            return jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void dumpCliente(JSONArray jsonDumpArray, String path, Cliente cliente){
        JSONObject jsonFile = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        jsonFile.put("nome", cliente.getNome());
        jsonFile.put("cpf", cliente.getCpf());
        jsonFile.put("telefone", cliente.getTelefone());
        jsonFile.put("endereço", cliente.getCpf());
        jsonArray.put(jsonFile);

        String data = jsonDumpArray.toString();
        try {
            writeFile = new FileWriter(path, false);

            if (data.equals("[]")) {
                writeFile.write(jsonArray.toString());
            }
            else{
                System.out.println(data);
                writeFile.write(data.substring(0, data.length()-1)+","+jsonArray.toString()+"]");
            }

            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            
        }
    }
}
