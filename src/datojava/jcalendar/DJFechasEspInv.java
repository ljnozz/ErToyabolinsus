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
  
public class DJFechasEspInv implements IDateEvaluator {  
  
 Calendar calendar = Calendar.getInstance();  
 private int i = 0;
    
 public DJFechasEspInv() {  
  
 }  
  
@Override  
public boolean isSpecial(Date date) {  
 calendar.setTime(date);  
 for (i = 0; i < DJJCalendar.fechasOcupadas().size(); i++) {  
  if (calendar.get(Calendar.MONTH) == DJJCalendar.fechasOcupadas()  
    .get(i).get(Calendar.MONTH)  
    && calendar.get(Calendar.DAY_OF_MONTH) == DJJCalendar  
      .fechasOcupadas().get(i)  
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
  
 @Override  
 public boolean isInvalid(Date date) {  
  calendar.setTime(date);  
  return calendar.get(Calendar.MONTH) == Calendar.JANUARY  
    && calendar.get(Calendar.DAY_OF_MONTH) == 01;  
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
