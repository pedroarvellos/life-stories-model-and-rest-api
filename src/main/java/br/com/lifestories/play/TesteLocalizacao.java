package br.com.lifestories.play;

import br.com.lifestories.model.entity.InstituicaoLongaPermanencia;
import br.com.lifestories.model.entity.Localizacao;
import br.com.lifestories.model.service.InstituicaoLongaPermanenciaService;
import br.com.lifestories.model.service.LocalizacaoService;
import java.math.BigDecimal;

/**
 *
 * @author Marcelo
 */
public class TesteLocalizacao {

    public static void main(String[] args) throws Exception {
        TesteLocalizacao teste = new TesteLocalizacao();
        teste.createLocalizacao();
    }

    private void createLocalizacao() throws Exception {
        InstituicaoLongaPermanencia instituicao = new InstituicaoLongaPermanencia();
        InstituicaoLongaPermanenciaService instituicaoService = new InstituicaoLongaPermanenciaService();
        instituicao.setEmail("instituicao@gmail.com");
        instituicao.setNome("testeLocalização");
        instituicao.setRegistroLegal("111-2222");
        instituicao.setSenha("1234");
        instituicao.setTelefone("(35) 96745-2344");
        instituicao.setTipo("inst");

        Localizacao localizacao = new Localizacao();
        LocalizacaoService service = new LocalizacaoService();
        localizacao.setLatitude(new BigDecimal(234.345678));
        localizacao.setLongitude(new BigDecimal(1111.345678));
        instituicao.setLocalizacao(localizacao);

        instituicaoService.create(instituicao);
        readByIdLocalizacao(localizacao.getId());
    }

    private void readByIdLocalizacao(Long id) throws Exception {
        LocalizacaoService service = new LocalizacaoService();
        Localizacao localizacao = service.readById(id);

        System.out.println("Id instituicao: " + localizacao.getId());
        System.out.println("Latitude: " + localizacao.getLatitude());
        System.out.println("Longitude: " + localizacao.getLongitude());
        
        updateLocalizacao(localizacao);
    }
    
    private void updateLocalizacao(Localizacao localizacao) throws Exception{
        LocalizacaoService service = new LocalizacaoService();
        localizacao.setLatitude(new BigDecimal(9999.99999));
        localizacao.setLongitude(new BigDecimal(33333.88888));
        service.update(localizacao);
    }
}
