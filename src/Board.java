import Game1.MainApplication1;
import Game2.MainApplication2;
import Game3.MainApplication3;
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
    private int roundNum = 3;
    EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            String game = ((Button)(actionEvent.getSource())).getText();
            switch(game){
                case "Game 1" :
                    Thread gameThread = new Thread();
                    try {
                        MainApplication1 game1 = new MainApplication1(stage);
                        /*if (game1.getWonGame) {

                        }*/
                        System.out.println("Running Main Application 1");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "Game 2" :
                    if (roundNum == 2 || roundNum == 3) {
                        try {
                            new MainApplication2(stage);
                            System.out.println("Running Main Application 2");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else {
                        System.out.println("You need to pass level 1 before proceeding");
                    }
                    break;
                case "Game 3" :
                    System.out.println("Under construction");
                    /*try {
                        new MainApplication3(stage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }*/
                    break;
                default :
                    System.out.println("No way");
                    break;
            }
        }
    };

    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("Main Board");
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane, 800, 800, true);

        for (int i = 0; i < 3; i++){
            buttons[i] = new Button("Game "+ (i + 1));
            buttons[i].setVisible(true);
            pane.getChildren().add(buttons[i]);
            buttons[i].setOnAction(action);
            buttons[i].setTranslateY(i * 100);
        }
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
