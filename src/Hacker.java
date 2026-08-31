public class Hacker extends Personaje{
    int inteligencia;

    @Override
    public void trabajar() {
        super.trabajar(); //funciona el metodo de personaje y de hacker
        System.out.println("Trabajando con capucha");
    }
}


