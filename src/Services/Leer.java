package Services;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rafael
 */
public class Leer {



    
    
    
public JSONArray leer(String ruta) throws ClientProtocolException, IOException, JSONException {
        
        HttpClient client = new DefaultHttpClient();
     int id=1;
 // HttpGet get = new HttpGet("http://ip.no-ip.org:8000/ruta");
    HttpGet get = new HttpGet(ruta);
  
    HttpResponse response = client.execute(get);
    HttpEntity entity= response.getEntity();
    String content = EntityUtils.toString(entity);
    System.out.println("ESTE ES EL JSON en LEER " + content);
  
   JSONArray json = new JSONArray(content);
   
   
        return json;
   }


    
    
    
    
  public static void main(String[] args) throws IOException, ClientProtocolException, JSONException {
    
//System.out.println(Leer("http://localhost/API_Medico/API_Medico/public/Citas/porFecha/2015-12-12"));
 }
}
