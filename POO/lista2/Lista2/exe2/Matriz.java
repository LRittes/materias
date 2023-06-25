import java.util.List;
import java.util.ArrayList;

public class Matriz<T> {
    private int n;
    private int m;
    private Object[][] elem;

    public Matriz(int m, int n) {
        this.n = n;
        this.m = m;
        elem = new Object[n][m];
    }

    public enum Quadrante {
        PRIMEIRO, SEGUNDO, TERCEIRO, QUARTO;
    }

    public boolean set(T object, int i, int j) {
        if (i > n || j > m) {
            return false;
        } else {
            elem[i][j] = object;
            return true;
        }
    }

    public T get(int i, int j) {
        if (i > n || j > m) {
            return null;
        } else {
            final T t = (T) elem[i][j];
            return t;
        }
    }

    public List<T> Linha(int linha) {
        List<T> l = new ArrayList<T>();

        if (linha > n) {
            return null;
        } else {
            for (int j = 0; j < m; j++) {
                final T t = (T) elem[linha][j];
                l.add(t);
            }
            return l;
        }
    }

    public List<T> getColuna(int coluna) {
        List<T> l = new ArrayList<T>();

        if (coluna > m) {
            return null;
        } else {
            for (int i = 0; i < m; i++) {
                final T t = (T) elem[i][coluna];
                l.add(t);
            }
            return l;
        }
    }

    public int numElementosLinhaQuadrante(Quadrante quadrante) {
        if (n % 2 == 0) {
            return n / 4;
        } else {
            switch (quadrante) {
                case PRIMEIRO:
                    return ((n / 2) + 1);
                case SEGUNDO:
                    return n;
                case TERCEIRO:
                    return n;
                case QUARTO:
                    break;
            }
        }
        return 0;
    }

    public List<T> getElementosQuadrante(Quadrante quadrante) {
        List<T> value = new ArrayList<T>();
        if (n % 2 == 0 && m % 2 == 0) {
            switch (quadrante) {
                case PRIMEIRO:
                    for (int i = 0; i < (n / 2); i++) {
                        for (int j = 0; j < (m / 2); j++) {
                            final T t = (T) elem[i][j];
                            value.add(t);
                        }
                    }
                    break;
                case SEGUNDO:
                    for (int i = 0; i < (n / 2); i++) {
                        for (int j = (m / 2); j < m; j++) {
                            final T t = (T) elem[i][j];
                            value.add(t);
                        }
                    }
                    break;
                case TERCEIRO:
                    for (int i = (n / 2); i < n; i++) {
                        for (int j = 0; j < (m / 2); j++) {
                            final T t = (T) elem[i][j];
                            value.add(t);
                        }
                    }
                    break;
                case QUARTO:
                    for (int i = (n / 2); i < n; i++) {
                        for (int j = (m / 2); j < m; j++) {
                            final T t = (T) elem[i][j];
                            value.add(t);
                        }
                    }
                    break;
            }
            return value;
        } else {
            switch (quadrante) {
                case PRIMEIRO:
                    for (int i = 0; i < ((n / 2) + 1); i++) {
                        for (int j = 0; j < ((m / 2) + 1); j++) {
                            final T t = (T) elem[i][j];
                            value.add(t);
                        }
                    }
                    break;
                case SEGUNDO:
                    for (int i = 0; i < (n / 2); i++) {
                        for (int j = ((m / 2) + 1); j < m; j++) {
                            final T t = (T) elem[i][j];
                            value.add(t);
                        }
                    }
                    break;
                case TERCEIRO:
                    for (int i = (n / 2); i < n; i++) {
                        for (int j = 0; j < ((m / 2) + 1); j++) {
                            final T t = (T) elem[i][j];
                            value.add(t);
                        }
                    }
                    break;
                case QUARTO:
                    for (int i = ((n / 2) + 1); i < n; i++) {
                        for (int j = ((m / 2) + 1); j < m; j++) {
                            final T t = (T) elem[i][j];
                            value.add(t);
                        }
                    }
                    break;
            }
        }
        return value;
    }

}
