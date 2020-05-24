import java.util.List;

public class CumulativeGameInfo {
    String name;
    int questionNumber;
    int questionsCorrect;
    List<Type> gameData;

    CumulativeGameInfo(List<Type> gameData) {
        this.gameData = gameData;
    }

    public String generateScoreText() {
        return "Score: " + Integer.toString(questionsCorrect) + "/" + Integer.toString(questionNumber);
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

    public boolean updateGameInfo(boolean buttonChoice, int buttonNumber) {
        boolean isCorrect = isCorrect(buttonChoice, buttonNumber);
        if (isCorrect)
            questionsCorrect++;

        questionNumber++;
        this.name = gameData.get(questionNumber).getName();

        return isCorrect;
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
}
