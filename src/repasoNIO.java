import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class repasoNIO {
    public static void main(String[] args) {
        FileSystem sistemaFicheros = FileSystems.getDefault();
// también puedo obtener el objeto Path a partir de Paths.get(String ruta),
// pero a partir de la version 11 se aconseja el método estático Path.of(String ruta)
        Path rutaFichero = sistemaFicheros.getPath("textos.txt");
        String text = "Esto es una cadena de prueba\n";
// Escribimos con el método estático, de la clase Files, writeString
// Requiere 3 parámetros: el objeto Path, el String y las opciones
        try {
            Files.writeString(rutaFichero, text/*.getBytes(StandardCharsets.UTF_8)*/,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            text = "Esto es una segunda cadena de prueba\n";
            Files.writeString(rutaFichero, text/*.getBytes(StandardCharsets.UTF_8)*/, StandardOpenOption.APPEND);
            // Podemos leer todas las líneas en un ArrayList con el método estático readAllLines de la clase Files
            // Requiere 2 parámetros: el objeto Path y la codificación, o uno sólo y la codificación será UTF_8
            List<String> lines = Files.readAllLines(Paths.get("textos.txt"), StandardCharsets.UTF_8);
            for (String line : lines)
                System.out.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
