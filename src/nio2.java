import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*Programa que lea de fichero volcando el contenido al buffer y mostrandolo por pantalla*/
public class nio2 {
    public static void main(String[] args) {
        int i=1;
        try {
            RandomAccessFile raf = new RandomAccessFile("../texto.txt","r");
            FileChannel fc= raf.getChannel();
            ByteBuffer bb = ByteBuffer.allocate(10); //Voy leyendo 10 Bytes y escribiéndolos al buffer
            //Leo del canal y lo escribo en el buffer
            while (fc.read(bb)>0)
            {
                System.out.println("Bloque "+i);
                //lee de golpe 1024 bytes y los escribe en el byteBuffer
                //Hago flip para pasar de escritura a lectura

                bb.flip();
                //muestro el contenido en pantalla(leer buffer, escribir en pantalla)
                while (bb.hasRemaining()) {
                    System.out.print((char) bb.get());
                }
                bb.clear();//Para cambiar de lectura a escritura el bytebuffer
                i++;
            }
            //fc.close();

            //Añadir bucle para repetir el bucle 12k

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
