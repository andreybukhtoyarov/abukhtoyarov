package ru.job4j.bomberman;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameUI extends Application {

    private Stage stage;
    private Scene scene;
    private Group group;

    @Override
    public void start(Stage stage) {
        Game bomberMan = new Game();
        int difficulty = bomberMan.getDifficulty();
        bomberMan.createGame(9 + difficulty, 2 + difficulty, 3 + difficulty);
        this.stage = stage;
        this.group = new Group();
        this.scene = new Scene(group, bomberMan.getBoardSize() * 20, bomberMan.getBoardSize() * 20);
        this.group.getChildren().addAll(bomberMan.getObstacles());
        this.group.getChildren().addAll(bomberMan.getMonsters());
        this.group.getChildren().add(bomberMan.getBomberManShape());
        this.stage.setScene(this.scene);
        this.stage.setResizable(false);
        this.stage.show();
        stage.setOnCloseRequest(event -> bomberMan.shutdownPoolAndAwaitTermination());
    }
}
