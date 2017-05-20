package poo;

import java.util.ArrayList;

public class ListaPresupuestos {
    
    private ArrayList<Presupuesto> listaPresupuesto;
    
    public ListaPresupuestos(){
        listaPresupuesto = new ArrayList<>();
    }

    public void addPresupuesto(Presupuesto presupuesto){
        boolean exists = false;
        for(Presupuesto presupuestoActual:listaPresupuesto){
            if(presupuestoActual.getNum()== presupuesto.getNum()){
                System.out.println("Ya hay un presupuesto con ese numero");
                exists = true;
                break;
            }
        }
        if(!exists){
            listaPresupuesto.add(presupuesto);
        }
    }
    
    public boolean existsPresupuestoNum(int numPresupuesto){
        boolean exists = false;
        for(Presupuesto presupuestoActual:listaPresupuesto){
            if(presupuestoActual.getNum() == numPresupuesto){
                exists = true;
                break;
            }
        }
        return exists;
    }
    
    public void removePresupuesto(Presupuesto presupuesto){
        listaPresupuesto.remove(presupuesto);
    }
    
    public ArrayList<Presupuesto> getListaPresupuesto() {
        return listaPresupuesto;
    }
    
    public void setLista(ArrayList<Presupuesto> lista) {
        this.listaPresupuesto = lista;
    }
    
    public ArrayList<Presupuesto> getPresupuestoByEstado(String estado){
        ArrayList<Presupuesto> getEstadoArray = new ArrayList<>();
        for(Presupuesto pres:this.listaPresupuesto){
            if(pres.getEstado().equalsIgnoreCase(estado)){
                getEstadoArray.add(pres);
            }
        }
        return getEstadoArray;
    }
    
}