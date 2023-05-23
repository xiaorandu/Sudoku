/*
* File Name: Sudoku.java
* Written by: Xiaoran Du
* Description: In this assignment, I use Java and JavaFX to implement a sudoku game. The overall layout of the game (in the file layout.fxml) is built from
* SceneBuilder. The package also includes Board.java, BoardController.java and Difficulty.java. These package implement the sudoku game logic.
* Users can fill in the selected number into the empty cell on the board and will get the hint if the number is not valid. The user will see the "congratulations"
* message if the user finally solves the problem.
* Challenges: The challenge parts are to implement the sudoku game logic and check if the filled-in board is a valid sudoku solution, and use GraphicsContext to draw
* different boards to reflect the current board status.
* Total Time Spend: 12 hours
*                  Revision History
* Date:                   By:               Action:
* -------------------------------------------------------
 04/25/2023             xd     Created the Sudoku java class.
*/

package com.example.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Sudoku extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Sudoku.class.getResource("layout.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 720, 480); //scene contains all the contents of a scene graph
        stage.setTitle("Sudoku");//set the stage title
        stage.setScene(scene);//attach the scene to the stage
        stage.show();//display the content of the stage
    }

    //launch the application
    public static void main(String[] args) {
        launch();
    }
}