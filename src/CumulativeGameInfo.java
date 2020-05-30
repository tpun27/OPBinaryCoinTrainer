import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CumulativeGameInfo {
    String gameName;
    String name;
    int questionNumber;
    int questionsCorrect;
    List<Type> gameData;
    String choiceOneLabel;
    String choiceTwoLabel;
    List<Boolean> answersSelected;
    List<Boolean> correctAnswers;

    CumulativeGameInfo(List<Type> gameData) {
        this.gameData = gameData;
        answersSelected = new ArrayList<>(gameData.size());
        correctAnswers = new ArrayList<>(gameData.size());
    }

    public String generateScoreText() {
        return "Score: " + Integer.toString(questionsCorrect) + "/" + Integer.toString(questionNumber);
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getQuestionsCorrect() {
        return questionsCorrect;
    }

    public void setQuestionsCorrect(int questionsCorrect) {
        this.questionsCorrect = questionsCorrect;
    }

    public void setChoiceOneLabel(String choiceOneLabel) {
        this.choiceOneLabel = choiceOneLabel;
    }

    public void setChoiceTwoLabel(String choiceTwoLabel) {
        this.choiceTwoLabel = choiceTwoLabel;
    }

    public void updateGameInfo(boolean buttonChoice, int buttonNumber) {
        answersSelected.add(buttonChoice);
        boolean isCorrect = isCorrect(buttonChoice, buttonNumber);
        if (isCorrect)
            questionsCorrect++;

        boolean correctAnswer = isCorrect ? buttonChoice : !buttonChoice;
        correctAnswers.add(correctAnswer);

        questionNumber++;
        this.name = gameData.get(questionNumber).getName();
    }

    public boolean isCorrect(boolean buttonChoice, int buttonNumber) {
        Type type = gameData.get(questionNumber);

        switch (buttonNumber) {
            case 0:
                return type.isSingleDecider() == buttonChoice;
            case 1:
                return type.isSelfAboveTribe() == buttonChoice;
            case 2:
                return type.isOrganizeAboveGather() == buttonChoice;
            case 3:
                return type.isReasonAboveValue() == buttonChoice;
            case 4:
                return type.isAbstractOverPhysical() == buttonChoice;
            case 5:
                return type.isConsumeOverBlast() == buttonChoice;
            case 6:
                return type.isSleepOverPlay() == buttonChoice;
            case 7:
                return type.isMasculineSensing() == buttonChoice;
            case 8:
                return type.isMasculineExtrovertedDecider() == buttonChoice;
            default:
                return false;
        }
    }

    public void generateResultsLog() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter(timeStamp + ".csv");

            csvWriter.append(gameName);
            csvWriter.append("\n");
            csvWriter.append(generateScoreText());
            csvWriter.append("\n");

            csvWriter.append("Name,");
            csvWriter.append("512_Type,");
            csvWriter.append("MBTI_Type,");
            csvWriter.append("Your Choice,");
            csvWriter.append("Correct Choice,");
            csvWriter.append("Answered Correctly");
            csvWriter.append("\n");

            int count = 0;
            Type type;
            for (Boolean choice : answersSelected) {
                type = gameData.get(count);

                csvWriter.append(type.getName());
                csvWriter.append(",");
                csvWriter.append(type.getType512String());
                csvWriter.append(",");
                csvWriter.append(type.getTypeMbtiString());
                csvWriter.append(",");
                csvWriter.append(answersSelected.get(count) ? choiceOneLabel : choiceTwoLabel);
                csvWriter.append(",");
                csvWriter.append(correctAnswers.get(count) ? choiceOneLabel : choiceTwoLabel);
                csvWriter.append(",");
                csvWriter.append(answersSelected.get(count) == correctAnswers.get(count) ?
                        "YES" : "NO");
                csvWriter.append("\n");
                count++;
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
