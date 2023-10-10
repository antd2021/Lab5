import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {

    private JPanel buttonPanel;
    private JPanel statsPanel;
    private JTextArea resultsTextArea;
    private int playerWins = 0;
    private int computerWins = 0;
    private int ties = 0;

    public RockPaperScissorsFrame() {
        setTitle("Rock Paper Scissors Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createGUI();

        setVisible(true);
    }

    private void createGUI() {
        // Title
        JLabel titleLabel = new JLabel("Rock Paper Scissors Game");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Choose One:"));
        buttonPanel.setLayout(new GridLayout(1, 3));

        ImageIcon rockPic = new ImageIcon("Rock.png");
        ImageIcon paperPic = new ImageIcon("Paper.png");
        ImageIcon scissorsPic = new ImageIcon("Scissors.png");
        //Creating the quit button now but putting it at the end

        JButton rockButton = new JButton("Rock", rockPic);
        JButton paperButton = new JButton("Paper", paperPic);
        JButton scissorsButton = new JButton("Scissors", scissorsPic);
        JButton quitButton = new JButton("Quit");
        //adding the buttons to the frame
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);


        add(buttonPanel, BorderLayout.CENTER);

        // Stats Panel
        statsPanel = new JPanel();
        statsPanel.setBorder(BorderFactory.createTitledBorder("Stats"));
        statsPanel.setLayout(new GridLayout(4, 2));
        //Player win stats
        JLabel playerWinsLabel = new JLabel("Player Wins-");
        JTextField playerWinsField = new JTextField();
        playerWinsField.setEditable(false);
        playerWinsField.setText(String.valueOf(playerWins));
        //Comp Loss Stats
        JLabel computerWinsLabel = new JLabel("Computer Wins-");
        JTextField computerWinsField = new JTextField();
        computerWinsField.setEditable(false);
        computerWinsField.setText(String.valueOf(computerWins));
        //Ties stats
        JLabel tiesLabel = new JLabel("Ties-");
        JTextField tiesField = new JTextField();
        tiesField.setEditable(false);
        tiesField.setText(String.valueOf(ties));

        statsPanel.add(playerWinsLabel);
        statsPanel.add(playerWinsField);
        statsPanel.add(computerWinsLabel);
        statsPanel.add(computerWinsField);
        statsPanel.add(tiesLabel);
        statsPanel.add(tiesField);
        statsPanel.add(quitButton);

        add(statsPanel, BorderLayout.SOUTH);

        // Results Text Area with ScrollPane
        resultsTextArea = new JTextArea(10, 25);
        resultsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsTextArea);
        add(scrollPane, BorderLayout.EAST);

        // Button Listeners
        rockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playGame("Rock");
            }
        });

        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playGame("Paper");
            }
        });

        scissorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playGame("Scissors");
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void playGame(String playerChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        int computerChoiceIndex = random.nextInt(choices.length);
        String computerChoice = choices[computerChoiceIndex];

        String result = determineWinner(playerChoice, computerChoice);
        resultsTextArea.append(result + "\n");

        updateStats(result);
    }

    private String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            ties++;
            return "Tie: " + playerChoice + " vs. " + computerChoice;
        } else if (
                (playerChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                        (playerChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                        (playerChoice.equals("Scissors") && computerChoice.equals("Paper"))
        ) {
            playerWins++;
            return "Player Wins: " + playerChoice + " beats " + computerChoice;
        } else {
            computerWins++;
            return "Computer Wins: " + computerChoice + " beats " + playerChoice;
        }
    }

    private void updateStats(String result) {
        ((JTextField) statsPanel.getComponent(1)).setText(String.valueOf(playerWins));
        ((JTextField) statsPanel.getComponent(3)).setText(String.valueOf(computerWins));
        ((JTextField) statsPanel.getComponent(5)).setText(String.valueOf(ties));
    }


}