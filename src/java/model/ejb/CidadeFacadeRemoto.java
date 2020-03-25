/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ejb;

import java.util.List;
import javax.ejb.Remote;
import model.entidades.Cidade;

/**
 *
 * @author wender
 */
@Remote
public interface CidadeFacadeRemoto {

    public void excluir(Cidade cidade);

    public List<Cidade> listarTodos();

    public Cidade novo();

    public Cidade pesquisar(Long id);

    public List<Cidade> pesquisar(String param);

    void salvar(Cidade cidade);
    
}
