public class Funcionario extends Usuario{
    private String name;
    private String cpf;
    private static String nivelAcesso = "1";
    public Funcionario(String name, String cpf, String acessoExtra){
        super(name, cpf, nivelAcesso+acessoExtra);
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


    public String getNivelAcesso(){
        return this.nivelAcesso;
    }
}
