import java.util.Date;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "passarinho")
@XmlAccessorType(XmlAccessType.FIELD)
@DatabaseTable(tableName = "passarinho")
public class Passarinho {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String species;

    @DatabaseField
    private BigDecimal weight;

    @DatabaseField(dataType = DataType.DATE)
    private Date migration;

    public String printMigration() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(migration);
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public BigDecimal getWeight() { return weight; }
    public void setWeight(BigDecimal weight) { this.weight = weight; }

    public Date getMigration() { return migration; }
    public void setMigration(Date migration) { this.migration = migration; }
}
