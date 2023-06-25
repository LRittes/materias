package apresentacao;

import javax.swing.table.AbstractTableModel;

import dados.CalculadoraEstatistica;
import dados.GerarTipo;

public class TabelaValores extends AbstractTableModel {
    private CalculadoraEstatistica calculadora = CalculadoraEstatistica.getInstance();
    private String[] colunas = { " Valores " };

    public void gerar(GerarTipo tipo, int n) {
        calculadora.getValores().clear();
        calculadora.gerar(tipo, n);
        fireTableStructureChanged();
    }

    public void remove() {
        calculadora.remove();
        fireTableStructureChanged();
    }

    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return calculadora.getValores().size();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return calculadora.getValores().get(linha);
    }

    public void adicionaValor(int valor) {
        calculadora.adicionarValor(valor);
        fireTableStructureChanged();
    }

    public void limpar() {
        calculadora.limparValores();
        fireTableStructureChanged();
    }
}
