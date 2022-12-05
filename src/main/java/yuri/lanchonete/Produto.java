package yuri.lanchonete;


public class Produto {
    
    ////////////////////// ATRIBUTOS //////////////////////
    private static int idControl;
    private int id;
    private String nome;
    private String descricao;
    private float valor;

    public Produto(String nome, String descricao, Float valor){
        setId(id);
        setNome(nome);
        setDescricao(descricao);
        setValor(valor);
    }


    ////////////////////// MÉTODOS //////////////////////
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = idControl;
        idControl ++;
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

    public static int getIdControl() {
        return idControl;
    }

    public static void setIdControl(int idControl) {
        Produto.idControl = idControl;
    }
    
    

}
