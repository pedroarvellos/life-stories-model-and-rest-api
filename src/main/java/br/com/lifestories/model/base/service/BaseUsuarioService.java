package br.com.lifestories.model.base.service;

import br.com.lifestories.model.base.BaseCRUDService;
import br.com.lifestories.model.entity.Usuario;

/**
 *
 * @author Marcelo
 */
public interface BaseUsuarioService extends BaseCRUDService<Usuario>{
    
    public Usuario validarUsuario(String email,String Senha)throws Exception;
}
