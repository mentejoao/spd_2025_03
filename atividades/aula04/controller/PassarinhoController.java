package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import view.PassarinhoView;
import model.Repositorio;

import java.net.URL;
import java.util.ResourceBundle;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class PassarinhoController extends AbstractCrudController<model.Passarinho, view.PassarinhoView, Integer> implements Initializable {

    @FXML
    private TableView<view.PassarinhoView> tabela;

    @FXML
    private TableColumn<view.PassarinhoView, Integer> idCol;
    @FXML
    private TableColumn<view.PassarinhoView, String> speciesCol;
    @FXML
    private TableColumn<view.PassarinhoView, BigDecimal> weightCol;
    @FXML
    private TableColumn<view.PassarinhoView, String> migrationCol;
    @FXML
    private TableColumn<view.PassarinhoView, String> viveiroCol;

    @FXML
    private TextField idField;
    @FXML
    private TextField speciesField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField migrationField;
    @FXML
    private ComboBox<model.Viveiro> viveiroCombo;

    @FXML
    private Button adicionarButton;
    @FXML
    private Button atualizarButton;
    @FXML
    private Button deletarButton;
    @FXML
    private Button cancelarButton;
    @FXML
    private Button salvarButton;

    private static final Repositorio<model.Passarinho, Integer> passarinhoRepo = model.Repositorios.PASSARINHO;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        speciesCol.setCellValueFactory(new PropertyValueFactory<>("species"));
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        migrationCol.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getMigrationFormatted()));
        viveiroCol.setCellValueFactory(cellData -> 
            cellData.getValue().viveiroNomeProperty());
        
        // Carregar viveiros no ComboBox
        carregarViveiros();
        
        super.initialize();
    }
    
    private void carregarViveiros() {
        List<model.Viveiro> viveiros = model.Repositorios.VIVEIRO.loadAll();
        viveiroCombo.getItems().clear();
        viveiroCombo.getItems().addAll(viveiros);
        
        // Configurar como o viveiro será exibido no ComboBox
        viveiroCombo.setCellFactory(listView -> new ListCell<model.Viveiro>() {
            @Override
            protected void updateItem(model.Viveiro item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNome());
                }
            }
        });
        
        viveiroCombo.setButtonCell(new ListCell<model.Viveiro>() {
            @Override
            protected void updateItem(model.Viveiro item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getNome());
                }
            }
        });
    }

    @Override
    protected Repositorio<model.Passarinho, Integer> getRepositorio() {
        return passarinhoRepo;
    }

    @Override
    protected view.PassarinhoView modelToView(model.Passarinho p) {
        String viveiroNome = "";
        if (p.getViveiro() != null) {
            viveiroNome = p.getViveiro().getNome();
        }
        
        return new view.PassarinhoView(
            p.getId(), 
            p.getSpecies(), 
            p.getWeight(), 
            p.getMigration(), 
            viveiroNome
        );
    }

    @Override
    protected model.Passarinho viewToModel() {
        model.Passarinho p = new model.Passarinho();
        p.setSpecies(speciesField.getText());
        
        try {
            p.setWeight(new BigDecimal(weightField.getText()));
        } catch (NumberFormatException e) {
            p.setWeight(BigDecimal.ZERO);
        }
        
        try {
            if (!migrationField.getText().isEmpty()) {
                p.setMigration(dateFormat.parse(migrationField.getText()));
            } else {
                p.setMigration(new Date());
            }
        } catch (ParseException e) {
            p.setMigration(new Date());
        }
        
        // Buscar viveiro selecionado no ComboBox
        model.Viveiro viveiroSelecionado = viveiroCombo.getValue();
        if (viveiroSelecionado != null) {
            p.setViveiro(viveiroSelecionado);
        }
        
        return p;
    }

    @Override
    protected void preencherCampos(view.PassarinhoView p) {
        idField.setText(String.valueOf(p.getId()));
        speciesField.setText(p.getSpecies());
        weightField.setText(p.getWeight().toString());
        migrationField.setText(p.getMigrationFormatted());
        
        // Selecionar o viveiro no ComboBox
        String viveiroNome = p.getViveiroNome();
        if (viveiroNome != null && !viveiroNome.isEmpty()) {
            for (model.Viveiro viveiro : viveiroCombo.getItems()) {
                if (viveiro.getNome().equals(viveiroNome)) {
                    viveiroCombo.setValue(viveiro);
                    break;
                }
            }
        } else {
            viveiroCombo.setValue(null);
        }
    }

    @Override
    protected void limparCampos() {
        idField.clear();
        speciesField.clear();
        weightField.clear();
        migrationField.clear();
        viveiroCombo.setValue(null);
    }

    @Override
    protected void desabilitarCampos(boolean desabilitado) {
        speciesField.setDisable(desabilitado);
        weightField.setDisable(desabilitado);
        migrationField.setDisable(desabilitado);
        viveiroCombo.setDisable(desabilitado);
    }

    @Override
    protected void desabilitarBotoes(boolean adicionar, boolean atualizar, boolean deletar, boolean cancelar, boolean salvar) {
        adicionarButton.setDisable(adicionar);
        atualizarButton.setDisable(atualizar);
        deletarButton.setDisable(deletar);
        cancelarButton.setDisable(cancelar);
        salvarButton.setDisable(salvar);
    }

    @Override
    protected TableView<view.PassarinhoView> getTabela() {
        return tabela;
    }

    @Override
    protected Integer getIdFromViewModel(view.PassarinhoView viewModel) {
        return viewModel.getId();
    }

    @Override
    protected void setIdOnEntity(model.Passarinho entidade, Integer id) {
        entidade.setId(id);
    }

    // Métodos que chamam a superclasse
    @FXML
    public void onAdicionarButtonAction() {
        super.onAdicionar();
    }

    @FXML
    public void onSalvarButtonAction() {
        super.onSalvar();
    }

    @FXML
    public void onAtualizarButtonAction() {
        super.onAtualizar();
    }

    @FXML
    public void onDeletarButtonAction() {
        super.onDeletar();
    }

    @FXML
    public void onCancelarButtonAction() {
        super.onCancelar();
    }
}
