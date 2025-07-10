import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.JAXBException;

public class Main {

    public static void main(String[] args) {
        try {
            Passarinho p = new Passarinho();
            p.setId(1);
            p.setSpecies("Canário-da-terra");
            p.setWeight(new BigDecimal("0.31"));
            p.setMigration(new Date());

            PassarinhoXmlSerializer serializer = new PassarinhoXmlSerializer();

            // Serializar para XML
            String xml = serializer.toXml(p);
            System.out.println("XML gerado:\n" + xml);

            // Desserializar do XML
            Passarinho p2 = serializer.fromXml(xml);
            System.out.println("Espécie desserializada: " + p2.getSpecies());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

