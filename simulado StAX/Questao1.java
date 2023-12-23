import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * Questao1
 */
public class Questao1 {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xmlif = XMLInputFactory.newFactory();
        XMLStreamReader xsr = xmlif.createXMLStreamReader(new FileReader("CaetanoVeloso.xml"));
        System.out.println("<ul>");
        boolean vTitle = false;
        int qttAuthor = 0;
        String title = null;
        while(xsr.hasNext()){
            String qName;
            switch (xsr.next()) {
                case XMLStreamConstants.START_ELEMENT:
                    qName = xsr.getLocalName();
                    if(qName.equalsIgnoreCase("title")){
                        vTitle = true;
                    }
                    if(qName.equalsIgnoreCase("author")){
                        qttAuthor++;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    qName = xsr.getText();
                    if(vTitle){
                        title = qName;
                        vTitle = false;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    qName = xsr.getLocalName();
                    if(qName.equalsIgnoreCase("authors")){
                        if(qttAuthor>1){
                            System.out.println("  <li>"+title+"</li>");
                        }
                        qttAuthor = 0;
                    }
                    break;
            }
        }
        System.out.println("</ul>");
    }

}