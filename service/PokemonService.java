package service;
import model.Pokemon;
import java.util.*;

public class PokemonService {
    private Map<String, Pokemon> pokemonMap;
    private List<Pokemon> userCollection;

    public PokemonService(Map<String, Pokemon> pokemonMap) {
        this.pokemonMap = pokemonMap;
        this.userCollection = new LinkedList<>();
    }

    public void addPokemonToUserCollection(String name) {
        if (!pokemonMap.containsKey(name)) {
            throw new IllegalArgumentException("Pokemon no encontrado.");
        }
        if (!userCollection.contains(pokemonMap.get(name))) {
            userCollection.add(pokemonMap.get(name));
        } else {
            System.out.println("El pokemon ya está en la colección.");
        }
    }

    public Pokemon getPokemon(String name) {
        return pokemonMap.get(name);
    }

    public List<Pokemon> getPokemonsSortedByType() {
        List<Pokemon> list = new LinkedList<>(userCollection);
        list.sort(Comparator.comparing(Pokemon::getType1));
        return list;
    }

    public List<Pokemon> getAllPokemonsSortedByType() {
        List<Pokemon> list = new LinkedList<>(pokemonMap.values());
        list.sort(Comparator.comparing(Pokemon::getType1));
        return list;
    }

    public List<Pokemon> getPokemonsByAbility(String ability) {
        List<Pokemon> result = new LinkedList<>();
        for (Pokemon p : pokemonMap.values()) {
            if (p.getAbility().equalsIgnoreCase(ability)) {
                result.add(p);
            }
        }
        return result;
    }
}