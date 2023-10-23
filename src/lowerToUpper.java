/*Hacer ejercicio que cambie minúsculas a mayúsculas y lo copie en otro fichero*/

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class lowerToUpper {
    public static void main(String[] args) {
        Path path = Paths.get("lower.txt");
        try(FileChannel fcl= FileChannel.open(path, StandardOpenOption.READ);
            FileChannel fce= FileChannel.open(Paths.get("upper.txt"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);) {

            ByteBuffer bb = ByteBuffer.allocate(1);


            //Leer del canal de lectura para escribir en el buffer y desde el buffer escribir al canal de escritura

            while (fcl.read(bb) > 0)//lee del canal, escribe en el buffer
            {
                System.out.println("1ºWhile");

                while (bb.hasRemaining()) {
                    byte byteActual = bb.get();//Leo el byte actual del bb
                    System.out.println("2ºWhile");
                    char letraActual = (char) byteActual; //Paso el byte actual a char
                    if (Character.isLowerCase(letraActual)) {//Compruebo si es minúscula, si es minúscula, lo convierto a mayuscula
                        letraActual = Character.toUpperCase(letraActual);
                    }
                    bb.flip();//hago flip para poder escribir en el bb
                    bb.put((byte) letraActual);//escribo la mayuscula en el byte
                }
                bb.flip();//He escrito, ahora voy a leer
                fce.write(bb);
                bb.clear();// he leído, ahora voy a volver a escribir
            }
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
}
