public class Personaje {
    private String nombre;
    private String apellido;
    private int vida; //this
    boolean esta_vivo = true;
    private int edad;

    //encapsulamiento: getters, setters.

    public void setearVida(int vida){
        //this.vida = vida; //poner this si tienen el mismo nombre
        if (vida < 0) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }

    public int verVida(){
        return this.vida;
    }

    public void ingresarEdad(int edad){
        if (edad < 0) {
            System.out.println("La edad no puede ser negativa, asi que le asignamos a tu personaje la edad de 18");
            this.edad = 18;
        } else {
            this.edad = edad;
        }
    }
    
    public int obtenerEdad(){
        return this.edad;
    }

    //tarea: encapsular: tonto, sopenco, conchudo, weon, pipi, caca
    public void setNombre(String nombre){
        this.nombre = censurarPalabra(nombre);
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setApellido(String apellido){
        this.apellido = censurarPalabra(apellido);
    }

    public String getApellido(){
        return this.apellido;
    }

    public String censurarPalabra(String palabra){
        boolean prohibida = false;
        switch (palabra) {
            case "tonto":
            case "sopenco":
            case "conchudo":
            case "weon":
            case "pipi":
            case "caca":
                prohibida = true;
                break;
            default:
                break;
        }
        if (prohibida) {
            System.out.println("No puedes elegir esa palabra, está prohibida!!");
            return "*".repeat(palabra.length());
        } else {
            return palabra;
        }
    }
}