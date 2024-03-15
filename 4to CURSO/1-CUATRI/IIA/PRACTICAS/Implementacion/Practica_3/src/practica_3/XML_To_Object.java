package practica_3;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class XML_To_Object {

    public static void main(String[] args) {
        try {
            String fichero = "C:\\Users\\ismae\\OneDrive\\Escritorio\\IIA\\PRÁCTICAS\\Practica_3\\datos.xml";
            String expresion = "//title"; //String para la expresion a generar

            File archivoXML = new File(fichero); // Reemplaza la ruta por el archivo XML

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(archivoXML);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            //XPathExpression xpathexpression = xpath.compile(expresion);
            //NodeList nodeList = (NodeList) xpathexpression.evaluate(document, XPathConstants.NODESET);

            Element root = document.getDocumentElement();

            //Funcion que convierte el XML en objeto
            parseXML(root, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Convertir XML con XSL en un nuevo XML
        //XSLT();
    }

    private static void parseXML(Element element, String indent) {
        System.out.println(indent + "<" + element.getTagName() + ">");

        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (node instanceof Element) {
                parseXML((Element) node, indent + "  ");
            } else if (node.getNodeType() == Node.TEXT_NODE) {
                System.out.println(indent + "  " + node.getTextContent());
            }
        }

        System.out.println(indent + "</" + element.getTagName() + ">");
    }

    private static void XSLT() {
        try {
            // Ruta del archivo XML de entrada
            String inputXML = "C:\\Users\\ismae\\OneDrive\\Escritorio\\Practica_3\\datos.xml"; // Reemplaza con la ruta de tu archivo XML de entrada

            // Ruta del archivo XSLT
            String xsltFile = "C:\\Users\\ismae\\OneDrive\\Escritorio\\Practica_3\\xsl.xsl"; // Reemplaza con la ruta de tu archivo XSLT

            // Ruta del archivo de salida
            String outputXML = "C:\\Users\\ismae\\OneDrive\\Escritorio\\Practica_3\\resultado.xml"; // Ruta del archivo XML de salida transformado

            // Cargar la plantilla XSLT
            Source xslt = new StreamSource(xsltFile);

            // Crear una factoría de transformadores
            TransformerFactory factory = TransformerFactory.newInstance();

            Transformer transformer = factory.newTransformer(xslt);
            Source xmlInput = new StreamSource(inputXML);

            // Especificar el archivo de salida para el resultado de la transformación
            Result result = new StreamResult(outputXML);

            // Aplicar la transformación XSLT
            transformer.transform(xmlInput, result);

            System.out.println("Transformación XSLT completada con éxito. El resultado se ha guardado en '" + outputXML + "'.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
