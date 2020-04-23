/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.Golebiowski.Pawel.Server.ModelPieces;

import pl.polsl.Golebiowski.Pawel.Server.ModelBoard.Square;

/**
 *
 * @author Pawcio
 * @version 1.2
 */
public class Pawn extends Piece {

    public Pawn(Square.Color color) {
        this.color = color;
    }

    /**
     * method defines way the Piece is moving
     *
     * @param positionX start position X of the Piece
     * @param positionY start position Y of the Piece
     * @param destinationX end position X of the Piece
     * @param destinationY end position Y of the Piece
     * @return if it is legal to move the Piece or not
     */
    @Override
    public boolean TryToMove(int positionX, int positionY, int destinationX, int destinationY) {
        /**
         * check if the position of the piece is on the board
         */
        if (positionX < 0 || positionX > 7 || positionY < 0 || positionY > 7) {
            return false;
        }
        /**
         * check if the destination of the piece is on the board
         */
        if (destinationX < 0 || destinationX > 7 || destinationY < 0 || destinationY > 7) {
            return false;
        }
        /**
         * Piece cannot move to the same position
         */
        if (positionX == destinationX && positionY == destinationY) {
            return false;
        }
        //Piece moves in one direction 1 or 2 titles 
        if (this.color == Square.Color.White) {
            if (positionX == destinationX && destinationY == positionY + 1) {
                return true;    //When pawn is moving 1 square
            } else if (positionX == destinationX && destinationY == positionY + 2 && positionY == 1) {
                return true;    //When pawn's first move is 2 squares at once
            } else if ((positionX == destinationX + 1 || positionX == destinationX - 1) && destinationY == positionY + 1) {
                return true;    //when pawn is capturing enemy piece
            } else {
                return false;
            }
        } else {
            if (positionX == destinationX && destinationY == positionY - 1) {
                return true;    //When pawn is moving 1 square
            } else if (positionX == destinationX && destinationY == positionY - 2 && positionY == 6) {
                return true;    //When pawn's first move is 2 squares at once
            } else if ((positionX == destinationX + 1 || positionX == destinationX - 1) && destinationY == positionY - 1) {
                return true;    //when pawn is capturing enemy piece
            } else {
                return false;
            }
        }

    }
}
