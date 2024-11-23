import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class InsertValuesInJSON {
    private final String rutaArchivo = "output.json";

    // Agregar usuario al JSON
    public void agregarUsuario(int id, String usuarioNombre, String mensaje) { 
        try {
            JSONParser recorrido = new JSONParser();
            JSONArray buscaUsuario = obtenerUsuarios(recorrido);
        
            boolean usuarioEncontrado = false;
            
            for (Object obj : buscaUsuario) {
                JSONObject usuario = (JSONObject) obj;
                if (Integer.parseInt(usuario.get("id").toString()) == id) {
                    usuarioEncontrado = true;
                    break;
                }
            }
            if (!usuarioEncontrado) {
                JSONObject nuevoUsuario = new JSONObject();
                nuevoUsuario.put("id", id);
                nuevoUsuario.put("username", usuarioNombre);
                nuevoUsuario.put("message", mensaje);
    
                JSONParser parser = new JSONParser();
                JSONArray usuarios = obtenerUsuarios(parser);
    
                usuarios.add(nuevoUsuario);
                guardarArchivo(usuarios);
                System.out.println("Usuario agregado exitosamente.");
            }
            else{
                System.out.println("Ya existe un usuario con el mismo ID");
            }
        } catch (Exception e) {
            System.err.println("Error al agregar usuario: " + e.getMessage());
        }
    }

    // Editar mensaje en el JSON
    public void editarMensaje(int id, String nuevoMensaje) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray usuarios = obtenerUsuarios(parser);
    
            boolean usuarioEncontrado = false;
    
            for (Object obj : usuarios) {
                JSONObject usuario = (JSONObject) obj;
                if (Integer.parseInt(usuario.get("id").toString()) == id) {
                    usuario.put("message", nuevoMensaje);
                    usuarioEncontrado = true;
                    break;
                }
            }
    
            if (usuarioEncontrado) {
                guardarArchivo(usuarios);
                System.out.println("Mensaje editado correctamente.");
            } else {
                System.out.println("No se encontró un usuario con el ID: " + id);
            }
    
        } catch (Exception e) {
            System.err.println("Error al editar mensaje: " + e.getMessage());
        }
    }    

    // Mostrar contenido del JSON
    public void imprimir() {
        MensajeTexto mt;
        MensajeVideo mv;
        try {
            JSONParser parser = new JSONParser();
            JSONArray usuarios = obtenerUsuarios(parser);

            for (Object obj : usuarios) {
                JSONObject usuario = (JSONObject) obj;
                System.out.print("\nID: " + usuario.get("id") + "\nUsuario: " + usuario.get("username") + "\nMensaje: ");
                try {
                    mv = new MensajeVideo((String) usuario.get("message"));
                    mv.mostrar();
                } catch (Exception e) {
                    mt = new MensajeTexto((String) usuario.get("message"));
                    mt.mostrar();
                }
                System.out.println("--------------------------------");
            }
        } catch (Exception e) {
            System.err.println("Error al imprimir contenido: " + e.getMessage());
        }
    }

    // Obtener usuarios del archivo
    private JSONArray obtenerUsuarios(JSONParser parser) throws IOException, ParseException {
        FileReader fileReader = new FileReader(rutaArchivo);
        JSONObject jsonObject = (JSONObject) parser.parse(fileReader);
        return (JSONArray) jsonObject.get("users");
    }

    // Guardar usuarios en el archivo
    private void guardarArchivo(JSONArray usuarios) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("users", usuarios);

        try (FileWriter fileWriter = new FileWriter(rutaArchivo)) {
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.flush();
        }
    }
}
