import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    // Variables
    // ------------------------------------------------------------

    static char[] board = {'1','2','3','4','5','6','7','8','9'};

    static char playerx = 'X';
    static char playero = 'O';
    static char currentplayer;

    static int gamemode;


    // Print Board
    // ------------------------------------------------------------

    public static void printboard() {

        System.out.println();
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2]);
        System.out.println("---+---+---");
        System.out.println(" " + board[3] + " | " + board[4] + " | " + board[5]);
        System.out.println("---+---+---");
        System.out.println(" " + board[6] + " | " + board[7] + " | " + board[8]);
        System.out.println();
    }


    // Player Move
    // ------------------------------------------------------------

    public static int playermove(Scanner input) {

        int move;

        while (true) {

            System.out.println("Choose an available spot:");
            System.out.println("---------------------------");

            if (input.hasNextInt()) {

                move = input.nextInt();
                System.out.println("---------------------------");

                if (move >= 1 && move <= 9) {

                    if (board[move - 1] != playerx && board[move - 1] != playero) {
                        return move;
                    }
                    else {
                        System.out.println("That spot is already taken. Try again.");
                    }
                }
                else {
                    System.out.println("Enter a number between 1 and 9.");
                }
            }
            else {
                System.out.println("Enter a number only.");
                input.next();
                System.out.println("---------------------------");
            }
        }
    }


    // CPU Move
    // ------------------------------------------------------------

    public static int cpumove() {

        Random random = new Random();

        int move;

        while (true) {

            move = random.nextInt(9) + 1;

            if (board[move - 1] != playerx && board[move - 1] != playero) {
                return move;
            }
        }
    }


    // Switch Player
    // ------------------------------------------------------------

    public static void switchplayer() {

        if (currentplayer == playerx) {
            currentplayer = playero;
        }
        else {
            currentplayer = playerx;
        }
    }


    // Check Winner
    // ------------------------------------------------------------

    public static boolean checkwinner() {

        if (board[0] == currentplayer && board[1] == currentplayer && board[2] == currentplayer) {
            return true;
        }
        if (board[3] == currentplayer && board[4] == currentplayer && board[5] == currentplayer) {
            return true;
        }
        if (board[6] == currentplayer && board[7] == currentplayer && board[8] == currentplayer) {
            return true;
        }
        if (board[0] == currentplayer && board[3] == currentplayer && board[6] == currentplayer) {
            return true;
        }
        if (board[1] == currentplayer && board[4] == currentplayer && board[7] == currentplayer) {
            return true;
        }
        if (board[2] == currentplayer && board[5] == currentplayer && board[8] == currentplayer) {
            return true;
        }
        if (board[0] == currentplayer && board[4] == currentplayer && board[8] == currentplayer) {
            return true;
        }
        if (board[2] == currentplayer && board[4] == currentplayer && board[6] == currentplayer) {
            return true;
        }

        return false;
    }


    // Check Tie
    // ------------------------------------------------------------

    public static boolean checktie() {

        for (int i = 0; i < board.length; i++) {

            if (board[i] != playerx && board[i] != playero) {
                return false;
            }
        }

        return true;
    }


    // Reset Board
    // ------------------------------------------------------------

    public static void resetboard() {

        board[0] = '1';
        board[1] = '2';
        board[2] = '3';
        board[3] = '4';
        board[4] = '5';
        board[5] = '6';
        board[6] = '7';
        board[7] = '8';
        board[8] = '9';

        currentplayer = playerx;
    }


    // Instruction
    // ------------------------------------------------------------

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Hello, welcome to TicTacToe.");
        System.out.println("Would you like to play with a friend or the CPU?");
        System.out.println("Type 1 for friend and 2 for CPU.");
        System.out.println("---------------------------");

        gamemode = input.nextInt();
        System.out.println("---------------------------");

        while (gamemode != 1 && gamemode != 2) {
            System.out.println("Type 1 for friend and 2 for CPU.");
            System.out.println("---------------------------");

            gamemode = input.nextInt();
            System.out.println("---------------------------");
        }

        System.out.println("To select a position, enter the matching number.");
        System.out.println();

        System.out.println(" Top Left = 1  | Top Mid = 2  | Top Right = 3");
        System.out.println("---------------+--------------+----------------");
        System.out.println(" Mid Left = 4  | Mid = 5      | Mid Right = 6");
        System.out.println("---------------+--------------+----------------");
        System.out.println(" Bot Left = 7  | Bot Mid = 8  | Bot Right = 9");

        System.out.println();
        System.out.println("Type 0 to begin:");
        System.out.println("---------------------------");

        int start = input.nextInt();
        System.out.println("---------------------------");

        while (start != 0) {
            System.out.println("Type 0 to begin:");
            System.out.println("---------------------------");

            start = input.nextInt();
            System.out.println("---------------------------");
        }


        // Play Again Loop
        // ------------------------------------------------------------

        int playagain = 1;

        while (playagain == 1) {

            resetboard();

            boolean gameover = false;


            // Game Loop
            // ------------------------------------------------------------

            while (!gameover) {

                System.out.println("Player " + currentplayer + " Turn");
                printboard();

                int move;

                if (gamemode == 2 && currentplayer == playero) {
                    System.out.println("Choose an available spot:");
                    System.out.println("---------------------------");

                    move = cpumove();
                    System.out.println(move);
                    System.out.println("---------------------------");
                }
                else {
                    move = playermove(input);
                }

                board[move - 1] = currentplayer;


                // Winner Check
                // ------------------------------------------------------------

                if (checkwinner()) {
                    System.out.println();
                    printboard();

                    System.out.println("Player " + currentplayer + " Wins");

                    if (currentplayer == playerx) {
                        System.out.println("Player " + playero + " Loses");
                    }
                    else {
                        System.out.println("Player " + playerx + " Loses");
                    }

                    gameover = true;
                }


                // Tie Check
                // ------------------------------------------------------------

                else if (checktie()) {
                    System.out.println();
                    printboard();
                    System.out.println("The game is a tie.");

                    gameover = true;
                }


                // Switch Player
                // ------------------------------------------------------------

                else {
                    switchplayer();
                }
            }


            // Play Again
            // ------------------------------------------------------------

            System.out.println();
            System.out.println("Would you like to play again?");
            System.out.println("Type 1 for yes and 2 for no.");
            System.out.println("---------------------------");

            playagain = input.nextInt();
            System.out.println("---------------------------");

            while (playagain != 1 && playagain != 2) {
                System.out.println("Type 1 for yes and 2 for no.");
                System.out.println("---------------------------");

                playagain = input.nextInt();
                System.out.println("---------------------------");
            }
        }


        // End
        // ------------------------------------------------------------

        System.out.println();
        System.out.println("Thank you for playing.");

        input.close();
    }
}
