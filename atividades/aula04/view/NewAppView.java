package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.net.URL;

public class NewAppView extends Application
{
    private FXMLLoader loader;
    private URL url;
    private Stage primaryStage;
    
    public NewAppView() {
        this.loader = new FXMLLoader();
        try {
            this.url = new File("view/app_new.fxml").toURI().toURL();
        } catch (Exception e) {
            System.out.println("Erro na carga do FXML:" + e);
        }
        this.loader.setLocation(this.url);        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Pane pane = loader.<Pane>load();
            
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.setTitle("JavaFX CRUD - Passarinho e Viveiro");
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Erro na carga da tela:" + e);
        }
    }
    
    public void run(String[] args) {
        launch(args);
    }
}
