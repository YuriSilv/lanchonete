import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import com.google.gson.Gson;

//import org.json.simple.*;
 

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        String json = "{\"cpf\":12345678900,\"nome\":\"Victor\",\"endereço\":\"dtna\"}";
		
		// --- transformando em Objeto Java --- //
		Gson gson = new Gson(); // conversor
		Cliente objCliente = gson.fromJson(json, Cliente.class);
		
		// exibindo dados em Java //
		System.out.println( objCliente.getNome() );
		System.out.println( objCliente.getCpf() );
		System.out.println( objCliente.getEnd());
    }
}
