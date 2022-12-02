public class Administrador extends Funcionario{

    private static final String nivelAcesso = "2";

    public Administrador(String name, String cpf, String nivelAcesso, String senha){
        super(name, cpf, nivelAcesso, senha);
    }
    
    public static String getNivelAcesso() {
        return nivelAcesso;
    }
}
