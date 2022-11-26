import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class Cliente extends Usuario{
    
    ////////////////////// ATRIBUTOS //////////////////////
    private String nome;
    private String telefone;
    private String end;
    private String cpf;
    private List<Pedidos> pedidos = new ArrayList<Pedidos>();
    //private final String id = UUID.randomUUID().toString();
    //private List ultimosPedidos = new ArrayList<Integer>();


    public Cliente(String nome, String cpf, String telefone, String end){
        super(nome, cpf, end);
        setCpf(cpf);
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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
