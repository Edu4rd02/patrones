import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.awt.Desktop;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Persona> usuarios = new ArrayList<>();
    public static List<Persona3> sistema = new ArrayList<>();
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        usuarios.add(new Persona("Usuario1"));
        usuarios.add(new Persona("Usuario2"));

        sistema.add(new Persona3("Sistema1"));
        sistema.add(new Persona3("Sistema2"));


        usuarios.get(0).agregarObservador(sistema.get(0));
        usuarios.get(1).agregarObservador(sistema.get(1));

        usuarios.get(0).crearMensajeTexto("Hola");

        InsertValuesInJSON is = new InsertValuesInJSON();
        is.imprimir();
        // usuario2.crearMensajeTexto("Hola");
    }
}
