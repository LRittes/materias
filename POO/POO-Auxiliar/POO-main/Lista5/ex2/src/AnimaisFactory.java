public class AnimaisFactory {
    private static AnimaisFactory instance = null;

    public static AnimaisFactory getInstace(){
        if(instance == null){
            instance = new AnimaisFactory();
        }
        return instance;
    }

    public Animal create(EnumerateAnimais animal){
        switch (animal){
            case CAO:
                return new Cao();
            case GATO:
                return new Gato();
            case RATO:
                return new Rato();
            case SAPO:
                return new Sapo();
            case COBRA:
                return new Cobra();
            default:
                return null;
        }
    }
}