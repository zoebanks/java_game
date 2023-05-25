package Other;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Graduation extends Stage {

    private String audioFile = "./sounds/pompandc.wav";
    private AudioInputStream audioStream ;
    private Clip clip;

    public Graduation() {
        Stage stage = new Stage();
        playMusic();
        StackPane root = new StackPane();
        Text gradTitle = new Text("Congratulations!");
        gradTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 30));
        Text gradDescription = new Text("\nYou completed your study abroad experience");
        gradDescription.setTextAlignment(TextAlignment.CENTER);
        gradDescription.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        VBox text = new VBox(gradTitle, gradDescription);
        text.setAlignment(Pos.CENTER);
        text.setTranslateY(-50);
        BackgroundImage myBI= new BackgroundImage(new Image("file:./img/pinkbackgroundwtauvel.png",800,800,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(myBI));
        root.getChildren().add(text);

        Scene enrollmentScene = new Scene(root, 800, 800);
        stage.setScene(enrollmentScene);
        stage.show();
    }

    public void playMusic() {

        //System.out.println("Playing music");
        try {
            clip = AudioSystem.getClip();
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(audioFile));

            clip.open(audioStream);

            // Play the audio
            clip.start();

            // Close the resources
            audioStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
