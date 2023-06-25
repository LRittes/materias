package com.example.dadosApi;

public class DeathReq {
    
    private Integer death_id;
    private String death;
    private String cause;
    private String responsible;
    private String last_words;
    private Integer season;
    private Integer episode;
    private Integer number_of_deaths;

    public String toString() {
        return "ID Morte: " + death_id + "\nMorto(a): " + death + "\nCausa: " + cause + "\nResponsavel: " + responsible + "\nUltimas palavras: " + last_words + "\nTemporada: " + season + "\nEpisodio: " + episode + "\nNumero de mortes: " + number_of_deaths;
    }

    public Integer getDeath_id() {
        return death_id;
    }

    public void setDeath_id(Integer death_id) {
        this.death_id = death_id;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getLast_words() {
        return last_words;
    }

    public void setLast_words(String last_words) {
        this.last_words = last_words;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public Integer getNumber_of_deaths() {
        return number_of_deaths;
    }

    public void setNumber_of_deaths(Integer number_of_deaths) {
        this.number_of_deaths = number_of_deaths;
    }
}


