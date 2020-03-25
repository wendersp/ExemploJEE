/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entidades.Cidade;

/**
 *
 * @author wender
 */
@Stateless
public class CidadeFacade extends AbstractFacade<Cidade> implements CidadeFacadeRemoto {

    @PersistenceContext(unitName = "ExemploJEEPU")
    private EntityManager em;

    public CidadeFacade() {
        super(Cidade.class);
    }
    
 

    @Override
    public Cidade novo() {
        return new Cidade();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void salvar(Cidade cidade) {
        cidade.setDataAlteracao(new Date());
        cidade.setDataCadastro(new Date());
        super.salvar(cidade);
    }

    @Override
    public void excluir(Cidade cidade) {
        super.excluir(cidade);
    }

    @Override
    public Cidade pesquisar(Long id) {
        return (Cidade) super.pesquisarPorId(id);
    }

    @Override
    public List<Cidade> pesquisar(String param) {        
        List<Cidade> lstCidade;
        Query consulta = em.createNamedQuery("Cidade.findByNome");
        consulta.setParameter("nome", param + "%");
        lstCidade = consulta.getResultList();
        return lstCidade;
    }

    
    @Override
    public List<Cidade> listarTodos() {
        return super.listarTodos();
    }
}
