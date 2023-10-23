import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class dniRAF {
    static Scanner ent = new Scanner(System.in);
    final static int TAM = 40;

    public static void main(String[] args) {


        do{
            System.out.println("********************");
            System.out.println("Escoge una opción:");
            System.out.println("1. Alta de empleado");
            System.out.println("2. Mostrar");
            System.out.println("0. Salir del programa");
            System.out.println("********************");

            int i = ent.nextInt();

            switch(i){
                case 1: alta();break;
                case 2: mostrar();break;
                case 0: System.exit(0);
                default:
                    System.out.println("Error, opción incorrecta\n");
            }

        }while( true );

    }
    //Falta cambiar que guarde usuarios del 1 al 1000 y no del 000 al 999
    public static void alta(){
        int dni,id, cuentaSinonimos =0;
        System.out.println("\nPrograma");
        try(RandomAccessFile raf = new RandomAccessFile("dniEmpleados.dat","rw")){


            System.out.println("Introduce un DNI sin letra");
            dni = ent.nextInt();

            // descargar la pulsación de intro
            ent.nextLine();
            System.out.println("Introduce su nombre");
            String nombre = ent.nextLine();
            System.out.println("Introduce salario");
            double salario = ent.nextDouble();


            id = dni%1000; //Me quedo con los tres últimos dígitos del dni para usarlo como id
            System.out.println("El id ahora es: "+id);//Compruebo que me quedo con los tres últimos dígitos
            raf.seek((long)id*TAM); // Te posicionas en la posición que le corresponde a ese, id

            //Leo esa posición y compruebo si esa posición está vacía, de ser así guardo el nombre.
            int compruebaId = raf.readInt();
            if (compruebaId == 0)    // la posición está libre
            {
                //Me vuelvo a posicionar en el principio
                raf.seek((long)id*TAM);
                System.out.println("Compruebo que escribe bien "+nombre);
                raf.writeInt(id);
                nombre = nombre.substring(0,Math.min(nombre.length(),26)); //es 26 y no 28 porque de los 28 los 2 primeros bytes no son para guardar caracteres, en ellos guarda la longitud
                raf.writeUTF(nombre);
                raf.writeDouble(salario);
            }
            else    // la posición estaba ocupada HAY QUE SUMARLE PARA QUE VAYA DE 40 EN 40.
            {
                cuentaSinonimos++;
                int bytesFaltantes;
                //Me posiciono al final del fichero y guardo ahí el empleado con un "id" que ya existe en otro empleado
                raf.seek(raf.length());
                raf.writeInt(id);
                nombre = nombre.substring(0,Math.min(nombre.length(),26)); //es 26 y no 28 porque de los 28 los 2 primeros bytes no son para guardar caracteres, en ellos guarda la longitud
                raf.writeUTF(nombre);
                raf.writeDouble(salario);

                // Rellena con bytes nulos para que el tamaño total del registro sea 40 bytes
                bytesFaltantes = TAM - (4 + (2 + nombre.length()) + 8);
                for (int i = 0; i < bytesFaltantes; i++) {
                    raf.writeByte(0);
                }

            }



        }catch(EOFException ignored){

        }catch(IOException e){

        }
    }

    public static  void mostrar(){
        int dni;
        int dniE;
        int id;
        try(RandomAccessFile raf = new RandomAccessFile("dniEmpleados.dat","r")){

            System.out.println("Introduce el DNI del empleado");
             dni = ent.nextInt();

             dniE=dni%1000;

            raf.seek((long) dniE *TAM);

            //una vez posicionado, muestro el empleado
             id = raf.readInt();

           /* StringBuffer nombreE = new StringBuffer();
            for(int i=0;i<28;i++){
                nombreE.append(raf.readChar());
            }*/
            String nom = raf.readUTF();

            double salario = raf.readDouble();

            System.out.println("Id: " + id + "\nNombre: " + nom + "\nSalario: " + salario);

        }catch(IOException e){
            System.err.println(e.getMessage());
        }

    }
}
