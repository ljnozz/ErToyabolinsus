/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datojava.jcalendar;

/**
 *
 * @author LjnozZ
 */
import java.awt.Color;  
import java.util.Calendar;  
import java.util.Date;  
import com.toedter.calendar.IDateEvaluator;  
import java.io.IOException;
import java.text.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
  
public class DJFechasEspInv implements IDateEvaluator {  
  
 Calendar calendar = Calendar.getInstance();  
 private int i = 0;
 DJJCalendar djjcalendar; 
 
 public DJFechasEspInv() throws IOException, ClientProtocolException, JSONException, ParseException {
     djjcalendar = new DJJCalendar();
     djjcalendar.fechasOcupadas();
  
 }  
  
@Override  
public boolean isSpecial(Date date) {  
    calendar.setTime(date);
    for (i = 0; i < djjcalendar.getFechasOcupadas().size(); i++) {
        if (calendar.get(Calendar.MONTH) == djjcalendar.getFechasOcupadas()
                .get(i).get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == djjcalendar.getFechasOcupadas().get(i)
                        .get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
    }
    return false;
}  
  
 @Override  
 public Color getSpecialForegroundColor() {  
  return Color.RED;  
 }  
  
 @Override  
 public Color getSpecialBackroundColor() {  
  return Color.WHITE;  
 }  
  
@Override  
public String getSpecialTooltip() {  
 return DJJCalendar.tipFechas().get(i);  
}  
 
/*
 @Override  
 public boolean isInvalid(Date date) {  
  calendar.setTime(date);  
  return calendar.get(Calendar.MONTH) == Calendar.JANUARY  
    && calendar.get(Calendar.DAY_OF_MONTH) == 01;  
 }  */
  
 @Override  
 public boolean isInvalid(Date date) {  
  return false;  
 } 

 @Override  
 public Color getInvalidForegroundColor() {  
  return Color.WHITE;  
 }  
  
 @Override  
 public Color getInvalidBackroundColor() {  
  return Color.BLACK;  
 }  
  
 @Override  
 public String getInvalidTooltip() {  
  return "No es día Laborable";  
 }  
}  
