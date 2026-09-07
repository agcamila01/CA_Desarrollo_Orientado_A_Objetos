Un array tiene una longitud fija,
Array es más rápido y eficiente en cuanto al uso de memoria
Array almacena tipos primitivos y objetos

-Un array es una estructura de datos de tamaño fijo que se utiliza para almacenar múltiples 
elementos del mismo tipo de datos en ubicaciones de memoria contiguas. 
Se accede a cada elemento mediante su índice, comenzando desde 0.
-Se puede acceder a la longitud de un array mediante la propiedad length

dataType[] arrayName; // Declaración 
dataType[] arrayName = new dataType[size]; // Declaración y creación 
int[] numbers = {10, 20, 30, 40, 50}; // Declaración, creación e inicialización

ArrayList puede crecer y encogerse dinámicamente.
ArrayList ofrece mayor flexibilidad gracias a sus métodos integrados.
ArrayList almacena solo objetos (utiliza clases contenedoras para los tipos primitivos)

-Se expande o contrae automáticamente a medida que se agregan o eliminan elementos.
-Mantiene el orden de inserción y permite elementos duplicados.
-Almacena únicamente objetos; para valores primitivos, 
    se utilizan clases contenedoras como Integer, Double y Character
    
ArrayList<String> list = new ArrayList<>();



