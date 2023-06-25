package negocio;
import dados.Contato;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ListaTelefonica {
    
    private Map<Character, List<Contato>> contatos = new HashMap<Character , List<Contato>>();

    public ListaTelefonica(){
        
        List<Contato> lista;
        for(char i = 'A'; i <= 'Z'; i++){
            //A cada letra ele adiciona junto uma linkedList
            lista = new LinkedList<Contato>();
            contatos.put(i, lista);
        }

    }

    public void adicionarContato(Contato contato){
        
        //String nome recebe o nome do contato colocando tudo em letra maiuscula
        String nome = contato.getNome().toUpperCase();
        //contatosTemp recebe a lista associada a chave nome.charAt(0) -> primeira letra do nome
        //get -> acessa o objeto associado a uma chave
        List<Contato> contatosTemp = contatos.get(nome.charAt(0));
        //adiciona a linkedList
        contatosTemp.add(contato);

    }

    public void removerContato(Contato contato){

        String nome = contato.getNome().toUpperCase();
        List<Contato> contatosTemp = contatos.get(nome.charAt(0));
        int posicao = contatosTemp.indexOf(contato);
        contatosTemp.remove(posicao);

    }

    public List<Contato> buscarContato(char letra) {

        List<Contato> buscaTemp = contatos.get(Character.toUpperCase(letra));

        return buscaTemp;
    }

    public Map<Character, List<Contato>> buscarContatos(){
        return contatos;
    }

    public String toString() {
        return "Contatos =" + contatos;
    }


}