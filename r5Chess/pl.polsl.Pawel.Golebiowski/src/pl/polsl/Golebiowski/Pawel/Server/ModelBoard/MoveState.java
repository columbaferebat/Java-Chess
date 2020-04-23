/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.Golebiowski.Pawel.Server.ModelBoard;

import java.util.Scanner;
import pl.polsl.Golebiowski.Pawel.Server.Controller.PositionNotFoundExeption;

/**
 * class to check if the player give correct co-ordinates
 *
 * @author Pawcio
 * @version 1.2
 */
public class MoveState {

    /**
     * positions of the piece and destination where you want it to go
     */
    private int positionX, positionY, destinationX, destinationY;
    /**
     * scanner is need to get co-ordinates from user
     */
    private final Scanner scanner;

    /**
     * check if user enter co-ordinates consisting of 4 characters
     *
     * @param isWhiteToMove says which players is up to move
     * @param moveSource string user gave to make a move
     * @return true or false
     */
    public boolean checkMoveSource4char(boolean isWhiteToMove, String moveSource) {

        if (moveSource.length() != 4) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * check if the user enter correct co-ordinates and print it
     *
     * @param moveSource String given to be checked by method
     * @return true if String is correct, false if not
     */
    public boolean checkMoveSourcePosition(String moveSource) {
        char signs[] = moveSource.toCharArray();
        try {
            positionX = LetterToPosition(signs[0]);
            positionY = NumberToPosition(signs[1]);
            destinationX = LetterToPosition(signs[2]);
            destinationY = NumberToPosition(signs[3]);
            return true;
        } catch (PositionNotFoundExeption e) {

            return false;
        }
    }

    /**
     * public constructor
     */
    public MoveState() {
        this.scanner = new Scanner(System.in);
    }

    private static int LetterToPosition(char sign) throws PositionNotFoundExeption {
        if (sign == 'a' || sign == 'A') {
            return 0;
        }
        if (sign == 'b' || sign == 'B') {
            return 1;
        }
        if (sign == 'c' || sign == 'C') {
            return 2;
        }
        if (sign == 'd' || sign == 'D') {
            return 3;
        }
        if (sign == 'e' || sign == 'E') {
            return 4;
        }
        if (sign == 'f' || sign == 'F') {
            return 5;
        }
        if (sign == 'g' || sign == 'G') {
            return 6;
        }
        if (sign == 'h' || sign == 'H') {
            return 7;
        } else {
            throw new PositionNotFoundExeption();
        }
    }

    private static int NumberToPosition(char sign) throws PositionNotFoundExeption {
        if (sign == '1') {
            return 0;
        }
        if (sign == '2') {
            return 1;
        }
        if (sign == '3') {
            return 2;
        }
        if (sign == '4') {
            return 3;
        }
        if (sign == '5') {
            return 4;
        }
        if (sign == '6') {
            return 5;
        }
        if (sign == '7') {
            return 6;
        }
        if (sign == '8') {
            return 7;
        } else {
            throw new PositionNotFoundExeption();
        }

    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public int getDestinationX() {
        return this.destinationX;
    }

    public int getDestinationY() {
        return this.destinationY;
    }
}
