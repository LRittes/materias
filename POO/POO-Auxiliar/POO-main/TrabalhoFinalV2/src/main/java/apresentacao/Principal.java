package apresentacao;

import excecoes.SelectException;

import java.sql.SQLException;

public class Principal {
    public static void main(String [] args) throws SQLException, SelectException, ClassNotFoundException {
        TelaPrincipal tela = new TelaPrincipal();
        tela.setVisible(true);
    }

}
