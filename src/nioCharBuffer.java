import java.nio.CharBuffer;

public class nioCharBuffer {
    public static void main(String[] args) {
       // CharBuffer cb = CharBuffer.allocate(10);
        String s = "BufferDemoDAM134";
        CharBuffer cb = CharBuffer.wrap(s); //Otra forma de crear el buffer con wrap

        //Escritura en el Buffer
        //cb.put(s);


        //Leer datos del buffer
        //1º Se cambia de modo escritura a modo lectura
       // cb.flip();
        while (cb.hasRemaining()) {
            System.out.print(cb.get());
            System.out.println(" leído en la posición: "+cb.position());
        }
        //Establezco la marca en la última posición
         cb.mark();
        //ME posiciono en 5
        cb.position(5);
        System.out.println("El caracter en la posición 5 es: "+cb.get());

        //retorno a donde había establecido la marca
        //cb.reset();

        //System.out.println("El caracter de la posición 5 es:" + cb.get(5));

        System.out.println("El caracter de la posición 2 es: "+cb.get(2));
        //He leído una posición absoluta, no cambia el valor de position
        System.out.println("Estoy en la posición "+cb.position());

    }

}
