
package gestion;

import entrada.MyInput;
import excepciones.HotelExcepciones;
import java.util.ArrayList;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import validaciones.validar;


public abstract class Habitacion implements Comparable<Habitacion>, Serializable{
    
    private String num_habitacion;
    private double precio_basico_dia;
    private int tipo_habitacion;
    private boolean comparte_baño;
    private double precio_camas_supletorias;
    private ArrayList<Reserva> lista_reservas;
    
    Habitacion(int precio_basico, int tipo, double precio_camas){
        
        this.lista_reservas = new ArrayList<Reserva>();
        this.precio_basico_dia = precio_basico;
        this.tipo_habitacion = tipo;
        this.num_habitacion = "";
        this.comparte_baño = false ;
        this.precio_camas_supletorias = precio_camas;
       
    }
    
    
    public void set_Precio_Camas_Supletorias(double a){
        this.precio_camas_supletorias = a;
    }
    public void set_Num_Habitacion(String a){   
        this.num_habitacion = a;    
    }
    
    public void set_Cambiar_Precio_Basico_Dia(double b){ 
        this.precio_basico_dia = b;
    }
 
    
    public void set_Comparte_Baño(boolean a){
        this.comparte_baño = a;
    }
    
    public void set_Reserva_Habitacion(Reserva a){
        this.lista_reservas.add(a); 
        
    }
    
    
    public String get_Num_Habitacion(){   
        return this.num_habitacion;    
    }
    
    public double get_Precio_Basico_Dia(){  
        return this.precio_basico_dia;
    }
    
    public double get_Precio_Camas_Supletorias(){
        return this.precio_camas_supletorias;
    }

    public String get_Comparte_Baño(){
        String a;
        if(this.comparte_baño){
            a = "Si";
        }else a = "No";
        return a;
    }
      
    public Reserva get_Reserva_Habitacion(int i){
        if(this.lista_reservas.size()> 0){
            return this.lista_reservas.get(i);
        }
        else return null;   
    }
    
    
    
    
    public int get_Tipo_Habitacion(){
        
        return this.tipo_habitacion;
    }
    
    
    public int tamano_Reservas(){
        return lista_reservas.size();   
    }

    public void borrar_reserva(Reserva r){
        
        lista_reservas.remove(r);
        System.out.println(">La reserva se a borrado con éxito.");
    }
    
    @Override
    public int compareTo(Habitacion o) {
        
        return num_habitacion.compareTo(o.get_Num_Habitacion());
       
    }
    
    @Override
    public String toString (){
		String mensaje="El número de habitación "+num_habitacion;
		return mensaje;
	}

 
    public void modificar_Comparte_Baño(){
        boolean  continuar =  true;
        do{ 
            System.out.print("Comparte baño [Si/No]: ");
            String a;                   
            a = MyInput.readString();
                                
            if(a.equalsIgnoreCase("Si")){
                set_Comparte_Baño(true);
                continuar = false;}
            else if(a.equalsIgnoreCase("No")){
                set_Comparte_Baño(true);
                continuar = false;
            }   
            
        }while(continuar);
  
    }
    
    //Metodos relaccionados con Reservas
    
    public void generar_Reserva(Date dia_inicio, Date dia_final){
        
        Reserva r = new Reserva();
        String respuesta;
        r.set_Dia_Inicio(dia_inicio);
        r.set_Dia_Final(dia_final);
        
        obtener_Datos_Reserva(r);
                    
        System.out.println("Esta seguro de reservar la habitación? [Si/No]:");
        respuesta = MyInput.readString();
        if (respuesta.equalsIgnoreCase("si") )
            set_Reserva_Habitacion(r);
        else System.out.println(">No se ha almacenado la reserva.");
    }
    
    public void actualizar_Reserva(){
        
        Reserva r = buscar_Reserva();
        if(r != null){
            System.out.println("Información de la reserva:");
            mostrar_info_reserva(r);
            System.out.println("\nIntroducir nuevos datos para la reserva:");
            obtener_Datos_Reserva(r);
            System.out.println("Habitacion modificada con éxito.");
        }
    }
    
    
    
    
    public void obtener_Datos_Reserva(Reserva r){
        String dni;
        String respuesta;
        boolean salir = true;
        boolean continuar = true;
        do{
            System.out.println("Ingrese el DNI Cliente: ");
            dni = MyInput.readString();
            r.set_Dni(dni);                   
            
        }while(dni.isEmpty()|| validar.validar_dni(dni) == false);
        
        if(get_Tipo_Habitacion()==3 || get_Tipo_Habitacion()==4){
            do{
                System.out.println("Indique el número de camas supletorias [0-3]: ");
                 
                try{
                    String camas = MyInput.readString(); 
                    if(camas.matches(".*[^0-9].*"))                       
                            throw new HotelExcepciones(">Error, introduce un valor numerico!");  
                 
                    else if(Integer.parseInt(camas) < (0) || Integer.parseInt(camas) > (3))
                        throw new HotelExcepciones(">Error, introduce un valor entre [0-3]"); 
                    
                    r.set_camas_supletorias(Integer.parseInt(camas));
                    continuar = false;
                        
                }catch(HotelExcepciones ex){
                        System.out.println(ex.getMessage());
                        continuar = true;
                }
            }while(continuar);
            if (get_Tipo_Habitacion()==4){
                r.set_Es_Vip_string();
            }
        }
  
        
        do{
            System.out.println("Esta pendiente de cobro? [Si/No]: ");
            respuesta = MyInput.readString();
            
            if(respuesta.equalsIgnoreCase("SI")){
                r.set_Pendiente_Cobro(true);
                salir = false;
            }else if(respuesta.equalsIgnoreCase("NO")){
                r.set_Pendiente_Cobro(false);
                salir = false;
            }
            
        }while(salir);
                
         
    }
    
     
    
    public Reserva buscar_Reserva(){
        
        Reserva r;
        System.out.println("Fecha de Entrada para realizar la consulta.");
                Date fecha_consulta = validar.validar_Fecha();
             
                for (int j = 0; j < tamano_Reservas(); j++) {             
                
                     if(get_Reserva_Habitacion(j) != null)
                    {
                        r = get_Reserva_Habitacion(j);
                        
                        if(r.get_Dia_Inicio().equals(fecha_consulta)){
                      
                           return r;
                    
                        }else {
                            System.out.println(">No existe ninguna reserva con le fecha indicada.");
                            
                        }
                            
                    }
                } 
                
        return null;
    }
    
        
    public void mostrar_info_reserva(Reserva r){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        if (r != null){
            
            System.out.print("\n->DNI:"+r.get_Dni());
            System.out.print(" -Habitación: "+get_Num_Habitacion());
            System.out.print(" -Fecha de Entrada:"+sdf.format(r.get_Dia_Inicio()));
            System.out.print(" -Fecha de Salida:"+sdf.format(r.get_Dia_Final()));
                        
            if(r.get_Pendiente_Cobro()) 
                System.out.print(" -Pendiente de cobro: Si");
            else System.out.print(" -Pendiente de cobro: No");
                        
            if(r.get_Es_Vip()) 
                System.out.print(" -VIP: Si");
            else System.out.print(" -VIP: No");
    
       }
    }
    
    
    public void get_listado_reservas(){ 
        
        for(int i = 0; i < tamano_Reservas();i++){
            Reserva r = get_Reserva_Habitacion(i);
            mostrar_info_reserva(r);
        }
        
        if (tamano_Reservas()<1){
            System.out.println("\n>La habitación "+get_Num_Habitacion()+" no tiene reservas.");
        }
        
    }
   
    
    public void get_Factura(){

        Reserva v = buscar_Reserva();
        if(v != null){
        
                if (v.get_Pendiente_Cobro()==false){
            
                    double numdias = (v.get_Dia_Final().getTime()-v.get_Dia_Inicio().getTime())/(24 * 60 * 60 * 1000);
            
                    if(numdias == 0) numdias = 1;
                       
                    System.out.println("\n----------FACTURA----------");
                    System.out.println("Dni: "+v.get_Dni());
                    System.out.println("Numero de habitacion: "+get_Num_Habitacion());
                    System.out.println("Tipo de habitacion: "+get_Tipo_Habitacion());
                    System.out.println("Precio por dia: "+get_Precio_Basico_Dia());
                    System.out.println("Total de dias: "+numdias);
                    System.out.println("Comparte baño: "+get_Comparte_Baño());
                    System.out.println("Camas supletorias: "+v.get_camas_supletorias());            
                    System.out.println("Vip: "+v.get_Es_Vip_string());
                    System.out.println("");
                
                    double precio_dias =(get_Precio_Basico_Dia()*numdias) ;
                
                    switch (get_Tipo_Habitacion()){
                
                    case 1: break;
                    
                    case 2:
                            if(get_Comparte_Baño().equalsIgnoreCase("no"))
                                precio_dias = 1.15*precio_dias;
                            break;
                
                    case 3:
                            if(v.get_camas_supletorias()>0)
                                precio_dias = precio_dias+(get_Precio_Camas_Supletorias()*numdias);                    
                            break;
                
                    case 4:
                            if(v.get_camas_supletorias()>0)
                            precio_dias = precio_dias+(get_Precio_Camas_Supletorias()*numdias);
                    
                            if(v.get_Es_Vip())
                                precio_dias = precio_dias*1.2;                   
                            break;
                    }
            
            System.out.println("Precio total: "+precio_dias);
            
        }else System.out.println(">La reserva aun esta pendiente de cobro"); 
                
      }
   }
    
    
    
}
