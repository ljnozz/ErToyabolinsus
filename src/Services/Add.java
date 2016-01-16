package Services;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONException;

//Ejemplo de como hacer un POST lean los comentarios para que entiendan
// Tienen que importar todas estas librerias las puse en el drive
// para importarlas le dan a la carpeta librarues de su proyecto
// luego le dan a add librery le dan a create 
// Primero ingregaremos las librerias http 
// Le ponen HTTP asi en mayuscula de nombre le dan a ok y luego le dan a ADD Jar
// bucan la carpeta que les mande commons y agregan todos esos archivos comoms que salen ahi
// luego aceptan la crearon les saldra en la lista y le dan a add y listo
// despues agrega la libreria de json agarran y hacen lo mismo le ponen json de nombre
// luego buscan el archivo jar en la carpeta json lo agregan y listo..


public class Add {
    
    // Ejemplo de como hacer un POST
    
    
    
    
    
    public void add(String ruta, JSONObject json) throws ClientProtocolException, IOException, JSONException{
        //Cita
        /*JSONObject cita=new JSONObject();
        cita.put("fecha","2015-12-12");
        cita.put("hora","10:09");
        cita.put("paciente","1");
        cita.put("medicos","1");
        cita.put("tratamiento","tratamiento");
        cita.put("diagnostico","diagnostico");
        cita.put("motivo", "Dolor de pecho");
        System.out.println(cita);
        */
        
//    //Paciente
//    JSONObject paciente=new JSONObject();
//    paciente.put("cedula",cedulaJ.getText().toString());
//    paciente.put("nombre",nombreJ.getText().toString());
//    paciente.put("apellido",apellidoJ.getText().toString());
//    paciente.put("direccion",direccionJ.getText().toString());
//    paciente.put("correo",correoJ.getText().toString());
//    paciente.put("tlfncasa",telefonoCasaJ.getText().toString());
//    paciente.put("tlfncelular",telefonoCelularJ.getText().toString());
//    System.out.print(paciente);
        
//         try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
//            HttpPost request = new HttpPost("http://localhost:8000/Citas");
//            StringEntity params = new StringEntity(cita.toString());
//            request.addHeader("content-type", "application/json");
//            request.setEntity(params);
//            HttpResponse result = httpClient.execute(request);
//
//            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
//           // System.out.println(json);
//            try {
//                JSONParser parser = new JSONParser();
//                Object resultObject = parser.parse(json);
//                System.out.println(resultObject.toString());
//
//                if (resultObject instanceof JSONArray) {
//                    JSONArray array=(JSONArray)resultObject;
//                    for (Object object : array) {
//                        JSONObject obj =(JSONObject)object;
//                        System.out.println(obj.get("example"));
//                        System.out.println(obj.get("fr"));
//                    }
//
//                }else if (resultObject instanceof JSONObject) {
//                    JSONObject obj =(JSONObject)resultObject;
//                    System.out.println(obj.get("example"));
//                    System.out.println(obj.get("fr"));
//                }
//
//            } catch (Exception e) {
//                // TODO: handle exception
//            }
//        }
HttpClient client = new DefaultHttpClient(); // cliente

//HttpPost url = new HttpPost("http://localhost/API_Citas/public/Citas/create");// aqui ponen el url que necesiten lo unico que tiene que cambiar es el /notes ponen el que quieran por ejemplo /crearusuario

//HttpPost url = new HttpPost("http://localhost/API_Citas/public/Diasocupados/insertarfecha");// aqui ponen el url que necesiten lo unico que tiene que cambiar es el /notes ponen el que quieran por ejemplo /crearusuario
//
HttpPost url = new HttpPost(ruta);
StringEntity se = new StringEntity( json.toString()); //convierte el json en este tipo para poder pasarlo
se.setContentType("application/json;charset=UTF-8"); // especificamos que es un json
url.setHeader("Accept", "application/json");              // acceptamos el json
url.setEntity(se); // lo agregamos al post
HttpResponse response = client.execute(url); // hacemos la peticion
System.out.println("RESPUESTA");
System.out.println(response.toString()); // imprimo la respuesta

// de aqui para abajo sirve para ver errores,
// por ejemplo s hay algun error con el servidor esto te manda la pagina de respuesta del servidor
// y te la escribe en la consola
// pero en texto al final se puede ver el tipo de error y eso..
BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
String line = "";
while ((line = rd.readLine()) != null) {
    System.out.println(line);
}
    
    
    }
    
    

 public static void main(String[] args) {
    

 }
}
