public class Funcionario extends Usuario{
    private static final String nivelAcesso = "1";
    protected String senha;
    public Funcionario(String name, String cpf, String nivelAcesso, String senha){
        super(name, cpf, nivelAcesso);
        this.senha = senha;
    }

    @Override
    public String getCpf() {
        return cpf;
    }

    @Override
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    public static String getNivelAcesso() {
        return nivelAcesso;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
