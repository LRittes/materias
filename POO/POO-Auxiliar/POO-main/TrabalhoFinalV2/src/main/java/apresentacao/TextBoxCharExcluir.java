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

public class TextBoxCharExcluir extends JPanel implements ActionListener {

    Sistema sistema = new Sistema("folgado23");
    JTextField text = new JTextField();
    JButton button = new JButton();
    PersonagemTableModel personagemTableModel;

    public TextBoxCharExcluir(PersonagemTableModel personagemTableModel) throws SQLException, SelectException, ClassNotFoundException {
        this.personagemTableModel = personagemTableModel;

        setLayout(new FlowLayout());
        text.setColumns(30);
        button.setText("Excluir");
        button.addActionListener(this);

        this.add(text);
        this.add(button);

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Digite o nome do personagem", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String personagem = text.getText();

        try {
            sistema.removerCharacter(sistema.getCharacter(personagem)[0]);
            personagemTableModel.atualizaLista(sistema.getListaCharacter());
            JOptionPane.showMessageDialog(null, "Personagem Excluido!!");
        } catch (DeleteException | SelectException ex) {
            throw new RuntimeException(ex);
        }

    }

}
