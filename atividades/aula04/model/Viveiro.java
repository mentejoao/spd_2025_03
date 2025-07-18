package model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.DataType;

@DatabaseTable(tableName = "viveiro")
public class Viveiro
{
    @DatabaseField(generatedId = true)
    private int id;
    
    @DatabaseField
    private String nome;
    
    @DatabaseField
    private String localizacao;
    
    @DatabaseField
    private int capacidadeMaxima;
    
    @DatabaseField
    private BigDecimal area; // em metros quadrados
    
    @DatabaseField(dataType=DataType.DATE)
    private Date dataCriacao;
    
    @DatabaseField
    private String responsavel;
    
    @DatabaseField
    private String tipoViveiro; // Ex: "Reprodução", "Reabilitação", "Exposição"

    // Método para imprimir a data de criação formatada
    public String printDataCriacao() {
        if (dataCriacao != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(dataCriacao);
        }
        return "";
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

    /** GET Method for nome */
    public String getNome() {
        return this.nome;
    }

    /** SET Method for nome */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** GET Method for localizacao */
    public String getLocalizacao() {
        return this.localizacao;
    }

    /** SET Method for localizacao */
    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    /** GET Method for capacidadeMaxima */
    public int getCapacidadeMaxima() {
        return this.capacidadeMaxima;
    }

    /** SET Method for capacidadeMaxima */
    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    /** GET Method for area */
    public BigDecimal getArea() {
        return this.area;
    }

    /** SET Method for area */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /** GET Method for dataCriacao */
    public Date getDataCriacao() {
        return this.dataCriacao;
    }

    /** SET Method for dataCriacao */
    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /** GET Method for responsavel */
    public String getResponsavel() {
        return this.responsavel;
    }

    /** SET Method for responsavel */
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    /** GET Method for tipoViveiro */
    public String getTipoViveiro() {
        return this.tipoViveiro;
    }

    /** SET Method for tipoViveiro */
    public void setTipoViveiro(String tipoViveiro) {
        this.tipoViveiro = tipoViveiro;
    }

    // Fim dos Getters e Setters

    @Override
    public String toString() {
        return "Viveiro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", capacidadeMaxima=" + capacidadeMaxima +
                ", area=" + area +
                ", responsavel='" + responsavel + '\'' +
                ", tipoViveiro='" + tipoViveiro + '\'' +
                '}';
    }

} // End class
