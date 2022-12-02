public abstract class Usuario {
    protected String nome;
    protected String cpf;
    protected String nivelAcesso;

    public Usuario(String nome, String cpf, String nivelAcesso){
        this.nome=nome;
        this.cpf= cpf;
        this.nivelAcesso = nivelAcesso;
    }
    
    public abstract String getNome();
    public abstract void setNome(String nome);

    public abstract String getCpf();
    public abstract void setCpf(String cpf);
    
}
