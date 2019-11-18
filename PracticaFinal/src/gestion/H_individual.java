
package gestion;

public class H_individual extends Habitacion {

    

    H_individual(){
        super(70,1,0);
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
    public void set_Comparte_Baño(boolean a){
        super.set_Comparte_Baño(a);
    }
    
    
    @Override
    public void set_Reserva_Habitacion(Reserva a){
        super.set_Reserva_Habitacion(a);    
    }
  
    @Override
    public double get_Precio_Basico_Dia(){  
        return super.get_Precio_Basico_Dia();
    }

    @Override
    public Reserva get_Reserva_Habitacion(int i){
        return super.get_Reserva_Habitacion(i);
    }
    
    @Override
    public int get_Tipo_Habitacion(){
        
        return super.get_Tipo_Habitacion();
    }
  
}
