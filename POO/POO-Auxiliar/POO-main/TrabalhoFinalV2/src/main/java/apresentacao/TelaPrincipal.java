package apresentacao;

import dadosAPI.Character;
import dadosAPI.Episode;
import excecoes.SelectException;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TelaPrincipal extends JFrame{

    Sistema sistema = new Sistema("folgado23");
    JTabbedPane pane;
    JTable tabela1, tabela2;
    PersonagemTableModel personagemTableModel;
    EpisodioTableModel episodioTableModel;
    TextBoxChar textBoxChar;
    TextBoxEp textBoxEp;
    TextBoxCharExcluir textBoxCharExcluir;
    TextBoxEpExcluir textBoxEpExcluir;
    public TelaPrincipal() throws SQLException, SelectException, ClassNotFoundException {
        pane = new JTabbedPane();

        personagemTableModel = new PersonagemTableModel(criaListaPersonagem());
        episodioTableModel = new EpisodioTableModel(criaListaEpisodio());

        tabela1 = new JTable();
        tabela1.setModel(personagemTableModel);
        tabela2 = new JTable();
        tabela2.setModel(episodioTableModel);

        textBoxChar = new TextBoxChar(personagemTableModel);
        textBoxEp = new TextBoxEp(episodioTableModel);
        textBoxCharExcluir = new TextBoxCharExcluir(personagemTableModel);
        textBoxEpExcluir = new TextBoxEpExcluir(episodioTableModel);

        pane.addTab("Salvar Personagem", textBoxChar);
        pane.addTab("Salvar Episodio", textBoxEp );
        pane.addTab("Excluir Personagem", textBoxCharExcluir);
        pane.addTab("Excluir Episodio", textBoxEpExcluir);
        pane.addTab("Personagens", null, tabela1, "Informacoes Personagens");
        pane.addTab("Episodios", null, tabela2, "Informacoes Episodios");

        Container c = getContentPane();
        setSize(1600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.add(pane, BorderLayout.CENTER);
    }

    private List<Character> criaListaPersonagem() throws SelectException {
        return sistema.getListaCharacter();
    }

    private List<Episode> criaListaEpisodio() throws SelectException {
        return sistema.getListaEpisode();
    }

}
