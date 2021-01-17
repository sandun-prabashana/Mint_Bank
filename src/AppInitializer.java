import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("ik/mint/pos/views/LoginForm.fxml"))));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
}
