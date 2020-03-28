package model.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.entidades.Estado;

/**
 *
 * @author wender
 */
@Stateless
public class EstadoFacade extends AbstractFacade<Estado> implements EstadoFacadeRemoto {

    @PersistenceContext(unitName = "ExemploJEEPU")
    private EntityManager em;

    public EstadoFacade() {
        super(Estado.class);
    }

    @Override
    public Estado novo() {
        return new Estado();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void salvar(Estado estado) throws Exception {
        try {
            super.salvar(estado);
        } catch (Exception ex) {
            throw new Exception("Erro ao salvar : " + ex.getMessage());
        }
    }

    @Override
    public void excluir(Estado estado) throws Exception {
        try {
            super.excluir((Estado) super.pesquisarPorId(estado.getId()));
        } catch (Exception ex) {
            throw new Exception("Erro ao excluir : " + ex.getMessage());
        }
    }

    @Override
    public Estado pesquisar(Long id) throws Exception {
        try {
            return (Estado) super.pesquisarPorId(id);
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar : " + ex.getMessage());
        }
    }

    @Override
    public List<Estado> pesquisar(String param) throws Exception {
        try {
            List<Estado> lstEstado;
            Query consulta = em.createNamedQuery("Estado.findByNome");
            consulta.setParameter("nome", param + "%");
            lstEstado = consulta.getResultList();
            return lstEstado;
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar : " + ex.getMessage());
        }
    }

    @Override
    public List<Estado> pesquisarPorSigla(String param) throws Exception {
        try {
            List<Estado> lstEstado;
            Query consulta = em.createNamedQuery("Estado.findBySigla");
            consulta.setParameter("nome", param + "%");
            lstEstado = consulta.getResultList();
            return lstEstado;
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar : " + ex.getMessage());
        }
    }

    @Override
    public List<Estado> listarTodos() throws Exception {
        try {
            return super.listarTodos();
        } catch (Exception ex) {
            throw new Exception("Erro ao pesquisar : " + ex.getMessage());
        }
    }
}
