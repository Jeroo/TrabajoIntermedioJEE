/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author scuev
 */
@Entity
@Table(name = "USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByCodigousuario", query = "SELECT u FROM Usuarios u WHERE u.codigousuario = :codigousuario")
    , @NamedQuery(name = "Usuarios.findByNombre", query = "SELECT u FROM Usuarios u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuarios.findByApellidos", query = "SELECT u FROM Usuarios u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "Usuarios.findByClave", query = "SELECT u FROM Usuarios u WHERE u.clave = :clave")
    , @NamedQuery(name = "Usuarios.findByTarjetacredito", query = "SELECT u FROM Usuarios u WHERE u.tarjetacredito = :tarjetacredito")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGOUSUARIO")
    private Integer codigousuario;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "CLAVE")
    private String clave;
    @Column(name = "TARJETACREDITO")
    private String tarjetacredito;
    @JoinColumn(name = "CODIGOTIPOUSUARIO", referencedColumnName = "CODIGOTIPOUSUARIO")
    @ManyToOne(optional = false)
    private Tiposusuarios codigotipousuario;

    public Usuarios() {
    }

    public Usuarios(Integer codigousuario) {
        this.codigousuario = codigousuario;
    }

    public Usuarios(Integer codigousuario, String nombre, String apellidos, String usuario, String clave) {
        this.codigousuario = codigousuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.clave = clave;
    }

    public Integer getCodigousuario() {
        return codigousuario;
    }

    public void setCodigousuario(Integer codigousuario) {
        this.codigousuario = codigousuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTarjetacredito() {
        return tarjetacredito;
    }

    public void setTarjetacredito(String tarjetacredito) {
        this.tarjetacredito = tarjetacredito;
    }

    public Tiposusuarios getCodigotipousuario() {
        return codigotipousuario;
    }

    public void setCodigotipousuario(Tiposusuarios codigotipousuario) {
        this.codigotipousuario = codigotipousuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigousuario != null ? codigousuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.codigousuario == null && other.codigousuario != null) || (this.codigousuario != null && !this.codigousuario.equals(other.codigousuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return usuario;
    }
    
}
