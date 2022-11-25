import java.util.ArrayList;
import java.util.List;

//import org.json.simple.*;;

public class App {
    public static void main(String[] args) throws Exception {
        Produto prod1 = new Produto("1", "Cachorro quente", 2.50F);
        Produto prod2 = new Produto("2", "Coca", 0.50F);

        List<Produto> pedidos = new ArrayList<Produto>();
        pedidos.add(prod2);
        pedidos.add(prod1);

        // Pedidos p = new Pedidos(pedidos);

        System.out.println(pedidos);
        // p.mostrarPedidos();
    }
}
