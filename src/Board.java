import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Board extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Main Board");
        Group root = new Group();
        Group gameRoot = new Group();
        Scene scene = new Scene(root, 512, 512, true);
        Scene nextScene = new Scene(gameRoot, 512, 512, true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
