public class Proxy{
    





    public Sistema verificarLogin(String email, String senha){
        /// daí ele vai buscar no JSON um usuario com esses dados e retornar o objeto usuario
        // depois vamo instanciar um objeto da classe sistema usando esse usuario como paramentro
        // Usuario teste = result JSON
        // Sistema s1 = new Sistema(teste);
        // s1.logar();
        // Nesse usuario já vai ter guardado o nivel de acesso dele, pra o sistema decidir suas funcionalidades
        System.out.println("Chegou aqui\n");

        if(email.equals("juangatao")&& senha.equals("123")){
            Funcionario u1 = new Funcionario("Juaan", "123.423", "0");
            Sistema s1 = new Sistema(u1);
            System.out.println("123 logado");
            return s1;

        }
        if(email.equals("yuriCorno")&& senha.equals("senha123")){
            Administrador u1 = new Administrador("Yuri", "134.423", "0");
            Sistema s1 = new Sistema(u1);
            return s1;

        }
        Sistema erro = new Sistema(null);
        return erro;
    }

    public void verificarCadastrar(String email, String senha){
    }



    public void gerarID(){
        
    }
}