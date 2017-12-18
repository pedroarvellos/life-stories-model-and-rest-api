package br.com.lifestories.play;

import br.com.lifestories.model.entity.Lingua;
import br.com.lifestories.model.service.LinguaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TesteLingua {

    public static void main(String[] args) throws Exception {
        TesteLingua tl = new TesteLingua();
        tl.createLingua();
    }

    private void createLingua() throws Exception {
        Lingua lingua = new Lingua();

        lingua.setNome("Inglês");;

        LinguaService service = new LinguaService();
        service.create(lingua);

        readByIdLingua(lingua.getId());
    }

    private void readByIdLingua(Long id) throws Exception {
        LinguaService service = new LinguaService();
        Lingua lingua = service.readById(id);

        readByCriteriaLingua(null, null, null);
    }

    private void readByCriteriaLingua(Map<Long, Object> criteria, Long limit, Long offset) throws Exception {
        LinguaService service = new LinguaService();
        List<Lingua> linguaList = new ArrayList();

        linguaList = service.readByCriteria(criteria, limit, offset);
        
        countById(null);
    }
    
    private void countById(Map<Long, Object> criteria) throws Exception {
        LinguaService service = new LinguaService();
        Long count = service.countByCriteria(criteria);
    }

}
