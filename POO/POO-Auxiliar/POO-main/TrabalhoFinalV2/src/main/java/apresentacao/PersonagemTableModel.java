package apresentacao;

import dadosAPI.Character;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonagemTableModel extends AbstractTableModel {

    private List<Character> personagens;
    private String[] colunas = {"ID", "Char_ID", "Name", "Birthday", "Occupation", "IMG", "Status", "Nickname", "Appearance", "Portrayed", "Category", "Better Call Saul Appearance"};

    public PersonagemTableModel(List<Character> p){
        personagens = p;
    }

    public int getColumnCount(){
        return colunas.length;
    }

    public int getRowCount(){
        return personagens.size();
    }

    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }

    public void setValueAt(Object valor, int linha, int coluna) {

        switch (coluna) {
            case 0 -> personagens.get(linha).setId((int)valor);
            case 1 -> personagens.get(linha).setChar_id((int) valor);
            case 2 -> personagens.get(linha).setName((String) valor);
            case 3 -> personagens.get(linha).setBirthday((String) valor);
            case 4 -> personagens.get(linha).setOccupation((String) valor);
            case 5 -> personagens.get(linha).setImg((String) valor);
            case 6 -> personagens.get(linha).setStatus((String) valor);
            case 7 -> personagens.get(linha).setNickname((String) valor);
            case 8 -> personagens.get(linha).setAppearance((String) valor);
            case 9 -> personagens.get(linha).setPortrayed((String) valor);
            case 10 -> personagens.get(linha).setCategory((String) valor);
            case 11 -> personagens.get(linha).setBetter_call_saul_appearance((String) valor);
        }
        fireTableCellUpdated(linha, coluna);
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0: return personagens.get(linha).getId();
            case 1: return personagens.get(linha).getChar_id();
            case 2: return personagens.get(linha).getName();
            case 3: return personagens.get(linha).getBirthday();
            case 4: return personagens.get(linha).getOccupation();
            case 5: return personagens.get(linha).getImg();
            case 6: return personagens.get(linha).getStatus();
            case 7: return personagens.get(linha).getNickname();
            case 8: return personagens.get(linha).getAppearance();
            case 9: return personagens.get(linha).getPortrayed();
            case 10: return personagens.get(linha).getCategory();
            case 11: return personagens.get(linha).getBetter_call_saul_appearance();
        };
        return null;
    }

    public String getColumnName(int num){
        return this.colunas[num];
    }

    public void atualizaLista(List<Character> p){
        this.personagens = p;
    }
}
