import Game1.MainApplication1;
import Game2.MainApplication2;
import Other.Graduation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Board extends Application{
    Stage stage;
    Button[] buttons = new Button[3];
    private int roundNum = 2;

    EventHandler<ActionEvent> action = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            String game = ((Button)(actionEvent.getSource())).getText();
            switch(game){
                case "Game 1" :
                    Thread gameThread = new Thread();
                    try {
                        MainApplication1 game1 = new MainApplication1(stage);
                        if (game1.getWonGame()) {
                            System.out.println("Won Game 1");
                            roundNum = 2;
                        }
                        //System.out.println("Running Main Application 1");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "Game 2" :
                    if (roundNum == 2) {
                        try {
                            new MainApplication2(stage);
                            //System.out.println("Running Main Application 2");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else {
                        System.out.println("You need to pass level 1 before proceeding");
                    }
                    break;
                case "Graduation" :
                    stage.close();
                    Graduation graduation = new Graduation();
                    break;
                default :
                    System.out.println("No way");
                    break;
            }
        }
    };

    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        StackPane root = new StackPane();

        BackgroundImage myBI= new BackgroundImage(new Image("file:./img/blue.png",800,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));

        Image usaFlag = new Image("file:./img/usaflag.png");
        Image frenchFlag = new Image("file:./img/frenchflag.png");
        ImageView usaFlagView1 = new ImageView(usaFlag);
        ImageView frenchFlagView1 = new ImageView(frenchFlag);
        usaFlagView1.setFitWidth(150);
        usaFlagView1.setFitHeight(100);
        frenchFlagView1.setFitWidth(150);
        frenchFlagView1.setFitHeight(120);

        ImageView usaFlagView2 = new ImageView(usaFlag);
        ImageView frenchFlagView2 = new ImageView(frenchFlag);
        usaFlagView2.setFitWidth(150);
        usaFlagView2.setFitHeight(100);
        frenchFlagView2.setFitWidth(150);
        frenchFlagView2.setFitHeight(120);

        HBox flags1 = new HBox();
        flags1.setAlignment(Pos.TOP_CENTER);
        flags1.getChildren().addAll(usaFlagView1, frenchFlagView1);
        flags1.setSpacing(250);
        flags1.setTranslateY(50);

        HBox flags2 = new HBox();
        flags2.setAlignment(Pos.BOTTOM_CENTER);
        flags2.getChildren().addAll(frenchFlagView2, usaFlagView2);
        flags2.setSpacing(250);
        flags2.setTranslateY(-50);
        root.getChildren().addAll(flags1, flags2);

        Text enrollmentTitle = new Text("Bienvenue!");
        enrollmentTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text enrollmentDescription = new Text(
                "\n\nYou are a study abroad student that just arrived at your new school" +
                        "\nPlay the two mini games available to you and get to know France." +
                        "\nOnce you feel that you have gotten the ENSEA experience," +
                        "\nget your diploma by clicking on the \"Graduation\" button." +
                        "\nNow, click the button below to enroll and start your semester" +
                        "\n\nGood luck!\n\n");
        enrollmentDescription.setTextAlignment(TextAlignment.CENTER);
        enrollmentDescription.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        Button startButton = new Button("Enroll");
        startButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        VBox text = new VBox(enrollmentTitle, enrollmentDescription, startButton);
        text.setAlignment(Pos.CENTER);
        root.getChildren().add(text);

        Scene enrollmentScene = new Scene(root, 800, 800);
        stage.setScene(enrollmentScene);
        stage.show();
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
                startGame();
            }
        });

    }

    public void startGame() {

        StackPane pane = new StackPane();

        BackgroundImage myBI= new BackgroundImage(new Image("file:./img/enseamap.png",800,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        pane.setBackground(new Background(myBI));

        stage.setTitle("ENSEA");
        Scene scene = new Scene(pane, 800, 800, true);

        buttons[0] = new Button("Game 1");
        buttons[0].setTranslateY(-255);
        buttons[1] = new Button("Game 2");
        buttons[1].setTranslateY(-100);
        buttons[2] = new Button("Graduation");
        buttons[2].setTranslateX(160);
        buttons[2].setTranslateY(170);

        for (int i = 0; i < 3; i++){
            buttons[i].setVisible(true);
            pane.getChildren().add(buttons[i]);
            buttons[i].setOnAction(action);
        }
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
