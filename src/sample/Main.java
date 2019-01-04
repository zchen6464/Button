package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        ArrayList<Integer> pattern = new ArrayList<Integer>();
        ArrayList<Integer> test = new ArrayList<Integer>();
        int length = 5;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Button");
        Button start = new Button("Press to Start");

        Button blue = new Button("Blue");
        Button green = new Button("Green");
        Button yellow = new Button("Yellow");
        Button red = new Button("Red");
        Button submit = new Button("Submit");

        Timer timer = new Timer();
        TimerTask light = new TimerTask() {
            @Override
            public void run() {
                blue.setStyle("-fx-background-color: white");
                green.setStyle("-fx-background-color: white");
                yellow.setStyle("-fx-background-color: white");
                red.setStyle("-fx-background-color: white");
                int num = RNGButton();
                pattern.add(num);
                if(num == 0)
                {
                    blue.setStyle("-fx-background-color: blue");
                }
                else if(num == 1)
                {
                    green.setStyle("-fx-background-color: green");
                }
                else if(num ==2)
                {
                    yellow.setStyle("-fx-background-color: yellow");
                }
                else
                {
                    red.setStyle("-fx-background-color: red");
                }
            }
        };

        EventHandler<ActionEvent> simonSays = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setPattern(primaryStage);
                    for (int num = 0; num < test.size(); num ++)
                    {
                        if(test.get(num) != pattern.get(num))
                        {
                            gameEnd(primaryStage);
                        }
                    }
                    if(test.size() >0)
                    {
                        for(int j = 0; j < test.size(); j++ )
                        {
                            test.remove(j);
                        }
                    }
                }
        };
        submit.setOnAction(simonSays);
        red.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                test.add(3);
            }
        });
        yellow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                test.add(2);
            }
        });
        green.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                test.add(1);
            }
        });
        blue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                test.add(0);
            }
        });
        Pane game = new Pane();
        HBox hitBox = new HBox(red,yellow,green,blue,submit);
        game.getChildren().add(hitBox);
        Scene secondary = new Scene(game,500,200);

        EventHandler<ActionEvent> gameStart = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(secondary);
                primaryStage.show();
                timer.schedule(light,1000l);
            }
        };
        start.setOnAction(gameStart);
        Pane main = new Pane();
        main.getChildren().add(start);
        Scene primary = new Scene(main,500,200);
        primaryStage.setScene(primary);
        primaryStage.show();
    }
    public int RNGButton()
    {
        int x = (int)(Math.random()*3);
        return x;
    }

    public void gameEnd(Stage primaryStage)
    {
        Label lost = new Label("You Lose");
        Pane end = new Pane();
        end.getChildren().add(lost);
        Scene endGame = new Scene(end,500,200);
        primaryStage.setScene(endGame);
        primaryStage.show();
    }
    public void setPattern(Stage primarstage)
    {
        for(int i = 0; i < length; i++)
        {
            timer.schedule(light, 1000l);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
