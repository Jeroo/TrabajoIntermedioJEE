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
@Table(name = "STOCK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s")
    , @NamedQuery(name = "Stock.findByCodigostock", query = "SELECT s FROM Stock s WHERE s.codigostock = :codigostock")
    , @NamedQuery(name = "Stock.findByCantidad", query = "SELECT s FROM Stock s WHERE s.cantidad = :cantidad")
    , @NamedQuery(name = "Stock.findByFechareposicion", query = "SELECT s FROM Stock s WHERE s.fechareposicion = :fechareposicion")
    , @NamedQuery(name = "Stock.findByUsuariorepuso", query = "SELECT s FROM Stock s WHERE s.usuariorepuso = :usuariorepuso")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGOSTOCK")
    private Integer codigostock;
    @Basic(optional = false)
    @Column(name = "CANTIDAD")
    private int cantidad;
    @Basic(optional = false)
    @Column(name = "FECHAREPOSICION")
    @Temporal(TemporalType.DATE)
    private Date fechareposicion;
    @Basic(optional = false)
    @Column(name = "USUARIOREPUSO")
    private String usuariorepuso;
    @JoinColumn(name = "CODIGOPRODUCTO", referencedColumnName = "CODIGOPRODUCTO")
    @ManyToOne(optional = false)
    private Productostienda codigoproducto;

    public Stock() {
    }

    public Stock(Integer codigostock) {
        this.codigostock = codigostock;
    }

    public Stock(Integer codigostock, int cantidad, Date fechareposicion, String usuariorepuso) {
        this.codigostock = codigostock;
        this.cantidad = cantidad;
        this.fechareposicion = fechareposicion;
        this.usuariorepuso = usuariorepuso;
    }

    public Integer getCodigostock() {
        return codigostock;
    }

    public void setCodigostock(Integer codigostock) {
        this.codigostock = codigostock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechareposicion() {
        return fechareposicion;
    }

    public void setFechareposicion(Date fechareposicion) {
        this.fechareposicion = fechareposicion;
    }

    public String getUsuariorepuso() {
        return usuariorepuso;
    }

    public void setUsuariorepuso(String usuariorepuso) {
        this.usuariorepuso = usuariorepuso;
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
        hash += (codigostock != null ? codigostock.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        if ((this.codigostock == null && other.codigostock != null) || (this.codigostock != null && !this.codigostock.equals(other.codigostock))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoproducto+" Cantidad Disponible "+cantidad;
    }
    
}
