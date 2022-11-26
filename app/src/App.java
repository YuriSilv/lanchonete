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
        /*Sistema sistema = new Sistema();
        sistema.CadastrarCliente("Yuri", "123","38988047122", "Diamantina-MG");
        sistema.CadastrarCliente("Yuri", "123","38988047122", "Diamantina-MG");
        sistema.CadastrarCliente("Juan", "451","38988521562", "Acre-MG");*/
        Calendar tempo = Calendar.getInstance();
        System.out.println(tempo.get(Calendar.HOUR_OF_DAY));
    }
}
