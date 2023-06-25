package com.example.dadosApi;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class Requests {

    public Requests(){}

    public CharacterResponse[] getCharacter(String nome){

        HttpResponse<CharacterResponse[]> response = Unirest.get("https://www.breakingbadapi.com/api/characters?name=" + nome).asObject(CharacterResponse[].class);

        return response.getBody();
    }

    public DeathReq[] getDeath(String nome){

        HttpResponse<DeathReq[]> response = Unirest.get("https://www.breakingbadapi.com/api/death?name="+nome).asObject(DeathReq[].class);

        return response.getBody();
    }

    public EpisodeReq [] getEpisode(Integer ep){

        HttpResponse<EpisodeReq[]> response = Unirest.get("https://www.breakingbadapi.com/api/episodes/"+ep	).asObject(EpisodeReq[].class);

        return response.getBody();
    }

    public QuoteReq[] getQuote(){

        HttpResponse<QuoteReq[]> response = Unirest.get("https://www.breakingbadapi.com/api/quote/random").asObject(QuoteReq[].class);

        return response.getBody();
    }



}