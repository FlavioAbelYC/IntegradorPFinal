package entidades;
public class Producto {
    private int idproducto;
    private int idtienda;
    private int idcate;
    private String nombreprod;
    private String descriprod;
    private String marcaprod;
    private String presenprod;
    private Float precioprod;
    private int stockprod;
    private int estadoprod;
    private String categoria;

    public Producto() {
    }

    public Producto(int idproducto, int idtienda, int idcate, String nombreprod, String descriprod, String marcaprod, String presenprod, Float precioprod, int stockprod, int estadoprod, String categoria) {
        this.idproducto = idproducto;
        this.idtienda = idtienda;
        this.idcate = idcate;
        this.nombreprod = nombreprod;
        this.descriprod = descriprod;
        this.marcaprod = marcaprod;
        this.presenprod = presenprod;
        this.precioprod = precioprod;
        this.stockprod = stockprod;
        this.estadoprod = estadoprod;
        this.categoria = categoria;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getIdtienda() {
        return idtienda;
    }

    public void setIdtienda(int idtienda) {
        this.idtienda = idtienda;
    }

    public int getIdcate() {
        return idcate;
    }

    public void setIdcate(int idcate) {
        this.idcate = idcate;
    }

    public String getNombreprod() {
        return nombreprod;
    }

    public void setNombreprod(String nombreprod) {
        this.nombreprod = nombreprod;
    }

    public String getDescriprod() {
        return descriprod;
    }

    public void setDescriprod(String descriprod) {
        this.descriprod = descriprod;
    }

    public String getMarcaprod() {
        return marcaprod;
    }

    public void setMarcaprod(String marcaprod) {
        this.marcaprod = marcaprod;
    }

    public String getPresenprod() {
        return presenprod;
    }

    public void setPresenprod(String presenprod) {
        this.presenprod = presenprod;
    }

    public Float getPrecioprod() {
        return precioprod;
    }

    public void setPrecioprod(Float precioprod) {
        this.precioprod = precioprod;
    }

    public int getStockprod() {
        return stockprod;
    }

    public void setStockprod(int stockprod) {
        this.stockprod = stockprod;
    }

    public int getEstadoprod() {
        return estadoprod;
    }

    public void setEstadoprod(int estadoprod) {
        this.estadoprod = estadoprod;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
}
