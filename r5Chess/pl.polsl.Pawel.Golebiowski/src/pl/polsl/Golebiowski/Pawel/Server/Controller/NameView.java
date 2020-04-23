package pl.polsl.Golebiowski.Pawel.Server.Controller;

import java.util.Scanner;

import pl.polsl.Golebiowski.Pawel.Server.ModelBoard.Player;

/**
 * Class setting name of the players
 *
 * @author Pawcio version 1.0
 */

@Deprecated
public class NameView {

    /**
     * class saves players' name
     */
    private Player white, black;

    /**
     * First argument is saved to String FirstPlayer Second argument is saved to
     * String SecondPlayer
     *
     * @param args
     */
    public static void main(String[] args) {
        /*name of first player*/
        String FirstPlayer;
        /*name of second player*/
        String SecondPlayer;
        /*view of players name that is going to be using later*/
        NameView PlayerNameView = new NameView();
        /*checking if the user enter 2 players' name*/
        try {
            PlayerNameView.CheckNamesAmount(args.length);
            FirstPlayer = args[0];
            SecondPlayer = args[1];
        } catch (AmountOfArgumentsExeption e) {
            System.out.println("Chess requires two players!");
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter first player's name: ");
            FirstPlayer = scanner.next();
            System.out.println("Enter second player's name: ");
            SecondPlayer = scanner.next();
        }
        /*setting given names to view*/
        PlayerNameView.SetPlayersName(FirstPlayer, SecondPlayer);

        System.out.println("First player's name: " + FirstPlayer);
        System.out.println("Second player's name: " + SecondPlayer);
    }

    /**
     * Non-parameter constructor
     */
    public NameView() {
        this.white = new Player();
        this.black = new Player();
    }

    /**
     *
     * @param FirstPlayer is first player's name
     * @param SecondPlayer is second player's name
     */
    public void SetPlayersName(String FirstPlayer, String SecondPlayer) {
        this.white.SetName(FirstPlayer);
        this.black.SetName(SecondPlayer);
    }

    /**
     *
     * @param amonut is amount of arguments that is being used currently
     * @throws AmountOfArgumentsExeption when amount of arguments is diffrent
     * than 2
     */
    void CheckNamesAmount(int amonut) throws AmountOfArgumentsExeption {
        if (amonut != 2) {
            throw new AmountOfArgumentsExeption();
        }
    }
}
