/*
* File Name: Board.java
* Written by: Xiaoran Du
* Description: The Board class defines the initial board, and have several methods to update the board. Also, the methods to check if the sudoku solution
* is valid is also included in this class.
* Challenges: The challenge part is to implement the sudoku game logic.
* Time Spend: 4 hours
*                  Revision History
* Date:                   By:               Action:
* -------------------------------------------------------
 04/25/2023             xd     Created the Board java class.
*/

package com.example.project;

//create a Board class
public class Board {
    private final int[][] initialBoardMedium;
    private final int[][] playingBoard;
    private final int[][] initialBoardEasy;
    private final int[][] initialBoardHard;

    //constructor
    public Board() {
        //set different difficulties for the board
        initialBoardMedium = new int[][]{
                {5, 3, 0, 0, 6, 1, 7, 0, 2},
                {0, 9, 7, 3, 0, 5, 0, 1, 4},
                {2, 1, 0, 7, 8, 9, 5, 0, 0},
                {9, 0, 1, 2, 0, 0, 0, 3, 5},
                {7, 6, 2, 1, 5, 3, 9, 4, 8},
                {8, 5, 3, 9, 4, 6, 1, 0, 7},
                {0, 8, 0, 5, 1, 0, 4, 0, 6},
                {4, 2, 6, 0, 0, 7, 0, 5, 1},
                {1, 7, 0, 6, 3, 4, 2, 0, 9}
        };

        initialBoardEasy = new int[][]{
                {5, 3, 0, 4, 6, 1, 7, 9, 2},
                {6, 9, 7, 0, 0, 5, 8, 0, 4},
                {2, 1, 4, 7, 8, 9, 5, 6, 3},
                {9, 4, 0, 2, 7, 8, 6, 0, 5},
                {7, 6, 2, 0, 5, 3, 0, 4, 8},
                {8, 5, 3, 0, 4, 6, 1, 0, 7},
                {3, 8, 0, 5, 0, 2, 4, 7, 6},
                {4, 2, 6, 8, 9, 7, 3, 0, 1},
                {1, 7, 5, 6, 3, 4, 2, 8, 9}
        };
        initialBoardHard = new int[][]{
                {0, 0, 0, 4, 0, 0, 0, 9, 0},
                {6, 0, 7, 0, 0, 0, 8, 0, 4},
                {0, 1, 0, 7, 0, 9, 0, 0, 3},
                {9, 0, 1, 0, 7, 0, 0, 3, 0},
                {0, 0, 2, 0, 0, 0, 9, 0, 0},
                {0, 5, 0, 0, 4, 0, 1, 0, 7},
                {3, 0, 0, 5, 0, 2, 0, 7, 0},
                {4, 0, 6, 0, 0, 0, 3, 0, 1},
                {0, 7, 0, 0, 0, 4, 0, 0, 0}
        };

        playingBoard = new int[9][9];
        //                answer
//                {5, 3, 8, 4, 6, 1, 7, 9, 2},
//                {6, 9, 7, 3, 2, 5, 8, 1, 4},
//                {2, 1, 4, 7, 8, 9, 5, 6, 3},
//                {9, 4, 1, 2, 7, 8, 6, 3, 5},
//                {7, 6, 2, 1, 5, 3, 9, 4, 8},
//                {8, 5, 3, 9, 4, 6, 1, 2, 7},
//                {3, 8, 9, 5, 1, 2, 4, 7, 6},
//                {4, 2, 6, 8, 9, 7, 3, 5, 1},
//                {1, 7, 5, 6, 3, 4, 2, 8, 9}
    }

    //get the initial board
    public int[][] getInitialBoard(Difficulty difficulty) {
        if (difficulty == Difficulty.EASY) return initialBoardEasy;
        else if (difficulty == Difficulty.MEDIUM) return initialBoardMedium;
        else {
            return initialBoardHard;
        }
    }

    //get the playingBoard
    public int[][] getPlayingBoard() {
        return playingBoard;
    }

    //update the board
    public void updateBoard(int val, int row, int col) {
        int[][] initialBoard = getInitialBoard(Difficulty.MEDIUM);
        //the board can only be updated if the initialBoard cell is 0
        if (initialBoard[row][col] == 0) {
            int[][] board = getCombineBoard(initialBoard, playingBoard);
            //can add the input if it is valid
            if (val >= 0 && val <= 9) {
                if (isValid(board, row, col, val)) {
                    playingBoard[row][col] = val;
                } else {
                    //show the hint if the input is not valid for now
                    System.out.println("It has duplicated number in the same row or column or small box");
                }
            } else {
                System.out.println("Your input is not valid.");
            }
        }
    }

    //combine the initial board and the playing board
    public int[][] getCombineBoard(int[][] initialBoard, int[][] playingBoard) {
        int[][] combined = new int[9][9];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (initialBoard[r][c] != 0) {
                    combined[r][c] = initialBoard[r][c];
                } else {
                    combined[r][c] = playingBoard[r][c];
                }
            }
        }

        return combined;
    }

    //    method to check if the solution is correct
    public boolean checkSolution(int[][] board) {
        //check the row
        for (int r = 0; r < 9; r++) {
            int sum = 0;
            for (int c = 0; c < 9; c++) {
                sum += board[r][c];
            }
            if (sum != 45) {
                return false;
            }
        }

        //check the column
        for (int c = 0; c < 9; c++) {
            int sum = 0;
            for (int r = 0; r < 9; r++) {
                sum += board[r][c];
            }
            if (sum != 45) {
                return false;
            }
        }

        //check the smaller group
        for (int r = 0; r < 9; r += 3) {
            for (int c = 0; c < 9; c += 3) {
                int sum = 0;
                for (int r1 = 0; r1 < 3; r1++) {
                    for (int c1 = 0; c1 < 3; c1++) {
                        sum += board[r1 + r][c1 + c];
                    }
                }
                if (sum != 45) {
                    return false;
                }
            }
        }
        return true;
    }

    //check if the input is valid
    public boolean isValid(int[][] board, int row, int col, int val) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val) {
                return false;
            }
            if (board[i][col] == val) {
                return false;
            }
            if (board[row / 3 * 3 + i / 3][col / 3 * 3 + i % 3] == val) {
                return false;
            }
        }
        return true;
    }

}


