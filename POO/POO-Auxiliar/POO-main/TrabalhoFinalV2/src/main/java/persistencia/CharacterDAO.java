package persistencia;

import dadosAPI.Character;
import dadosAPI.CharacterResponse;
import excecoes.DeleteException;
import excecoes.InsertException;
import excecoes.SelectException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharacterDAO {

    private static CharacterDAO instance = null;

    private PreparedStatement selectNewId;
    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement selectAll;

    public static CharacterDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
        if(instance == null){
            instance = new CharacterDAO();
        }
        return instance;
    }

    private CharacterDAO() throws ClassNotFoundException, SQLException, SelectException{
        Connection conexao = Conexao.getConexao();
        selectNewId = conexao.prepareStatement("select nextVal('id_char')");
        insert = conexao.prepareStatement("insert into character values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        select = conexao.prepareStatement("select * from character where id_character = ?");
        delete = conexao.prepareStatement("delete from character where char_id = ?");
        selectAll = conexao.prepareStatement("select * from character");

    }

    private int selectNewId() throws SelectException{
        try{
            ResultSet rs = selectNewId.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SelectException("Erro ao buscar novo id da tabela character");
        }
        return 0;
    }

    public void insert(CharacterResponse character) throws InsertException, SelectException{
        try {
            insert.setInt(1, selectNewId());
            insert.setInt(2, character.getChar_id());
            insert.setString(3, character.getName());
            insert.setString(4, character.getBirthday());
            insert.setString(5, Arrays.toString(character.getOccupation()));
            insert.setString(6, character.getImg());
            insert.setString(7, character.getStatus());
            insert.setString(8, character.getNickname());
            insert.setString(9, Arrays.toString(character.getAppearance()));
            insert.setString(10, character.getPortrayed());
            insert.setString(11, character.getCategory());
            insert.setString(12, Arrays.toString(character.getBetter_call_saul_appearance()));
            insert.executeUpdate();
        } catch (SQLException e) {
            throw new InsertException("Erro ao inserir personagem!!");
        }
    }

    public Character select(int character) throws SelectException{
        try {
            select.setInt(1, character);
            ResultSet rs = select.executeQuery();
            if(rs.next()){
                int id = rs.getInt(1);
                Integer char_id = rs.getInt(2);
                String name = rs.getString(3);
                String birthday = rs.getString(4);
                String occupation = rs.getString(5);
                String img = rs.getString(6);
                String status = rs.getString(7);
                String nickname = rs.getString(8);
                String appearance = rs.getString(9);
                String portrayed = rs.getString(10);
                String category = rs.getString(11);
                String better_call_saul_appearance = rs.getString(12);
                return new Character(id, char_id,name,birthday,occupation,img,status,nickname,appearance,portrayed,category,better_call_saul_appearance);
            }
        } catch (SQLException e) {
            throw new SelectException("Erro buscar personagem!!");
        }
        return null;
    }

    public void delete(CharacterResponse character) throws DeleteException {
        try{
            delete.setInt(1, character.getChar_id());
            delete.executeUpdate();
        } catch (SQLException e) {
            throw new DeleteException("Erro ao deletar personagem!!");
        }

    }

    public List<Character> selectAll() throws SelectException{

        List<Character> characters = new ArrayList<Character>();

        try{
            ResultSet rs = selectAll.executeQuery();
            while(rs.next()){
                Character personagem = new Character();
                personagem.setId(rs.getInt(1));
                personagem.setChar_id(rs.getInt(2));
                personagem.setName(rs.getString(3));
                personagem.setBirthday(rs.getString(4));
                personagem.setOccupation(rs.getString(5));
                personagem.setImg(rs.getString(6));
                personagem.setStatus(rs.getString(7));
                personagem.setNickname(rs.getString(8));
                personagem.setAppearance(rs.getString(9));
                personagem.setPortrayed(rs.getString(10));
                personagem.setCategory(rs.getString(11));
                personagem.setBetter_call_saul_appearance(rs.getString(12));

                characters.add(personagem);
            }
        } catch (SQLException e) {
            throw  new SelectException("Erro ao buscar episodios!!");
        }
        return characters;
    }

}
