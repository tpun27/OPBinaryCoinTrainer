import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class GameScreens {
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

//    public static List<Integer> currentQuestionNumbers =
//            new ArrayList<>(Collections.nCopies(MENU_BUTTON_NAMES.length, 0));
//
//    public static List<List<String>> nameAndScorePairs = new ArrayList<List<String>>(MENU_BUTTON_NAMES.length) {{
//        for (int i = 0; i < MENU_BUTTON_NAMES.length; i++) {
//            nameAndScorePairs.add(new ArrayList<>(2));
//        }
//    }};

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
        for (String instruction : GameScreens.MENU_INSTRUCTIONS) {
            JLabel label = new JLabel(instruction, JLabel.CENTER);
            label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            pane.add(label);
        }
    }

    public static void addMainButtons(Container pane) {
        menuButtons = new ArrayList<>();
        for (String buttonName : GameScreens.MENU_BUTTON_NAMES) {
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

                    if (action == JOptionPane.CANCEL_OPTION)
                        return;

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
                List<Type> gameData = getGameData(buttonNumber, action);

                JFrame frame = new JFrame(MENU_BUTTON_NAMES[buttonNumber]);
                frame.setSize(600, 150);

                JPanel gamePanel = new JPanel();
                gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
                TitledBorder titledBorder = BorderFactory.createTitledBorder("Score:");
                gamePanel.setBorder(titledBorder);

                JLabel nameLabel = new JLabel("");
                nameLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
                nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.BOLD, 40));

                JButton firstButton = new JButton(ANSWER_BUTTON_NAMES[buttonNumber][0]);
                JButton secondButton = new JButton(ANSWER_BUTTON_NAMES[buttonNumber][1]);

                firstButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                secondButton.setAlignmentX(Component.CENTER_ALIGNMENT);

                final CumulativeGameInfo cumulativeGameInfo = new CumulativeGameInfo(gameData);
                cumulativeGameInfo.setName(gameData.get(0).getName());
                cumulativeGameInfo.setQuestionNumber(0);
                cumulativeGameInfo.setQuestionsCorrect(0);

                nameLabel.setText(cumulativeGameInfo.getName());
                titledBorder.setTitle(cumulativeGameInfo.generateScoreText());

                firstButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String oldName = cumulativeGameInfo.getName();
                        boolean gameOver = false;
                        boolean isCorrect = cumulativeGameInfo.isCorrect(true, buttonNumber);
                        try {
                            cumulativeGameInfo.updateGameInfo(true, buttonNumber);
                        } catch (IndexOutOfBoundsException ex) {
                            gameOver = true;
                        }

                        if (!gameOver) {
                            nameLabel.setText(cumulativeGameInfo.getName());
                            titledBorder.setTitle(cumulativeGameInfo.generateScoreText());
                            gamePanel.repaint();
                        }

                        String message = (isCorrect) ? "Correct Choice: " : "Incorrect Choice: ";
                        message += firstButton.getText();
                        message += ", for " + oldName;

                        JOptionPane.showMessageDialog(frame, message);

                        if (gameOver) {
                            JOptionPane.showMessageDialog(frame,
                                    "Game Over. Thanks for playing! " + cumulativeGameInfo.generateScoreText());
                            frame.dispose();
                        }
                    }
                });

                secondButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String oldName = cumulativeGameInfo.getName();
                        boolean gameOver = false;
                        boolean isCorrect = cumulativeGameInfo.isCorrect(false, buttonNumber);
                        try {
                            cumulativeGameInfo.updateGameInfo(false, buttonNumber);
                        } catch (IndexOutOfBoundsException ex) {
                            gameOver = true;
                        }

                        if (!gameOver) {
                            nameLabel.setText(cumulativeGameInfo.getName());
                            titledBorder.setTitle(cumulativeGameInfo.generateScoreText());
                            gamePanel.repaint();
                        }

                        String message = (isCorrect) ? "Correct Choice: " : "Incorrect Choice: ";
                        message += firstButton.getText();
                        message += ", for " + oldName;

                        JOptionPane.showMessageDialog(frame, message);

                        if (gameOver) {
                            JOptionPane.showMessageDialog(frame,
                                    "Game Over. Thanks for playing! " + cumulativeGameInfo.generateScoreText());
                            frame.dispose();
                        }
                    }
                });

                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        cumulativeGameInfo.generateResultsLog();
                        JOptionPane.showMessageDialog(frame, "Data Saved");
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

    public static List<Type> getGameData(int buttonNumber, int action) {
        if (action == JOptionPane.YES_OPTION) {
            return getProgressiveGameData(buttonNumber);
        }
        else if (action == JOptionPane.NO_OPTION) {
            return Application.generateShuffledAllTypesList();
        }
        return new ArrayList<>();
    }

    public static List<Type> getProgressiveGameData(int buttonNumber) {
        switch (buttonNumber) {
            case 0:
                // Progressive mode doesn't make sense for this option
                return Application.generateShuffledAllTypesList();
            case 1:
                return Application.generateDeciderThenObserverProgressiveList();
            case 2:
                return Application.generateObserverThenDeciderProgressiveList();
            case 3:
                return Application.generateDeciderThenObserverProgressiveList();
            case 4:
                return Application.generateObserverThenDeciderProgressiveList();
            case 5:
                return Application.shuffleTwoListsThenCombineIntoNewList(
                        Application.getConsumeOrBlastFirstList(),
                        Application.getSleepOrPlayFirstList()
                );
            case 6:
                return Application.shuffleTwoListsThenCombineIntoNewList(
                        Application.getSleepOrPlayFirstList(),
                        Application.getConsumeOrBlastFirstList()
                );
            case 7:
                return Application.generateSensingProgressiveList();
            case 8:
                return Application.generateExtrovertedDeciderProgressiveList();
            default:
                return new ArrayList<>();
        }
    }
}
