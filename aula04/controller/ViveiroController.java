package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import view.ViveiroView;
import model.Repositorio;

import java.net.URL;
import java.util.ResourceBundle;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class ViveiroController extends AbstractCrudController<model.Viveiro, view.ViveiroView, Integer> implements Initializable {

    @FXML
    private TableView<view.ViveiroView> tabelaViveiro;

    @FXML
    private TableColumn<view.ViveiroView, Integer> idViveiroCol;
    @FXML
    private TableColumn<view.ViveiroView, String> nomeViveiroCol;
    @FXML
    private TableColumn<view.ViveiroView, String> localizacaoCol;
    @FXML
    private TableColumn<view.ViveiroView, Integer> capacidadeCol;
    @FXML
    private TableColumn<view.ViveiroView, BigDecimal> areaCol;
    @FXML
    private TableColumn<view.ViveiroView, String> dataCriacaoCol;
    @FXML
    private TableColumn<view.ViveiroView, String> responsavelCol;
    @FXML
    private TableColumn<view.ViveiroView, String> tipoViveiroCol;

    @FXML
    private TextField idViveiroField;
    @FXML
    private TextField nomeViveiroField;
    @FXML
    private TextField localizacaoField;
    @FXML
    private TextField capacidadeField;
    @FXML
    private TextField areaField;
    @FXML
    private TextField dataCriacaoField;
    @FXML
    private TextField responsavelField;
    @FXML
    private TextField tipoViveiroField;

    @FXML
    private Button adicionarViveiroButton;
    @FXML
    private Button atualizarViveiroButton;
    @FXML
    private Button deletarViveiroButton;
    @FXML
    private Button cancelarViveiroButton;
    @FXML
    private Button salvarViveiroButton;

    private static final Repositorio<model.Viveiro, Integer> viveiroRepo = model.Repositorios.VIVEIRO;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idViveiroCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomeViveiroCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        localizacaoCol.setCellValueFactory(new PropertyValueFactory<>("localizacao"));
        capacidadeCol.setCellValueFactory(new PropertyValueFactory<>("capacidadeMaxima"));
        areaCol.setCellValueFactory(new PropertyValueFactory<>("area"));
        dataCriacaoCol.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getDataCriacaoFormatted()));
        responsavelCol.setCellValueFactory(new PropertyValueFactory<>("responsavel"));
        tipoViveiroCol.setCellValueFactory(new PropertyValueFactory<>("tipoViveiro"));
        
        super.initialize();
    }

    @Override
    protected Repositorio<model.Viveiro, Integer> getRepositorio() {
        return viveiroRepo;
    }

    @Override
    protected view.ViveiroView modelToView(model.Viveiro v) {
        return new view.ViveiroView(
            v.getId(), 
            v.getNome(), 
            v.getLocalizacao(), 
            v.getCapacidadeMaxima(),
            v.getArea(),
            v.getDataCriacao(),
            v.getResponsavel(),
            v.getTipoViveiro()
        );
    }

    @Override
    protected model.Viveiro viewToModel() {
        model.Viveiro v = new model.Viveiro();
        v.setNome(nomeViveiroField.getText());
        v.setLocalizacao(localizacaoField.getText());
        v.setResponsavel(responsavelField.getText());
        v.setTipoViveiro(tipoViveiroField.getText());
        
        try {
            v.setCapacidadeMaxima(Integer.parseInt(capacidadeField.getText()));
        } catch (NumberFormatException e) {
            v.setCapacidadeMaxima(0);
        }
        
        try {
            v.setArea(new BigDecimal(areaField.getText()));
        } catch (NumberFormatException e) {
            v.setArea(BigDecimal.ZERO);
        }
        
        try {
            if (!dataCriacaoField.getText().isEmpty()) {
                v.setDataCriacao(dateFormat.parse(dataCriacaoField.getText()));
            } else {
                v.setDataCriacao(new Date());
            }
        } catch (ParseException e) {
            v.setDataCriacao(new Date());
        }
        
        return v;
    }

    @Override
    protected void preencherCampos(view.ViveiroView v) {
        idViveiroField.setText(String.valueOf(v.getId()));
        nomeViveiroField.setText(v.getNome());
        localizacaoField.setText(v.getLocalizacao());
        capacidadeField.setText(String.valueOf(v.getCapacidadeMaxima()));
        areaField.setText(v.getArea().toString());
        dataCriacaoField.setText(v.getDataCriacaoFormatted());
        responsavelField.setText(v.getResponsavel());
        tipoViveiroField.setText(v.getTipoViveiro());
    }

    @Override
    protected void limparCampos() {
        idViveiroField.clear();
        nomeViveiroField.clear();
        localizacaoField.clear();
        capacidadeField.clear();
        areaField.clear();
        dataCriacaoField.clear();
        responsavelField.clear();
        tipoViveiroField.clear();
    }

    @Override
    protected void desabilitarCampos(boolean desabilitado) {
        nomeViveiroField.setDisable(desabilitado);
        localizacaoField.setDisable(desabilitado);
        capacidadeField.setDisable(desabilitado);
        areaField.setDisable(desabilitado);
        dataCriacaoField.setDisable(desabilitado);
        responsavelField.setDisable(desabilitado);
        tipoViveiroField.setDisable(desabilitado);
    }

    @Override
    protected void desabilitarBotoes(boolean adicionar, boolean atualizar, boolean deletar, boolean cancelar, boolean salvar) {
        adicionarViveiroButton.setDisable(adicionar);
        atualizarViveiroButton.setDisable(atualizar);
        deletarViveiroButton.setDisable(deletar);
        cancelarViveiroButton.setDisable(cancelar);
        salvarViveiroButton.setDisable(salvar);
    }

    @Override
    protected TableView<view.ViveiroView> getTabela() {
        return tabelaViveiro;
    }

    @Override
    protected Integer getIdFromViewModel(view.ViveiroView viewModel) {
        return viewModel.getId();
    }

    @Override
    protected void setIdOnEntity(model.Viveiro entidade, Integer id) {
        entidade.setId(id);
    }

    // Métodos que chamam a superclasse
    @FXML
    public void onAdicionarViveiroButtonAction() {
        super.onAdicionar();
    }

    @FXML
    public void onSalvarViveiroButtonAction() {
        super.onSalvar();
    }

    @FXML
    public void onAtualizarViveiroButtonAction() {
        super.onAtualizar();
    }

    @FXML
    public void onDeletarViveiroButtonAction() {
        super.onDeletar();
    }

    @FXML
    public void onCancelarViveiroButtonAction() {
        super.onCancelar();
    }
}
