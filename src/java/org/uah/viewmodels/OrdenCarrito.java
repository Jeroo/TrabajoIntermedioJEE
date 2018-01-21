/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uah.viewmodels;

import beans.Stock;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author scuev
 */
public class OrdenCarrito {
    
    private String nombreProducto;
    private String codigoProducto;
    private int cantidad;
    private double precio;
    private List<String> listCantidad;

    public OrdenCarrito(String codigoProducto, int cantidad, double precio) {
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    public OrdenCarrito() {
    }
    
    

    public OrdenCarrito(String nombreProducto, String codigoProducto, int cantidad, double precio) {
        this.nombreProducto = nombreProducto;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<String> getListCantidad() {
        
               
        return listCantidad;
      }
       
    /**
     *
     * @param stock
     * @return
     */
    public List<String> llenarListaCantidad(Stock stock) {
        
        if (stock != null) {
           listCantidad = new ArrayList<String>();
           for (int i = 1; i <= stock.getCantidad(); i++) {
            
                listCantidad.add(""+i);            

               }
           return listCantidad;
        }
        
        return listCantidad;
    }

    public void setListCantidad(List<String> listCantidad) {
        this.listCantidad = listCantidad;
    }
    
}
