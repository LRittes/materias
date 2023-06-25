import java.util.ArrayList;
import java.util.List;

//incompleto

public class Turma {

    private List<Aluno> listaAlunos = new ArrayList<>();

    public void adicionarAluno(Aluno add){
        listaAlunos.add(add);
    }
    public void ordenarAlunoPorMedia(){
        //Collections.sort((List<T>) listaAlunos);
    }
    
}
