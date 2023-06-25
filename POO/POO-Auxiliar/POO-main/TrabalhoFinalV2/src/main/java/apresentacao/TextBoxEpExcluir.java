package apresentacao;

import excecoes.DeleteException;
import excecoes.SelectException;
import negocio.Sistema;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TextBoxEpExcluir extends JPanel implements ActionListener {

    Sistema sistema = new Sistema("folgado23");
    JTextField text = new JTextField();
    JButton button = new JButton();
    EpisodioTableModel episodioTableModel;

    public TextBoxEpExcluir(EpisodioTableModel episodioTableModel) throws SQLException, SelectException, ClassNotFoundException {
        this.episodioTableModel = episodioTableModel;

        setLayout(new FlowLayout());
        text.setColumns(30);
        button.setText("Excluir");
        button.addActionListener(this);

        this.add(text);
        this.add(button);

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Digite o numero do EP", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        Integer episodio = Integer.valueOf(text.getText());

        try {
            sistema.removerEpisode(sistema.getEpisode(episodio)[0]);
            episodioTableModel.atualizaLista(sistema.getListaEpisode());
            JOptionPane.showMessageDialog(null, "Episodio Excluido!!");
        } catch (DeleteException | SelectException ex) {
            throw new RuntimeException(ex);
        }

    }

}
