package com.example.persistencia;

import com.example.dadosApi.EpisodeReq;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArquivoEpisodeDAO{

    private final String caminho = "demo/src/main/java/com/example/persistencia/files/episode.csv";
    private static EditorTexto arquivo = new EditorTexto();

    private String toCSV(EpisodeReq episodeReq){
        String p = "";

        p+= episodeReq.getEpisode_id() + "; ";
        p+= episodeReq.getTitle() + "; ";
        p+= episodeReq.getSeason() + "; ";
        p+= episodeReq.getEpisode() + "; ";
        p+= episodeReq.getAir_date() + "; ";
        p+= Arrays.toString(episodeReq.getCharacters()) + "; ";
        p+= episodeReq.getSeries() + ";";

        return p;
    }

    private EpisodeReq fromCSV (String linhaCSV){

        String[] atributos = linhaCSV.split(";");

        EpisodeReq episodeReq = new EpisodeReq();
        episodeReq.setEpisode_id(Integer.valueOf(atributos[0]));
        episodeReq.setTitle(atributos[1]);
        episodeReq.setSeason(atributos[2]);
        episodeReq.setEpisode(atributos[3]);
        episodeReq.setAir_date(atributos[4]);
        episodeReq.setCharacters(atributos[5].split(","));
        episodeReq.setSeries(atributos[6]);

        return episodeReq;
    }

    private List<String> listaEpisodeToString(List<EpisodeReq> episodeReq){

        List<String> arquivo = new LinkedList<String>();
        for(EpisodeReq episode : episodeReq){
            arquivo.add(toCSV(episode));
        }

        return arquivo;
    }

    private List<EpisodeReq> stringToListaEpisode(List<String> arquivo){
        List<EpisodeReq> episode = new LinkedList<EpisodeReq>();
        for(String linha : arquivo){
            episode.add(fromCSV(linha));
        }
        return episode;
    }

    public List<EpisodeReq> leEpisodeArquivo(){
        return stringToListaEpisode(arquivo.leTexto(caminho));
    }

    public void salvaEpisodesArquivo(List<EpisodeReq> episode){
        arquivo.gravaTexto(caminho, listaEpisodeToString(episode));
    }

    public void salvaEpisodeArquivo(EpisodeReq episode){
        arquivo.gravaTexto(caminho, toCSV(episode));
    }

}
