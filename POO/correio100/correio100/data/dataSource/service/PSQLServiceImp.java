package data.dataSource.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class PSQLServiceImp implements Service {

    Connection conn;

    public PSQLServiceImp() {
        try {
            // Step 1: Carrega o driver JDBC.
            Class.forName("org.postgresql.Driver");
            // Step 2: Estabelece a conexão com o BD.
            String url = "jdbc:postgresql://localhost:5432/correio";
            String user = "postgres";
            String pass = "udesc";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException c) {
            System.err.println("Driver não encontrado");
        } catch (SQLException e) {
            System.err.println("Não foi possível conectar");
        }
    }

    @Override
    public Map<String, Object> get(String path) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void put(Object obj) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Object obj) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Object obj) {
        // TODO Auto-generated method stub

    }
}
