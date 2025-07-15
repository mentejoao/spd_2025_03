package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.Date;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import model.PassarinhoRepository;
import view.AppView;

public class AppController implements Initializable {
    @FXML
    private TableView<view.Passarinho> tabela;
    @FXML
    private TableColumn<view.Passarinho, Integer> idCol;
    @FXML
    private TableColumn<view.Passarinho, String> speciesCol;
    @FXML
    private TableColumn<view.Passarinho, BigDecimal> weightCol;
    @FXML
    private TableColumn<view.Passarinho, Date> migrationCol;
    @FXML
    private TextField idField;
    @FXML
    private TextField speciesField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField migrationField;
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
    
    AppView appView;
    
    private static model.Database database = new model.Database("app.sqlite");
    private static model.PassarinhoRepository passarinhoRepo = 
        new model.PassarinhoRepository(database);
        
    public AppController() {
        this.appView = new AppView();
    }
    
    public static void main(String[] args) throws Exception {
        AppController appController = new AppController();
        appController.appView.run(args);
    }
    
    private void desabilitarBotoes(boolean adicionar, boolean atualizar, boolean deletar, boolean cancelar, boolean salvar) {
        adicionarButton.setDisable(adicionar);
        atualizarButton.setDisable(atualizar);
        deletarButton.setDisable(deletar);
        cancelarButton.setDisable(cancelar);
        salvarButton.setDisable(salvar);        
    }
    
    private void desabilitarCampos(boolean desabilitado) {
        speciesField.setDisable(desabilitado);
        weightField.setDisable(desabilitado);
        migrationField.setDisable(desabilitado);
    }
    
    private void limparCampos() {
        idField.setText("");
        speciesField.setText("");
        weightField.setText("");
        migrationField.setText("");        
    }
    
    @FXML
    public void onCancelarButtonAction() {
        desabilitarCampos(true);
        desabilitarBotoes(false,true,true,true,true);
        limparCampos();
        tabela.getSelectionModel().select(-1);        
    }
    
    @FXML
    public void onSalvarButtonAction() {
        try {
            view.Passarinho selectedPassarinho = tabela.getSelectionModel().getSelectedItem();
            
            if (selectedPassarinho != null) {
                // MODO UPDATE - atualizar registro existente
                model.Passarinho passarinho = new model.Passarinho();
                passarinho.setId(selectedPassarinho.getId());
                passarinho.setSpecies(speciesField.getText());
                passarinho.setWeight(new BigDecimal(weightField.getText()));
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                passarinho.setMigration(dateFormat.parse(migrationField.getText()));
                
                // Atualizar no banco
                passarinhoRepo.update(passarinho);
                
                // Atualizar o objeto view
                selectedPassarinho.setSpecies(passarinho.getSpecies());
                selectedPassarinho.setWeight(passarinho.getWeight());
                selectedPassarinho.setMigration(passarinho.getMigration());
                
                // Refresh da tabela
                tabela.refresh();
                
            } else {
                // MODO CREATE - criar novo registro
                model.Passarinho passarinho = new model.Passarinho();            
                passarinho.setSpecies(speciesField.getText());
                passarinho.setWeight(new BigDecimal(weightField.getText()));
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                passarinho.setMigration(dateFormat.parse(migrationField.getText()));            
                model.Passarinho passarinho_salvo = passarinhoRepo.create(passarinho); 
                view.Passarinho passarinhoView = modelToView(passarinho_salvo);
                tabela.getItems().add(passarinhoView);
                tabela.getSelectionModel().select(passarinhoView);
            }
            
            desabilitarCampos(true);
            desabilitarBotoes(false, true, true, true, true);
            
        } catch(Exception e) {
            new Alert(AlertType.ERROR, "Erro ao salvar: "+e.getMessage()).show();
        }
    }    
    
    @FXML
    public void onAtualizarButtonAction() {
        view.Passarinho selectedPassarinho = tabela.getSelectionModel().getSelectedItem();
        if (selectedPassarinho != null) {
            desabilitarCampos(false);
            desabilitarBotoes(true, true, true, false, false);
        }
    }

    @FXML
    public void onAdicionarButtonAction() {
        tabela.getSelectionModel().select(-1);
        desabilitarCampos(false);
        desabilitarBotoes(true,true,true,false,false);
        limparCampos();
    }

    @FXML
    public void onDeletarButtonAction() {
        view.Passarinho selectedPassarinho = tabela.getSelectionModel().getSelectedItem();
        if (selectedPassarinho != null) {
            try {
                // Criar objeto model a partir do view selecionado
                model.Passarinho passarinhoToDelete = new model.Passarinho();
                passarinhoToDelete.setId(selectedPassarinho.getId());
                passarinhoToDelete.setSpecies(selectedPassarinho.getSpecies());
                passarinhoToDelete.setWeight(selectedPassarinho.getWeight());
                passarinhoToDelete.setMigration(selectedPassarinho.getMigration());
                
                // Deletar do banco
                passarinhoRepo.delete(passarinhoToDelete);
                
                // Remover da tabela
                tabela.getItems().remove(selectedPassarinho);
                
                // Limpar campos e resetar botões
                limparCampos();
                desabilitarCampos(true);
                desabilitarBotoes(false, true, true, true, true);
                
            } catch (Exception e) {
                new Alert(AlertType.ERROR, "Erro ao deletar: " + e.getMessage()).show();
            }
        }
    }

    @FXML
    private void handlePassarinhoSelected(view.Passarinho newSelection) {
        if (newSelection != null) {
            idField.setText(Integer.toString(newSelection.getId()));
            speciesField.setText(newSelection.getSpecies());
            weightField.setText(newSelection.getWeight().toString());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            migrationField.setText(dateFormat.format(newSelection.getMigration()));
            desabilitarBotoes(false,false,false,true,true);
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        speciesCol.setCellValueFactory(
                new PropertyValueFactory<>("species"));
        weightCol.setCellValueFactory(
                new PropertyValueFactory<>("weight"));
        migrationCol.setCellValueFactory(
                new PropertyValueFactory<>("migration"));
        tabela.setItems(loadAllPassarinhos());
        tabela.getSelectionModel().selectedItemProperty().addListener(
            (observableValue, oldSelection, newSelection) -> {
                handlePassarinhoSelected(newSelection);
            });
    }
    
    private view.Passarinho modelToView(model.Passarinho passarinho) {
        return new view.Passarinho(
            passarinho.getId(),
            passarinho.getSpecies(),
            passarinho.getWeight(),
            passarinho.getMigration()
        );
    }
    
    private ObservableList<view.Passarinho> loadAllPassarinhos() {
        ObservableList<view.Passarinho> lista = 
            FXCollections.observableArrayList();
        List<model.Passarinho> listaFromDatabase = passarinhoRepo.loadAll();
        for(model.Passarinho passarinho: listaFromDatabase) {
            lista.add(modelToView(passarinho));
        }
        return lista;
    }
}