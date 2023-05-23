/*
* File Name: BoardController.java
* Written by: Xiaoran Du
* Description: The BoardController class includes the drawBaord method to draw different boards, and also has several event handler methods
* to implement mouse events.
* Challenges: The challenge part is to use GraphicsContext to draw the boards, and also to implement different event handler methodds.
* Time Spend: 4 hours
*                  Revision History
* Date:                   By:               Action:
* -------------------------------------------------------
 04/25/2023             xd     Created the BoardController java class.
*/

package com.example.project;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {
    @FXML//FXML loader injects from the layout
    private Button btOne;
    @FXML
    private Button btTwo;
    @FXML
    private Button btThree;
    @FXML
    private Button btFour;
    @FXML
    private Button btFive;
    @FXML
    private Button btSix;
    @FXML
    private Button btSeven;
    @FXML
    private Button btEight;
    @FXML
    private Button btNine;
    @FXML
    private Canvas canvas;
    @FXML
    private Text result;

    //make a new board declaration
    Board board;
    int selectedRow;
    int selectedCol;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        board = new Board(); //create a new board instance
        GraphicsContext context = canvas.getGraphicsContext2D(); //get graphic contet from canvas
        drawBoard(context);//call drawBoard method
        selectedCol = 0;
        selectedRow = 0;
        result.setVisible(false);
    }

    //draw a board
    public void drawBoard(GraphicsContext context) {
        context.clearRect(0, 0, 450, 450);
        //draw the empty board
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int posX = c * 50;
                int posY = r * 50;
                int width = 50;
                context.setStroke(Color.BLACK);
                context.setLineWidth(1);
                context.strokeRect(posX, posY, width, width);
            }
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int posXDark = c * 150;
                int posYDark = r * 150;
                int width = 150;
                context.setStroke(Color.BLACK);
                context.setLineWidth(3);
                context.strokeRect(posXDark, posYDark, width, width);
            }
        }

        //set the highlight color for the selected cell
        context.setStroke(Color.ORANGE);
        context.setLineWidth(5);
        context.strokeRect(selectedCol * 50, selectedRow * 50, 50, 50);

        //draw the initial board
        int[][] initial = board.getInitialBoard(Difficulty.MEDIUM);
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int posX = c * 50 + 15;
                int posY = r * 50 + 35;
                context.setFill(Color.BLACK);
                context.setFont(new Font(30));
                if (initial[r][c] != 0) {
                    context.fillText(String.valueOf(initial[r][c]), posX, posY);
                }
            }
        }

        //draw the playing board
        int[][] playing = board.getPlayingBoard();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                int posX = c * 50 + 15;
                int posY = r * 50 + 35;
                context.setFill(Color.PURPLE);
                context.setFont(new Font(30));
                if (playing[r][c] != 0) {
                    context.fillText(String.valueOf(playing[r][c]), posX, posY);
                }
            }
        }

        //show the result text when the board solution is correct
        int[][] combinedBoard = board.getCombineBoard(initial, playing);
        if (board.checkSolution(combinedBoard)) {
            result.setVisible(true);
        }

        //method to handle the mouse onclick event
        canvas.setOnMouseClicked(e -> {
            int mouseX = (int) e.getX();
            int mouseY = (int) e.getY();
            selectedRow = mouseY / 50;
            selectedCol = mouseX / 50;
            //get the canvas graphics context and redraw
            drawBoard(canvas.getGraphicsContext2D());
        });

        //methods to handle button click events
        btOne.setOnAction(e -> {
            board.updateBoard(1, selectedRow, selectedCol);
            drawBoard(canvas.getGraphicsContext2D());

        });

        btTwo.setOnAction(e -> {
            board.updateBoard(2, selectedRow, selectedCol);
            drawBoard(canvas.getGraphicsContext2D());

        });
        btThree.setOnAction(e -> {
            board.updateBoard(3, selectedRow, selectedCol);
            drawBoard(canvas.getGraphicsContext2D());

        });
        btFour.setOnAction(e -> {
            board.updateBoard(4, selectedRow, selectedCol);
            drawBoard(canvas.getGraphicsContext2D());

        });
        btFive.setOnAction(e -> {
            board.updateBoard(5, selectedRow, selectedCol);
            drawBoard(canvas.getGraphicsContext2D());

        });
        btSix.setOnAction(e -> {
            board.updateBoard(6, selectedRow, selectedCol);
            drawBoard(canvas.getGraphicsContext2D());

        });
        btSeven.setOnAction(e -> {
            board.updateBoard(7, selectedRow, selectedCol);
            drawBoard(canvas.getGraphicsContext2D());

        });
        btEight.setOnAction(e -> {
            board.updateBoard(8, selectedRow, selectedCol);
            drawBoard(canvas.getGraphicsContext2D());

        });
        btNine.setOnAction(e -> {
            board.updateBoard(9, selectedRow, selectedCol);
            drawBoard(canvas.getGraphicsContext2D());

        });
    }
}