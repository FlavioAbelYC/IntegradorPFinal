package entidades;
public class Tienda {
    private int id;
    private int idus;
    private String ruc;
    private String nombre;
    private int estado;
    private int iddetalle;
    private int iddistrito;
    private String direccion;
    private String latitud;
    private String longitud;

    public Tienda() {
    }

    public Tienda(int id, int idus, String ruc, String nombre, int estado, int iddetalle, int iddistrito, String direccion, String latitud, String longitud) {
        this.id = id;
        this.idus = idus;
        this.ruc = ruc;
        this.nombre = nombre;
        this.estado = estado;
        this.iddetalle = iddetalle;
        this.iddistrito = iddistrito;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdus() {
        return idus;
    }

    public void setIdus(int idus) {
        this.idus = idus;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public int getIddistrito() {
        return iddistrito;
    }

    public void setIddistrito(int iddistrito) {
        this.iddistrito = iddistrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    
    
}
