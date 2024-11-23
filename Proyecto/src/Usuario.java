import java.util.List;
import java.util.ArrayList;

interface Observador{
    void actualizar(String mensaje);
}

interface Sujeto {
    void agregarObservador(Observador observador);
    void eliminarObservador(Observador observador);
    void notificarObservador(Observador observador, String mensaje);
}

class Persona implements Sujeto{
    private String nombre;
    private List<Observador> observadores = new ArrayList<>();

    public Persona(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void agregarObservador(Observador observador){
        observadores.add(observador);
    }
    @Override
    public void eliminarObservador(Observador observador){
        observadores.remove(observador);
    }
    @Override
    public void notificarObservador(Observador observadorClave, String mensaje){
        //!ACTUALIZAR Y MOSTRAR EL CONTENIDO DEL JSON CADA QUE SE AGREGUE UN MENSAJE
        InsertValuesInJSON in = new InsertValuesInJSON();
        in.imprimir(); //ESTO MUESTRA EL CONTENIDO
    }

    public void crearMensajeTexto(String mensaje){
        notificarObservador(null, mensaje);
    }
}

class Persona3 implements Observador{
    private String nombre;

    public Persona3(String nombre){
        this.nombre=nombre;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println(nombre+" ha recibido el mensaje: "+mensaje);
    }

}
