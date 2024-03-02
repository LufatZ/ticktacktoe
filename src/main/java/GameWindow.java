import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow implements ActionListener {
    int nextNewPlayer = 1;
    JButton button;
    JButton[] buttons = new JButton[Main.board.length];
    JFrame window = new JFrame();
    JPanel gamePanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JLabel welcome = new JLabel("Welcome to LufatZs TicTacToe!");
    JPanel createPlayerPanel = new JPanel();
    JLabel errorNoName = new JLabel("You typed nothing!");
    JLabel winnerLabel = new JLabel();
    JLabel playerNameText = new JLabel("Choose a name for player " + nextNewPlayer);
    JTextField playerName = new JTextField();
    JButton ok = new JButton("OK");

    public GameWindow() {
        window.setTitle("Tic Tac Toe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.add(gamePanel);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(welcome, BorderLayout.NORTH);
        createPlayerGui();

        window.pack();
        window.setVisible(true);
    }
    public void createPlayerGui(){
        gamePanel.add(createPlayerPanel, BorderLayout.CENTER);
        createPlayerPanel.setLayout(new BorderLayout());
        createPlayerPanel.add(playerNameText,BorderLayout.NORTH);
        createPlayerPanel.add(playerName,BorderLayout.SOUTH);
        gamePanel.add(ok,BorderLayout.SOUTH);
        ok.addActionListener(this);

    }
    public void BoardGui() {
        gamePanel.add(boardPanel);
        boardPanel.setLayout(new GridLayout(3,3));
        for (int i = 0; i <buttons.length; i++) {
            button = new JButton(String.valueOf(Main.board[i]));
            buttons[i] = button;
            boardPanel.add(button);
            button.addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.ok){
            if (playerName.getText().isEmpty()){
                createPlayerPanel.add(errorNoName, BorderLayout.CENTER);
            }else {
                Player.setPlayer(playerName.getText());
                playerName.setText("");
                nextNewPlayer++;
                if (nextNewPlayer == 3) {
                    gamePanel.remove(createPlayerPanel);
                    gamePanel.remove(ok);
                    BoardGui();
                }
            }
            playerNameText.setText("Choose a name for player " + nextNewPlayer);
        }
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                buttons[i].setBackground(Player.getPlayerColor(Main.getActivePlayer()));
                Main.setNumberToField(i);
                Main.turn++;
                Main.checkWinner();
                if (Main.isWinner() || Main.allSlotsUsed()){
                    if (Main.isWinner()){
                        winnerLabel.setText("The winner is " + Player.getName(Main.winner));
                    }else {
                        winnerLabel.setText("There is no winner. All Slots are already used. Please start a new game!");
                    }
                    gamePanel.remove(boardPanel);
                    gamePanel.add(winnerLabel);
                }
                break;
            }
        }
        refreshBoard();
    }
    public  void refreshBoard(){
        gamePanel.revalidate();
        gamePanel.repaint();
    }
}
