import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//Patron decorador ya que por el momento al aplicacion no será escalable
interface Mensaje{
    void mostrar() throws IOException, URISyntaxException;
}

class MensajeTexto implements Mensaje{
    private String contenido = "";

    public MensajeTexto(String contenido){
        this.contenido=contenido;
    } 
    
    @Override
    public void mostrar(){
        System.out.println(this.contenido);
    }
}

class MensajeVideo implements Mensaje{
    private String enlace = "";

    public MensajeVideo(String enlace){
        this.enlace=enlace;
    } 
    
    @Override
    public void mostrar() throws IOException, URISyntaxException{
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI(this.enlace));
            System.out.println(this.enlace);
            
        } else {
            System.err.println("Desktop no está soportado en este sistema.");
        }
    }
}
