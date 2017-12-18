package br.com.lifestories.api.mock;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joao Pedro
 */
public class AlunoService {

    private static List<Aluno> alunoList = new ArrayList<>();

    public void add(Aluno aluno) {
        alunoList.add(aluno);
    }

    public void remove(Aluno aluno) {
        alunoList.remove(aluno);
    }

    public void update(Aluno aluno) {        
        for (Aluno aux : alunoList) {
            if (aux.getIdConexaoSocket().equals(aluno.getIdConexaoSocket())) {
                alunoList.remove(aux);
                alunoList.add(aluno);
            }
        }
    }
    
    public Aluno readById(String id) {
        Aluno aluno = null;
        for (Aluno aux : alunoList) {
            if (aux.getIdConexaoSocket().equals(id)) {
                aluno = aux;
            }
        }
        return aluno;
    }

    public List readAll() {
        return alunoList;
    }

}
