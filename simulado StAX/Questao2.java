import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class Questao2 {

    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xmlif = XMLInputFactory.newFactory();
        XMLStreamReader xsr = xmlif.createXMLStreamReader(new FileReader("CaetanoVeloso.xml"));

        int qttAlbum = 0;
        int maiorQttAlbum = 0;
        int yearAtual = 0;
        int maiorYear = 0;

        while(xsr.hasNext()){
            String qName;

            switch (xsr.next()) {
                case XMLStreamConstants.START_ELEMENT:
                    qName = xsr.getLocalName();

                    if(qName.equalsIgnoreCase("album")){
                        int tempYear = Integer.parseInt(xsr.getAttributeValue(0));
                        if(tempYear == yearAtual){
                            qttAlbum++;
                        }else{
                            qttAlbum = 1;
                            yearAtual = tempYear;
                        }

                        if(qttAlbum>maiorQttAlbum){
                            maiorYear = yearAtual;
                            maiorQttAlbum = qttAlbum;
                        }
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    qName = xsr.getText();

                    break;

                case XMLStreamConstants.END_ELEMENT:
                    qName = xsr.getLocalName();

                    break;
            }
        }
        System.out.println("<year count=\"" + maiorQttAlbum + "\">" + maiorYear + "</year>");
    }

}
