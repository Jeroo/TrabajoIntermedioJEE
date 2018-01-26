/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.service;

import beans.ProductosProv;
import beans.ProductosProvPK;
import beans.ProductosProv_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author scuev
 */
@Stateless
@Path("beans.productosprov")
public class ProductosProvFacadeREST extends AbstractFacade<ProductosProv> {

    @PersistenceContext(unitName = "TiendaWebPU")
    private EntityManager em;

    private ProductosProvPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;codigoproducto=codigoproductoValue;cif=cifValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        beans.ProductosProvPK key = new beans.ProductosProvPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> codigoproducto = map.get("codigoproducto");
        if (codigoproducto != null && !codigoproducto.isEmpty()) {
            key.setCodigoproducto(codigoproducto.get(0));
        }
        java.util.List<String> cif = map.get("cif");
        if (cif != null && !cif.isEmpty()) {
            key.setCif(cif.get(0));
        }
        return key;
    }

    public ProductosProvFacadeREST() {
        super(ProductosProv.class);
    }

   /* @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(ProductosProv entity) {
        super.create(entity);
    }*/

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, ProductosProv entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        beans.ProductosProvPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ProductosProv find(@PathParam("id") PathSegment id) {
        beans.ProductosProvPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductosProv> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductosProv> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
     @GET
     @Path("cantidad/{codigoProducto}/{cantidad}")
     @Produces({"application/xml", "application/json"})
     public java.util.List<ProductosProv> findAllByCantidad(@PathParam("codigoProducto") String codigoProducto, 
           @PathParam("cantidad")  int cantidad) {
         
         //Criteria API
        // Establece el criteria builder que nos permite construir la query y el predicado
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        // Crea una variable de tipo Cuentas en la que guarda la consulta
        CriteriaQuery<ProductosProv> cq = cb.createQuery(ProductosProv.class);
        // Determina la tabla sobre la que se realiza la consulta
        Root<ProductosProv> from = cq.from(ProductosProv.class);
        // Se construye el predicado determinando el valor deseado para la tabla seleccionada
        //Predicate predicado = cb.between(from.get(ProductosProv_.cantidad), cantidad);
        // Creamos una select
        CriteriaQuery<ProductosProv> select = cq.select(from);
        // Se construye la sentencia where con el predicado
        //select.where(predicado);
        cq.orderBy(cb.desc(from.get(ProductosProv_.cantidad)));
        //Extraer los resultados en una lista
        TypedQuery<ProductosProv> q = em.createQuery(cq);
        List<ProductosProv> res = q.getResultList();
        
        return res;
         
         
    }
     
    @GET
    @Path("fecha/{codigoProducto}/{cantidad}/{fecha}")
    @Produces({"application/xml", "application/json"})
    public java.util.List<ProductosProv> findAllByFecha(@PathParam("codigoProducto") String codigoProducto, 
            @PathParam("cantidad") int cantidad, @PathParam("fecha") String fecha) {
           //Criteria API
        // Establece el criteria builder que nos permite construir la query y el predicado
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        // Crea una variable de tipo Cuentas en la que guarda la consulta
        CriteriaQuery<ProductosProv> cq = cb.createQuery(ProductosProv.class);
        // Determina la tabla sobre la que se realiza la consulta
        Root<ProductosProv> from = cq.from(ProductosProv.class);
        // Se construye el predicado determinando el valor deseado para la tabla seleccionada
        //Predicate predicado = cb.between(from.get(ProductosProv_.cantidad), cantidad);
        // Creamos una select
        CriteriaQuery<ProductosProv> select = cq.select(from);
        // Se construye la sentencia where con el predicado
        //select.where(predicado);
        cq.orderBy(cb.desc(from.get(ProductosProv_.cantidad)));
        //Extraer los resultados en una lista
        TypedQuery<ProductosProv> q = em.createQuery(cq);
        List<ProductosProv> res = q.getResultList();
        
        return res;
    }
     

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

   
    
}
