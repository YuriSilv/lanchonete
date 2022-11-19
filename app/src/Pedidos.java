import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedidos {

    ////////////////////// ATRIBUTOS //////////////////////
    private Date dataPedido = new Date();
    private Date dataEntrega = new Date();
    private List produtos = new ArrayList<Produto>();
    private float valor;


    public Pedidos(List produtos){
        setProdutos(produtos);
    }


    ////////////////////// MÉTODOS //////////////////////
    public void mostrarPedidos(){
        System.out.println(produtos.get(1));
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setProdutos(List produtos) {
        this.produtos = produtos;
    }

    public List getProdutos() {
        return produtos;
    }
}
