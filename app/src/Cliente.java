import java.util.List;
import java.util.ArrayList;

public class Cliente {
    
    ////////////////////// ATRIBUTOS //////////////////////
    private String nome;
    private String telefone;
    private String end;
    //private List ultimosPedidos = new ArrayList<Integer>();


    public Cliente(String nome, String telefone, String end){
        setNome(nome);
        setEnd(end);
        setTelefone(telefone);
    }


    ////////////////////// MÉTODOS //////////////////////
    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
