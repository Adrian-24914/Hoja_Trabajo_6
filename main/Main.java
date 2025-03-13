package main;
import factory.MapFactory;
import model.Pokemon;
import service.PokemonService;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Selección de la implementación del Map
        System.out.println("Seleccione la implementación de MAP:");
        System.out.println("1) HashMap \n2) TreeMap \n3) LinkedHashMap");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Map<String, Pokemon> pokemonMap = MapFactory.createMap(choice);
        PokemonService service = new PokemonService(pokemonMap);

        // Menú
        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1) Agregar Pokémon a la colección");
            System.out.println("2) Mostrar datos de un Pokémon");
            System.out.println("3) Mostrar colección ordenada por tipo");
            System.out.println("4) Mostrar todos los Pokémon ordenados por tipo");
            System.out.println("5) Buscar Pokémon por habilidad");
            System.out.println("6) Salir");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    String name = scanner.nextLine();
                    service.addPokemonToUserCollection(name);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    name = scanner.nextLine();
                    Pokemon p = service.getPokemon(name);
                    if (p != null) {
                        System.out.println(p.getName() + " - " + p.getType1() + " - " + p.getAbility());
                    } else {
                        System.out.println("Pokémon no encontrado.");
                    }
                    break;
                case 3:
                    service.getPokemonsSortedByType().forEach(
                        pokemon -> System.out.println(pokemon.getName() + " - " + pokemon.getType1())
                    );
                    break;
                case 4:
                    service.getAllPokemonsSortedByType().forEach(
                        pokemon -> System.out.println(pokemon.getName() + " - " + pokemon.getType1())
                    );
                    break;
                case 5:
                    System.out.print("Ingrese la habilidad: ");
                    String ability = scanner.nextLine();
                    service.getPokemonsByAbility(ability).forEach(
                        pokemon -> System.out.println(pokemon.getName())
                    );
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
