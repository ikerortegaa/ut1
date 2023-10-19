import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/*2. Copia el contenido de un fichero en otro haciendo uso
de los métodos estáticos de la clase Files de java.nio.*/
public class ej2 {
    public static void main(String[] args) {
        // Ruta del archivo de origen
        Path sourcePath = Paths.get("origenEj2.txt");
        // Ruta del archivo de destino
        Path destinationPath = Paths.get("destinoEj2.txt");

        try {
            // Copia el archivo de origen al archivo de destino
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
