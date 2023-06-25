package persistencia;

import java.util.LinkedList;
import java.util.List;

import dados.Contato;

public class ArquivoContatoDAO{

    private final String caminho = "src/persistencia/files/contatos.csv";
    private EditorTexto editor = new EditorTexto();

    private String toCSV(Contato contato){
        String c = "";

        c += contato.getNome() + ",";
        c += contato.getTelefone();

        return c;
    }

    private Contato fromCSV(String linhaCSV){

        String atributos[] = linhaCSV.split(",");

        Contato c = new Contato();
        c.setNome(atributos[0]);
        c.setTelefone(atributos[1]);

        return c;
    }

    private List<Contato> stringToListaContato(List<String> contatos){
        List<Contato> listaContatos = new LinkedList<Contato>();
        for(String c : contatos){
            listaContatos.add(fromCSV(c));
        }
        return listaContatos;
    }

    private List<String> listaContatosToString (List<Contato> contatos){
        List<String> listaContatos = new LinkedList<String>();
        for(Contato c : contatos){
            listaContatos.add(toCSV(c));
        }
        return listaContatos;
    }

    public List<Contato> leContatos(){
        return stringToListaContato(editor.leTexto(caminho));
    }

    public void salvaContatos(List<Contato> contatos){
        editor.gravaTexto(caminho, listaContatosToString(contatos));
    }

    public void salvaContatos(Contato contato){
        editor.gravaTexto(caminho, toCSV(contato));
    }

}