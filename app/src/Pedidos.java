import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Pedidos {

    ////////////////////// ATRIBUTOS //////////////////////
    private Calendar dataPedido = Calendar.getInstance();
    private Calendar dataEntrega = Calendar.getInstance();
    private List<Produto> produtos = new ArrayList<Produto>();
    private float valor;


    public Pedidos(List<Produto> produtos){
        setProdutos(produtos);
    }

    ////////////////////// MÉTODOS //////////////////////
    public void mostrarPedidos(){
        System.out.println(produtos);
    }

    public Calendar getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Calendar dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Calendar getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Calendar dataPedido) {
        this.dataPedido = dataPedido;
    }

    public float getValor() {
        for(int i = 0; i < this.produtos.size(); i++){
            this.valor += this.produtos.get(i).getValor();
        }
        return this.valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }
}
