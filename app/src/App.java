import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import org.json.simple.*;;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Sistema s1 = new Sistema();
        s1.cadastrarCliente("Yuri", "12345678900", "12345678", "Cidade Nova");
        s1.cadastrarCliente("Ruan", "12345678911", "12345678", "Jardim Imp");
        s1.listarClientes();

        s1.removerCliente("12345678911");
        s1.listarClientes();
    }
}
