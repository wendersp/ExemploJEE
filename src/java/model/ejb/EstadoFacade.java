
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
    public void salvar(Estado estado) {
        estado.setDataAlteracao(new Date());
        estado.setDataCadastro(new Date());
        super.salvar(estado);
    }

    @Override
    public void excluir(Estado estado) {
        super.excluir((Estado)super.pesquisarPorId(estado.getId()));        
    }

    @Override
    public Estado pesquisar(Long id) {
        return (Estado) super.pesquisarPorId(id);
    }

    @Override
    public List<Estado> pesquisar(String param) {        
        List<Estado> lstEstado;
        Query consulta = em.createNamedQuery("Estado.findByNome");
        consulta.setParameter("nome", param + "%");
        lstEstado = consulta.getResultList();        
        return lstEstado;
    }

    @Override
    public List<Estado> pesquisarPorSigla(String param) {
        List<Estado> lstEstado;
        Query consulta = em.createNamedQuery("Estado.findBySigla");
        consulta.setParameter("nome", param + "%");
        lstEstado = consulta.getResultList();
        return lstEstado;
    }

    @Override
    public List<Estado> listarTodos() {
        return super.listarTodos();
    }
}
