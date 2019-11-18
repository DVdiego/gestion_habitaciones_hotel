
package validaciones;

import entrada.MyInput;
import excepciones.HotelExcepciones;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class validar {
   
    
    
    public static boolean validar_dni(String DNI){//funcion para validar el dni
        DNI = DNI.toLowerCase();
        Pattern p = Pattern.compile("(([0-9]{8,8})(\\W?)([a-zA-Z]))");
        Matcher m = p.matcher(DNI);
        int resto = 0;
        char letra = 'a';
        if (m.matches()){
            Pattern pat = Pattern.compile("[0-9]+");
            Matcher mat = pat.matcher(DNI);
            while (mat.find()) {
                String numero=(DNI.substring(mat.start(), mat.end()) + "");
                int numero_entero = Integer.parseInt(numero);
                resto= numero_entero%23;
                
                String letras = "trwagmyfpdxbnjzsqvhlcke";
                letra = letras.charAt(resto);
                
            }
        }
        if (m.matches() && DNI.charAt(8)==letra) {
            return true;
        } else {
            System.out.println("Dni erroneo.");
            return false;
        }
    }  
    
    
    public  static Date validar_Fecha(){ //necesario utilizar tambien en esta clase
        System.out.print("\nIntroduzca la fecha con formato dd/mm/yyyy: ");
        
        String fecha = MyInput.readString();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaEnviar = null;
        formatoDelTexto.setLenient(false);
        Date fecha_actual = new Date();
        
        try{
            fechaEnviar = formatoDelTexto.parse(fecha);
            //if(fecha_actual.after(fechaEnviar)) throw new HotelExcepciones("La fecha debe ser posterior a la actual.");
            return fechaEnviar;
        } catch (ParseException e){ 
            System.out.println("Formato de la fecha incorrecto.");
            return null;
        }  

   }
    
    
}
