
package serializable;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class EscribirObjetos {
    
    private ObjectOutputStream oos;
    
    public void escribir_objeto(ArrayList objeto){
        
        try{
        oos = new ObjectOutputStream(new FileOutputStream("base_datos_hotel.out"));
        oos.writeObject(objeto);
        
        }catch(Exception e){
            System.out.println("Se produjo un error en la escritura del archivo: "+e);
            
        }
        
    }
    
}
