public class Administrador extends Funcionario{

    private static final String nivelAcesso = "2";
    private String userName;
    private String senha;

    public Administrador(String name, String cpf, String nivelAcesso, String userName, String senha){
        super(name, cpf, nivelAcesso);
    }
    
    public static String nivelAcesso() {
        return nivelAcesso;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }
    
}
