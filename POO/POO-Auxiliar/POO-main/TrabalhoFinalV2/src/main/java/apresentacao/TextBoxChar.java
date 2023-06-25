package apresentacao;

import excecoes.InsertException;
import excecoes.SelectException;
import negocio.Sistema;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TextBoxChar extends JPanel implements ActionListener{

    Sistema sistema = new Sistema("folgado23");
    JTextField text = new JTextField();
    JButton button = new JButton();
    PersonagemTableModel personagemTableModel;

    public TextBoxChar(PersonagemTableModel personagemTableModel) throws SQLException, SelectException, ClassNotFoundException {
        this.personagemTableModel = personagemTableModel;

        setLayout(new FlowLayout());
        text.setColumns(30);
        button.setText("Salvar");
        button.addActionListener(this);

        this.add(text);
        this.add(button);

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Digite o nome do personagem", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String personagem = String.valueOf(text.getText());

        try {
            sistema.adicionarCharacter(sistema.getCharacter(personagem)[0]);
            personagemTableModel.atualizaLista(sistema.getListaCharacter());
            JOptionPane.showMessageDialog(null, "Personagem Salvo!!!");
        } catch (SelectException | InsertException ex) {
            throw new RuntimeException(ex);
        }

    }
}
