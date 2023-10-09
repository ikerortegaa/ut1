import java.io.*;
import java.util.Scanner;
public class ficheroRAF {
    private static final int maxString=28;
    private static final int tam=(Integer.SIZE/8)+(Double.SIZE/8)+maxString;
    private static int id;
    private static String nombre;
    private static double salario;
    private static Scanner ent = new Scanner(System.in);

    public static void main(String[] args) {


        Scanner ent = new Scanner(System.in);
        int opcion;
        while(true) {
            System.out.println("****************");
            System.out.println("Elige una opción:\n0-Cerrar programa\n1-Dar de alta un empleado\n2-Buscar un empleado\n3-Eliminar un empleado");
            System.out.println("****************");


            opcion = ent.nextInt();
            switch (opcion) {
                case 0:
                    System.out.println("FIN!!!");System.exit(0);
                    break;
                case 1:
                    alta();
                    break;
                case 2:
                    buscar();
                    break;
                /*case 3:
                    eliminar();
                    break;*/
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }
    public static void alta(){

        try(RandomAccessFile raf=new RandomAccessFile("empleados.dat","rw"))
        {
            System.out.print("ID: ");
            id=ent.nextInt();
            ent.nextLine();
            System.out.print("Nombre: ");
            nombre=ent.nextLine();
            System.out.print("Salario: ");
            salario=ent.nextDouble();
            raf.seek((long) (tam - 1) *id);
            raf.writeInt(id);
            raf.writeUTF(nombre);
            raf.writeDouble(salario);
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
    }
    public static void buscar(){
        File f=new File("empleados.dat");
        if(f.exists()){
            try(RandomAccessFile raf=new RandomAccessFile(f,"r")){
                System.out.print("Introduce id empleado a buscar: ");
                int buscaemp=ent.nextInt();
                while(true){
                    raf.seek(0);
                    raf.seek((long) (tam - 1) *buscaemp);
                    id=raf.readInt();
                    nombre=raf.readUTF();
                    salario=raf.readDouble();
                    if(buscaemp==id){
                        System.out.println(id+" "+nombre+" "+salario);
                    }
                    else{
                        System.out.println("Empleado no encontrado.");
                    }
                    break;
                }
            }
            catch(EOFException e){}
            catch(IOException e){
                System.err.println(e.getMessage());
            }
        }
        else
            System.out.println("El archivo no existe.");
    }

}

