/*Hacer ejercicio que cambie minúsculas a mayúsculas y lo copie en otro fichero*/

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class lowerToUpper {
    public static void main(String[] args) {
        try (FileChannel fce = FileChannel.open(Paths.get("upper.txt"),StandardOpenOption.WRITE, StandardOpenOption.CREATE);
             FileChannel fcl = FileChannel.open(Paths.get("lower.txt"), StandardOpenOption.READ))
        {
            ByteBuffer bb = ByteBuffer.allocate(1);
            char letra;

            //Leo del canal de lectura para escribir en el buffer
            while (fcl.read(bb)>0)
            {
               bb.flip();
               letra = (char) bb.get();
                //Ya he leido, ahora hay que escribir en el buffer

                if(letra > 96 && letra < 123 ) {
                    letra = (char) (letra - 32);
                    bb.flip();
                    bb.put((byte) letra);
                }

                bb.flip();
                fce.write(bb);
                bb.clear();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




