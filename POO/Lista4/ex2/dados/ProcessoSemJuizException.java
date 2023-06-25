package dados;

public class ProcessoSemJuizException extends Exception {
    ProcessoSemJuizException() {
        super("Não há Juiz para o Processo");
    }
}
