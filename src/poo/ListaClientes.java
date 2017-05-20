package poo;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaClientes implements Serializable{

    private ArrayList<Cliente> listaCliente;

    public ListaClientes() {
        listaCliente = new ArrayList<>();
    }
    public void altaCliente(Cliente c) {
        boolean exists = false;
        for(Cliente clienteActual: listaCliente) {
            if(clienteActual.getTelefono().equalsIgnoreCase(c.getTelefono())) {
                System.out.println("Ya Existe");
                exists = true;
            }
        }
        if(!exists) {
            listaCliente.add(c);
        }
    }
    
    public Cliente encontrar(String telf) {
        for(Cliente cliente : listaCliente) {
            if (cliente.getTelefono().equals(telf)) {
                return cliente;
            }
        }
      return null;
    }
    
    public boolean existsPresupuesto(int numPresupuesto){
        boolean exists = false;
        for(Cliente clienteActual: listaCliente){
            if(clienteActual.getPresupuestos().existsPresupuestoNum(numPresupuesto)){
                exists = true;
                break;
            }
        }
        return exists;
    }
    
    public boolean clienteExists (String num) {
        boolean exists = false;
        for(Cliente clienteActual: listaCliente) {
            if(clienteActual.getTelefono().equalsIgnoreCase(num)) {
                exists = true;
            }
        }
        return exists;
    }
    
    public Cliente getByNum(String num) {
        for(Cliente clienteActual: listaCliente) {
            if(clienteActual.getTelefono().equalsIgnoreCase(num)) {
                return clienteActual;
            }
        }
        return null;
    }
    
    public void removeCliente(Cliente c) {
        listaCliente.remove(c);
    }
    
    public ArrayList<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setLista_cliente(ArrayList<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }
    
}