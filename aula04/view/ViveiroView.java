package view;

import java.math.BigDecimal;
import java.util.Date;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ViveiroView {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty nome;
    private SimpleStringProperty localizacao;
    private SimpleIntegerProperty capacidadeMaxima;
    private SimpleObjectProperty<BigDecimal> area;
    private SimpleObjectProperty<Date> dataCriacao;
    private SimpleStringProperty responsavel;
    private SimpleStringProperty tipoViveiro;
    
    public ViveiroView(int id, String nome, String localizacao, int capacidadeMaxima, 
                      BigDecimal area, Date dataCriacao, String responsavel, String tipoViveiro) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.localizacao = new SimpleStringProperty(localizacao);
        this.capacidadeMaxima = new SimpleIntegerProperty(capacidadeMaxima);
        this.area = new SimpleObjectProperty<>(area);
        this.dataCriacao = new SimpleObjectProperty<>(dataCriacao);
        this.responsavel = new SimpleStringProperty(responsavel);
        this.tipoViveiro = new SimpleStringProperty(tipoViveiro);
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

    /**GET Method Propertie nome*/
    public String getNome(){
        return this.nome.get();
    }//end method getNome

    /**SET Method Propertie nome*/
    public void setNome(String nome){
        this.nome.set(nome);
    }//end method setNome

    /**GET Method Propertie localizacao*/
    public String getLocalizacao(){
        return this.localizacao.get();
    }//end method getLocalizacao

    /**SET Method Propertie localizacao*/
    public void setLocalizacao(String localizacao){
        this.localizacao.set(localizacao);
    }//end method setLocalizacao

    /**GET Method Propertie capacidadeMaxima*/
    public int getCapacidadeMaxima(){
        return this.capacidadeMaxima.get();
    }//end method getCapacidadeMaxima

    /**SET Method Propertie capacidadeMaxima*/
    public void setCapacidadeMaxima(int capacidadeMaxima){
        this.capacidadeMaxima.set(capacidadeMaxima);
    }//end method setCapacidadeMaxima

    /**GET Method Propertie area*/
    public BigDecimal getArea(){
        return this.area.get();
    }//end method getArea

    /**SET Method Propertie area*/
    public void setArea(BigDecimal area){
        this.area.set(area);
    }//end method setArea

    /**GET Method Propertie dataCriacao*/
    public Date getDataCriacao(){
        return this.dataCriacao.get();
    }//end method getDataCriacao

    /**SET Method Propertie dataCriacao*/
    public void setDataCriacao(Date dataCriacao){
        this.dataCriacao.set(dataCriacao);
    }//end method setDataCriacao

    /**GET Method Propertie responsavel*/
    public String getResponsavel(){
        return this.responsavel.get();
    }//end method getResponsavel

    /**SET Method Propertie responsavel*/
    public void setResponsavel(String responsavel){
        this.responsavel.set(responsavel);
    }//end method setResponsavel

    /**GET Method Propertie tipoViveiro*/
    public String getTipoViveiro(){
        return this.tipoViveiro.get();
    }//end method getTipoViveiro

    /**SET Method Propertie tipoViveiro*/
    public void setTipoViveiro(String tipoViveiro){
        this.tipoViveiro.set(tipoViveiro);
    }//end method setTipoViveiro

    // Método para formatação da data
    public String getDataCriacaoFormatted() {
        if (this.dataCriacao.get() != null) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(this.dataCriacao.get());
        }
        return "";
    }

//End GetterSetterExtension Source Code


}//End class
