/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author scuev
 */
@Entity
@Table(name = "VENTAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v")
    , @NamedQuery(name = "Ventas.findByCodigoventa", query = "SELECT v FROM Ventas v WHERE v.codigoventa = :codigoventa")
    , @NamedQuery(name = "Ventas.findByCantidadvendidas", query = "SELECT v FROM Ventas v WHERE v.cantidadvendidas = :cantidadvendidas")
    , @NamedQuery(name = "Ventas.findByFechaventa", query = "SELECT v FROM Ventas v WHERE v.fechaventa = :fechaventa")
    , @NamedQuery(name = "Ventas.findByUsuariocompra", query = "SELECT v FROM Ventas v WHERE v.usuariocompra = :usuariocompra")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGOVENTA")
    private Integer codigoventa;
    @Basic(optional = false)
    @Column(name = "CANTIDADVENDIDAS")
    private int cantidadvendidas;
    @Basic(optional = false)
    
    @Column(name = "FECHAVENTA")
    private String fechaventa;
    
    @Basic(optional = false)
    @Column(name = "USUARIOCOMPRA")
    private String usuariocompra;
    @JoinColumn(name = "CODIGOPRODUCTO", referencedColumnName = "CODIGOPRODUCTO")
    @ManyToOne(optional = false)
    private Productostienda codigoproducto;

    public Ventas() {
    }

    public Ventas(Integer codigoventa) {
        this.codigoventa = codigoventa;
    }

    public Ventas(Integer codigoventa,Productostienda codigoproducto, int cantidadvendidas, String usuariocompra, String fechaventa) {
        this.codigoventa = codigoventa;
        this.codigoproducto = codigoproducto;
        this.cantidadvendidas = cantidadvendidas;        
        this.usuariocompra = usuariocompra;
        this.fechaventa = fechaventa;
    }

    public Integer getCodigoventa() {
        return codigoventa;
    }

    public void setCodigoventa(Integer codigoventa) {
        this.codigoventa = codigoventa;
    }

    public int getCantidadvendidas() {
        return cantidadvendidas;
    }

    public void setCantidadvendidas(int cantidadvendidas) {
        this.cantidadvendidas = cantidadvendidas;
    }

    public String getFechaventa() {
        return fechaventa;
    }

    public void setFechaventa(String fechaventa) {
        this.fechaventa = fechaventa;
    }

    public String getUsuariocompra() {
        return usuariocompra;
    }

    public void setUsuariocompra(String usuariocompra) {
        this.usuariocompra = usuariocompra;
    }

    public Productostienda getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(Productostienda codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoventa != null ? codigoventa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.codigoventa == null && other.codigoventa != null) || (this.codigoventa != null && !this.codigoventa.equals(other.codigoventa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoventa + " usuario compra: "+ usuariocompra;
    }
    
}
