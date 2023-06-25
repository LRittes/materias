package lista1.exer1;

public class Sistema {
    private Franquia franquia;

    public Sistema() {
    }

    public Sistema(Franquia franquia) {
        this.franquia = franquia;
    }

    public Franquia getFranquia() {
        return franquia;
    }

    public void setFranquia(Franquia franquia) {
        this.franquia = franquia;
    }

    @Override
    public String toString() {
        return "Sistema [franquia=" + franquia + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sistema other = (Sistema) obj;
        if (franquia == null) {
            if (other.franquia != null)
                return false;
        } else if (!franquia.equals(other.franquia))
            return false;
        return true;
    }

}
