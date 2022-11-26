import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
public class Pedidos {

    ////////////////////// ATRIBUTOS //////////////////////
    private long dataPedido;


    //////teste
    private long dataPedidoCompletado;
    
    //////Final teste


    private int horarioPedido;
    private List<Produto> produtos = new ArrayList<Produto>();
    private float valor;
    private String status;
    private Calendar tempo = Calendar.getInstance();


    public Pedidos(List<Produto> produtos){
        setProdutos(produtos);


        tempo.set(2022, Calendar.FEBRUARY, 27);
        setDataPedido();
        tempo.set(2022, Calendar.FEBRUARY, 28);
        setDataPedidoCompletado(tempo.getTimeInMillis());

    }

    ////////////////////// MÉTODOS //////////////////////
    public void mostrarPedidos(){
        System.out.println(produtos);
    }

    public long getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(){
        this.dataPedido = tempo.getTimeInMillis();
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

    /**
     * @return long return the dataPedidoCompletado
     */
    public long getDataPedidoCompletado() {
        return dataPedidoCompletado;
    }

    /**
     * @param dataPedidoCompletado the dataPedidoCompletado to set
     */
    public void setDataPedidoCompletado(long dataPedidoCompletado) {
        this.dataPedidoCompletado = dataPedidoCompletado;
    }

    /**
     * @return int return the horarioPedido
     */
    public int getHorarioPedido() {
        return horarioPedido;
    }

    /**
     * @param horarioPedido the horarioPedido to set
     */
    public void setHorarioPedido(int horarioPedido) {
        this.horarioPedido = horarioPedido;
    }

    /**
     * @return String return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return Calendar return the tempo
     */
    public Calendar getTempo() {
        return tempo;
    }

    /**
     * @param tempo the tempo to set
     */
    public void setTempo(Calendar tempo) {
        this.tempo = tempo;
    }

}
