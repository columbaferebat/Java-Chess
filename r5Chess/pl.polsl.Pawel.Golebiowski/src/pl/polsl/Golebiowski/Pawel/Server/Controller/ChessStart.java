/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.Golebiowski.Pawel.Server.Controller;

import pl.polsl.Golebiowski.Pawel.Server.ModelBoard.Board;
import pl.polsl.Golebiowski.Pawel.Server.ModelBoard.MoveState;
import pl.polsl.Golebiowski.Pawel.Server.ModelBoard.RulesCheck;
import pl.polsl.Golebiowski.Pawel.Server.ModelBoard.Square;
import pl.polsl.Golebiowski.Pawel.Server.ModelPieces.Piece;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 * The main class of the server
 *
 * @author Pawcio
 * @version 1.3
 */
public class ChessStart {

    /**
     * port number
     */
    final private int PORT = 8888;

    /**
     * field represents the socket waiting for client connections
     */
    private ServerSocket serverSocket;

    /**
     * Creates the server socket
     *
     * @throws IOException when prot is already bind
     */
    ChessStart() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    /**
     * The main application method
     *
     */
    public static void main(String args[]) {

        System.out.println("Welcome to Chess Game!");
        /**
         * Initalizing main objects responsible to store current game status
         */
        Board board = new Board();
        ViewBoard viewBoard = new ViewBoard();
        MoveState moveState = new MoveState();
        Scanner scanner = new Scanner(System.in);
        /**
         * positions and destinations of piece that is up to move
         */
        int positionX, positionY, destinationX, destinationY;
        /**
         * Piece that is actually moving in the game
         */
        Piece movingPiece;
        Square destinationSquare;
        Square startingSquare;
        /**
         * moveSource - string given by client to make a move
         */
        String moveSource = "";
        /**
         * flags that help keep game with rules
         */
        boolean isWhiteToMove = true;
        boolean correctText;
        boolean isMoveAllowed;

        while (true) {
            /**
             * printing the board
             */
            viewBoard.showBoard(board);
            System.out.println(viewBoard.showPlayerStatus(isWhiteToMove));
            /**
             * check if client entered good coordinates (4 char: e.g e1e2)
             */
            correctText = false;
            while (correctText == false) {
                moveSource = scanner.nextLine();
                if (moveState.checkMoveSource4char(isWhiteToMove, moveSource) == true) {
                    if (moveState.checkMoveSourcePosition(moveSource) == true) {
                        correctText = true;
                    }
                    if (correctText == false) {
                        System.out.println("This is not coordinates of the board!");
                    }
                }
                if (correctText == false) {
                    System.out.println("Enter co-ordinates of the piece, then it's destination (e.g a1a2)");
                }
            }
            /**
             * initalizing positions
             */
            positionX = moveState.getPositionX();
            positionY = moveState.getPositionY();
            destinationX = moveState.getDestinationX();
            destinationY = moveState.getDestinationY();
            /**
             * checking
             */
            try {
                startingSquare = board.getSquare(positionX, positionY);
                movingPiece = startingSquare.getPieceOn();
                destinationSquare = board.getSquare(destinationX, destinationY);

                /**
                 * checking if the Piece can move to particular square [is
                 * Player owner][is the destination Square empty]
                 */
                isMoveAllowed = false;
                if (RulesCheck.isOwner(isWhiteToMove, movingPiece) == true) {
                    if (movingPiece.TryToMove(positionX, positionY, destinationX, destinationY) == true) {
                        if (RulesCheck.TryToCapture(destinationSquare, isWhiteToMove, startingSquare) == true) {
                            if (RulesCheck.checkStraightLine(destinationSquare, startingSquare, board) == true) {
                                board.setPiece(destinationX, destinationY, movingPiece);
                                board.setPiece(positionX, positionY, null);
                                isWhiteToMove = !isWhiteToMove;
                                isMoveAllowed = true;
                                if (RulesCheck.isThereKing(board, isWhiteToMove) == false) {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (isMoveAllowed == false) {
                    System.out.println("This move is not allowed!");
                }
            } catch (NullPointerException e) {
                System.out.println("There is no piece at this square!");
            }

            System.out.println();

        }
        viewBoard.showBoard(board);
        if (isWhiteToMove == true) {
            System.out.println("Black wins");
        } else {
            System.out.println("White wins");
        }
    }
}
