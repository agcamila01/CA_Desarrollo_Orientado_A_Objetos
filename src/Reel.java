public class Reel extends Publicacion {
    private int duracionSegundos;
    private String audioNombre;
    private int reproducciones;

    public Reel(String id, String autor, int duracionSegundos, String audioNombre) {
        super(id, autor);
        setSegundos(duracionSegundos);
        setAudioNombre(audioNombre);
    }

    @Override 
    public void mostrarDetalle(){
        System.out.println("Detalles del Reel");
    }

    public void reproducir(){
        this.reproducciones++;
        System.out.println("Reproduciendo Reel...");
    }

    public void setSegundos(int segundos){
        if (segundos < 3) {
            this.duracionSegundos = 3;
            System.out.println("Duración ajustada al mínimo (3s)");
        } else if (segundos > 90) {
            this.duracionSegundos = 90;
            System.out.println("Duración ajustada al máximo (90s)");
        } else {
            this.duracionSegundos = 90;
        }
    }

    public void setAudioNombre(String audioNombre){
        if (audioNombre.trim().length() <= 0) {
            this.audioNombre = "Audio original de " + getAutor();
        } else {
            this.audioNombre = audioNombre;
        }
    }

    public int getSegundos(){
        return this.duracionSegundos;
    }

    public String getAudioNombre(){
        return this.audioNombre;
    }

    public int getReproducciones(){
        return this.reproducciones;
    }
    
}
