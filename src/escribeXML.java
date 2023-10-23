//Programa en el que se van a creear varios empleados y se van a escribir

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class escribeXML {
    public static void main(String[] args) {
        List <Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1,"Pepe Pérez",2000));
        empleados.add(new Empleado(2,"Julián Muñoz",1000));
        empleados.add(new Empleado(3,"Sergio Ramos",1500));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            Document documento = dom.createDocument(null, "xml", null);
            Element raiz = documento.createElement("empleados");
            documento.getDocumentElement().appendChild(raiz);
            Element nodoProducto = null , nodoDatos = null ;
            Text texto = null;

            for(Empleado emp: empleados)
            {
                //Se une cada elemento con su elemento padre
                nodoProducto=documento.createElement("empleado");
                raiz.appendChild(nodoProducto);
                //id
                nodoDatos=documento.createElement("id");
                nodoProducto.appendChild(nodoDatos);
                texto= documento.createTextNode(String.valueOf(emp.getId()));
                nodoDatos.appendChild(texto);
                //nombre
                nodoDatos=documento.createElement("nombre");
                nodoProducto.appendChild(nodoDatos);
                texto= documento.createTextNode(String.valueOf(emp.getNombre()));
                nodoDatos.appendChild(texto);
                //Salario
                nodoDatos=documento.createElement("salario");
                nodoProducto.appendChild(nodoDatos);
                texto= documento.createTextNode(String.valueOf(emp.getSalario()));
                nodoDatos.appendChild(texto);


            }
            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File("empleados.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent","yes");
            transformer.transform(source,resultado);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

}
