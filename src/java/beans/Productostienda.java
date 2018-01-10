/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author scuev
 */
@Entity
@Table(name = "PRODUCTOSTIENDA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productostienda.findAll", query = "SELECT p FROM Productostienda p")
    , @NamedQuery(name = "Productostienda.findByCodigoproducto", query = "SELECT p FROM Productostienda p WHERE p.codigoproducto = :codigoproducto")
    , @NamedQuery(name = "Productostienda.findByPrecio", query = "SELECT p FROM Productostienda p WHERE p.precio = :precio")})
public class Productostienda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGOPRODUCTO")
    private String codigoproducto;
    @Basic(optional = false)
    @Column(name = "PRECIO")
    private double precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoproducto")
    private Collection<Ventas> ventasCollection;
    @JoinColumn(name = "CODIGOPRODUCTO", referencedColumnName = "CODIGOPRODUCTO", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Productospool productospool;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoproducto")
    private Collection<Stock> stockCollection;

    public Productostienda() {
    }

    public Productostienda(String codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public Productostienda(String codigoproducto, double precio) {
        this.codigoproducto = codigoproducto;
        this.precio = precio;
    }

    public String getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @XmlTransient
    public Collection<Ventas> getVentasCollection() {
        return ventasCollection;
    }

    public void setVentasCollection(Collection<Ventas> ventasCollection) {
        this.ventasCollection = ventasCollection;
    }

    public Productospool getProductospool() {
        return productospool;
    }

    public void setProductospool(Productospool productospool) {
        this.productospool = productospool;
    }

    @XmlTransient
    public Collection<Stock> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<Stock> stockCollection) {
        this.stockCollection = stockCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoproducto != null ? codigoproducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productostienda)) {
            return false;
        }
        Productostienda other = (Productostienda) object;
        if ((this.codigoproducto == null && other.codigoproducto != null) || (this.codigoproducto != null && !this.codigoproducto.equals(other.codigoproducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoproducto;
    }
    
}
