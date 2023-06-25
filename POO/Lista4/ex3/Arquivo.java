public class Arquivo {
    private String nome;
    private String extensao;

    public Arquivo(String nome) {
        this.nome = nome;
        try {
            if (nome.length() < 10 || nome.length() > 256) {
                throw new NomeInvalidoException("Possui mais que 256 ou menos que 10 caracteres");
            }
            if (nome.contains("(") || nome.contains(")") || nome.contains("[") || nome.contains("]")) {
                throw new NomeInvalidoException("Possui colchetes ou parenteses");
            }
            if (nome.contains("\"") || nome.contains("'")) {
                throw new NomeInvalidoException("Possui aspas simples ou duplas");
            }
            if (nome.contains("\n")) {
                throw new NomeInvalidoException("Possui quebra de linhas");
            }
        } catch (NomeInvalidoException e) {
            e.printStackTrace();
        }
    }

    // getters
    public String getNome() {
        return nome;
    }

    public String getExtensao() {
        return extensao;
    }

    // setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }
}
