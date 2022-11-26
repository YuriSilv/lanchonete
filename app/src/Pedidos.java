import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

public class Pedidos {

    ////////////////////// ATRIBUTOS //////////////////////
    private Date dataPedido;
    private int horarioPedido;
    private List<Produto> produtos = new ArrayList<Produto>();
    private float valor;
    private String status;
    private Calendar tempo = Calendar.getInstance();


    public Pedidos(List<Produto> produtos, Date dataPedido){
        setProdutos(produtos);
        setDataPedido();
    }

    ////////////////////// MÉTODOS //////////////////////
    public void mostrarPedidos(){
        System.out.println(produtos);
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(){
        this.dataPedido = tempo.getTime();
        this.horarioPedido = tempo.get(Calendar.HOUR_OF_DAY);
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

    public void atualizarStatus() {
        int tempoAtual = tempo.get(Calendar.HOUR_OF_DAY);

        if (tempoAtual == horarioPedido){
            this.status = "Preparando";
        }else if(tempoAtual+1 == horarioPedido){
            this.status = "Pronto";
        }
    }
}
