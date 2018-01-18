/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author scuev
 */
@Stateless
public class ProductospoolFacade extends AbstractFacade<Productospool> {

    @PersistenceContext(unitName = "TiendaWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductospoolFacade() {
        super(Productospool.class);
    }
    
    public List<Productospool> getUltimoProducto() {
       /*JPQL */
   
         //Criteria API
        // Establece el criteria builder que nos permite construir la query y el predicado
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        // Crea una variable de tipo Usuarios en la que guarda la consulta
        CriteriaQuery<Productospool> cq = cb.createQuery(Productospool.class);
        // Determina la tabla sobre la que se realiza la consulta
        Root<Productospool> from = cq.from(Productospool.class);
        // Se construye el predicado determinando el valor deseado para la tabla seleccionada
        //Predicate predicado = cb.equal(from.get(Stock_.codigoproducto));
        // Creamos una select
        CriteriaQuery<Productospool> select = cq.select(from);
        // Se construye la sentencia where con el predicado
        //select.where(predicado);
        //Extraer los resultados en una lista
        TypedQuery<Productospool> q = em.createQuery(cq);
        List<Productospool> res = q.getResultList();
        
        return res;
        
    }
    
}
