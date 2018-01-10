/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author scuev
 */
@Stateless
public class TiposusuariosFacade extends AbstractFacade<Tiposusuarios> {

    @PersistenceContext(unitName = "TiendaWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiposusuariosFacade() {
        super(Tiposusuarios.class);
    }
    
}
