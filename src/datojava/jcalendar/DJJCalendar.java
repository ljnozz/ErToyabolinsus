/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datojava.jcalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author LjnozZ
 */
public class DJJCalendar {

private int i = 0;    
    
public static List<Calendar> fechasOcupadas() {  
 List<Calendar> fechasOcupadas = new ArrayList<Calendar>();  
        

        Calendar calendar = new GregorianCalendar(2015, Calendar.SEPTEMBER, 10);  
        fechasOcupadas.add(calendar);  
        calendar = new GregorianCalendar(2015, Calendar.DECEMBER, 11);  
        fechasOcupadas.add(calendar);  
        calendar = new GregorianCalendar(2015, Calendar.NOVEMBER, 18);  
        fechasOcupadas.add(calendar);  

        return fechasOcupadas;  
    }  




public static List<String> tipFechas() {  
 List<String> tips = new ArrayList<String>();  
  
 tips.add("Septiembre 10");  
 tips.add("Noviembre 10");  
 tips.add("Noviembre 18");  
  
 return tips;  
}  




}
