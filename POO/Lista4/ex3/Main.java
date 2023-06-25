public class Main {
    public static void main(String[] args) {

        SistemaArquivo sistema = new SistemaArquivo();
        sistema.criarDiretorio("Documentos");
        sistema.criarDiretorio("Download");

        sistema.criarDocumento("Treino", "Documentos", "Segunda - Peito/ Terça - perna/ Quarto - costa");
        sistema.criarMusica("21_Question_50_cent", "Download", 589);
        sistema.criarVideo("Mata_Leao_jiu_jitsu", "Download", 1080);
        sistema.criarVideo("Allen_Iverson_Crossover", "Documentos", 1080);
        sistema.criarMusica("Fair_Trade_Drake", "Download", 2);

        System.out.println(sistema.toString());

    }
}