import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int turn = 0;
    static int[] board = new int[9];
    static int player = 0;
    static int winner = 0;
    public static void main(String[] args) {
        new GameWindow();
        clearConsole();
        createPlayer();
        while (winner == 0){
            clearConsole();
            System.out.println("Info: Turn " + turn + " begins.");
            showBoard(board);
            makeTurn();
            checkWinner();
        }
    }
    public static void createPlayer(){
        for (int i = 1; i<3;i++){
            String playerName = "";
            while (playerName.isEmpty()) {
                System.out.println("Info: Give player " + i + " a name.");
                playerName = scanner.nextLine();
                if (playerName.isEmpty()) {
                    System.out.println("Error: you typed nothing!");
                }
            }
            Player.setPlayer(playerName);
        }
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
            System.out.println("Info: its your turn " + Player.getName(getActivePlayer()));
            System.out.println("Info: Select a slot with the numbers between 0-8.");
            int boardField = scanner.nextInt();
            if (boardField < 9 && boardField >= 0){
                if (board[boardField] != 0){
                    System.out.println("Error: " + boardField + " is already used. Try again!");
                    continue;
                }
                setNumberToField(boardField);
                break;
            }
            System.out.println("Error: " + boardField + " is no valid input! Try again!");
        }
        turn++;
    }
    public static void setNumberToField(int field){
        board[field] = getActivePlayer();
    }
    public static void checkWinner(){
        String message = "";
        String winnerMessage = "Info: The winner is ";
        String endMessage = "Info: All slots are used. There is no winner! :(";
        if (isWinner()){
            message = winnerMessage;
        }
        else if (allSlotsUsed()){
            winner = -1;
            message = endMessage;
        }
        System.out.println(message  + Player.getName(winner));
    }
    public static Boolean allSlotsUsed(){
        if (board[0] != 0 && board[1] != 0 && board[2] != 0 && board[3] != 0 && board[4] != 0 && board[5] != 0 && board[6] != 0 && board[7] != 0 && board[8] != 0){
            winner = -1;
            return true;
        }
        return false;
    }
    public static Boolean isWinner(){
        if (board[0] == board[1] && board[0] == board[2] && board[0] != 0){
            winner = board[0];
            return true;
        }
        else if (board[3] == board[4 ]&& board[3] == board[5] && board[3] != 0){
            winner = board[3];
            return true;
        }
        else if (board[6] == board[7] && board[6] == board[8] && board[6] != 0){
            winner = board[6];
            return true;
        }
        else if (board[0] == board[3] && board[0] == board[6] && board[0] != 0){
            winner = board[0];
            return true;
        }
        else if (board[1] == board[4] && board[1] == board[7] && board[1] != 0){
            winner = board[1];
            return true;
        }
        else if (board[2] == board[5] && board[2] == board[8] && board[2] != 0){
            winner = board[2];
            return true;
        }
        else if (board[0] == board[4] && board[0] == board[8] && board[0] != 0){
            winner = board[0];
            return true;
        }
        else if (board[2] == board[4] && board[2] == board[6] && board[2] != 0){
            winner = board[2];
            return true;
        }
        return false;
    }
    public static void clearConsole(){
        System.out.println("\n".repeat(20));
    }
}
