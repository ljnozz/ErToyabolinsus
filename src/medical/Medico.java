/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medical;

/**
 *
 * @author LjnozZ
 */



public class Medico {
    
int horaInicio;
int minInicio;
int MAXhorasDeAtencionxdia;
int minutosDeAtencionxPaciente;
int citasPorDia;
    
    public Medico(int horaInicio, int minInicio, int MAXhorasDeAtencionxdia, int minutosDeAtencionxPaciente){
        
        this.horaInicio=horaInicio;
        this.minInicio=minInicio;
        this.MAXhorasDeAtencionxdia=MAXhorasDeAtencionxdia;
        this.minutosDeAtencionxPaciente=minutosDeAtencionxPaciente;
        this.citasPorDia=cantidadDeCitasxDia(MAXhorasDeAtencionxdia,minutosDeAtencionxPaciente);
        
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getMinInicio() {
        return minInicio;
    }

    public int getMAXhorasDeAtencionxdia() {
        return MAXhorasDeAtencionxdia;
    }

    public int getMinutosDeAtencionxPaciente() {
        return minutosDeAtencionxPaciente;
    }

    public int getCitasPorDia() {
        return citasPorDia;
    }

    
        
    public int cantidadDeCitasxDia (int MAXhorasDeAtencionxdia, int minutosDeAtencionxPaciente){
        int cantidad;
        cantidad=(this.MAXhorasDeAtencionxdia*60)/minutosDeAtencionxPaciente;
        return cantidad;
    }
    
    
    
    
}
