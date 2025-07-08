import java.util.Date;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;

@DatabaseTable(tableName = "passarinho")
public class Passarinho
{
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField
    private String species;
    
    @DatabaseField
    private BigDecimal weight;
    
    @DatabaseField(dataType=DataType.DATE)
    private Date migration;

    // Método para imprimir a data de migração formatada
    public String printMigration() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(migration);
    }

    // Início dos Getters e Setters

    /** GET Method for id */
    public int getId() {
        return this.id;
    }

    /** SET Method for id */
    public void setId(int id) {
        this.id = id;
    }

    /** GET Method for species */
    public String getSpecies() {
        return this.species;
    }

    /** SET Method for species */
    public void setSpecies(String species) {
        this.species = species;
    }

    /** GET Method for weight */
    public BigDecimal getWeight() {
        return this.weight;
    }

    /** SET Method for weight */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /** GET Method for migration */
    public Date getMigration() {
        return this.migration;
    }

    /** SET Method for migration */
    public void setMigration(Date migration) {
        this.migration = migration;
    }

    // Fim dos Getters e Setters

} // End class
