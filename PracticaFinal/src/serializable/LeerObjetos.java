
package serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class LeerObjetos {
    
    ObjectInputStream ois;
     public ArrayList leer_objeto(){ 
        ArrayList retorno = new ArrayList<>(); 
        try{
        ois = new ObjectInputStream(new FileInputStream("base_datos_hotel.out"));
        
        retorno =  (ArrayList) ois.readObject();
        
        return retorno;
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Se produjo un error en la lectura del archivo: "+e);
        }
        return retorno; 
    }
    
}
