import Game1.MainApplication1;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.EventListener;

public class Board extends Application{
    Stage stage;
    Button[] buttons = new Button[3];
    EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            String game = ((Button)(actionEvent.getSource())).getText();
            switch(game){
                case "Game 0" :
                    Thread gameThread=new Thread();
                    try {
                        new MainApplication1(stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                default :
                    System.out.println("No way");

            }
        }
    };

    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        primaryStage.setTitle("Main Board");
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, 512, 512, true);

        for (int i =0;i<3;i++){
            buttons[i]=new Button("Game "+i);
            buttons[i].setVisible(true);
            pane.getChildren().add(buttons[i]);
            buttons[i].setOnAction(action);
            buttons[i].setTranslateY(i*100);
        }
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
