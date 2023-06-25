import java.util.ArrayList;
import java.util.List;

public class Servidor implements Sujeito{
    private String ip;
    private String ultimaMensagem;
    private List<Observador> observadores = new ArrayList<>();

    public void enviarMensagem(String mensagem){
        this.ultimaMensagem = mensagem;
        this.notificar();
    }


    @Override
    public void adicionar(Observador observador) {
        this.observadores.add(observador);
    }

    @Override
    public void remover(Observador observador) {
        this.observadores.remove(observador);
    }

    @Override
    public void notificar() {
        for(Observador observador : observadores){
            observador.atualizar(this.ultimaMensagem);
        }
    }

    public String getIp() {
        return ip;
    }

    public String getUltimaMensagem() {
        return ultimaMensagem;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUltimaMensagem(String ultimaMensagem) {
        this.ultimaMensagem = ultimaMensagem;
    }
}
