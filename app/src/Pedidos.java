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
    private String cpf;


    public Pedidos(List<Produto> produtos){
        setProdutos(produtos);

        // Exemplo de manipulacao de datas - Bom pra lembrar depois
        // tempo.set(2022, Calendar.FEBRUARY, 27);
        // setDataPedido();
        // tempo.set(2022, Calendar.FEBRUARY, 28);
        // setDataPedidoCompletado(tempo.getTimeInMillis());

    }

    ////////////////////// MÉTODOS //////////////////////
    
    public void adicionarProduto(Produto novoProduto){
        // if(this.status.equals("Iniciado") || this.status.equals("Iniciado")){
        //     System.out.println("Não é mais possível modifica pedido já está");
            
        // }
            // Daqui pra baixo está funcionando
            produtos.add(novoProduto);
            System.out.println("Test: Novo produto adicionado");
            return;
    }
    public void mostrarPedido(){
        System.out.println(produtos);
    }

    public void atualizarStatus(int estadoAtual) {
        //// Imaginando, por agora, um mundo com 5 status(Ideia incial, pode ser alterado)... Iniciado, preparando, pronto, em transporte e entregue 
        if(estadoAtual==1){
            System.out.println("Pedido já na cadeia de pedidos");
            this.status = "Iniciado"; //Era pra ser SET status, mas to com preguica - Depois mudo
            return;
        }
        if(estadoAtual==2){
            System.out.println("Preparacao do pedido iniciada");
            this.status = "Preparando";
            return;
        }
        if(estadoAtual==3){
            System.out.println("Pedido pronto");
            this.status = "Pronto";
            return;
        }
        if(estadoAtual==4){
            System.out.println("Pedido em transito");
            this.status = "Em transito";
            return;
        }
        if(estadoAtual==5){
            System.out.println("Preparacao do pedido iniciada");
            this.status = "Entregue";
            // this.dataPedidoCompletado();    Em construcao... fazer o salvar do tempo aq -TALVEZ-
            return;
        }
    }






    // GETTERS E SETTERS 

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
