import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class nio4 {
    public static void main(String[] args) {
   int[] nums = new int[10];
           /*  for (int i=0; i<10;i++)
            nums[i]= 1+(int)(Math.random()*10);
        IntBuffer ib = IntBuffer.wrap(nums);*/

        //Después de creado y escrito el buffer, leo de él para escribir a fichero
        ByteBuffer bb = ByteBuffer.allocate(40);
        for (int i=0; i<10;i++)
            nums[i]= 1+(int)(Math.random()*10);
        // A partir del bytebyffer trabajo de 4 en 4 bytes (tamaño del int) con asIntBuffer y después escribo con put(int[])
        //Comprobamos el contenido dl array
        System.out.println(Arrays.toString(nums));
        IntBuffer ib=bb.asIntBuffer();//Lo trato como un intbuffer
        ib.put(nums);
        //Comprobamos el contenido del buffer
        System.out.println(ib.toString());
        ib.flip();
        try (FileChannel fc = FileChannel.open(Paths.get("aleatorios.dat"), StandardOpenOption.CREATE,StandardOpenOption.WRITE);){

            //lee del bytebuffer con los 10 enteros y lso escribe en el canal
            fc.write(bb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
