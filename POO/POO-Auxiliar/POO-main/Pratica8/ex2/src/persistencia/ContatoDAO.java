package persistencia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dados.Contato;

public class ContatoDAO {

    private ArquivoContatoDAO arquivoContatoDAO = new ArquivoContatoDAO();

    public void insert(Contato contato){
        arquivoContatoDAO.salvaContatos(contato);
    }

    public void delete(Contato contato){
        List<Contato> contatos = arquivoContatoDAO.leContatos();
        int index = 0;
        for(int i = 0 ; i < contatos.size() ; i++){
            if(contatos.get(i).getNome() == contato.getNome()){
                index = i;
            }
        }
        contatos.remove(index);
        arquivoContatoDAO.salvaContatos(contatos);
    }

    public Map<Character, List<Contato>> getAll(){

        Map<Character, List<Contato>> todosContatos = new HashMap<Character, List<Contato>>();
        List<Contato> lista;

        for(char i = 'A' ; i <= 'Z' ; i++){
            lista = new LinkedList<Contato>();
            todosContatos.put(i, lista);
        }

        List<Contato> contatos = arquivoContatoDAO.leContatos();

        for(int j = 0; j < contatos.size() ; j++){
            String nome = contatos.get(j).getNome().toUpperCase();
            List<Contato> temp = todosContatos.get(nome.charAt(0));
            temp.add(contatos.get(j));
        }

        return todosContatos;
    }

}