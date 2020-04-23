/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.Golebiowski.Pawel.Server.ModelBoard;

/**
 *
 * @author Pawcio version 1.0
 */
@Deprecated
public class Player {

    private String name;

    /**
     * Non-parameter constructor
     */
    public Player() {
    }

    /**
     * method to set name of the player
     * @param NewName name of a player
     */
    public void SetName(String NewName) {
        this.name = NewName;
    }

}
