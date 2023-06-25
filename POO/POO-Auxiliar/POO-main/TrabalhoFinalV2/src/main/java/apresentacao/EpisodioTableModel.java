package apresentacao;

import dadosAPI.Character;
import dadosAPI.Episode;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EpisodioTableModel extends AbstractTableModel {

    private List<Episode> episodios;
    private String[] colunas = {"ID", "Episode_ID", "Title", "Season", "Episode", "Air date", "Characters", "Series"};

    public EpisodioTableModel(List<Episode> ep){
        episodios = ep;
    }

    @Override
    public int getRowCount() {
        return episodios.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    public Class getColumnClass(int c){
        return getValueAt(0,c).getClass();
    }

    public void setValueAt(Object valor, int linha, int coluna) {

        switch (coluna) {
            case 0 -> episodios.get(linha).setId((int)valor);
            case 1 -> episodios.get(linha).setEpisode_id((int) valor);
            case 2 -> episodios.get(linha).setTitle((String) valor);
            case 3 -> episodios.get(linha).setSeason((String) valor);
            case 4 -> episodios.get(linha).setEpisode((String) valor);
            case 5 -> episodios.get(linha).setAir_date((String) valor);
            case 6 -> episodios.get(linha).setCharacters((String) valor);
            case 7 -> episodios.get(linha).setSeries((String) valor);
        }
        fireTableCellUpdated(linha, coluna);
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0: return episodios.get(linha).getId();
            case 1: return episodios.get(linha).getEpisode_id();
            case 2: return episodios.get(linha).getTitle();
            case 3: return episodios.get(linha).getSeason();
            case 4: return episodios.get(linha).getEpisode();
            case 5: return episodios.get(linha).getAir_date();
            case 6: return episodios.get(linha).getCharacters();
            case 7: return episodios.get(linha).getSeries();
        };
        return null;
    }

    public String getColumnName(int num){
        return this.colunas[num];
    }

    public void atualizaLista(List<Episode> e){
        this.episodios = e;
    }
}
