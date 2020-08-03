package aldulaia;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Altaher Al-Dulaimi
 */
public class Assign5 extends Application {

    public static void main(String[] args) {

        launch();

    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Assign5FXML.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Cookie Inventory");
        stage.show();
    }

}
