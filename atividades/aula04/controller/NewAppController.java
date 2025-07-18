package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.Parent;
import view.NewAppView;

import java.net.URL;
import java.util.ResourceBundle;

public class NewAppController implements Initializable {
    @FXML
    private Tab tabPassarinho;
    @FXML
    private Tab tabViveiro;
    
    NewAppView appView;
    
    public NewAppController() {
        this.appView = new NewAppView();
    }
    
    public static void main(String[] args) throws Exception {
        NewAppController appController = new NewAppController();
        appController.appView.run(args);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Carrega passarinho.fxml
            Parent passarinhoContent = FXMLLoader.load(getClass().getResource("../view/passarinho.fxml"));
            tabPassarinho.setContent(passarinhoContent);

            // Carrega viveiro.fxml
            Parent viveiroContent = FXMLLoader.load(getClass().getResource("../view/viveiro.fxml"));
            tabViveiro.setContent(viveiroContent);
        } catch (Exception e) {
            System.out.println("Erro ao carregar FXMLs: " + e.getMessage());
            e.printStackTrace();
        }       
    }
}
