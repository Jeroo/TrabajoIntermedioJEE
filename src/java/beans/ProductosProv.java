/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "PRODUCTOS_PROV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosProv.findAll", query = "SELECT p FROM ProductosProv p")
    , @NamedQuery(name = "ProductosProv.findByCodigoproducto", query = "SELECT p FROM ProductosProv p WHERE p.productosProvPK.codigoproducto = :codigoproducto")
    , @NamedQuery(name = "ProductosProv.findByCif", query = "SELECT p FROM ProductosProv p WHERE p.productosProvPK.cif = :cif")
    , @NamedQuery(name = "ProductosProv.findByNombre", query = "SELECT p FROM ProductosProv p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "ProductosProv.findByPrecio", query = "SELECT p FROM ProductosProv p WHERE p.precio = :precio")
    , @NamedQuery(name = "ProductosProv.findByCantidad", query = "SELECT p FROM ProductosProv p WHERE p.cantidad = :cantidad")
    , @NamedQuery(name = "ProductosProv.findByFechaent", query = "SELECT p FROM ProductosProv p WHERE p.fechaent = :fechaent")})
public class ProductosProv implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductosProvPK productosProvPK;
    @Column(name = "NOMBRE")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO")
    private Double precio;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    @Column(name = "FECHAENT")
    @Temporal(TemporalType.DATE)
    private Date fechaent;
    @JoinColumn(name = "CIF", referencedColumnName = "CIF", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Proveedores proveedores;

    public ProductosProv() {
    }

    public ProductosProv(ProductosProvPK productosProvPK) {
        this.productosProvPK = productosProvPK;
    }

    public ProductosProv(String codigoproducto, String cif) {
        this.productosProvPK = new ProductosProvPK(codigoproducto, cif);
    }

    public ProductosProvPK getProductosProvPK() {
        return productosProvPK;
    }

    public void setProductosProvPK(ProductosProvPK productosProvPK) {
        this.productosProvPK = productosProvPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaent() {
        return fechaent;
    }

    public void setFechaent(Date fechaent) {
        this.fechaent = fechaent;
    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productosProvPK != null ? productosProvPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosProv)) {
            return false;
        }
        ProductosProv other = (ProductosProv) object;
        if ((this.productosProvPK == null && other.productosProvPK != null) || (this.productosProvPK != null && !this.productosProvPK.equals(other.productosProvPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  productosProvPK.getCodigoproducto()+"-"+productosProvPK.getCif()+"-"+nombre+"-"+cantidad+"-"+precio;
    }
    
}
