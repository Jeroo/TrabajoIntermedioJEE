/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author scuev
 */
@Entity
@Table(name = "PRODUCTOSPOOL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productospool.findAll", query = "SELECT p FROM Productospool p")
    , @NamedQuery(name = "Productospool.findByCodigoproducto", query = "SELECT p FROM Productospool p WHERE p.codigoproducto = :codigoproducto")
    , @NamedQuery(name = "Productospool.findByNombre", query = "SELECT p FROM Productospool p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Productospool.findByDescripcion", query = "SELECT p FROM Productospool p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Productospool.findByImg", query = "SELECT p FROM Productospool p WHERE p.img = :img")})
public class Productospool implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGOPRODUCTO")
    private String codigoproducto;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "IMG")
    private String img;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productospool")
    private Productostienda productostienda;

    public Productospool() {
    }

    public Productospool(String codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public Productospool(String codigoproducto, String nombre, String descripcion, String img) {
        this.codigoproducto = codigoproducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.img = img;
    }

    public String getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Productostienda getProductostienda() {
        return productostienda;
    }

    public void setProductostienda(Productostienda productostienda) {
        this.productostienda = productostienda;
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
        if (!(object instanceof Productospool)) {
            return false;
        }
        Productospool other = (Productospool) object;
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
