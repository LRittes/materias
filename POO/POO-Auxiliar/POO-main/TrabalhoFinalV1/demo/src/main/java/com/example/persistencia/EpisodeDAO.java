package com.example.persistencia;

import java.util.List;

import com.example.dadosApi.EpisodeReq;

public class EpisodeDAO {

    private ArquivoEpisodeDAO arquivoEpisodeDAO = new ArquivoEpisodeDAO();

    public void insert(EpisodeReq episode){
        arquivoEpisodeDAO.salvaEpisodeArquivo(episode);
    }

    public void delete(EpisodeReq episode){
        List<EpisodeReq> characters = arquivoEpisodeDAO.leEpisodeArquivo();
        int index = 0;
        for(int i = 0 ; i < characters.size() ; i++){
            if(characters.get(i).equals(episode)){
                index = i;
            }
        }
        characters.remove(index);
        arquivoEpisodeDAO.salvaEpisodesArquivo(characters);
    }

    public List<EpisodeReq> getAll(){
        return arquivoEpisodeDAO.leEpisodeArquivo();
    }
    
}
