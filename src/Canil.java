public class Canil {
    
    public static void main(String[] args) {
        System.out.println("***** Bienvenido al Canil *****");
        
        Perro perro_del_profe = new Perro();
        Perro perro_de_camila = new Perro();
        
        perro_del_profe.nombre = "Gimli";
        perro_del_profe.edad = 6;
        perro_del_profe.peso = 40;
        perro_del_profe.tiene_vacuna = false; 

        perro_de_camila.nombre = "Sami";
        perro_de_camila.edad = 12;
        perro_de_camila.peso = 35;
        perro_de_camila.tiene_vacuna = true;

        if (perro_de_camila.edad > 2){
            System.out.println(perro_de_camila.nombre + " tiene más de dos anios");
        } else{
            System.out.println(perro_de_camila.nombre + " tiene menos de dos anios");
        }

        if (perro_de_camila.peso > 20){
            System.out.println(perro_de_camila.nombre + " clasifica como Perro Pesado");
        } else{
            System.out.println(perro_de_camila.nombre + " clasifica como Perro Ligero");
        }


        System.out.println("Gracias por su visita :)");
    }

}