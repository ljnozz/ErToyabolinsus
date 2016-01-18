/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datojava.jcalendar;

import Services.Leer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author LjnozZ
 */
public class DJJCalendar {

    private int i = 0;    
        //////////////Lecturas y escrituras de la BD
        Leer rutasLeer;
        SimpleDateFormat formato;
        JSONArray fechasOcupadasArray;
        List<Calendar> fechasOcupadas = new ArrayList<Calendar>();
        DateTimeFormatter formatter;
        
    public DJJCalendar() throws IOException, ClientProtocolException, JSONException {
        rutasLeer = new Leer();
        formato = new SimpleDateFormat("yyyy-MM-dd");
        fechasOcupadasArray = rutasLeer.leer("http://localhost/API_Citas/public/Diasocupados/diasOcupados");
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    
    

public void fechasOcupadas() throws IOException, ClientProtocolException, JSONException, ParseException {  
        for (int i = 0;i<fechasOcupadasArray.length();i++) {  
            JSONObject obj = (JSONObject)fechasOcupadasArray.get(i);
            //Date date = formato.parse(obj.get("diasOcupados").toString());
            LocalDate date = LocalDate.parse(obj.get("diasOcupados").toString(), formatter);
            Calendar calendar = new GregorianCalendar(date.getYear(),date.getMonthValue()-1,date.getDayOfMonth());
            //Calendar calendar = new GregorianCalendar(2015,Calendar.DECEMBER,12);  
            fechasOcupadas.add(calendar);  
        }
        
            System.out.println("retorne el djj"); 
    }

    public List<Calendar> getFechasOcupadas() {
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
