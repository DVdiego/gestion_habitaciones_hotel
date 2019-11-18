
package gestion;

public class H_familiar extends Habitacion {
    
    H_familiar(){
        super(100,3,25);
        
    }
  
    @Override
    public void set_Num_Habitacion(String a) {
        super.set_Num_Habitacion(a); 
    }
    
    @Override
    public void set_Cambiar_Precio_Basico_Dia(double b){  
        super.set_Cambiar_Precio_Basico_Dia(b);
    }
 
    
    @Override
    public void set_Reserva_Habitacion(Reserva a){
        super.set_Reserva_Habitacion(a);    
    }
    

}
