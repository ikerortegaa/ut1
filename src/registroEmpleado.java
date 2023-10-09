import java.io.*;
import java.util.*;

public class registroEmpleado {
    static Scanner ent = new Scanner(System.in);
    final static int TAM = 44;

    public static void main(String[] args) {

        do{
            System.out.println("Escoge una opción:");
            System.out.println("1. Alta de empleado");
            System.out.println("2. Mostrar");
            System.out.println("0. Salir del programa");
            int i = ent.nextInt();

            switch(i){
                case 1: alta();break;
                case 2: mostrar();break;
                case 0: System.exit(0);
                default:
                    System.out.println("opcion inexsistente\n");
            }

        }while( true );

    }

    public static void alta(){
        System.out.println("\nbienvenido al apartado de alta de empleados");
        try(RandomAccessFile raf = new RandomAccessFile("empleados.dat","rw")){

            System.out.println("* un id (el id funciona como posicionamiento también)");
            long id = ent.nextLong();
            // descargar la pulsación de intro
            ent.nextLine();
            System.out.println("Introduce su nombre");
            String nombre = ent.nextLine();

            System.out.println(nombre + "con string");

            //hago que el string ocupe 28
            /*StringBuffer sbf = new StringBuffer(nombre);
            sbf.setLength(28);

            System.out.println(sbf + "con sbf");*/

            System.out.println("Introduce salario");
            double salario = ent.nextDouble();

            raf.seek((id-1)*TAM); // Te posicionas en el numero de bytes que quieras
            raf.writeLong(id);
            nombre = nombre.substring(0,Math.min(nombre.length(),26)); //es 26 y no 28 porque de los 28 los 2 primeros bytes no son para guardar caracteres, en ellos guarda la longitud
            raf.writeUTF(nombre);
            raf.writeDouble(salario);


        }catch(EOFException ignored){

        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static  void mostrar(){
        System.out.println("\nbienvenido al apartado de mostrar empleados");
        try(RandomAccessFile raf = new RandomAccessFile("empleados.dat","r")){

            System.out.println("Introduce el id del empleado");
            long idE = ent.nextLong();

            raf.seek((idE-1)*TAM);

            //una vez posicionado, muestro el empleado
            long id = raf.readLong();

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
