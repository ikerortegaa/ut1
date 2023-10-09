import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class propiedades {
    public static void main(String[] args) {
        // ESCRITURA
        Properties pr = new Properties();
        pr.setProperty("usuario","pepe");
        pr.setProperty("password","pepepepe");
        pr.setProperty("correo","pepe@dam.com");
        pr.setProperty("equipoFutbol","Celtic de Glasgow");
        pr.setProperty("edad","21");
        try {
            pr.store(new FileWriter("props.conf"),"Propiedades de mi aplicación");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // LECTURA
        Properties pr2= new Properties();
        String usuario,passwd,correo,equipo;
        int edad;

        try {
            pr2.load(new FileReader("props.conf"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        usuario = pr2.getProperty("usuario");
        passwd = pr2.getProperty("password");
        correo = pr2.getProperty("correo");
        equipo = pr2.getProperty("equipoFutbol");
        edad = Integer.parseInt(pr2.getProperty("edad"));

        System.out.println("usuario: " + usuario);
        System.out.println("contraseña: " + passwd);
        System.out.println("correo: " + correo);
        System.out.println("equipo de fútbol: " + equipo);
        System.out.println("edad: " + edad);

        // modificar edad, añadir propiedad fechaFinServicio y volver a guardar
        Date fecha = new Date(124,11,31);
        int incrementoEdad = ++edad; //OJO QUE SI PONES EDAD++, SE ASIGNA LA VARIABLE Y LA SUMA SE PIERDE :)
        pr2.setProperty("edad", String.valueOf(incrementoEdad));
        pr2.setProperty("fechaCaducidad", String.valueOf(fecha));

        try {
            pr2.store(new FileWriter("props.conf"), "segunda edicion");
        } catch (IOException e){
            System.out.println("jojojo");
        }
    }
}
