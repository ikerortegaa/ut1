import java.io.*;

public class repasoIO {
    static String text;
    public static void main(String[] args) {
        try (FileWriter fw= new FileWriter("pruebasIO.txt");) {
            // escritura
            String text = "Esto es una cadena de prueba\n";
            fw.write(text);
            text = "Esto es una segunda cadena de prueba\n";
            fw.write(text);
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
        try(FileReader fr = new FileReader("pruebasIO.txt");
            BufferedReader br = new BufferedReader(fr))
        {
            // lectura
            while((text = br.readLine()) != null)
                System.out.println(text);
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
