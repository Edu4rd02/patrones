import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;

class InsertValuesInJSON {
    public void agregarUsuario(int id, String usuarioNombre, String mensaje){
        String rutaArchivo = "output.json"; // Archivo JSON

        // Nuevo usuario que queremos agregar
        JSONObject nuevoUsuario = new JSONObject();
        nuevoUsuario.put("id", 2);
        nuevoUsuario.put("username", "usuario2");
        nuevoUsuario.put("message", "¡Hola de nuevo!");

        try {
            // Leer el archivo existente
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(rutaArchivo));

            // Convertir el contenido a un JSONObject
            JSONObject jsonObject = (JSONObject) obj;

            // Obtener el arreglo "users"
            JSONArray usuarios = (JSONArray) jsonObject.get("users");

            // Agregar el nuevo usuario al arreglo
            usuarios.add(nuevoUsuario);

            // Sobrescribir el archivo con los datos actualizados
            try (FileWriter fileWriter = new FileWriter(rutaArchivo)) {
                fileWriter.write(jsonObject.toJSONString());
                fileWriter.flush();
                System.out.println("Usuario agregado exitosamente.");
            }

        } catch (IOException | ParseException e) {
            System.err.println("Error al manejar el archivo JSON: " + e.getMessage());
        }
    
    }

    public static void imprimirMensajes(){

    }

    public void imprimir(){
        String filePath = "output.json";

        // Crear el parser
        JSONParser parser = new JSONParser();

        try {
            // Leer el archivo y convertirlo a un JSONObject
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject2 = (JSONObject) obj;

            // Obtener el arreglo de usuarios
            JSONArray usersArray2 = (JSONArray) jsonObject2.get("users");

            // Iterar sobre el arreglo
            for (Object userObj : usersArray2) {
                JSONObject user = (JSONObject) userObj;
                System.out.println(user.get("username"));
                MensajeVideo tmpv = new MensajeVideo((String)user.get("message"));
                try {
                    tmpv.mostrar();
                } catch (IOException | URISyntaxException e) {
                    MensajeTexto tmpt = new MensajeTexto((String)user.get("message"));
                    tmpt.mostrar();
                }
                System.out.println();
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
