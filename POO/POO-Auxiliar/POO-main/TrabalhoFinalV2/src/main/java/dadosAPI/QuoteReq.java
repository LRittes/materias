package dadosAPI;

public class QuoteReq {
    
    private Integer quote_id;
    private String quote;
    private String author;
    private String series;

    public QuoteReq(){}

    public String toString() {
        return "Author: " + author + "\nID: " + quote_id + "\nFala: " + quote + "\nSerie: " + series ;
    }

    public Integer getQuote_id() {
        return quote_id;
    }
    public void setQuote_id(Integer quote_id) {
        this.quote_id = quote_id;
    }
    public String getQuote() {
        return quote;
    }
    public void setQuote(String quote) {
        this.quote = quote;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getSeries() {
        return series;
    }
    public void setSeries(String series) {
        this.series = series;
    }

}
