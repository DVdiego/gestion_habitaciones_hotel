
package gestion;

import entrada.MyInput;


public class Gestion_Hotel {
    
    public static void main(String[] args) {
        
        
        boolean continuar = true;
        Hotel puntero = Hotel.getInstance();
        
        do{
            System.out.println("\n-----------------------------------------------------");
            System.out.println("|   Sistema de Gestión de Habitaciones y Reservas   |");
            System.out.println("-----------------------------------------------------");
            System.out.println("1. Gestión Habitaciones");
            System.out.println("2. Gestión Reservas");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");
            String opcion = MyInput.readString();
            
            
            switch(opcion){
                
                case "1": menu_gestion_Habitaciones(puntero); break;
            
                case "2": menu_gestion_Reservas(puntero); break;
                
                case "3": continuar = false; break;
                
                default: continuar = true; break;
                               
                }
            
    
        }while(continuar);
   
        
    }
      
    
    public static void menu_gestion_Habitaciones(Hotel a){
        
        
        
        boolean continuar = true;
        Hotel gestion = a;
         
        do{
            try{
                
                System.out.println("\n++++++++++++++++++++++++++++++");
                System.out.println("+    Gestión Habitaciones    +");
                System.out.println("++++++++++++++++++++++++++++++");
                System.out.println("1. Añadir Habitación");
                System.out.println("2. Modificar Datos Habitación"); 
                System.out.println("3. Información de una Habitación");
                System.out.println("4. Eliminar Habitación");
                System.out.println("5. Lista de Todas las Habitaciones");
                System.out.println("6. Filtrar Habitaciones Disponibles por Tipo y Fecha"); 
                System.out.println("7. Guardar Modificaciones"); 
                System.out.println("8. Volver Atras");
            
                System.out.print("Opcion: ");
                String opcion = MyInput.readString();

                switch  (opcion){
                
                    case "1": do{ 
                              gestion.registrar_Habitacion(); 
                              System.out.println("Desea seguir agregando habitaciones? [Si/No]:");
                             
                             }while((MyInput.readString().compareToIgnoreCase("si")) == 0 ); break;
            
                    case "2": gestion.modificar_Habitacion(); break;
                
                    case "3": gestion.info_Habitacion(); break;
                
                    case "4": gestion.eliminar_Habitacion(); break;
                
                    case "5": gestion.listado_Habitaciones(); break;
                
                    case "6": gestion.filtrar_Habitaciones_No_Registradas(); break;
                
                    case "7": gestion.guardar_Configuracion(); break;

                    case "8": continuar = false; break;
                
                    default: continuar = false; break;
                }
                
            }catch(Exception ex){
                
                System.out.println("Se produjo un error del tipo:"+ex);
            }
            
         }while(continuar);

    }
    
    
    public static void menu_gestion_Reservas(Hotel a){
        
        
        boolean continuar = true;
        Hotel gestion = a;
         
         do{
            System.out.println("\n++++++++++++++++++++++++++");
            System.out.println("+    Gestión Reservas    +");
            System.out.println("++++++++++++++++++++++++++");
            System.out.println("1. Añadir Reserva"); 
            System.out.println("2. Modificar Reserva");
            System.out.println("3. Obtener Información de una Reserva");
            System.out.println("4. Eliminar Reserva"); 
            System.out.println("5. Lista de Todas las Reservas"); 
            System.out.println("6. Generar Factura");  
            System.out.println("7. Guardar Modificaciones"); 
            System.out.println("8. Volver Atras");
            
            System.out.print("Opcion: ");
            String opcion = MyInput.readString();
            
            switch  (opcion){
                
                case "1": do{ 
                              gestion.registrar_Reservas(); 
                              System.out.println("Desea seguir agregando reservas? [Si / No]:");
                             
                             }while((MyInput.readString().compareToIgnoreCase("si")) == 0 ); break;
            
                case "2": gestion.modificar_Reserva(); break;
                
                case "3": gestion.info_Reserva(); break;
                
                case "4": gestion.eliminar_Reserva(); break;
                
                case "5": gestion.listado_Reservas(); break;
                
                case "6": gestion.generar_Factura(); break;
                
                case "7": gestion.guardar_Configuracion(); break;

                case "8": continuar = false; break;
                
                default : continuar = false; break;
                
            }
  
         }while(continuar);
        
        
    }
        
}

