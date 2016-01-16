/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Citas;

import javax.swing.JLabel;

/**
 *
 * @author Yanir
 */


public class Citas extends JLabel {
String Hora;
String Paciente;
String Cedula;
String Dia;

    public Citas(String noCita, String hora){
        this.Hora=hora;
        this.setText(noCita);
        System.out.println("Esta es la hora: "+Hora);
        
    }
    


    public Citas(String Hora, String Paciente, String Dia) {
        this.Hora = Hora;
        this.Paciente = Paciente;
        this.Dia = Dia;
        
        this.setText( Hora +": "+ this.Paciente);
    }
    
    public Citas(int i) {
        
        switch (i){
                case 0:
                        this.Dia = "11/12/2015";
                        this.Hora = "2:00 - 2:30";
                        this.Paciente = "Yanir Castillo";
                        this.Cedula = "84.278.404";
                        this.setText(Hora +": "+ Paciente +" - "+ this.Cedula );
                        break;
                case 1:
                        this.Dia = "11/12/2015";
                        this.Hora = "2:30 - 3:00";
                        this.Paciente = "Lino de Aguiar";
                        this.Cedula = "20.503.792";
                        this.setText(Hora +": "+ Paciente +" - "+ this.Cedula );
                        break;
                
                case 2:
                        this.Dia = "11/12/2015";
                        this.Hora = "3:00 - 3:30";
                        this.Paciente = "Miguel Boscan";
                        this.Cedula = "25.266.350";
                        this.setText(Hora +": "+ Paciente +" - "+ this.Cedula );
                        break;
        }
        
    }

    public String getHora() {
        return Hora;
    }

    Citas() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
