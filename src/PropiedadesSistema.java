// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class PropiedadesSistema {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("El separador de directorios es " + System.getProperty("file.separator"));
        System.out.println("El directorio de inicio de sesión del usuario es " + System.getProperty("user.home"));
        System.out.println("El directorio actual es " + System.getProperty("user.dir"));
        System.out.println("El separador de líneas es " + System.getProperty("line.separator"));

        }
}