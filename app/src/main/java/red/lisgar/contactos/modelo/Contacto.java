package red.lisgar.contactos.modelo;

public class Contacto {
    //Declrarcion de atributos
    private int id;
    private String nombre;
    private String telefono;
    private String correo_electronico;
    private String agrego_correo;

    //Encapsulacion
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getAgrego_correo() {
        return agrego_correo;
    }

    public void setAgrego_correo(String agrego_correo) {
        this.agrego_correo = agrego_correo;
    }
}
