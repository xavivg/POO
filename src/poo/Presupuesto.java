package poo;

public class Presupuesto {

    private Integer num;
    private String concepto;
    private double precioTotal;
    private String estado;

    
    public Presupuesto(Integer num, String concepto, double precioTotal, String estado) {
        this.num = num;
        this.concepto = concepto;
        this.precioTotal = precioTotal;
        this.estado = estado;
    }
    
    public Presupuesto(){
        
    }
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public double calcPrecioFinal(Cliente c){
        double PrecioFinal;
        double precioVip;
        if(c.isVip()){
           precioVip = ((this.precioTotal)-(this.precioTotal*0.05));
           PrecioFinal = ((precioVip*0.21)+(precioVip));
           
        }else{
            PrecioFinal = ((this.precioTotal)+(this.precioTotal*0.21));
        }
        return PrecioFinal;
    }
    
    
    
}