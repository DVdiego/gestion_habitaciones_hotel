
package gestion;
import entrada.MyInput;
import java.io.Serializable;
import java.util.Date;

public class Reserva implements Comparable<Reserva>, Serializable {
    
   private Date dia_inicio;
   private Date dia_final;
   private String dni;
   private int camas_supletorias;
   
   private boolean vip, pendiente_cobro; 
    
   Reserva(){
       this.dia_inicio = null;
       this.dia_final = null;
       this.dni = "";
       this.pendiente_cobro = true;
       this.vip = false;
       this.camas_supletorias = 0;
        
   }
   
   
   public void set_Dia_Inicio(Date a){
       
       this.dia_inicio = a;
        
   }
   
   public void set_Dia_Final(Date b){
       
       this.dia_final = b;
   }
   
   public void set_Dni(String c){
       
       this.dni = c;
             
   }
   
   public void set_Pendiente_Cobro(boolean a){
       
       this.pendiente_cobro = a;
   }
   
   public void set_Es_Vip(boolean b){
       
       this.vip = b;
   }
   
   public void set_camas_supletorias(int a){
       this.camas_supletorias = a;
   }
   
   
   public void set_Es_Vip_string(){
       boolean  continuar =  true;
        do{ 
            System.out.print("Es vip: [Si/No]: ");
            String a;                   
            a = MyInput.readString();
                                
            if(a.equalsIgnoreCase("Si")){
                set_Es_Vip(true);
                continuar = false;}
            else if(a.equalsIgnoreCase("No")){
                set_Es_Vip(true);
                continuar = false;
            }   
            
        }while(continuar);
   }
   
   public int get_camas_supletorias(){
       return this.camas_supletorias;
   }
   
   
   
   public Date get_Dia_Inicio(){
       
       return this.dia_inicio;
   }
   
   public Date get_Dia_Final(){
       
       return this.dia_final;
   }
   
   public String get_Dni(){
       
       
       return this.dni;
   }
   
   public boolean get_Pendiente_Cobro(){
       
       
       return this.pendiente_cobro;
   }
   
   public boolean get_Es_Vip(){
       
      return this.vip;
   }
   
    @Override
    public int compareTo(Reserva o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
   public String get_Es_Vip_string(){
       String a;
       if(this.vip){
           a = "Si";
       }else
           a = "No";
       return a;
   }
   
   
   
 

   
   
   
   
}
