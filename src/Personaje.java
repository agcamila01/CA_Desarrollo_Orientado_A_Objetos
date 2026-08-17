public class Personaje {
    String nombre;
    String apellido;
    private int vida;
    boolean esta_vivo = true;

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
}

