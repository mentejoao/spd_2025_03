import javax.xml.bind.*;
import java.io.StringWriter;
import java.io.StringReader;
import java.util.List;

public class PassarinhoXmlSerializer
{
    private JAXBContext context;

    public PassarinhoXmlSerializer() throws JAXBException {
        context = JAXBContext.newInstance(Passarinho.class);
    }

    public String toXml(Passarinho passarinho) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        marshaller.marshal(passarinho, sw);
        return sw.toString();
    }
    
    public String toXml(List<Passarinho> passarinhos) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        marshaller.marshal(passarinhos, sw);
        return sw.toString();
    }
    


    public Passarinho fromXml(String xml) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader sr = new StringReader(xml);
        return (Passarinho) unmarshaller.unmarshal(sr);
    }
}
