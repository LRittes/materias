package persistencia;

import dados.SelectException;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnderecoDAO {
    private static EnderecoDAO instance = null;

    private PreparedStatement selectNewId;
    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;

    public static EnderecoDAO getInstance() throws ClassNotFoundException, SQLException, SelectException{
        if(instance == null){
            instance = new EnderecoDAO();
        }
        return instance;
    }
}
