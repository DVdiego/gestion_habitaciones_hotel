
package gestion;

public class H_doble extends Habitacion {

    H_doble(){
        super(80,2,0);
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
