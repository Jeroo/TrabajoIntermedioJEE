/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeans;


import beans.ProductosProv;
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
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.ws.rs.ClientErrorException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.uah.viewmodels.OrdenCarrito;
import proveedores.ClienteProveedores;



/**
 *
 * @author scuev
 */
@ManagedBean
/*@RequestScoped*/
@ViewScoped
public class ProdMB implements Serializable {

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
    
    ClienteProveedores proveedores = new ClienteProveedores();   
   

    /**
     * Creates a new instance of ProdMB
     */
    public ProdMB() {
        
       
    }
   
    

    // Objetos declaración
    private Tiposusuarios objTodigotipousuario;
    private ProductosProv objProductosProv;
    private Usuarios objUsurios;
    private Productostienda productostienda;
    private Productospool productospool;
    private Ventas ventas;
    private Stock objStock;
    private OrdenCarrito objOrdenCarrito;
    //Obtenemos la fecha actual para alamcenarla posteriormente
    Calendar calendar = Calendar.getInstance();
    Date myDate = new Date();

  
    
    
    //Listas de Objetos del sistema    
    private List<Productostienda> listaProductostienda;  
    private List<ProductosProv> listaProductosProv;
    private List<ProductosProv> listaProductosProvSelect;
    private List<Productospool> listaProductospool;
    private List<Ventas> listaVentas;
    private List<Usuarios> listaUsuarios;
    private List<Stock> listaStock;
    private List<OrdenCarrito> listaOrdenCarrito;
    private List<OrdenCarrito> listaOrdenCarritoSeleccionar;
    private List<String> listaCantidad;
   

   
    
    // Propiedades de cada objeto a utilizar
    private int codigousuario;  
    private String nombreUsuario; 
    private Date fecha;
    private String cif; 
    private String nombreProv; 
    private String direccionProv;
    private String codigoProductoProv;
    private String tfnoProv;
    private int cantidadProv;
    private double precioProv;
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
    private String cantidadSelect; 
    private Date fechareposicion;   
    private int cantidadCarrito = 0;

    public String getCodigoProductoProv() {
        return codigoProductoProv;
    }

    public void setCodigoProductoProv(String codigoProductoProv) {
        this.codigoProductoProv = codigoProductoProv;
    }

    
    public ProductosProv getObjProductosProv() {
        return objProductosProv;
    }

    public void setObjProductosProv(ProductosProv objProductosProv) {
        this.objProductosProv = objProductosProv;
    }

    public List<ProductosProv> getListaProductosProv() {
        
        if (listaProductosProv == null) {
        try {
            Gson gson = new Gson();
            String lista = proveedores.findAll_JSON(String.class);
            Type tipoListaProv = new TypeToken<List<ProductosProv>>() {
            }.getType();
            List<ProductosProv> listac = gson.fromJson(lista, tipoListaProv);
            listaProductosProv = listac;
            // FacesContext.getCurrentInstance().addMessage("men", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Listado generado correctamente."));
        } catch (ClientErrorException | JsonParseException | NumberFormatException e) {
                 FacesContext.getCurrentInstance().addMessage("men", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Listado erróneo: " + e.toString()));
            }
        }
        return listaProductosProv;
    }

    public List<ProductosProv> getListaProductosProvSelect() {
        return listaProductosProvSelect;
    }

    public void setListaProductosProvSelect(List<ProductosProv> listaProductosProvSelect) {
        this.listaProductosProvSelect = listaProductosProvSelect;
    }
    
    

    public void setListaProductosProv(List<ProductosProv> listaProductosProv) {
        this.listaProductosProv = listaProductosProv;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public String getDireccionProv() {
        return direccionProv;
    }

    public void setDireccionProv(String direccionProv) {
        this.direccionProv = direccionProv;
    }

    public String getTfnoProv() {
        return tfnoProv;
    }

    public void setTfnoProv(String tfnoProv) {
        this.tfnoProv = tfnoProv;
    }

    public int getCantidadProv() {
        return cantidadProv;
    }

    public void setCantidadProv(int cantidadProv) {
        this.cantidadProv = cantidadProv;
    }

    public double getPrecioProv() {
        return precioProv;
    }

    public void setPrecioProv(double precioProv) {
        this.precioProv = precioProv;
    }
    
    
    
    

    public List<OrdenCarrito> getListaOrdenCarritoSeleccionar() {
        return listaOrdenCarritoSeleccionar;
    }

    public void setListaOrdenCarritoSeleccionar(List<OrdenCarrito> listaOrdenCarritoSeleccionar) {
        this.listaOrdenCarritoSeleccionar = listaOrdenCarritoSeleccionar;
    }  
    
    

    public String getCantidadSelect() {
        return cantidadSelect;
    }

    public void setCantidadSelect(String cantidadSelect) {
        this.cantidadSelect = cantidadSelect;
    }
    
    

    public OrdenCarrito getObjOrdenCarrito() {
        return objOrdenCarrito;
    }

    public void setObjOrdenCarrito(OrdenCarrito objOrdenCarrito) {
        this.objOrdenCarrito = objOrdenCarrito;
    }

    public List<String> getListaCantidad() {
        return listaCantidad;
    }

    public void setListaCantidad(List<String> listaCantidad) {
        this.listaCantidad = listaCantidad;
    }   
    
    public void onDateSelect(SelectEvent event) {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date sqlDate = new java.sql.Date((long) event.getObject());
        fecha = sqlDate;
        //facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
        FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Selecciono la Fecha: "+fecha));

    }
   
    public List<OrdenCarrito> getListaOrdenCarrito() {
                
        if (listaOrdenCarrito == null) {
            
            listaOrdenCarrito = (List<OrdenCarrito>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("listaOrdenCarrito");
            
            return listaOrdenCarrito;
        }
       
        return listaOrdenCarrito;
    }

    public void setListaOrdenCarrito(List<OrdenCarrito> listaOrdenCarrito) {
        this.listaOrdenCarrito = listaOrdenCarrito;
    }

    public int getCantidadCarrito() {
        return cantidadCarrito;
    }

    public void setCantidadCarrito(int cantidadCarrito) {
        this.cantidadCarrito = cantidadCarrito;
    }
    
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
        
        if (listaVentas == null) {
            
            listaVentas = ventasFacade.findAll();
            
            return listaVentas;
        }
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
        
        if (listaProductospool == null) {
            
           listaProductospool = productospoolFacade.findAll();
        }
        return listaProductospool;
    }

    public void setListaProductospool(List<Productospool> listaProductospool) {
        this.listaProductospool = listaProductospool;
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

    /*public String getPage() {
        return page;
    }*/
    
    public String getPage() {
        listaUsuarios = usuariosFacade.getUsuario(usuario);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cantidadCarrito", 0);
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
                FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Reposición corresta en el Stock de la Tienda de Computadoras"));
                listaStock = null;
            } else {
                FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No existe el producto en stock."+codigoProducto));
                return;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Reposición de producto incorrecta: " + e.toString()));
             return;
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
    
    public String Comprar(){
     
        listaOrdenCarrito = (List<OrdenCarrito>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("listaOrdenCarrito");
        try {
            List<Stock> st = null;
            for (OrdenCarrito ordenCarrito : listaOrdenCarrito) {
            
                cantidad = ordenCarrito.getCantidad();
                productostienda = productostiendaFacade.find(ordenCarrito.getCodigoProducto());
                st = stockFacade.getStockPorProducto(productostienda); 

                if (st.size() > 0) {

                    usuario = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");               
                    objStock = st.get(0);
                    objStock.setCantidad(objStock.getCantidad()-cantidad);
                    //objStock.setUsuariorepuso(usuario); 
                    //objStock.setFechareposicion(calendar.getTime().toString());                               
                    stockFacade.edit(objStock);
                    
                    Ventas v = null;
                    List<Ventas> lv = ventasFacade.findAll();
                    
                    if (lv.size() > 0) {
                        
                         //Guardamos la compra en la tabla ventas
                        final Comparator<Ventas> comp = (p1, p2) -> Integer.compare( p1.getCodigoventa(), p2.getCodigoventa());
                        Ventas ventas = lv.stream()
                                               .max(comp)
                                               .get();
                         v = new Ventas(ventas.getCodigoventa()+1, productostienda, cantidad, usuario, calendar.getTime().toString());
                         
                         
                    }else {
                    
                         v = new Ventas(1, productostienda, cantidad, usuario, calendar.getTime().toString());
                    
                    }
                    
                    ventasFacade.create(v);
                 
                    FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Compra del producto codigo: "+productostienda.getCodigoproducto()+" fue realizada correctamente"));
                    listaStock = null;                  
                

                } else {
                    FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No existe el producto en stock. "+ordenCarrito.getCodigoProducto()));
                }           
           }
            
            listaOrdenCarrito = null;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaOrdenCarrito", null);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cantidadCarrito", 0);
            
            page = "Clientes";
        
            return page;
           
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Hubo un error al realizar la compra: " + e.toString()));
        }         
               
        return null;
    }
    
    public double getTotalCompra(int cantidad, String codigoProducto){
        
        double total = 0.0;
        
        if (listaOrdenCarrito != null) {
            cantidadCarrito = listaOrdenCarrito.size();
            for (OrdenCarrito orden : listaOrdenCarrito) {  
                 if ( orden.getCodigoProducto().equals(codigoProducto)) {

                     total += cantidad * orden.getPrecio();

                  }else{
                     
                     total += orden.getCantidad() * orden.getPrecio();

                 }
            }  
            
        }
        
        return total;
    }    
    
    public void handleChangeCarrito(ValueChangeEvent event){
    
        String  codigoProductoSelect = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigoProductoSelect");
        Boolean labelVerificarSelect = false;
        if (codigoProductoSelect != null) {
             
             codigoProducto = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigoProductoSelect");
             labelVerificarSelect = true;

         }
        
         if (codigoProducto != null) {
            
            productospool = productospoolFacade.find(codigoProducto);
            productostienda = productostiendaFacade.find(codigoProducto);
            
            nombreProducto =  productospool.getNombre();
            descripcion =  productospool.getDescripcion();
            precio = productostienda.getPrecio();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productospool", productospool);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productostienda", productostienda);
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Cambio la cantidad a comprar del producto: "+codigoProducto+"-"+nombreProducto));

            
            try {
                objStock = stockFacade.getStockPorProducto(productostienda).get(0);
                cantidad = stockFacade.getStockPorProducto(productostienda) != null ? stockFacade.getStockPorProducto(productostienda).get(0).getCantidad() : 0;      
                
                 
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objStock", objStock);
                  
            } catch (Exception e) {
               String error = ""+e;
            }
          
            
            
        }
      
        if (labelVerificarSelect) {


            cantidadSelect = event.getNewValue().toString();
            cantidad = Integer.parseInt(cantidadSelect);
            precio = getTotalCompra(cantidad, codigoProducto);

            listaOrdenCarrito = (List<OrdenCarrito>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("listaOrdenCarrito");

             for (OrdenCarrito orden : listaOrdenCarrito) {                     

                 if (orden.getCodigoProducto().equals(codigoProducto)) {

                     orden.setCantidad(cantidad);

                  }
            } 

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaOrdenCarrito", listaOrdenCarrito);

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cantidad", cantidad);


        }
    
    }
    
    public void handleChange(){   

       
        
        if (codigoProducto != null) {
            
            productospool = productospoolFacade.find(codigoProducto);
            productostienda = productostiendaFacade.find(codigoProducto);
            
            nombreProducto =  productospool.getNombre();
            descripcion =  productospool.getDescripcion();
            precio = productostienda.getPrecio();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productospool", productospool);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productostienda", productostienda);
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Selección del producto: "+codigoProducto+"-"+nombreProducto));

            
            try {
                objStock = stockFacade.getStockPorProducto(productostienda).get(0);
                cantidad = stockFacade.getStockPorProducto(productostienda) != null ? stockFacade.getStockPorProducto(productostienda).get(0).getCantidad() : 0;      
                
                 
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("objStock", objStock);
                  
            } catch (Exception e) {
               String error = ""+e;
            }
          
            
            
        }
         
   }
    
    public void borrarProducto() { 
      
      try
        {
          productospool = (Productospool)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productospool");
          productostienda = (Productostienda)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productostienda");
          objStock = (Stock)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("objStock");
            
          stockFacade.remove(objStock);          
          productostiendaFacade.remove(productostienda);
          productospoolFacade.remove(productospool);   
          listaProductostienda = null;
          listaProductospool = null;
          
          FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Fue eliminado el producto"+codigoProducto+"-"+productospool.getNombre()+" correctamente."));
           
                
        } catch (EJBException e) {
            
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "No se pudo eliminar el producto. Error: " + e.toString()));
        }
     
     }
    
    public void modificarProducto() { 
         
         try {
                
                Productospool p = productospoolFacade.find(codigoProducto);
                p.setDescripcion(descripcion);
                p.setImg("urlImg");
                p.setNombre(nombreProducto);
                p.setProductostienda(productostienda);
                productospoolFacade.edit(p); 
                
                Productostienda pt = productostiendaFacade.find(p.getCodigoproducto());
                pt.setPrecio(precio);                
                productostiendaFacade.edit(pt);
                
                List<Stock> st = stockFacade.getStockPorProducto(pt);
                Stock stk = null;
                if (st.size() > 0) {
                    
                   usuario = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
                   stk = stockFacade.find(st.get(0).getCodigostock());
                   stk.setCantidad(cantidad);
                   stk.setFechareposicion(calendar.getTime().toString());
                   stk.setUsuariorepuso(usuario);
                   stockFacade.edit(stk);
                    
                 
                }else {
                
                   st = stockFacade.getUltimoStock();
                   final Comparator<Stock> comp = (p1, p2) -> Integer.compare( p1.getCodigostock(), p2.getCodigostock());
                   Stock stock = st.stream()
                                          .max(comp)
                                          .get();
                   stk = new Stock(stock.getCodigostock()+1,cantidad, usuario,calendar.getTime().toString());
                   stk.setCodigoproducto(pt);
                   stockFacade.create(stk);
                
                }              
                
                
                
          FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "modificación del producto"+codigoProducto+"-"+nombreProducto+" correcta."));
           
                
        } catch (EJBException e) {
            
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "modificación del producto incorrecta: " + e.toString()));
        }
       
         
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
    
    public String agregarAlCarrito() {
        
          codigoProducto = (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigoProductoSelect");
        if (codigoProducto != null) {
            
            try {
                
                productospool = productospoolFacade.find(codigoProducto);
                productostienda = productostiendaFacade.find(codigoProducto);

                nombreProducto =  productospool.getNombre();
                descripcion =  productospool.getDescripcion();
                precio = productostienda.getPrecio();
                cantidad = stockFacade.getStockPorProducto(productostienda) != null ? stockFacade.getStockPorProducto(productostienda).get(0).getCantidad() : 0;
                objStock = stockFacade.getStockPorProducto(productostienda).get(0);
                OrdenCarrito orden = new OrdenCarrito();
                listaCantidad = orden.llenarListaCantidad(objStock);

                OrdenCarrito oc = new OrdenCarrito(nombreProducto, codigoProducto, 1, precio);
                oc.setListCantidad(listaCantidad);                
                
                
                listaOrdenCarrito = (List<OrdenCarrito>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("listaOrdenCarrito");
                if (listaOrdenCarrito == null) {
                   
                    listaOrdenCarrito = new ArrayList<OrdenCarrito>();
                }
                
                listaOrdenCarrito.add(oc);           
                
                 precio = getTotalCompra(1, "");
                 
                 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaOrdenCarrito", listaOrdenCarrito);
                 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cantidadCarrito", cantidadCarrito);
                 
            } catch (Exception e) {
                String error =  ""+e;
            }   
            
            
        }
        
        page = "Clientes";
            
        return page;
        
      
    }
   
    
     public void buscaPorCantidad() {
                   
         try {
             
             if (cantidad < 0 || codigoProducto.isEmpty() || codigoProducto == null) {
                 
                FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Debe completar los campos para realizar la busquedad"));
                listaProductosProv = null;
                getListaProductosProv();
                return;

             }
             
             Gson gson = new Gson();
             String listaProductos =  proveedores.findAllByCantidad_JSON(String.class, codigoProducto, Integer.toString(cantidad));
             Type tipoListaProv = new TypeToken<List<ProductosProv>>() {
             }.getType();
             List<ProductosProv> listaProv = gson.fromJson(listaProductos, tipoListaProv);
             listaProductosProv = listaProv;
             getListaProductosProv();
             
             if (listaProv.size() > 0) {
               
                 FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Listado de productos encontrados. Cantidad encontrada: "+listaProv.size()));
  
             }else {
             
                 FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "No hay resultados para esta busqueda"));

             
             }

             
         } catch (Exception e) {
             
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Listado erróneo: " + e.toString()));

         }
       
       
    }
     
     
     public void buscaPorFecha() {
                   
         try {
             
             if (cantidadProv < 0 || codigoProductoProv.isEmpty() || codigoProductoProv == null || fecha == null) {
                 
                FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Debe completar los campos para realizar la busquedad"));
                listaProductosProv = null;
                getListaProductosProv();
                return;

             }
             
            DateFormat  format = new SimpleDateFormat("yyyy-MM-dd");
            // java.util.Date parsed = format.parse(fecha.toString());
            java.sql.Date sql = new java.sql.Date(fecha.getTime());
            Gson gson = new Gson();
            String listaProductos =  proveedores.findAllByFecha_JSON(String.class, codigoProductoProv, Integer.toString(cantidadProv), sql.toString());
            Type tipoListaProv = new TypeToken<List<ProductosProv>>() {
            }.getType();
            List<ProductosProv> listaProv = gson.fromJson(listaProductos, tipoListaProv);
            listaProductosProv = listaProv; 
             
        
             getListaProductosProv();
             FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Listado de productos encontrados"));

             
         } catch (Exception e) {
             
            FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Listado erróneo: " + e.toString()));

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
    
     //edición y guardado de la modificación de una fila
    public void onRowEditProv(RowEditEvent event) {
        
        try {
            
            objProductosProv = (ProductosProv) event.getObject();          
            codigoProductoProv = objProductosProv.getProductosProvPK().getCodigoproducto();
            
            Gson gson = new Gson();
            String lista = proveedores.findAll_JSON(String.class);
            Type tipoListaProv = new TypeToken<List<ProductosProv>>() {
            }.getType();
            List<ProductosProv> listac = gson.fromJson(lista, tipoListaProv);
            listaProductosProv = listac;
            cantidadProv = objProductosProv.getCantidad();
            
            codigoProducto = objProductosProv.getProductosProvPK().getCodigoproducto();
            cantidad = cantidadProv;
            
            reponeStock();

            for (ProductosProv ppv : listaProductosProv) {
                 
                if (ppv.getProductosProvPK().getCodigoproducto().equals(objProductosProv.getProductosProvPK().getCodigoproducto()) 
                        && ppv.getProductosProvPK().getCif().equals(objProductosProv.getProductosProvPK().getCif())) {
                   
                    if (cantidadProv <= ppv.getCantidad()) {
                        
                        objProductosProv.setCantidad(ppv.getCantidad()-cantidadProv);
                        
                        
                        proveedores.edit_JSON(objProductosProv,objProductosProv.getProductosProvPK().getCodigoproducto());

                        FacesMessage msg = new FacesMessage("Producto Comprado:", objProductosProv.getNombre());
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        
                        return;

                    }else {
                    
                         FacesMessage msg = new FacesMessage("La cantidad introducida es mayor a la cantidad que se tiene en stock para el producto: ", objProductosProv.getNombre()+" Cantidad Disponible en Stock es: "+ppv.getCantidad());
                         FacesContext.getCurrentInstance().addMessage(null, msg);
                         return;
                    }
                    
                }
            }


            
        } catch (Exception e) {
            
            FacesMessage msg = new FacesMessage("Error al realizar la compra intente otra vez. ", e.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
       
    }
    
     //edición y cancelación de la modificación de una fila
    public void onRowCancelProv(RowEditEvent event) {
        objProductosProv = (ProductosProv) event.getObject();
        FacesMessage msg = new FacesMessage("Compra cancelada", objProductosProv.getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    public void eliminarDelCarrito() {
        
        if (listaOrdenCarritoSeleccionar != null && listaOrdenCarritoSeleccionar.size() > 0) {
            
           listaOrdenCarrito = (List<OrdenCarrito>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("listaOrdenCarrito");

            for (OrdenCarrito ordenCarrito : listaOrdenCarritoSeleccionar) {
            
                  listaOrdenCarrito.remove(ordenCarrito);
             }
            
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listaOrdenCarrito", listaOrdenCarrito);
          
           
           precio = getTotalCompra(1, "");
           FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cantidadCarrito", cantidadCarrito);
           FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Producto(s) eliminado(s) de la lista correctamente"));

        }else {
        
          FacesContext.getCurrentInstance().addMessage("msgs", new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Hubo un error al intentar eliminar, es posible que no haya selecionado por lo menos un producto"));

        
        }
    }
    
    
}
