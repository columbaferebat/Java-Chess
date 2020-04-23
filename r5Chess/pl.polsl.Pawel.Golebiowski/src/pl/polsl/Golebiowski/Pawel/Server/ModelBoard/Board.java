/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.Golebiowski.Pawel.Server.ModelBoard;

import pl.polsl.Golebiowski.Pawel.Server.ModelPieces.*;
import pl.polsl.Golebiowski.Pawel.Server.ModelPieces.*;

/**
 * class of model of board
 *
 * @author Pawcio
 * @version 1.1
 */
public class Board {

    /**
     * defining columns' designations
     */
    private int a = 0, b = 1, c = 2, d = 3, e = 4, f = 5, g = 6, h = 7;

    /**
     * Look of Board:
     *
     * 0 | R N B Q K B N R
     * 1 | P P P P P P P P
     * 2 | - - - - - - - -
     * 3 | - - - - - - - -
     * 4 | - - - - - - - -
     * 5 | - - - - - - - -
     * 6 | p p p p p p p p
     * 7 | r n b q k b n r
     * _ _ _ _ _ _ _ _
     * A B C D E F G H
     *
     * uppercase means color is White
     *
     */
    private Square[][] Squares = new Square[8][8];

    public Board() {
        /**
         * initializing pieces
         */
        Square.Color black = Square.Color.Black;
        Square.Color white = Square.Color.White;

        Bishop BlackBishop = new Bishop(black);
        King BlackKing = new King(black);
        Queen BlackQueen = new Queen(black);
        Knight BlackKnight = new Knight(black);
        Rook BlackRook = new Rook(black);
        Pawn BlackPawn = new Pawn(black);

        Bishop WhiteBishop = new Bishop(white);
        King WhiteKing = new King(white);
        Queen WhiteQueen = new Queen(white);
        Knight WhiteKnight = new Knight(white);
        Rook WhiteRook = new Rook(white);
        Pawn WhitePawn = new Pawn(white);

        /**
         * filling board with pieces
         */
        for (int i = 0; i < 8; i++) {
            Squares[i][6] = new Square(BlackPawn, i, 6);
            Squares[i][1] = new Square(WhitePawn, i, 1);
        }

        Squares[a][0] = new Square(WhiteRook, 0, 0);
        Squares[h][0] = new Square(WhiteRook, 7, 0);
        Squares[a][7] = new Square(BlackRook, 0, 7);
        Squares[h][7] = new Square(BlackRook, 7, 7);

        Squares[b][0] = new Square(WhiteKnight, 1, 0);
        Squares[g][0] = new Square(WhiteKnight, 6, 0);
        Squares[b][7] = new Square(BlackKnight, 1, 7);
        Squares[g][7] = new Square(BlackKnight, 6, 7);

        Squares[c][0] = new Square(WhiteBishop, 2, 0);
        Squares[f][0] = new Square(WhiteBishop, 5, 0);
        Squares[c][7] = new Square(BlackBishop, 2, 7);
        Squares[f][7] = new Square(BlackBishop, 5, 7);

        Squares[d][0] = new Square(WhiteQueen, 3, 0);
        Squares[d][7] = new Square(BlackQueen, 3, 7);

        Squares[e][0] = new Square(WhiteKing, 4, 0);
        Squares[e][7] = new Square(BlackKing, 4, 7);

        /**
         * filling middle of the board with empty squares
         */
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                Squares[j][i] = new Square(j, i);
            }
        }
    }

    /**
     * constructor that creates empty board
     *
     */
    public Board(boolean TestBoard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Squares[j][i] = new Square(j, i);
            }
        }
    }

    public Square[][] getBoard() {
        return this.Squares;
    }

    public Square getSquare(int positionX, int positionY) {
        return this.Squares[positionX][positionY];
    }

    public void setPiece(int positionX, int positionY, Piece examplePiece) {
        Square newSquare = new Square(examplePiece, positionX, positionY);
        this.Squares[positionX][positionY] = newSquare;
    }

}
