public class Video extends Arquivo {
    Integer qualidade;

    public Video(String nome) {
        super(nome + ".mp4");
    }

    public Integer getQualidade() {
        return qualidade;
    }

    public void setQualidade(Integer qualidade) {
        this.qualidade = qualidade;
    }

    @Override
    public String toString() {
        return "\t" + this.getNome() + "\n\tQualidade: " + this.getQualidade() + "p";
    }
}
