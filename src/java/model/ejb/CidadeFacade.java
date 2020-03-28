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
    public void salvar(Cidade cidade) throws Exception {
        try {
            cidade.setDataAlteracao(new Date());
            cidade.setDataCadastro(new Date());
            super.salvar(cidade);
        } catch (Exception ex) {
            throw new Exception("Erro ao salvar : " + ex.getMessage());
        }
    }

    @Override
    public void excluir(Cidade cidade) throws Exception {
        try {
            super.excluir(cidade);
        } catch (Exception ex) {
            throw new Exception("Erro ao excluir : " + ex.getMessage());
        }
    }

    @Override
    public Cidade pesquisar(Long id) throws Exception {
        try {
            return (Cidade) super.pesquisarPorId(id);
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar : " + ex.getMessage());
        }
    }

    @Override
    public List<Cidade> pesquisar(String param) throws Exception {
        try {
            List<Cidade> lstCidade;
            Query consulta = em.createNamedQuery("Cidade.findByNome");
            consulta.setParameter("nome", param + "%");
            lstCidade = consulta.getResultList();
            return lstCidade;
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar : " + ex.getMessage());
        }
    }

    @Override
    public List<Cidade> listarTodos() throws Exception {
        try {
            return super.listarTodos();
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar : " + ex.getMessage());
        }
    }
}
