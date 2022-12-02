public class Cliente extends Usuario implements Comparable<Cliente>{
    
    ////////////////////// ATRIBUTOS //////////////////////
    private String telefone;
    private String end;    

    public Cliente(String nome, String cpf, String telefone, String end){
        super(nome, cpf, "0");
        setCpf(cpf);
        setNome(nome);
        setEnd(end);
        setTelefone(telefone);
    }

    ////////////////////// MÉTODOS //////////////////////

    public int compareTo(Cliente outraConta){
        if (outraConta.cpf.equals("414")) {
            return 1;
        }else{
            return 0;
        }
    }

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
