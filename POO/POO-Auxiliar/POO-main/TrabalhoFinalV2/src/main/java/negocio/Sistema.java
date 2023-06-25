package negocio;


import apresentacao.PersonagemTableModel;
import dadosAPI.Character;
import dadosAPI.*;
import excecoes.DeleteException;
import excecoes.InsertException;
import excecoes.SelectException;
import persistencia.CharacterDAO;
import persistencia.Conexao;
import persistencia.EpisodeDAO;

import java.sql.SQLException;
import java.util.List;

public class Sistema {

    private final Requests requests = new Requests();
    private final CharacterDAO characterDAO;
    private final EpisodeDAO episodeDAO;

    public Sistema(String senha) throws SQLException, SelectException, ClassNotFoundException {
        Conexao.setSenha(senha);
        characterDAO = CharacterDAO.getInstance();
        episodeDAO = EpisodeDAO.getInstance();
    }

    public void adicionarCharacter(CharacterResponse c) throws SelectException, InsertException {
        characterDAO.insert(c);
    }
    public void removerCharacter(CharacterResponse c) throws DeleteException {
        characterDAO.delete(c);
    }
    public List<Character> getListaCharacter() throws SelectException {
        return characterDAO.selectAll();
    }

    public void adicionarEpisode(EpisodeReq ep) throws SelectException, InsertException {
        episodeDAO.insert(ep);
    }
    public void removerEpisode(EpisodeReq ep) throws DeleteException {
        episodeDAO.delete(ep);
    }
    public List<Episode> getListaEpisode() throws SelectException {
        return episodeDAO.selectAll();
    }

    public CharacterResponse[] getCharacter(String nome){
        return requests.getCharacter(nome);
    }
    public EpisodeReq[] getEpisode(Integer ep){
        return requests.getEpisode(ep);
    }
    public DeathReq[] getDeath(String nome){
        return requests.getDeath(nome);
    }
    public QuoteReq[] getQuote(){
        return requests.getQuote();
    }


}
