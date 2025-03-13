package service;
import model.Pokemon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class CSVReader {
    public static void loadPokemonData(String filePath, Map<String, Pokemon> pokemonMap) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Saltar la primera línea (encabezados)

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length < 9) continue; // Validación de datos

                String name = values[0].trim();
                String type1 = values[2].trim();
                String ability = values[7].trim().split(" ")[0]; // Tomar solo la primera habilidad

                Pokemon pokemon = new Pokemon(name, type1, ability);
                pokemonMap.put(name, pokemon);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
}

