/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeans;

import beans.Productospool;
import beans.ProductospoolFacade;
import beans.Productostienda;
import beans.ProductostiendaFacade;
import beans.Stock;
import beans.StockFacade;
import beans.Tiposusuarios;
import beans.TiposusuariosFacade;
import beans.Usuarios;
import beans.UsuariosFacade;
import beans.Ventas;
import beans.VentasFacade;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import javax.persistence.criteria.Path;
import javax.servlet.ServletContext;
import static javax.servlet.SessionTrackingMode.URL;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.whois.WhoisClient;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;



/**
 *
 * @author scuev
 */
@ManagedBean
@RequestScoped
public class ProdMB {

    @EJB
    private TiposusuariosFacade tiposusuariosFacade;

    @EJB
    private VentasFacade ventasFacade;

    @EJB
    private UsuariosFacade usuariosFacade;

    @EJB
    private StockFacade stockFacade;

    @EJB
    private ProductospoolFacade productospoolFacade;

    @EJB
    private ProductostiendaFacade productostiendaFacade;
    

    /**
     * Creates a new instance of ProdMB
     */
    public ProdMB() {
    }
    
    

    // Objetos declaración
    private Tiposusuarios objTodigotipousuario;
    private Usuarios objUsurios;
    private Productostienda productostienda;
    private Productospool productospool;
    private Ventas ventas;
    private Stock objStock;
    //Obtenemos la fecha actual para alamcenarla posteriormente
    Calendar calendar = Calendar.getInstance();

  
    
    
    //Listas de Objetos del sistema    
    private List<Productostienda> listaProductostienda;    
    private List<Productospool> listaProductospool;
    private List<Ventas> listaVentas;
    private List<Usuarios> listaUsuarios;
    private List<Stock> listaStock;

   
    
    // Propiedades de cada objeto a utilizar
    private int codigousuario;  
    private String nombreUsuario;   
    private String apellidos; 
    private String usuario;   
    private String clave;  
    private String tarjetacredito;
    private String nombreProducto;   
    private String codigoProducto;
    private String nombre;
    private String descripcion;
    private String img;
    private boolean ponerTienda = true;
    private String page = "clientes";
    private double precio;  
    private int codigostock;
    private int cantidad;
    private Date fechareposicion;   
    private int cantidadCarrito = 0;
    
    
    public Stock getObjStock() {
        return objStock;
    }

    public StockFacade getStockFacade() {
        return stockFacade;
    }    
    

    public void setObjStock(Stock objStock) {
        this.objStock = objStock;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public Tiposusuarios getObjTodigotipousuario() {
        return objTodigotipousuario;
    }

    public void setObjTodigotipousuario(Tiposusuarios objTodigotipousuario) {
        this.objTodigotipousuario = objTodigotipousuario;
    }

    public Usuarios getObjUsurios() {
        return objUsurios;
    }

    public void setObjUsurios(Usuarios objUsurios) {
        this.objUsurios = objUsurios;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }

    public List<Ventas> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Ventas> listaVentas) {
        this.listaVentas = listaVentas;
    }

    public List<Usuarios> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public int getCodigousuario() {
        return codigousuario;
    }

    public void setCodigousuario(int codigousuario) {
        this.codigousuario = codigousuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public boolean isPonerTienda() {
        return ponerTienda;
    }

    public void setPonerTienda(boolean ponerTienda) {
        this.ponerTienda = ponerTienda;
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

    public int getCodigostock() {
        return codigostock;
    }

    public void setCodigostock(int codigostock) {
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
        return usuario;
    }

    public void setUsuariorepuso(String usuariorepuso) {
        this.usuario = usuariorepuso;
    }

    public Productospool getProductospool() {
        return productospool;
    }

    public void setProductospool(Productospool productospool) {
        this.productospool = productospool;
    }

    public List<Productospool> getListaProductospool() {
        return listaProductospool;
    }

    public void setListaProductospool(List<Productospool> listaProductospool) {
        this.listaProductospool = listaProductospool;
    }   

    /*public String getPage() {
        return page;
    }*/
    
    public String getPage() {
        listaUsuarios = usuariosFacade.getUsuario(usuario);
        if (listaUsuarios.size() <= 0) {
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario no encontrado debe registrarse si no tiene una cuenta"));

            usuario = null;
            return null;
        } else {
            Usuarios us = listaUsuarios.get(0);
            if (us.getClave().equals(clave)) {
                String tipo = us.toString();
                FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario Autenticado:\n" + us.getUsuario()));

                page = tipo;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipo", tipo);
                //String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
                
            } else {
                usuario = null;
                FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Contraseña incorrecta"));
                return null;

            }
        }
        return page;
    } 
    
    
    public String logout() {
        
        //Destruimos la session del usuario logeado en la tienda
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario LogOut"));
        page = "Clientes";
        
        return page;
    }
    
    public String registroUsuarios() {
        
        listaUsuarios = usuariosFacade.getUsuario(usuario);
        
        if (listaUsuarios.size() > 0) {            
            
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario tiene una cuenta"));

            
        } else {
            
             List<Usuarios> u = null;
             u = usuariosFacade.getUltimoUsuario();
             
             if (u.size() > 0) {
                Usuarios usr = u.get(u.size()-1);
                codigousuario = usr.getCodigousuario()+1;
                List<Tiposusuarios> tu = tiposusuariosFacade.getTipoUsuario();
                Usuarios us = new Usuarios(codigousuario, nombre, apellidos, usuario, clave,tarjetacredito,tu.get(0));
                usuariosFacade.create(us);
                
                FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario creado con exito, ahora puede realizar compras en la tienda"));

                page = "Clientes";
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", usuario);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipo", tu.get(0).getTipo());
                
            }else{
             
                FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Usuario no se pudo crear"));

                page = "Clientes";
             }
            
             
        }
        return page;
    } 
    
       
    public int agregarAlCarrito() {
        
        cantidadCarrito++;       
        
        return cantidadCarrito;
    }

    public void setPage(String page) {
        this.page = page;
    }
    
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
    
     public List<Stock> getListaStock() {
         
         if (listaStock == null) {
           listaStock = stockFacade.findAll();
        }
        return listaStock;
    }

    public void setListaStock(List<Stock> listaStock) {
        this.listaStock = listaStock;
    }
    
     public int getCantidadStock(Productostienda producto) { 
         
       
        List<Stock> st = getStockFacade().getStockPorProducto(producto);
        
         if (st.size() > 0) {
             
             return st.get(0).getCantidad() > 0 ? st.get(0).getCantidad() : 0;
             
         }else{
         
            return 0;
         }
        
     }
     
     
    public void reponeStock() { 
        
        try {
            
            //comprobamos que no existe
            List<Stock> st = null;
            productostienda = productostiendaFacade.find(codigoProducto);
            st = stockFacade.getStockPorProducto(productostienda);            
            if (st.size() > 0) {
                usuario = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");               
                objStock = st.get(0);
                objStock.setCantidad(objStock.getCantidad()+cantidad);
                objStock.setUsuariorepuso(usuario); 
                objStock.setFechareposicion(calendar.getTime().toString());                               
                stockFacade.edit(objStock);
                FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Reposición corresta"));
                listaStock = null;
            } else {
                FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No existe el producto en stock."+codigoProducto));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Reposición de producto incorrecta: " + e.toString()));
        }        
    }
    
    /** En este metodo guardamos la imagen que se muestra cada producto
       * @author  Salvador Cuevas
       * @version 1.0
       * @param event captura el evento del FileUpload que tiene el archivo tipo imagen para su tratamiento
     * @throws java.io.IOException
       * @since   18-01-2018
    */    
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        
        /** 
         * Capturamos el codigo del producto o el label que identifica donde esta el control             
         */        
        codigoProducto = (String) event.getComponent().getAttributes().get("codigo"); 
        if (codigoProducto != null && !"".equals(codigoProducto) && !"P-".equals(codigoProducto)) {
            
            // verificamos si el label indica que no esta en el listado de los productos la imagen a agregar
            
            if ("agregar".equals(codigoProducto)) {
                
                List<Productospool> p = null;
                p = productospoolFacade.getUltimoProducto();
                Productospool pp = new Productospool();
                pp = p.get(p.size() -1);
                
                codigoProducto = pp.getCodigoproducto();
                if (codigoProducto != null && !codigoProducto.isEmpty()) {
                    
                   // LLamamos el metodo que incrementa en 1 el codigo del producto
                    codigoProducto = incrementarCodigoProducto(codigoProducto);
                    
                }else {
                    
                   codigoProducto = "P-0001";
                  
                }
                
            }
                
            if (!"agregar".equals(codigoProducto)) {
                
                String path = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/");
                SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
                String name = fmt.format(new Date())
                        + event.getFile().getFileName().substring(
                              event.getFile().getFileName().lastIndexOf('.'));

                File file = new File(path + "resources/images/productos/" + codigoProducto+".jpg");

                InputStream is = event.getFile().getInputstream();
                OutputStream out = new FileOutputStream(file);
                byte buf[] = new byte[1024];
                int len;
                while ((len = is.read(buf)) > 0)
                    out.write(buf, 0, len);
                is.close();
                out.close();


                FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " fue subido");
                FacesContext.getCurrentInstance().addMessage(null, message);
                
            }else{
            
                 FacesMessage message = new FacesMessage("Error", " no ha indicado el codigo del producto");
                 FacesContext.getCurrentInstance().addMessage(null, message);
            }
             
        }else{
        
            FacesMessage message = new FacesMessage("Error", " no ha indicado el codigo del producto");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
       
    }
    
    public String incrementarCodigoProducto(String codigo){
    
    
        String[] parts = codigo.split("-");
        int incrementarCodigoProducto  = Integer.parseInt(parts[1]);
        incrementarCodigoProducto = incrementarCodigoProducto + 1;

        switch (String.valueOf(incrementarCodigoProducto).length()) {
            case 1:
                codigo = "P-000" + incrementarCodigoProducto;
                break;
            case 2:
                codigo = "P-00" + incrementarCodigoProducto;
                break;
            case 3:
                codigo = "P-0" + incrementarCodigoProducto;
                break;
            case 4:
                codigo = String.valueOf(incrementarCodigoProducto);
                break;
            default:
                break;
        }
        
        return codigo;
    }
    
     public void altaProducto() {               
                 
         try {
             
            //comprobamos que no existe
            Productospool p = null;                
            List<Productospool> lpp = null;
            lpp = productospoolFacade.getUltimoProducto();
            p = new Productospool();
            p = lpp.get(lpp.size() -1);
            codigoProducto = p.getCodigoproducto();

            if (codigoProducto != null && !codigoProducto.isEmpty()) {

               // LLamamos el metodo que incrementa en 1 el codigo del producto
                codigoProducto = incrementarCodigoProducto(codigoProducto);

            }else {

               codigoProducto = "P-0001";

            }

            p = new Productospool(codigoProducto,nombreProducto, descripcion, "urlImg");
            productospoolFacade.create(p);        

            if (ponerTienda) {   
                
                usuario = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
                Productostienda pt = null;
                pt = productostiendaFacade.find(codigoProducto);          
               
                if (pt == null) {
                    
                    pt = new Productostienda(codigoProducto, precio);
                    productostiendaFacade.create(pt);                     

                }else {
                    
                     //pt.setCodigoproducto(codigoProducto);
                     pt.setPrecio(precio);
                     productostiendaFacade.edit(pt);                     
                    
                }  
                
                List<Stock> st = null;
                Stock objst = null;

                 st = stockFacade.getStockPorProducto(pt);
                 if (st.size() <= 0) {
                  
                   st = stockFacade.getUltimoStock();
                   final Comparator<Stock> comp = (p1, p2) -> Integer.compare( p1.getCodigostock(), p2.getCodigostock());
                   Stock stock = st.stream()
                                          .max(comp)
                                          .get();
                   objst = new Stock(stock.getCodigostock()+1,cantidad, usuario,calendar.getTime().toString());
                   objst.setCodigoproducto(pt);
                   stockFacade.create(objst);

                }else{

                   objst = stockFacade.find(st.get(st.size() -1).getCodigostock());
                   objst.setCantidad(objst.getCantidad()+cantidad);
                   objst.setFechareposicion(calendar.getTime().toString());
                   objst.setUsuariorepuso(usuario);
                   stockFacade.edit(objst);
                   
                } 
                
                

           }                   
                
          FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Alta de producto correcta."));
           
                
        } catch (EJBException e) {
            
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Alta de producto incorrecta: " + e.toString()));
        }
    }   
   
     
     
    //edición y guardado de la modificación de una fila
    public void onRowEdit(RowEditEvent event) {
        
        productostienda = (Productostienda) event.getObject();          
        productostiendaFacade.edit(productostienda);
        productospoolFacade.edit(productostienda.getProductospool());
        FacesMessage msg = new FacesMessage("Producto modificado:", productostienda.getProductospool().getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //edición y cancelación de la modificación de una fila
    public void onRowCancel(RowEditEvent event) {
        productostienda = (Productostienda) event.getObject();
        FacesMessage msg = new FacesMessage("Modificación cancelada", productostienda.getProductospool().getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
}
