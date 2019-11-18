
package gestion;

import java.util.*;
import serializable.*;
import entrada.MyInput;
import validaciones.validar;
import excepciones.HotelExcepciones;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.io.Serializable;


public class Hotel implements Serializable{

   private static Hotel instance;
   private ArrayList<Habitacion> lista_habitaciones;
   HashMap<Integer, String> tipo = new HashMap<Integer, String>();  
   LeerObjetos leer; 
   
   
    Hotel(){
     
        this.leer = new LeerObjetos();
        this.lista_habitaciones = leer.leer_objeto();
        tipo.put(1,"Individual");tipo.put(2,"Doble     ");tipo.put(3,"Familiar  ");tipo.put(4, "Suite     ");
       
    }
    
    public static Hotel getInstance(){
        
        if (instance == null){
            instance = new Hotel();
        }
        return instance;
    }
     
    
   @Override
    public Object clone() throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }
    
    
    public void registrar_Habitacion(){ 
        
        System.out.println("**Registrar la Habitacion**\n");              
        String num_h = "1";        
        boolean repetir = true;
        
        do{
                               
            num_h = obtener_numero_habitacion();             
                
            if((buscar_Habitacion(num_h)== null)){
                
                agregar_Habitacion(num_h);
                repetir = false;
                
            }else {
                
                System.out.println(">Este número de habitación ya existe.");
                repetir = false;  
                
            } 
            
        }while(repetir); 
          
    }
    
    
    private void agregar_Habitacion(String num_h){ 
                                    
        double precio = 0;            
        String opcion = obtener_tipo_habitacion();
                
            switch(opcion){
                    
                case "1":                       
                        H_individual ind = new H_individual();
                        lista_habitaciones.add(ind);
                        ind.set_Num_Habitacion(num_h);
                        System.out.println("Precio habitación");
                        precio= modificar_Precio();
                        ind.set_Cambiar_Precio_Basico_Dia(precio);
                        ind.set_Comparte_Baño(true);                   
                        break;
                        
                case "2":                       
                        H_doble dob = new H_doble();
                        lista_habitaciones.add(dob);
                        dob.set_Num_Habitacion(num_h);
                        System.out.println("Precio habitación");
                        precio= modificar_Precio();
                        dob.set_Cambiar_Precio_Basico_Dia(precio);
                        dob.modificar_Comparte_Baño();              
                        break;
                        
                case "3":                        
                        H_familiar fam = new H_familiar();
                        lista_habitaciones.add(fam);
                        fam.set_Num_Habitacion(num_h);
                        System.out.println("Precio habitación");
                        precio= modificar_Precio();
                        fam.set_Cambiar_Precio_Basico_Dia(precio);                                        
                        break; 
                        
                case "4":                        
                        H_suite suit = new H_suite();
                        lista_habitaciones.add(suit);
                        suit.set_Num_Habitacion(num_h);
                        System.out.println("Precio habitación");
                        precio= modificar_Precio();
                        suit.set_Cambiar_Precio_Basico_Dia(precio);                                          
                        break;
            }
                
        System.out.println(">Creada con exito."); 
               
    }
    
    
    public void modificar_Habitacion(){ 
        
        System.out.println("**Modificar Habitación**\n");              
             
        boolean repetir = true;
        
        do{
                                
            String num_h = obtener_numero_habitacion();             
            double precio = 0;
                
            if((buscar_Habitacion(num_h) != null)){
                
                actualizar_Habitacion(num_h);
                repetir = false;
                
            }else {
                    System.out.println(">Este número de habitación no existe.");
                    System.out.println("Desea continuar? Si/No");
                    String opcion = MyInput.readString();
                    
                    if(opcion.compareToIgnoreCase("Si") == 0){
                       repetir = true; 
                    }else repetir = false;
                 
            } 
            
        }while(repetir); 
          
    }
    
    private void actualizar_Habitacion(String num_h){  
        
        Habitacion h = buscar_Habitacion(num_h);          
        double precio = 0;
                
        int opcion = h.get_Tipo_Habitacion();
        System.out.println("Datos de la habitación a modificar:");
        mostrar_Habitacion(h);    
        System.out.println("");
        switch(opcion){
                case 1:
                        System.out.println("Precio habitación");
                        precio= modificar_Precio();                       
                        h.set_Cambiar_Precio_Basico_Dia(precio);
                        h.set_Comparte_Baño(true);
                    
                        break;
                case 2:
                        
                        System.out.println("Precio habitación");
                        precio= modificar_Precio();
                        h.set_Cambiar_Precio_Basico_Dia(precio);
                        System.out.println("Comparte Baño");
                        h.modificar_Comparte_Baño();
                        
                        break;
                case 3:
                        
                        System.out.println("Precio habitación");
                        precio = modificar_Precio();
                        h.set_Cambiar_Precio_Basico_Dia(precio);
                        System.out.println("Precio camas supletorias");
                        precio = modificar_Precio();
                        h.set_Precio_Camas_Supletorias(precio);
                        break; 
                case 4:
                        System.out.println("Precio habitación");
                        precio = modificar_Precio();
                        h.set_Cambiar_Precio_Basico_Dia(precio);
                        System.out.println("Precio camas supletorias");
                        precio = modificar_Precio();
                        h.set_Precio_Camas_Supletorias(precio);
                        break; 
                        
                }
                
        System.out.println(">Modificada con exito.\n"); 
               
    }
    
    
    private double modificar_Precio(){
            
        
        boolean continuar;
        double precio = 0;
        do{
            System.out.print("Introducir precio:");
            try{
                precio = MyInput.readDouble();
                continuar = false;
            }catch(Exception e){
            
               System.out.println(">El valor introducido no es correcto.");
               continuar = true; 
            }
           
        }while(continuar);
        
      return precio;      
        
    }
    
    
    
    private String obtener_numero_habitacion(){
        String num_h = "";
        boolean continuar = true;
        do{
                
                try {
                    System.out.println("Introduce el número de habitacion: ");
                    num_h = MyInput.readString();
                    continuar = false;
                    if(num_h.matches(".*[^0-9].*"))                       
                        throw new HotelExcepciones("El número de habitaciones para cada planta va del X00 al X20 para cada planta X, total de plantas: 6");              
                    else if(Integer.parseInt(num_h) < (99) || Integer.parseInt(num_h) > (620)) 
                        throw new HotelExcepciones("El número de habitaciones para cada planta va del X00 al X20 para cada planta X, total de plantas: 6");                   
                    else if( Integer.parseInt(num_h) > (120) && Integer.parseInt(num_h) < (199))   
                         throw new HotelExcepciones("El número de habitaciones para cada planta va del X00 al X20 para cada planta X, total de plantas: 6");
                    else if( Integer.parseInt(num_h) > (220) && Integer.parseInt(num_h) < (299))                      
                        throw new HotelExcepciones("El número de habitaciones para cada planta va del X00 al X20 para cada planta X, total de plantas: 6");
                    else if( Integer.parseInt(num_h) > (320) && Integer.parseInt(num_h) < (399))   
                        throw new HotelExcepciones("El número de habitaciones para cada planta va del X00 al X20 para cada planta X, total de plantas: 6");   
                     else if( Integer.parseInt(num_h) > (420) && Integer.parseInt(num_h) < (499))   
                        throw new HotelExcepciones("El número de habitaciones para cada planta va del X00 al X20 para cada planta X, total de plantas: 6");  
                    else if( Integer.parseInt(num_h) > (520) && Integer.parseInt(num_h) < (599))
                        throw new HotelExcepciones("El número de habitaciones para cada planta va del X00 al X20 para cada planta X, total de plantas: 6");
                    
                } catch (HotelExcepciones ex) {
                    System.out.println(ex.getMessage());
                    continuar = true;
                }
                        
            }while(continuar);
        return num_h;
    }
    
    
    private String obtener_tipo_habitacion(){
        boolean continuar = true;
        
        String opcion = "";
        do{
                                    
            try{
                   
                System.out.println("Seleccionar Tipo Habitación [1:Individual, 2:Doble, 3:Familiar, 4:Suite]");
                opcion = MyInput.readString();
                continuar = false;
                if(opcion.matches(".*[^1234].*") ) throw new HotelExcepciones("Introduce el valor númerico correspondiente al Tipo de habitacíon deseado.");
                    
            }catch(HotelExcepciones ex){
                        
                System.out.println(ex.getMessage());
                continuar = true;
                
            }
                       
        }while(continuar);
                        
        return opcion;
    }
    
    
   
    
    
    public void listado_Habitaciones(){
            
        Collections.sort(lista_habitaciones);
        System.out.println("\n**Listado de todas las habitaciones**");
           
            for (int i = 0; i < lista_habitaciones.size(); i++) {
                           
                mostrar_Habitacion(lista_habitaciones.get(i));
                
            }
            
            if(lista_habitaciones.isEmpty()) System.out.println(">No se han agregado habitaciones.");
            pausa();
        System.out.println(""); 
    }
    
    
    public void mostrar_Habitacion(Habitacion h){
        
        System.out.print("\n>Habitación: "+h.get_Num_Habitacion());  
        System.out.print(" -Precio dia: "+h.get_Precio_Basico_Dia()+" ");
        System.out.print(" -Tipo: "+tipo.get(h.get_Tipo_Habitacion()));        
        System.out.print(" -Comparte baño: "+h.get_Comparte_Baño());
        
        if(h.get_Tipo_Habitacion()>2)
            System.out.print(" -Precio camas supletorias: "+h.get_Precio_Camas_Supletorias());
               
    }
    
     
    private Habitacion buscar_Habitacion(String a){
        
        
        for (int i = 0; i < lista_habitaciones.size(); i++) {
            
            if(a.compareTo(lista_habitaciones.get(i).get_Num_Habitacion()) == 0){
                return lista_habitaciones.get(i);
            }
            
        }
       
        return null;
    }    

    
    
    public void info_Habitacion(){ 
       
        String num = obtener_numero_habitacion();
        Habitacion h = buscar_Habitacion(num);
        if (h != null){
            System.out.println("\n**Información de la habitación**");
            mostrar_Habitacion(h);
        }
        pausa();
              
    }
    
    
    public void eliminar_Habitacion(){
        
        System.out.println("\n**Eliminar Habitación**");
        String num_h = obtener_numero_habitacion();
        Habitacion h = buscar_Habitacion(num_h);
        if(h != null){      
            
            lista_habitaciones.remove(h);
            System.out.println(">Habitación eliminada con exito!.");
                   
        }else System.out.println(">El número de habitacion indicado no existe en la lista.");
        
    }
    
    
    public void filtrar_Habitaciones_No_Registradas(){
        
        Date dia_inicio;
        Date dia_final;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");        
        String t_habitacion = obtener_tipo_habitacion();
        do{        
        do{
            System.out.print("Ingrese Fecha de Entrada ");
            dia_inicio = validar.validar_Fecha();
   
        }while(dia_inicio == null);
        
        do{
            System.out.print("Ingrese Fecha de Salida ");
            dia_final = validar.validar_Fecha();

        }while(dia_final == null);
        }while(dia_final.compareTo(dia_inicio)<0);
        System.out.println("\nListado habitaciones disponibles de tipo:"+tipo.get(Integer.parseInt(t_habitacion))+" con fecha entrada: "+sdf.format(dia_inicio)+" y fecha salida: "+sdf.format(dia_final)+":");
        filtrar_Habitaciones(Integer.parseInt(t_habitacion),dia_inicio ,dia_final);
        pausa();
        
    }
    
    public void guardar_Configuracion(){
       
           EscribirObjetos guardar = new EscribirObjetos();
           guardar.escribir_objeto(lista_habitaciones);
           System.out.println("\n>>>Se ha guardado con éxito...");
           pausa();
           
    }
    
    
   
    public void pausa(){
       
        try {
           System.out.println("\n>>Pulse una [Intro] para continuar<<");
           int read = System.in.read();
        } catch (IOException ex) {
           
        }
        System.out.println("\n");
    }
    
    
    
    /*Metodos Gestion RESERVAS*/
    public void registrar_Reservas(){ 
       
        System.out.println("**Registrar la Reserva**\n");     
        
        Date dia_inicio;
        Date dia_final;
        String num_h;
         String t_habitacion = obtener_tipo_habitacion();
        
        do{
            do{
                Date fecha_actual = new Date();
               System.out.print("Fecha de Entrada ");
                dia_inicio = validar.validar_Fecha();
                if(fecha_actual.after(dia_inicio)){
                    System.out.println("La fecha debe ser posterior a la actual.");
                    dia_inicio = null;
                }
            
            }while(dia_inicio == null);                     
        
            do{
                System.out.print("Fecha de Salida ");
                dia_final = validar.validar_Fecha();
                if(dia_final.before(dia_inicio)) {
                    System.out.println(">Error, la fecha de salida no debe ser anterior a la de entrada!.");
                    dia_final = null;
                }
            
            }while(dia_final == null);
            
        }while(dia_final.compareTo(dia_inicio)<0);
        
        
        System.out.println("\nListado habitaciones disponibles:");
        ArrayList validar = filtrar_Habitaciones(Integer.parseInt(t_habitacion),dia_inicio ,dia_final);
        System.out.println("\n");
        
        if(validar.isEmpty() == false){
            do{
                num_h = obtener_numero_habitacion();       
                Habitacion h = buscar_Habitacion(num_h);
            
                if(buscar_Habitacion(num_h) != null&& validar.contains(num_h)) { 
                    
                    h.generar_Reserva(dia_inicio, dia_final);
               
                }else System.out.println(">Habitación no encontrada!") ;
                
            }while (buscar_Habitacion(num_h) == null);
        }else System.out.println("no hay habitaciones disponibles");
    }
           
    
    public void modificar_Reserva(){
        String num_h;
            num_h = obtener_numero_habitacion();       
            Habitacion h = buscar_Habitacion(num_h);
            
            if(buscar_Habitacion(num_h) != null) { 
                h.actualizar_Reserva();
                
            }else System.out.println(">Habitación no encontrada!") ;
        System.out.println("Nota: Si desea cambiar las fechas de entrada y salida, debe realizar una nueva reserva.");
        pausa();
    }
    
    
    
    public ArrayList filtrar_Habitaciones(int n, Date fecha_entrada, Date fecha_salida){
        ArrayList habitaciones_disponibles = new ArrayList();
       int contador =0;
        for (int i = 0; i < lista_habitaciones.size(); i++) {
                        
            if(lista_habitaciones.get(i).get_Tipo_Habitacion() == n){
                    if(lista_habitaciones.get(i).tamano_Reservas() > 0){   
                     
                        for (int j = 0; j < lista_habitaciones.get(i).tamano_Reservas() ; j++){
                            
                            Reserva r = lista_habitaciones.get(i).get_Reserva_Habitacion(j);
                           
                            if((r.get_Dia_Final().before(fecha_entrada) && r.get_Dia_Inicio().before(fecha_entrada)) || (r.get_Dia_Inicio().after(fecha_salida) && r.get_Dia_Final().after(fecha_salida)) ){
                               
                                contador++;
                            }
                                       
                        }
                        
                        if(contador == lista_habitaciones.get(i).tamano_Reservas()){
                            System.out.print("\nHabitación: "+lista_habitaciones.get(i).get_Num_Habitacion());
                            habitaciones_disponibles.add(lista_habitaciones.get(i).get_Num_Habitacion());
                        }
                    
                    }else{
                        System.out.print("\nHabitación: "+lista_habitaciones.get(i).get_Num_Habitacion());
                        habitaciones_disponibles.add(lista_habitaciones.get(i).get_Num_Habitacion());
                      
                    }
            }
        }  
        return habitaciones_disponibles;
    }
     

    public void listado_Reservas(){
        
        System.out.println("\nListado de reservas de todas las habitaciones:");
        for (int i = 0; i < lista_habitaciones.size(); i++) {
            lista_habitaciones.get(i).get_listado_reservas();                   
        }
        if(lista_habitaciones.size()<1)
            System.out.print(">La lista de reservas está vacia.");    
        pausa();           
    }
   
   
   
    
    public void info_Reserva(){ 
               
        String num_h = obtener_numero_habitacion();       
        Habitacion h = buscar_Habitacion(num_h);
        Reserva r = new Reserva();
        boolean repetir = true;
        
        do{             
                
            if(buscar_Habitacion(num_h) != null){
                if(h.tamano_Reservas()>0){
                    r = h.buscar_Reserva();
                    if(r != null){
                        System.out.println("\nInformación de la reserva:");
                        h.mostrar_info_reserva(r);
                    }
                }else System.out.println(">La habitacion no tiene reservas");
                repetir = false;
            }else {
                System.out.println(">Este número de habitación no existe.");
                System.out.println("Desea continuar? Si/No");
                String opcion = MyInput.readString();
                    
                if(opcion.compareToIgnoreCase("Si") == 0){
                    repetir = true; 
                }else repetir = false; 
            }   
        }while(repetir); 
        pausa();              
       
    }
    
    
    public void eliminar_Reserva(){
          
        String num_h = obtener_numero_habitacion();
        Habitacion h = buscar_Habitacion(num_h);
        Reserva r = new Reserva();
        
        boolean repetir = true;
        
        do{
                
            if((buscar_Habitacion(num_h) != null)){
 
                r = h.buscar_Reserva();
                if(r!=null){
                    h.borrar_reserva(r);
                    System.out.println(">La reserva se ha eliminado con exito.");
                    }
                
                repetir = false;
                
            }else {
                System.out.println(">Este número de habitación no existe.");
                System.out.println("Desea continuar? Si/No");
                String opcion = MyInput.readString();
                    
                if(opcion.compareToIgnoreCase("Si") == 0){
                    repetir = true; 
                }else repetir = false; 
            } 
        }while(repetir); 
        pausa();
    }
    
    
    public void generar_Factura(){
        
        System.out.println("\n**Generar Factura**\n"); 
        String num_h = obtener_numero_habitacion();
        Habitacion h = buscar_Habitacion(num_h);
        if (h != null){
            h.get_Factura();
        }else
            System.out.println(">El número de habitación no existe.");
        pausa();
    }
    
}

