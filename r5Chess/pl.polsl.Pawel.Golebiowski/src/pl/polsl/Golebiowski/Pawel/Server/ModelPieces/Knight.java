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
 * @version 1.1
 */
public class Knight extends Piece {

    public Knight(Square.Color color) {
        this.color = color;
    }

    /**
     * method defines way the knight is moving
     *
     * @param positionX start position X of the knight
     * @param positionY start position Y of the knight
     * @param destinationX end position X of the knight
     * @param destinationY end position Y of the knight
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

        int diffrenceX = Piece.absoluteValue(destinationX - positionX);
        int diffrenceY = Piece.absoluteValue(destinationY - positionY);

        if (diffrenceX + diffrenceY == 3) {
            return true;
        } else {
            return false;
        }
    }

}
