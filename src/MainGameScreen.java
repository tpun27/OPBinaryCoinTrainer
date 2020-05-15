import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainGameScreen {
    public static String APP_TITLE = "OP Binary Coin Trainer";

    public static String[] MENU_INSTRUCTIONS = new String [] {
            "Welcome to the OP Binary Coin trainer!",
            "Please select from our menu options to start training!"
    };

    public static String[] MENU_BUTTON_NAMES = new String [] {
            "1) Double Observer vs. Double Decider",
            "2) Self above Tribe vs. Tribe above Self",
            "3) Organize over Gather vs. Gather over Organize",
            "4) Reasons over Values vs. Values over Reasons",
            "5) Abstract over Physical vs. Physical over Abstract",
            "6) Consume over Blast vs. Blast over Consume",
            "7) Sleep over Play vs. Play over Sleep",
            "8) Masculine Sensing vs. Feminine Sensing",
            "9) Masculine Extroverted Decider vs. Feminine Extroverted Decider"
    };

    public static String[][] ANSWER_BUTTON_NAMES = new String[][] {
            {"Double Observer (OO)", "Double Decider (DD)"},
            {"Self above Tribe", "Tribe above Self"},
            {"Organize over Gather", "Gather over Organize"},
            {"Reasons (T) over Values (F)", "Values (F) over Reasons (T)"},
            {"Abstract (N) over Physical (S)", "Physical (S) over Abstract (N)"},
            {"Consume over Blast", "Blast over Consume"},
            {"Sleep over Play", "Play over Sleep"},
            {"Masculine Sensing", "Feminine Sensing"},
            {"Masculine De", "Feminine De"}
    };

    public static List<JButton> menuButtons;

    public static void createAndShowGUI() {
        JFrame frame = new JFrame(APP_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 350);

        Container pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        addMainInstructions(pane);
        addMainButtons(pane);
        addMenuButtonHandlers(frame);

        frame.setVisible(true);
    }

    public static void addMainInstructions(Container pane) {
        for (String instruction : MainGameScreen.MENU_INSTRUCTIONS) {
            JLabel label = new JLabel(instruction, JLabel.CENTER);
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            pane.add(label);
        }
    }

    public static void addMainButtons(Container pane) {
        menuButtons = new ArrayList<>();
        for (String buttonName : MainGameScreen.MENU_BUTTON_NAMES) {
            JButton button = new JButton(buttonName);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            pane.add(button);

            menuButtons.add(button);
        }
    }

    public static void addMenuButtonHandlers(JFrame frame) {
        int count = 0;
        for (JButton menuButton : menuButtons) {
            // anonymous functions cannot use local variables unless they are final
            final int buttonNumber = count;
            menuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int action = JOptionPane.showConfirmDialog(
                            frame,
                            "Enter Easy/Progressive Mode?",
                            "",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    createGameFrame(buttonNumber, action);
                }
            });
            count++;
        }
    }

    public static void createGameFrame(int buttonNumber, int action) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame(MENU_BUTTON_NAMES[buttonNumber]);
                frame.setSize(600, 150);

                JPanel gamePanel = new JPanel();
                gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
                TitledBorder titledBorder = BorderFactory.createTitledBorder("Score: 7/7");
                gamePanel.setBorder(titledBorder);
//                gamePanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);

                JLabel nameLabel = new JLabel("Bill Gates");
                nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.BOLD, 40));

                JButton firstButton = new JButton(ANSWER_BUTTON_NAMES[buttonNumber][0]);
                JButton secondButton = new JButton(ANSWER_BUTTON_NAMES[buttonNumber][1]);

                firstButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                secondButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                firstButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nameLabel.setText("Barack Obama");
                        titledBorder.setTitle("Score: 8/8");
                        gamePanel.repaint();
                    }
                });

                secondButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                    }
                });

                gamePanel.add(nameLabel);
                gamePanel.add(firstButton);
                gamePanel.add(secondButton);

                frame.add(gamePanel);
                frame.setVisible(true);
            }
        });
    }
}
