package br.com.lifestories.api.mock;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joao Pedro
 */
public class ProfessorService {

    private static List<Professor> professorList = new ArrayList<>();

    public void add(Professor professor) {
        professorList.add(professor);
    }

    public void remove(Professor professor) {
        professorList.remove(professor);
    }

    public void update(Professor professor) {        
        for (Professor aux : professorList) {
            if (aux.getIdConexaoSocket().equals(professor.getIdConexaoSocket())) {
                professorList.remove(aux);
                professorList.add(professor);
            }
        }
    }
    
    public Professor readById(String id) {
        Professor professor = null;
        for (Professor aux : professorList) {
            if (aux.getIdConexaoSocket().equals(id)) {
                professor = aux;
            }
        }
        return professor;
    }

    public List readAll() {
        return professorList;
    }

}
