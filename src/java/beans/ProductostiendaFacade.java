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
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author scuev
 */
@Stateless
public class ProductostiendaFacade extends AbstractFacade<Productostienda> {

    @PersistenceContext(unitName = "TiendaWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductostiendaFacade() {
        super(Productostienda.class);
    }
    
   /* //listado por patrón del nombre de usuario
    public java.util.List<Tuple> findAllByNombre(String patronNom) {
        //Criteria API
        // Establece el criteria builder que nos permite construir la query y el predicado
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        // Crea una variable de tipo Usuarios en la que guarda la consulta
        CriteriaQuery<Productostienda> cq = cb.createQuery(Productostienda.class);
        // Determina la tabla sobre la que se realiza la consulta
        Root<Productostienda> from = cq.from(Productostienda.class);
        Join<Productostienda, Productospool> address = cq.join(Productostienda_.codigoproducto).join(Productospool_.codigoproducto);
        // Se construye el predicado determinando el valor deseado para la tabla seleccionada
        //Predicate predicado = cb.like(from.get(Usuarios_.nombre), "%"+patronNom+"%");
        // Creamos una select
        CriteriaQuery<Productostienda> select = cq.select(address);
        // Se construye la sentencia where con el predicado
        select.where(predicado);
        //Extraer los resultados en una lista
        TypedQuery<Tuple> q = em.createQuery(cq);
        List<Tuple> res = q.getResultList();
        
        return res;
    /*JPQL
    Query q = em.createQuery("SELECT OBJECT(u) FROM Usuarios AS u
    WHERE u.nombre LIKE :nomUsr");
    q.setParameter("nomUsr", "%"+patronNom+"%");
    return q.getResultList();
  
    }*/
    
}
