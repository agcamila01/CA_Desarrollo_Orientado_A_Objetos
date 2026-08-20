public class Personaje {
    private String nombre;
    String apellido;
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
        if (nombre == "tonto") {
            this.nombre = "a";
        } else {
            this.nombre = nombre;
        }
    }

    public String getNombre(){
        return this.nombre;
    }

}