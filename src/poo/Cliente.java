package poo;
import java.util.List;

public class Cliente {

    private String nombre;
    private String apellido;
    private String telefono;
    private boolean vip;
    private ListaPresupuestos presupuestos;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String telefono, boolean vip) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.vip = vip;
        presupuestos = new ListaPresupuestos();
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public ListaPresupuestos getPresupuestos() {
        return presupuestos;
    }

    public void setPresupuestos(ListaPresupuestos presupuestos) {
        this.presupuestos = presupuestos;
    }
}