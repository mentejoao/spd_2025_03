package view;

import java.math.BigDecimal;
import java.util.Date;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PassarinhoView {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty species;
    private SimpleObjectProperty<BigDecimal> weight;
    private SimpleObjectProperty<Date> migration;
    private SimpleStringProperty viveiroNome;
    
    public PassarinhoView(int id, String species, BigDecimal weight, Date migration, String viveiroNome) {
        this.id = new SimpleIntegerProperty(id);
        this.species = new SimpleStringProperty(species);
        this.weight = new SimpleObjectProperty<>(weight);
        this.migration = new SimpleObjectProperty<>(migration);
        this.viveiroNome = new SimpleStringProperty(viveiroNome);
    }
    
//Start GetterSetterExtension Source Code

    /**GET Method Propertie id*/
    public int getId(){
        return this.id.get();
    }//end method getId

    /**SET Method Propertie id*/
    public void setId(int id){
        this.id.set(id);
    }//end method setId
    
    /**GET Method Property id*/
    public SimpleIntegerProperty idProperty(){
        return this.id;
    }//end method idProperty

    /**GET Method Propertie species*/
    public String getSpecies(){
        return this.species.get();
    }//end method getSpecies

    /**SET Method Propertie species*/
    public void setSpecies(String species){
        this.species.set(species);
    }//end method setSpecies
    
    /**GET Method Property species*/
    public SimpleStringProperty speciesProperty(){
        return this.species;
    }//end method speciesProperty

    /**GET Method Propertie weight*/
    public BigDecimal getWeight(){
        return this.weight.get();
    }//end method getWeight

    /**SET Method Propertie weight*/
    public void setWeight(BigDecimal weight){
        this.weight.set(weight);
    }//end method setWeight
    
    /**GET Method Property weight*/
    public SimpleObjectProperty<BigDecimal> weightProperty(){
        return this.weight;
    }//end method weightProperty

    /**GET Method Propertie migration*/
    public Date getMigration(){
        return this.migration.get();
    }//end method getMigration

    /**SET Method Propertie migration*/
    public void setMigration(Date migration){
        this.migration.set(migration);
    }//end method setMigration
    
    /**GET Method Property migration*/
    public SimpleObjectProperty<Date> migrationProperty(){
        return this.migration;
    }//end method migrationProperty

    /**GET Method Propertie viveiroNome*/
    public String getViveiroNome(){
        return this.viveiroNome.get();
    }//end method getViveiroNome

    /**SET Method Propertie viveiroNome*/
    public void setViveiroNome(String viveiroNome){
        this.viveiroNome.set(viveiroNome);
    }//end method setViveiroNome
    
    /**GET Method Property viveiroNome*/
    public SimpleStringProperty viveiroNomeProperty(){
        return this.viveiroNome;
    }//end method viveiroNomeProperty

    // Método para formatação da data
    public String getMigrationFormatted() {
        if (this.migration.get() != null) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(this.migration.get());
        }
        return "";
    }

//End GetterSetterExtension Source Code


}//End class
