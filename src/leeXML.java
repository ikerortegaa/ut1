import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class leeXML {
    public static void main(String[] args) {
        List <Empleado> listaEmpleados = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File("empleados.xml"));
            NodeList empleados = documento.getElementsByTagName("empleado");


            //Vamos a leer el xml creando los objetos Empleado
            for (int i = 0; i < empleados.getLength() ; i++) {
                Node emp = empleados.item(i);
                Element elemento = (Element) emp;
                //Como devuleve un String, los valores que no lo son hay que castearlos
                int id = Integer.parseInt(elemento.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
                String nombre = elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue();
                double salario = Double.parseDouble(elemento.getElementsByTagName("salario ").item(0).getChildNodes().item(0).getNodeValue());

                //Creo el objeto empleado

                Empleado empleado = new Empleado(id,nombre,salario);
                listaEmpleados.add(empleado);
            }

            for (Empleado e:listaEmpleados) {
                System.out.println(e);
            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
