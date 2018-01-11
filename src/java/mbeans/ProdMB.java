/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeans;

import beans.Productostienda;
import beans.ProductostiendaFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author scuev
 */
@Named(value = "prodMB")
@RequestScoped
public class ProdMB {

    @EJB
    private ProductostiendaFacade productostiendaFacade;

    /**
     * Creates a new instance of ProdMB
     */
    public ProdMB() {
    }
    
    private String nombreProducto;   
    private String codigoProducto;
    private double precio;  
    private Productostienda productostienda;
    private List<Productostienda> listaProductostienda;
    
     public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

   
    public String getNombreProducto() {
        return nombreProducto;
    }

    public Productostienda getProductostienda() {
        return productostienda;
    }

    public void setProductostienda(Productostienda productostienda) {
        this.productostienda = productostienda;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<Productostienda> getListaProductostienda() {
        
        if (listaProductostienda == null) {
           listaProductostienda = productostiendaFacade.findAll();
        }
        return listaProductostienda;
    }

    public void setListaProductostienda(List<Productostienda> listaProductostienda) {
        this.listaProductostienda = listaProductostienda;
    }
    
    
}
