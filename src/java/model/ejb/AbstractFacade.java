
package model.ejb;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author wender
 * @param <T>
 */
public abstract class AbstractFacade<T> {
    
    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void salvar(T entity) throws Exception {
        getEntityManager().merge(entity);  
        getEntityManager().flush();
    }
    
    public void excluir(T entity) throws Exception {        
        getEntityManager().remove(entity);
    }
    
    public Object pesquisarPorId(Long id) throws Exception {
        return getEntityManager().find(entityClass, id);        
    }
    
    public List<T> listarTodos() throws Exception {
        javax.persistence.criteria.CriteriaQuery cq = 
                getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));               
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    
}
