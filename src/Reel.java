public class Reel extends Publicacion {
    private int duracionSegundos;
    private String audioNombre;
    private int reproducciones;
    private static int contadorReel = 1;

    public Reel(Autor autor, int duracionSegundos, String audioNombre) {
        super("RE-" + contadorReel, autor);
        contadorReel++;
        setSegundos(duracionSegundos);
        setAudioNombre(audioNombre);
    }

    @Override 
    public void mostrarDetalle(){
        System.out.println("=== " + this.getId() + " ===");
        System.out.println("Autor: " + this.getAutor().getNombre());
        System.out.println("Duracion: " + this.getSegundos() + " segundos");
        System.out.println("Nombre del audio: " + this.getAudioNombre());
        System.out.println("Reproducciones: " + this.getReproducciones());
        System.out.println("Me gusta: " + this.getLikes());
        System.out.println("Fecha de Publicación: " + this.getFechaCreacion());
        System.out.println("===========================");
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
            this.audioNombre = "Audio original de " + getAutor().getNombre();
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
