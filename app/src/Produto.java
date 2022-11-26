import java.util.Calendar;

public class Produto {
    
    ////////////////////// ATRIBUTOS //////////////////////
    private String id;
    private String nome;
    private String descricao;
    private float valor;

    public Produto(String id, String nome, String descricao, Float valor){
        setId(id);
        setNome(nome);
        setDescricao(descricao);
        setValor(valor);
    }


    ////////////////////// MÉTODOS //////////////////////
    @Override
    public String toString(){
        return "|| OBJETO:  "+this.id+"|| Descricao:  "+this.descricao+"|| Valor: " +this.valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

}
