
package entidades;

public class Usuario {
    private int idusuario;
    private String dni;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String email;
    private String password;
    private int idprivilegio;
    private int estado;
    private String nombreprivilegio;
    public Usuario() {
    }

    public Usuario(int idusuario, String dni, String nombres, String apellidos, String direccion, String telefono, String email, String password, int idprivilegio, int estado, String nombreprivilegio) {
        this.idusuario = idusuario;
        this.dni = dni;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.idprivilegio = idprivilegio;
        this.estado = estado;
        this.nombreprivilegio = nombreprivilegio;
    }

    public String getNombreprivilegio() {
        return nombreprivilegio;
    }

    public void setNombreprivilegio(String nombreprivilegio) {
        this.nombreprivilegio = nombreprivilegio;
    }

  
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdprivilegio() {
        return idprivilegio;
    }

    public void setIdprivilegio(int idprivilegio) {
        this.idprivilegio = idprivilegio;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
