public class Funcionario extends Usuario{
    private String name;
    private String cpf;
    private final String nivelAcesso = "2";

    public Funcionario(String name, String cpf){
        setCpf(cpf);
        setName(name);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
