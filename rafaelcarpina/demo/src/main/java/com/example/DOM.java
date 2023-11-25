package com.example;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOM {

    public static String serializeXmlObject(org.w3c.dom.Document docToSerialize) throws Exception {
        // Get a factory (DOMImplementationLS) for creating a Load and Save object.
        org.w3c.dom.ls.DOMImplementationLS impl = (org.w3c.dom.ls.DOMImplementationLS) org.w3c.dom.bootstrap.DOMImplementationRegistry
                .newInstance().getDOMImplementation("LS");
        // Use the factory to create an object (LSSerializer) used to
        // write out or save the document.
        org.w3c.dom.ls.LSSerializer writer = impl.createLSSerializer();
        org.w3c.dom.DOMConfiguration config = writer.getDomConfig();
        config.setParameter("xml-declaration", false);
        config.setParameter("format-pretty-print", Boolean.TRUE);

        // Use the LSSerializer to write out or serialize the document to a String.
        String serializedXML = writer.writeToString(docToSerialize);
        return serializedXML;
    }

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        File fileXML = new File("bibliography.xml");
        Document xml = db.parse(fileXML);
        //File fileXSL = new File("bibliography.xsl");
        //Document xsl = db.parse(fileXSL);

        Source xmls = new StreamSource(fileXML);
        //Source xslt = new StreamSource(fileXSL);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        //Transformer transformer = factory.newTransformer(xslt);
        //DOMResult result = new DOMResult();
        transformer.transform(xmls, new StreamResult(new File("output.xml")));
        //Document root = (Document) result.getNode();
        //System.out.println(serializeXmlObject(root));

        // Processor p = new Processor(false);
        // XsltCompiler compiler = p.newXsltCompiler();
        // XsltExecutable exec = compiler.compile(xslt);
        // Serializer out = p.newSerializer(System.out);
        // out.setOutputProperty(Serializer.Property.METHOD, "html");
        // out.setOutputProperty(Serializer.Property.INDENT, "yes");
        // Xslt30Transformer transformer2 = exec.load30();
        // transformer2.transform(xmls, out);

        // Document doc = db.newDocument();

        // Element root = doc.createElement("root");
        // root.setAttribute("teste", "valor");
        // doc.appendChild(root);

        // Element p = doc.createElement("p");
        // p.setTextContent("Parágrafo.");
        // root.appendChild(p);

        // Element x = doc.createElement("x");
        // x.setTextContent("Parágrafo.");
        // root.insertBefore(x, p);

        // System.out.println(serializeXmlObject(doc));

        // XPathFactory xpathfactory = XPathFactory.newInstance();
        // XPath xpath = xpathfactory.newXPath();
        // XPathExpression expr = xpath.compile("//book[count(author) >
        // 1]/title/text()");
        // NodeList list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        // for (int i = 0; i < list.getLength(); i++) {
        // System.out.println(list.item(i).getNodeValue());
        // }
        // System.out.println(list.getLength());

        // expr = xpath.compile("//book[@category='SO']/price");
        // list = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        // double sum = 0;
        // for (int i = 0; i < list.getLength(); i++) {
        // sum += Double.parseDouble(list.item(i).getTextContent());
        // }
        // System.out.println(sum / list.getLength());

        // Processor p = new Processor(false);
        // net.sf.saxon.s9api.DocumentBuilder dbu = p.newDocumentBuilder();
        // XdmNode node = dbu.build(file);
        // XPathCompiler xpath2 = p.newXPathCompiler();
        // XPathExecutable exec = xpath2.compile("//book[count(author) > 1]/title");
        // XPathSelector selector = exec.load();
        // selector.setContextItem(node);
        // selector.forEach(item -> System.out.println(item.getStringValue()));
        // System.out.println(selector.evaluate());
    }

    public static void main2(String[] args) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("bibliography.xml"));
        NodeList list = doc.getElementsByTagName("book");
        int qtt = 0;

        double sum = 0;
        int qtd = 0;
        for (int i = 0; i < list.getLength(); i++) {
            Element book = (Element) list.item(i);
            String category = book.getAttribute("category");
            if (category.equals("SO")) {
                Node price = book.getElementsByTagName("price").item(0);
                sum += Double.parseDouble(price.getTextContent());
                qtd++;
            }
            NodeList authors = book.getElementsByTagName("author");
            if (authors.getLength() > 1) {
                Node title = book.getElementsByTagName("title").item(0);
                System.out.println(title.getTextContent());
                qtt++;
            }
        }
        System.out.println(qtt);
        System.out.println(sum / qtd);
    }
}
