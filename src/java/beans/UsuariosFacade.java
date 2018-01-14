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
import javax.persistence.Query;
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
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "TiendaWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    public List<Usuarios> getUltimoUsuario() {
       /*JPQL */
   
        //Criteria API
        // Establece el criteria builder que nos permite construir la query y el predicado
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        // Crea una variable de tipo Usuarios en la que guarda la consulta
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        // Determina la tabla sobre la que se realiza la consulta
        Root<Usuarios> from = cq.from(Usuarios.class);
        
        // Creamos una select
        CriteriaQuery<Usuarios> select = cq.select(from);
        // Se construye el predicado determinando el valor deseado para la tabla seleccionada
        //Predicate predicado = cb.equal(from.get());
        // Se construye la sentencia where con el predicado
        //select.where(predicado);
        //Extraer los resultados en una lista
        TypedQuery<Usuarios> q = em.createQuery(cq);
        List<Usuarios> res =  q.getResultList();     
        
        return res;
        
    }
    
     public List<Usuarios> getUsuario(String usuario) {
       //Criteria API
        // Establece el criteria builder que nos permite construir la query y el predicado
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        // Crea una variable de tipo Usuarios en la que guarda la consulta
        CriteriaQuery<Usuarios> cq = cb.createQuery(Usuarios.class);
        // Determina la tabla sobre la que se realiza la consulta
        Root<Usuarios> from = cq.from(Usuarios.class);
        // Se construye el predicado determinando el valor deseado para la tabla seleccionada
        Predicate predicado = cb.equal(from.get(Usuarios_.usuario), usuario.trim());
        // Creamos una select
        CriteriaQuery<Usuarios> select = cq.select(from);
        // Se construye la sentencia where con el predicado
        select.where(predicado);
        //Extraer los resultados en una lista
        TypedQuery<Usuarios> q = em.createQuery(cq);
        List<Usuarios> res =  q.getResultList();
         
       return res;
        
    }
    
}
