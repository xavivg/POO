package POO;
import poo.Cliente;
import poo.ListaClientes;
import poo.ListaPresupuestos;
import poo.Presupuesto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import tools.*;
import tools.InputData;

public class POO {

    private static ListaClientes clientes;
    private static ListaPresupuestos presupuestos;
    private static Fichero FClientes;
    private static Fichero FPresupuestos;
    private static String[] EPresupuesto = {"aceptado","pendiente","rechazado"};

    public static void main(String[] args) {
        //--------------MAIN------------
        
        FClientes = new Fichero("Clientes.xml");
        FPresupuestos = new Fichero("Presupuestos.xml");
        
        clientes = (ListaClientes) FClientes.leer();
        presupuestos = (ListaPresupuestos) FPresupuestos.leer();
        
        if(clientes==null){
            clientes = new ListaClientes();
        }
        
        if(presupuestos==null){
            presupuestos = new ListaPresupuestos();
        }
        //-----------
        
        int opcion;
        do {
            mostrarMenu();
            opcion = InputData.pedirEntero("Escoge una opción");
            switch (opcion) {
                case 1:
                    altaCliente();
                    break;
                case 2:
                    altaPresupuesto();
                    break;
                case 3:
                    getPresupuestoByEstado("pendiente");
                    break;
                case 4:
                    getPresupuestoByTelf();
                    break;
                case 5:
                    getPresupuestoByEstado("rechazado");
                    break;
                case 6:
                    mostrarClientes();
                    break;
                case 8:
                    System.out.println("Adeu!");
                    break;
                default:
                    System.out.println("Opción incorrecta.");
            }
        } while (opcion != 8);
    }
    
    private static void mostrarClientes() {
        for(Cliente clienteActual: clientes.getListaCliente()){
            System.out.println("Nombre: "+clienteActual.getNombre());
            System.out.println("Apellido: "+clienteActual.getApellido());
            System.out.println("Telefono: "+clienteActual.getTelefono());
            String vipString = clienteActual.isVip() ? "Sí" : "No";
            System.out.println("VIP: "+vipString);
            int totalPresupuestos=0;
            for(Presupuesto countPresupuesto: clienteActual.getPresupuestos().getListaPresupuesto()){
                totalPresupuestos++;
            }
            System.out.println("Nº de presupuestos:\t"+totalPresupuestos);
            System.out.println("-------------------------------------");
        }
    }
    
     private static void getPresupuestoByTelf() {
        String clienteNum = cadenaNoVacia("Introduce el telefono del cliente");
        boolean exists = clientes.clienteExists(clienteNum);
        Cliente cliente = clientes.getByNum(clienteNum);
        if(exists){
        System.out.println("Presupuestos del cliente: "+cliente.getNombre()+" "+cliente.getApellido());
            if(!cliente.getPresupuestos().getListaPresupuesto().isEmpty()){
                for(Presupuesto presupuestoActual: cliente.getPresupuestos().getListaPresupuesto()){
                    System.out.println("Nº:\t\t\t"+presupuestoActual.getNum());
                    System.out.println("Concepto:\t\t"+presupuestoActual.getConcepto());
                    System.out.println("Presupuesto neto:\t"+presupuestoActual.getPrecioTotal()+"€");
                    System.out.println("Presupuesto final:\t"+presupuestoActual.calcPrecioFinal(cliente)+"€");
                }
            }else{
                System.out.println("La lista de presupuestos del cliente: "+cliente.getNombre()+" está vacía");
            }
        }else{
            System.out.println("El cliente no existe.");
        }
    }
    

    private static void getPresupuestoByEstado(String estado) {
        System.out.println("\tLista de presupuestos por estado: "+estado);
        ArrayList<Presupuesto> listaPresupuestosPendientes;
        for(Cliente clienteActual: clientes.getListaCliente()){
            System.out.println("Presupuestos del cliente: "+clienteActual.getNombre());
            
            listaPresupuestosPendientes = clienteActual.getPresupuestos().getPresupuestoByEstado(estado);
            if(!listaPresupuestosPendientes.isEmpty()){
                for(Presupuesto presupuestoActual: listaPresupuestosPendientes){
                    System.out.println("Nº: "+presupuestoActual.getNum());
                    System.out.println("Concepto: "+presupuestoActual.getConcepto());
                    System.out.println("Presupuesto neto: "+presupuestoActual.getPrecioTotal()+" eur.");
                    System.out.println("Presupuesto Total: "+presupuestoActual.calcPrecioFinal(clienteActual)+" eur.");
                }
                System.out.println("---------------------");
                
            }else{
                System.out.println("La lista de presupuestos de este cliente está vacía según el criterio \""+estado+"\"");
            }
        }
    }

    public static void altaPresupuesto(){
        String clienteNum = cadenaNoVacia("Introduce el número del cliente");
        boolean exists = clientes.clienteExists(clienteNum);
        
        Cliente cliente = clientes.getByNum(clienteNum);
        
        if(exists){
            boolean existsNumPresupuesto;
            int numPresupuesto=0;
            do{
            numPresupuesto = tools.InputData.pedirEntero("Introduce el número del presupuesto");
            existsNumPresupuesto = clientes.existsPresupuesto(numPresupuesto);
            if(existsNumPresupuesto){
                System.out.println("Ya existe un presupuesto registrado bajo ese número");
            }
            }while(existsNumPresupuesto);
            
            String concepto = cadenaNoVacia("Introduce el concepto");
            double presupuestoNeto = tools.InputData.pedirDouble("Introduce el total del presupuesto");
            boolean valid = false;
            boolean pendiente = false;
            String estado;
            
            do{
                estado = cadenaNoVacia("Estado del presupuesto: (aceptado/pendiente/rechazado)");
                for(String x:EPresupuesto){
                    if(x.equalsIgnoreCase(estado)){
                        valid = true;
                    }
                }
                System.out.println("Introduce una opción correcta");
            }while(!valid && !pendiente);
            
            Presupuesto presupuesto = new Presupuesto(numPresupuesto, concepto, presupuestoNeto, estado);
            cliente.getPresupuestos().addPresupuesto(presupuesto);
            FClientes.grabar(clientes);
            
        }else{
            System.out.println("El cliente no existe");
        }
    }

    private static void altaCliente() {
        String nombre = cadenaNoVacia("Nombre: ");
        String apellidos = cadenaNoVacia("Apellidos: ");
        String telefono = cadenaNoVacia("Telefono: ");
        String respuesta;
        boolean vip = false;
        do {
            respuesta = cadenaNoVacia("Es un cliente VIP? (si/no)?");
            if (respuesta.equalsIgnoreCase("SI")) {
                vip = true;
            } else if (respuesta.equalsIgnoreCase("NO")) {
                vip = false;
            } else {
                System.out.println("Debes responder SI o NO");
            }
        } while (!respuesta.equalsIgnoreCase("SI") && !respuesta.equalsIgnoreCase("NO"));

        Cliente c = new Cliente(nombre, apellidos, telefono, vip);
        
        clientes.altaCliente(c);
        FClientes.grabar(clientes);

    }

    private static int pedirEntero(String mensaje) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numero = 0;
        boolean error;
        do {
            try {
                System.out.println(mensaje);
                numero = Integer.parseInt(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error entrada salida");
                error = true;
            } catch (NumberFormatException ex) {
                  System.out.println("No has introducido un nº entero.");
                error = true;
            }
        } while (error); 
        return numero;
    }

    private static String cadenaNoVacia(String msg) {
        String cadena;
        do {
            cadena = InputData.pedirCadena(msg);
            if (cadena.equals("")) {
                  System.out.println("No se puede dejar en blanco");
            }
        } while (cadena.equals(""));
        return cadena;
    }

    public static void mostrarMenu() {
          System.out.println("MENU");
          System.out.println("1. Alta Cliente");
          System.out.println("2. Nuevo presupuesto");
          System.out.println("3. Presupuestos pendientes");
          System.out.println("4. Listado de presupuestos de un cliente");
          System.out.println("5. Listado de presopuestos rechazados");
          System.out.println("6. Listado de clientes, con  el total de presupuestos que tiene cada uno");
          System.out.println("7. Cambiar estado de un presupuesto");
          System.out.println("8. Salir esto de presupuesto");
    }

}