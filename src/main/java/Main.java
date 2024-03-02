import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int turn = 0;
    static int[] board = new int[9];
    static int player = 0;
    static int winner = 0;
    public static void main(String[] args) {
        clearConsole();
        createPlayer();
        while (winner == 0){
            clearConsole();
            System.out.println("Info: Turn "+turn+" begins.");
            showBoard(board);
            makeTurn();
            getWinner();
        }
    }
    public static void createPlayer(){
        System.out.println("Info: Give player 1 a name.");
        String playerNameA = scanner.nextLine();
        Player playerA = new Player(playerNameA);
        System.out.println("Info: Give player 2 a name.");
        String playerNameB = scanner.nextLine();
        Player playerB = new Player(playerNameB);
    }
    public static void showBoard(int[] board){
        System.out.println("_".repeat(13));
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("_".repeat(13));
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("_".repeat(13));
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("_".repeat(13));
    }
    public static int getActivePlayer(){
        player = turn % 2 +1;
        return player;
    }
    public static void makeTurn(){
        while (true){
            System.out.println("Info: its your turn " + Player.getName(getActivePlayer())); //ToDo get Playername
            System.out.println("Info: Select a slot with the numbers between 0-8.");
            int boardField = scanner.nextInt();
            if (boardField < 9 && boardField >= 0){
                if (board[boardField] != 0){
                    System.out.println("Error: " + boardField + " is already used. Try again!");
                    continue;
                }
                board[boardField] = getActivePlayer();
                break;
            }
            System.out.println("Error: " + boardField + " is no valid input! Try again!");
        }
        turn++;
    }
    public static void getWinner(){
        String message = "";
        String winnerMessage = "Info: The winner is ";
        String endMessage = "Info: All slots are used. There is no winner! :(";
        if (board[0] == board[1] && board[0] == board[2]){
            winner = board[0];
            message = winnerMessage;
        }
        else if (board[3] == board[4 ]&& board[3] == board[5]){
            winner = board[3];
            message = winnerMessage;
        }
        else if (board[6] == board[7] && board[6] == board[8]){
            winner = board[6];
            message = winnerMessage;
        }
        else if (board[0] == board[3] && board[0] == board[6]){
            winner = board[0];
            message = winnerMessage;
        }
        else if (board[1] == board[4] && board[1] == board[7]){
            winner = board[1];
            message = winnerMessage;
        }
        else if (board[2] == board[5] && board[2] == board[8]){
            winner = board[2];
            message = winnerMessage;
        }
        else if (board[0] == board[4] && board[0] == board[8]){
            winner = board[0];
            message = winnerMessage;
        }
        else if (board[2] == board[4] && board[2] == board[6]){
            winner = board[2];
            message = winnerMessage;
        }
        else if (board[0] != 0 && board[1] != 0 && board[2] != 0 && board[3] != 0 && board[4] != 0 && board[5] != 0 && board[6] != 0 && board[7] != 0 && board[8] != 0){
            winner = -1;
            message = endMessage;
        }
        System.out.println(message  + Player.getName(winner));
    }
    public static void clearConsole(){
        System.out.println("\n".repeat(20));
    }
}
