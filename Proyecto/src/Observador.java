interface Observador{
    void actualizar(String mensaje);
}

interface Sujeto {
    void notificarObservador();
}

class Persona implements Sujeto{
    private String mensaje;
    private int id;


    public Persona(int id,String mensaje){
        this.mensaje = mensaje;
        this.id = id;
    }
    @Override
    public void notificarObservador(){
        InsertValuesInJSON manejadorJSON = new InsertValuesInJSON();
        manejadorJSON.editarMensaje(this.id,this.mensaje);
        manejadorJSON.imprimir(); 
    }
}
