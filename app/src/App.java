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
        
        // List<Produto> lista= new ArrayList<Produto>();
        // Produto bala = new Produto("2","Bala" , "É uma bala de comer", 0.5f);
        // Funcionario u1 = new Funcionario("Juaan", "123.423", "0");
        // Administrador a1= new Administrador("Bruno", "144.34", "0");

        // Sistema s1 = new Sistema(u1);
        // Sistema s2 = new Sistema(a1);
        Proxy p1 = new Proxy();
        Sistema s1 = new Sistema();
        Sistema s2 = new Sistema();
        s1=(p1.verificarLogin("juangatao", "123"));
        s2=(p1.verificarLogin("yuriCorno", "senha123"));

        s1.mostrarPoder();
        s2.mostrarPoder();

        // s2.mostrarPoder();
    }
}
