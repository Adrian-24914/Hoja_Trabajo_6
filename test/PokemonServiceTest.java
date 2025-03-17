package test;
import org.junit.*;
import model.Pokemon;
import service.PokemonService;
import java.util.*;

public class PokemonServiceTest {
    private PokemonService pokemonService;
    private Map<String, Pokemon> pokemonMap;
    private Pokemon pikachu;
    private Pokemon bulbasaur;

    @Before
    public void setUp() {
        // Inicialización de objetos y estructura de datos
        pokemonMap = new HashMap<>();
        
        pikachu = new Pokemon("Pikachu", "Electric", "Static");
        bulbasaur = new Pokemon("Bulbasaur", "Grass", "Overgrow");

        pokemonMap.put(pikachu.getName(), pikachu);
        pokemonMap.put(bulbasaur.getName(), bulbasaur);

        pokemonService = new PokemonService(pokemonMap);
    }

    // Test para agregar un Pokémon a la colección del usuario
    @Test
    public void testAddPokemonToUserCollection() {
        pokemonService.addPokemonToUserCollection("Pikachu");
        
        // Verificamos que Pikachu se ha agregado correctamente
        List<Pokemon> esperado = Arrays.asList(pikachu);
        Assert.assertEquals(esperado, pokemonService.getPokemonsSortedByType());
    }

    // Test para agregar un Pokémon que no existe y asegurar que lance excepción
    @Test
    public void testAddPokemonToUserCollection_ShouldThrowExceptionWhenPokemonNotFound() {
        try {
            pokemonService.addPokemonToUserCollection("Charmander");
            Assert.fail("Se esperaba una excepción");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Pokemon no encontrado.", e.getMessage());
        }
    }

    // Test para intentar agregar un Pokémon duplicado y verificar que no se agregue
    @Test
    public void testAddDuplicatePokemonToUserCollection() {
        pokemonService.addPokemonToUserCollection("Pikachu");
        pokemonService.addPokemonToUserCollection("Pikachu");

        // Verificamos que solo haya un Pokémon en la colección
        List<Pokemon> esperado = Arrays.asList(pikachu);
        Assert.assertEquals(esperado, pokemonService.getPokemonsSortedByType());
    }

    // Test para obtener un Pokémon por su nombre
    @Test
    public void testGetPokemon() {
        Assert.assertEquals(pikachu, pokemonService.getPokemon("Pikachu"));
    }

    // Test para verificar que los Pokémon se ordenen por tipo en la colección del usuario
    @Test
    public void testGetPokemonsSortedByType() {
        pokemonService.addPokemonToUserCollection("Pikachu");
        pokemonService.addPokemonToUserCollection("Bulbasaur");

        // Verificamos que los Pokémon se ordenen correctamente
        List<Pokemon> esperado = Arrays.asList(bulbasaur, pikachu);
        Assert.assertEquals(esperado, pokemonService.getPokemonsSortedByType());
    }

    // Test para verificar que todos los Pokémon se ordenen por tipo
    @Test
    public void testGetAllPokemonsSortedByType() {
        List<Pokemon> esperado = Arrays.asList(bulbasaur, pikachu);
        Assert.assertEquals(esperado, pokemonService.getAllPokemonsSortedByType());
    }

    // Test para verificar que se obtienen los Pokémon con una habilidad específica
    @Test
    public void testGetPokemonsByAbility() {
        List<Pokemon> esperado = Arrays.asList(pikachu);
        Assert.assertEquals(esperado, pokemonService.getPokemonsByAbility("Static"));
    }

    // Test para verificar que la lista esté vacía cuando no haya coincidencias con la habilidad
    @Test
    public void testGetPokemonsByAbility_ShouldReturnEmptyListWhenNoMatch() {
        List<Pokemon> esperado = new ArrayList<>();
        Assert.assertEquals(esperado, pokemonService.getPokemonsByAbility("Blaze"));
    }
}

