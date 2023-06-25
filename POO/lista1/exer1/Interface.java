package lista1.exer1;

import java.util.List;

public class Interface {
    List<String> metodos;

    public List<String> getMetodos() {
        return metodos;
    }

    public void setMetodos(List<String> metodos) {
        this.metodos = metodos;
    }

    public Interface() {
    }

    public Interface(List<String> metodos) {
        this.metodos = metodos;
    }

    @Override
    public String toString() {
        return "Interface [metodos=" + metodos + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Interface other = (Interface) obj;
        if (metodos == null) {
            if (other.metodos != null)
                return false;
        } else if (!metodos.equals(other.metodos))
            return false;
        return true;
    }

}
