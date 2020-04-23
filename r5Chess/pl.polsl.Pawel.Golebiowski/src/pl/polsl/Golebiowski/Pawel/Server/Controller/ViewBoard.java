/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.Golebiowski.Pawel.Server.Controller;

import pl.polsl.Golebiowski.Pawel.Server.ModelBoard.Board;
import pl.polsl.Golebiowski.Pawel.Server.ModelBoard.Square;
import pl.polsl.Golebiowski.Pawel.Server.ModelPieces.*;
import pl.polsl.Golebiowski.Pawel.Server.ModelPieces.*;

/**
 * class to show current state of board
 *
 * @author Pawcio
 * @version 1.1
 */
public class ViewBoard {

    /**
     * show current board in the console
     *
     * @param board is the board that is going to be printed
     * @param output PrintWriter output to print current board
     */
    public void showBoard(Board board) {

        Square[][] exampleBoard = board.getBoard();
        String readyString = "";
        for (int i = 0; i < 8; i++) {
            readyString += i + 1 + " | ";
            for (int j = 0; j < 8; j++) {
                try {
                    Piece pieceGot = exampleBoard[j][i].getPieceOn();
                    if (pieceGot.getColor() == Square.Color.Black) {
                        if (pieceGot instanceof Pawn) {
                            readyString += " p";
                        }
                        if (pieceGot instanceof Rook) {
                            readyString += " r";
                        }
                        if (pieceGot instanceof Knight) {
                            readyString += " n";
                        }
                        if (pieceGot instanceof Bishop) {
                            readyString += " b";
                        }
                        if (pieceGot instanceof King) {
                            readyString += " k";
                        }
                        if (pieceGot instanceof Queen) {
                            readyString += " q";
                        }
                    } else {
                        if (pieceGot instanceof Pawn) {
                            readyString += " P";
                        }
                        if (pieceGot instanceof Rook) {
                            readyString += " R";
                        }
                        if (pieceGot instanceof Knight) {
                            readyString += " N";
                        }
                        if (pieceGot instanceof Bishop) {
                            readyString += " B";
                        }
                        if (pieceGot instanceof King) {
                            readyString += " K";
                        }
                        if (pieceGot instanceof Queen) {
                            readyString += " Q";
                        }

                    }
                } catch (NullPointerException emptySquare) {
                    readyString += " -";
                }
            }
            //readyString += "\r";
            System.out.println(readyString);
            readyString = "";
        }

        System.out.println("     _ _ _ _ _ _ _ _ ");
        System.out.println("     A B C D E F G H");
        System.out.println("");

    }

    public String showPlayerStatus(boolean isWhiteToMove) {
        if (isWhiteToMove == true) {
            return "Whites to move:";
        } else {
            return "Blacks to move:";
        }
    }

}
