/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.Golebiowski.Pawel.Server.ModelBoard;

import pl.polsl.Golebiowski.Pawel.Server.ModelPieces.Piece;

/**
 * class of single squere on the board
 *
 * @author Pawcio
 * @version 1.1
 */
public class Square {

    public enum Color {
        White,
        Black;
    }
    int positionX, positionY;
    /**
     * kind of piece on the square
     */
    Piece pieceOn;

    /**
     * occupied means if any piece is on the square
     */
    public Square() {

    }

    public Square(int positionX, int positionY) {
        this.pieceOn = null;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Square(Piece examplePiece, int positionX, int positionY) {
        this.pieceOn = examplePiece;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Piece getPieceOn() {
        return this.pieceOn;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

}
