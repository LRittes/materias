package com.example.negocio;

import java.util.List;

import com.example.dadosApi.CharacterResponse;
import com.example.dadosApi.DeathReq;
import com.example.dadosApi.EpisodeReq;
import com.example.dadosApi.QuoteReq;
import com.example.dadosApi.Requests;
import com.example.persistencia.CharacterDAO;
import com.example.persistencia.EpisodeDAO;

public class Sistema {
    
    private Requests requests = new Requests();
    private CharacterDAO characterDAO = new CharacterDAO();
    private EpisodeDAO episodeDAO = new EpisodeDAO();

    public void adicionarCharacter(CharacterResponse c){
        characterDAO.insert(c);
    }
    public void removerCharacter(CharacterResponse c){
        characterDAO.delete(c);
    }
    public List<CharacterResponse> getListaCharacter(){
        return characterDAO.getAll();
    }

    public void adicionarEpisode(EpisodeReq ep){
        episodeDAO.insert(ep);
    }
    public void removerEpisode(EpisodeReq ep){
        episodeDAO.delete(ep);
    }
    public List<EpisodeReq> getListaEpisode(){
        return episodeDAO.getAll();
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
