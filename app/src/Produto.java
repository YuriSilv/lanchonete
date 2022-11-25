public class Produto {
    
    ////////////////////// ATRIBUTOS //////////////////////
    private String id;
    private String descricao;
    private float valor;
    


    public Produto(String id, String descricao, float valor){
        setDescricao(descricao);
        setId(id);
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
}
