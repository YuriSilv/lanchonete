public class Administrador extends Funcionario{

    private static final String nivelAcesso = "2";

    public Administrador(String name, String cpf, String nivelAcessoExtra){
        super(name, cpf, nivelAcesso+nivelAcessoExtra);
    }
}
