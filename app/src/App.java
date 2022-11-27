import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import org.json.simple.*;
 

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Sistema s1 = new Sistema();
        //s1.cadastrarCliente("Yuri", "12345678900", "12345678", "Cidade Nova");
        //s1.cadastrarCliente("Ruan", "12345678911", "12345678", "Jardim Imp");
        //JsonConex connection = new JsonConex();
        
        //Cliente c = new Cliente("Yuri", "12345678910", "12345678", "DTNA");
        
        //connection.dumpCliente("app\\data\\clientes.json", c);
        //s1.logar("lordYuri", "senha123");
        s1.cadastrarCliente("Yuri", "12345678910", "12345678", "DTNA");
    }
}
