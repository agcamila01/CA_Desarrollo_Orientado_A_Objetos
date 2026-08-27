public class Personaje {
    private String nombre;
    private String apellido;
    private int vida; //this
    boolean esta_vivo = true;
    private int edad;
    private String tipoPersonaje;

    //encapsulamiento: getters:obtener el valor de un atributo, setters:asignar valor.
    public Personaje(){
        this.tipoPersonaje = "Sin categoria";
    }

    public Personaje(String tipoPersonaje){
        this.tipoPersonaje = tipoPersonaje;
    }

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
        if (nombre.length() < 1){
            System.out.println("El nombre del personaje no puede quedar vacio");
        }
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

    public void setTipoPersonaje(String tipoPersonaje){
        this.tipoPersonaje = tipoPersonaje;
    }

    public String getTipo(){
        return this.tipoPersonaje;
    }

    public String censurarPalabra(String palabra){
        // if (nombre.equals("palabra"));
        palabra = palabra.toLowerCase();
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