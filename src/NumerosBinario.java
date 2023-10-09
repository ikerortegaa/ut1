// Programa que escribe los 10s primeros números naturales en binario a fichero

import java.io.*;
//import java.io.File;
//import java.io.FileOutputStream;

public class NumerosBinario {
    public static void main(String[] args) {
        //programa que escriba 10 numeros en binario a fichero y luego los lea
        File archivoBinario = new File("numeros.bin");

        //escritura del archivo

        // try (FileWriter fw = new FileWriter(archivoBinario))
        try(FileOutputStream fos = new FileOutputStream(archivoBinario);
            DataOutputStream oos = new DataOutputStream(fos);)
        {

            for (int i = 1; i <= 10 ; i++)
                oos.writeDouble(Math.random());
        } catch (IOException e) {
            System.out.println("No se ha encontrado el archivo");
            System.out.println(e.getMessage());
        }
    }

}
