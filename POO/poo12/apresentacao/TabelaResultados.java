package apresentacao;

import javax.swing.table.AbstractTableModel;

import dados.CalculadoraEstatistica;

public class TabelaResultados extends AbstractTableModel {
    private CalculadoraEstatistica calculadora = CalculadoraEstatistica.getInstance();

    private String[] colunas = { " Sorteado ", " Somatorio ", "Media Geomtrica ", " Variancia ", " Desvio Padrao ",
            " Amplitude " };

    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        if (!calculadora.getValores().isEmpty()) {
            switch (coluna) {
                case 0:
                    return calculadora.sortear();
                case 1:
                    return calculadora.somatorio();
                case 2:
                    return calculadora.mediaAritmetica();
                case 3:
                    return calculadora.mediaGeometrica();
                case 4:
                    return calculadora.variancia();
                case 5:
                    return calculadora.desvioPadrao();
                case 6:
                    return calculadora.amplitude();
            }
        } else {
            return " - ";
        }
        return null;
    }

    public void atualizar() {
        fireTableStructureChanged();
    }
}