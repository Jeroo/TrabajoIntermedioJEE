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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author scuev
 */
@Entity
@Table(name = "TIPOSUSUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiposusuarios.findAll", query = "SELECT t FROM Tiposusuarios t")
    , @NamedQuery(name = "Tiposusuarios.findByCodigotipousuario", query = "SELECT t FROM Tiposusuarios t WHERE t.codigotipousuario = :codigotipousuario")
    , @NamedQuery(name = "Tiposusuarios.findByTipo", query = "SELECT t FROM Tiposusuarios t WHERE t.tipo = :tipo")
    , @NamedQuery(name = "Tiposusuarios.findByDescripcion", query = "SELECT t FROM Tiposusuarios t WHERE t.descripcion = :descripcion")})
public class Tiposusuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGOTIPOUSUARIO")
    private Integer codigotipousuario;
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigotipousuario")
    private Collection<Usuarios> usuariosCollection;

    public Tiposusuarios() {
    }

    public Tiposusuarios(Integer codigotipousuario) {
        this.codigotipousuario = codigotipousuario;
    }

    public Tiposusuarios(Integer codigotipousuario, String tipo, String descripcion) {
        this.codigotipousuario = codigotipousuario;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Integer getCodigotipousuario() {
        return codigotipousuario;
    }

    public void setCodigotipousuario(Integer codigotipousuario) {
        this.codigotipousuario = codigotipousuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigotipousuario != null ? codigotipousuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiposusuarios)) {
            return false;
        }
        Tiposusuarios other = (Tiposusuarios) object;
        if ((this.codigotipousuario == null && other.codigotipousuario != null) || (this.codigotipousuario != null && !this.codigotipousuario.equals(other.codigotipousuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return tipo;
    }
    
}
