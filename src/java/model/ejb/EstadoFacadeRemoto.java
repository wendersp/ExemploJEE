/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ejb;

import java.util.List;
import javax.ejb.Remote;
import model.entidades.Estado;

/**
 *
 * @author wender
 */
@Remote
public interface EstadoFacadeRemoto {
    
    public void excluir(Estado estado) throws Exception;

    public List<Estado> listarTodos() throws Exception;

    public Estado novo();

    public Estado pesquisar(Long id) throws Exception;

    public List<Estado> pesquisar(String param) throws Exception;

    public List<Estado> pesquisarPorSigla(String param) throws Exception;

    public void salvar(Estado estado) throws Exception;
    
}
