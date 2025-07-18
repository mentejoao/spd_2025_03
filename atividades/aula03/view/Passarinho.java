package view;

import java.math.BigDecimal;
import java.util.Date;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Passarinho {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty species;
    private SimpleObjectProperty<BigDecimal> weight;
    private SimpleObjectProperty<Date> migration;
    
    public Passarinho(int id, String species, BigDecimal weight, Date migration) {
        this.id = new SimpleIntegerProperty(id);
        this.species = new SimpleStringProperty(species);
        this.weight = new SimpleObjectProperty<>(weight);
        this.migration = new SimpleObjectProperty<>(migration);        
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

    /**GET Method Propertie species*/
    public String getSpecies(){
        return this.species.get();
    }//end method getSpecies

    /**SET Method Propertie species*/
    public void setSpecies(String species){
        this.species.set(species);
    }//end method setSpecies

    /**GET Method Propertie weight*/
    public BigDecimal getWeight(){
        return this.weight.get();
    }//end method getWeight

    /**SET Method Propertie weight*/
    public void setWeight(BigDecimal weight){
        this.weight.set(weight);
    }//end method setWeight

    /**GET Method Propertie migration*/
    public Date getMigration(){
        return this.migration.get();
    }//end method getMigration

    /**SET Method Propertie migration*/
    public void setMigration(Date migration){
        this.migration.set(migration);
    }//end method setMigration

//End GetterSetterExtension Source Code


}//End class