import java.io.*;

public class escrituraBin {
    public static void main(String[] args) {


        try (FileOutputStream fos = new FileOutputStream("escritura.dat");
             DataOutputStream dos = new DataOutputStream(fos);
             FileInputStream fis = new FileInputStream("escritura.dat");
             DataInputStream dis = new DataInputStream(fis))
        {
            for (int i =1; i<=10; i++) //Escribe a fichero
                dos.writeInt(i);
            for (int i = 1; i <=10 ; i++) //Lee de fichero
                System.out.println(dis.readInt());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }




    }
}
