/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.Golebiowski.Pawel.Server.ModelBoard;

import pl.polsl.Golebiowski.Pawel.Server.ModelPieces.*;
import pl.polsl.Golebiowski.Pawel.Server.ModelPieces.*;

/**
 * class to check if the player move his pieces with chess rules
 *
 * @author Pawcio
 * @version 1.2
 */
public class RulesCheck {

    /**
     * Checks if current Piece can capture piece on destination Square, returns
     * true if destination Square is empty
     *
     * @param destinationSquare
     * @param isWhiteToMove boolean is white to move
     * @param startingSquare
     * @return piece can go there or not
     */
    public static boolean TryToCapture(Square destinationSquare, boolean isWhiteToMove, Square startingSquare) {

        try {
            if (RulesCheck.compareColorOfPlayerAndPiece(isWhiteToMove, destinationSquare.getPieceOn().getColor()) == false) {
                if (startingSquare.getPieceOn() instanceof Pawn) {
                    if (destinationSquare.getPositionX() != startingSquare.getPositionX()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } //NullPointerException appears when destinationSquare is empty
        catch (NullPointerException e) {
            if (startingSquare.getPieceOn() instanceof Pawn) {
                if (destinationSquare.getPositionY() != startingSquare.getPositionY()
                        && destinationSquare.getPositionX() != startingSquare.getPositionX()) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return true;
            }
        }

    }

    /**
     * @param isWhiteToMove true if whites move, if false - black
     * @param piece piece that player want to move
     * @return true - player owns the piece
     */
    public static boolean isOwner(boolean isWhiteToMove, Piece piece) {
        if (RulesCheck.compareColorOfPlayerAndPiece(isWhiteToMove, piece.getColor()) == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param board
     * @param isWhiteToMove
     * @return
     */
    public static boolean isThereKing(Board board, boolean isWhiteToMove) {

        boolean isWhiteToLookFor = compareColorOfPlayerAndPiece(isWhiteToMove, Square.Color.White);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                try {
                    if (board.getSquare(i, j).pieceOn instanceof King) {
                        if (isWhiteToLookFor == true) {
                            if(board.getSquare(i, j).pieceOn.getColor()==Square.Color.White)
                            {
                                return true;
                            }
                        }
                        else{
                            if(board.getSquare(i, j).pieceOn.getColor()==Square.Color.Black)
                            {
                                return true;
                            }
                        }
                    }
                } catch (NullPointerException e) {
                    //empty square
                }
            }
        }
        return false;
    }

    /**
     *
     * @param destinationSquare
     * @param startingSquare
     * @param board whole board
     * @return if there is something in line or not
     */
    public static boolean checkStraightLine(Square destinationSquare, Square startingSquare, Board board) {
        Piece movingPiece = startingSquare.getPieceOn();
        int positionX = startingSquare.getPositionX();
        int positionY = startingSquare.getPositionY();
        int destinationX = destinationSquare.getPositionX();
        int destinationY = destinationSquare.getPositionY();
        /**
         * descibes which way to go horizontally and vertically - positive or
         * negative
         */
        int whichWayX, whichWayY;

        if (movingPiece instanceof Queen || movingPiece instanceof Rook || movingPiece instanceof Bishop) {
            /**
             * way of moving of Rook
             */
            if (positionX == destinationX) {
                whichWayY = destinationY - positionY;
                return RulesCheck.checkLineRookVertically(whichWayY, positionX, positionY, destinationY, board);
            } else if (positionY == destinationY) {
                whichWayX = destinationX - positionX;
                return RulesCheck.checkLineRookDiagonally(whichWayX, positionX, positionY, destinationX, board);
            } else if (positionY != destinationY && positionX != destinationX) {
                /**
                 * way of moving of Bishop
                 */
                whichWayY = destinationY - positionY;
                whichWayX = destinationX - positionX;
                return RulesCheck.checkLineBishop(whichWayX, whichWayY, destinationSquare, startingSquare, board);

            } else {
                /**
                 * unknown Piece
                 */
                return false;
            }
        } else {
            return true;
        }
        //spradzanie czy bishop queen i rook nie maja nic w prostej linii
    }

    /**
     *
     * @param isWhiteToMove true if whites move, if false - black
     * @param color color of the piece
     * @return false if given color of the piece and current player that is up
     * to move are diffrent
     */
    private static boolean compareColorOfPlayerAndPiece(boolean isWhiteToMove, Square.Color color) {
        if (color == Square.Color.White && isWhiteToMove == true) {
            return true;
        } else if (color == Square.Color.Black && isWhiteToMove == false) {
            return true;
        } else {
            return false;
        }
    }

    private static int absoluteValue(int value) {
        if (value < 0) {
            return -value;
        } else {
            return value;
        }
    }

    private static boolean checkLineRookVertically(int whichWay, int positionX, int positionY, int destination, Board board) {
        if (whichWay > 0) {
            for (int i = positionY + 1; i < destination; i++) {
                try {
                    if (board.getSquare(positionX, i).getPieceOn() != null) {
                        return false;
                    }
                } catch (NullPointerException e) {
                    //current square is empty
                }
            }
        } else {
            for (int i = positionY - 1; i > destination; i--) {
                try {
                    if (board.getSquare(positionX, i).getPieceOn() != null) {
                        return false;
                    }
                } catch (NullPointerException e) {
                    //current square is empty
                }
            }
        }
        return true;
    }

    private static boolean checkLineRookDiagonally(int whichWay, int positionX, int positionY, int destination, Board board) {
        if (whichWay > 0) {
            for (int i = positionX + 1; i < destination; i++) {
                try {
                    if (board.getSquare(i, positionY).getPieceOn() != null) {
                        return false;
                    }
                } catch (NullPointerException e) {
                    //current square is empty
                }
            }
        } else {
            for (int i = positionX - 1; i > destination; i--) {
                try {
                    if (board.getSquare(i, positionY).getPieceOn() != null) {
                        return false;
                    }
                } catch (NullPointerException e) {
                    //current square is empty
                }
            }
        }
        return true;
    }

    private static boolean checkLineBishop(int whichWayX, int whichWayY,
            Square destinationSquare, Square startingSquare, Board board) {
        int positionX = startingSquare.getPositionX();
        int positionY = startingSquare.getPositionY();
        int destinationX = destinationSquare.getPositionX();
        int destinationY = destinationSquare.getPositionY();

        if (whichWayX > 0 && whichWayY > 0) {
            for (int i = 1; positionX + i < destinationX || positionY + i < destinationY; i++) {
                try {
                    if (board.getSquare(positionX + i, positionY + i).getPieceOn() != null) {
                        return false;
                    }
                } catch (NullPointerException e) {
                    //current square is empty
                }
            }
        } else if (whichWayX < 0 && whichWayY > 0) {
            for (int i = 1; positionX - i > destinationX || positionY + i < destinationY; i++) {
                try {
                    if (board.getSquare(positionX - i, positionY + i).getPieceOn() != null) {
                        return false;
                    }
                } catch (NullPointerException e) {
                    //current square is empty
                }
            }
        } else if (whichWayX < 0 && whichWayY < 0) {
            for (int i = 1; positionX - i > destinationX || positionY - i > destinationY; i++) {
                try {
                    if (board.getSquare(positionX - i, positionY - i).getPieceOn() != null) {
                        return false;
                    }
                } catch (NullPointerException e) {
                    //current square is empty
                }
            }
        } else if (whichWayX > 0 && whichWayY < 0) {
            for (int i = 1; positionX + i < destinationX || positionY - i > destinationY; i++) {
                try {
                    if (board.getSquare(positionX + i, positionY - i).getPieceOn() != null) {
                        return false;
                    }
                } catch (NullPointerException e) {
                    //current square is empty
                }
            }
        }
        return true;
    }
}
