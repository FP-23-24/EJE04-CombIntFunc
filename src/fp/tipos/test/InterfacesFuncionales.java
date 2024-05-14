package fp.tipos.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.crypto.dsig.Transform;

import fp.tipos.Persona;

public class InterfacesFuncionales {

	
	public static List<Persona> crearPoolDatos(){
		List<Persona> personas = new ArrayList<>();
		 personas.add( new Persona("Juan", 20, true, 
                 Set.of("fútbol", "cine"), 
                 Set.of("España", "Chile"), 
                 List.of("Don Quijote", "El Principito")));
		 personas.add(new Persona("Maria", 15, false, 
                 Set.of("lectura", "música", "cine"), 
                 Set.of("Francia", "Chile"), 
                 List.of("El Principito", "1984")));
		 personas.add(  new Persona("Pedro", 17, true, 
                 Set.of("fútbol", "natación"), 
                 Set.of("México"), 
                 List.of("Cien Años de Soledad", "Moby Dick")));
	    personas.add(new Persona("Luisa", 25, false,
                 Set.of("música", "cine"),
                 Set.of("Italia", "España"),
                 List.of("Orgullo y prejuicio", "El Señor de los Anillos")));
		 personas.add(new Persona("Andrea", 18, true,
                 Set.of("fútbol", "baloncesto"),
                 Set.of("Colombia"),
                 List.of("Harry Potter", "Los juegos del hambre")));
		 personas.add(new Persona("Carlos", 30, true,
                 Set.of("natación"),
                 Set.of("Estados Unidos"),
                 List.of("El Alquimista", "Las Crónicas de Narnia")));
		 personas.add(new Persona("Elena", 22, false,
                 Set.of("lectura"),
                 Set.of("Argentina"),
                 List.of("La sombra del viento", "El Perfume")));
		 personas.add(new Persona("Laura", 28, false,
                 Set.of("senderismo"),
                 Set.of("Alemania"),
                 List.of("1984", "El Código Da Vinci")));
		 personas.add(new Persona("Santiago", 19, true,
                 Set.of("música", "videojuegos"),
                 Set.of("Perú", "Brasil"),
                 List.of("Crónicas de una muerte anunciada", "El Laberinto de los Espíritus")));
		 personas.add(new Persona("Ana", 16, true,
                 Set.of("baloncesto", "bailar"),
                 Set.of("Uruguay"),
                 List.of("Los pilares de la Tierra", "El Hobbit")));
		return personas;
	}
	
	public static void main(String [] args) {
		
		 //Inicializando la lista de objetos
		 List<Persona> personas = crearPoolDatos();
	    
		 
		 /******************************************************
		  * EJERCICIOS DE PREDICADOS
		  *****************************************************/
	     System.out.println("Mayores de 18 años:");
	     //Predicado: tiene la persona más de 18 años
	     Predicate<Persona> predMayor18=null; 
	     filtrar(personas, predMayor18).stream()
				.forEach(p->System.out.println("\t"+p));
	    	
	     System.out.println("Tiene licencia de conducir:");
	     Predicate<Persona> predTieneLic=null; 
	     filtrar(personas, predTieneLic).stream()
			.forEach(p->System.out.println("\t"+p));
 
	    System.out.println("Han visitado Chile:");
	    Predicate<Persona> predVisitChile = null;
	    filtrar(personas, predVisitChile).stream()
			.forEach(p->System.out.println("\t"+p));

	    System.out.println("Le gustan el cine y la música:");
	    Predicate<Persona> predGustaCineMus = null;
	    filtrar(personas, predGustaCineMus).stream()
			.forEach(p->System.out.println("\t"+p));

	    System.out.println(" No tienen licencia de conducir:");
	    Predicate<Persona> predNoTieneLic = null;
	    filtrar(personas, predNoTieneLic).stream()
			.forEach(p->System.out.println("\t"+p));

    	System.out.println("No han visitado México, pero sí Chile:");
    	 Predicate<Persona> predNoMexSiChil = null;
 	    filtrar(personas, predNoMexSiChil).stream()
 			.forEach(p->System.out.println("\t"+p));

 	   System.out.println("No han visitado México o Chile:");
 	   Predicate<Persona> predNoMexOChil = null;
	   filtrar(personas, predNoMexOChil).stream()
			.forEach(p->System.out.println("\t"+p));

	
	   
	 /******************************************************
	  * FUNCIONES
	  *****************************************************/
	   System.out.println("Nombre de la persona:");
 	   Function<Persona, String> funNombre = Persona::getNombre;
	   transformarList(personas, funNombre).stream()
					.forEach(p->System.out.println("\t"+p));

	   
 	   System.out.println("Num libros leídos:");
 	   Function<Persona, Integer> funNumLibros = null;
	   transformarList(personas, funNumLibros).stream()
					.forEach(p->System.out.println("\t"+p));

	   
	   System.out.println("Último libro leído:");
 	   Function<Persona, String> funUltimoLibro = null;
	   transformarList(personas, funUltimoLibro).stream()
					.forEach(p->System.out.println("\t"+p));

	 
	   System.out.println("Países visitados");
	   Function<Persona, Stream<String>> funPaisesVisitados=null;
   	   System.out.println(transformarAplanarSet(personas, funPaisesVisitados));

  	 /******************************************************
   	  * COMBINACIÓN DE  FUNCIONES
   	  *****************************************************/ 
   	   System.out.println ("Num caracteres de titulos de ultimo libro leido");
   	   Function<String, Integer> funNumCar=null;
   	   Function<Persona, Integer> funCarUltLib =null;
   	   transformarList(personas, funCarUltLib).stream()
   	   		.forEach(p->System.out.println("\t"+p));


   	   System.out.println ("Inicial del nombre ");
	   Function<String, Character> funInicial=s->s.charAt(0);
	   Function<Persona, Character> funInicialPer =funNombre.andThen(funInicial);
	   Function<Persona, Character> funInicialPer2 =funInicial.compose(funNombre);
	   
	   transformarList(personas, funInicialPer).stream()
	   		.forEach(p->System.out.println("\t"+p));
	   


	}

	private static <T> List<T> filtrar(Collection<T> col, Predicate<T> pred) {
		
		return col.stream()
				  .filter(pred)
				  .collect (Collectors.toList());
	}

	
	private static <T,R> List<R> transformarList(Collection<T> col, Function<T, R> funcion) {
		return col.stream()
				  .map(funcion)
				  .collect (Collectors.toList());
	}

	private static <T,R> Set<R> transformarAplanarSet(Collection<T> col, Function<T, Stream<R>> funcion) {
		return col.stream()
				  .flatMap(funcion)
				  .collect (Collectors.toSet());
	}

}
