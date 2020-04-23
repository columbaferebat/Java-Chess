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
public class Bishop extends Piece {

    public Bishop(Square.Color color) {
        this.color = color;
    }

    /**
     * method defines way the bishop is moving
     *
     * @param positionX start position X of the bishop
     * @param positionY start position Y of the bishop
     * @param destinationX end position X of the bishop
     * @param destinationY end position Y of the bishop
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

        int diffrenceX = Piece.absoluteValue(destinationX - positionX);
        int diffrenceY = Piece.absoluteValue(destinationY - positionY);

        if (diffrenceX == diffrenceY) {
            return true;
        } else {
            return false;
        }
    }
}
