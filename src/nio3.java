/*Copiar el contenido de origen.txt a destino.txt
* (similar al comando cp de copia)*/


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class nio3 {
    public static void main(String[] args) {
        Path path = Paths.get("origen.txt");
        try(FileChannel fcl= FileChannel.open(path, StandardOpenOption.READ);
            FileChannel fce= FileChannel.open(Paths.get("destino.txt"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);) {

            ByteBuffer bb = ByteBuffer.allocate(50);
            //Leer del canal de lectura para escribir en el buffer y desde el buffer escribir al canal de escritura
            while (fcl.read(bb) > 0)//lee del canal, escribe en el buffer
            {
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
