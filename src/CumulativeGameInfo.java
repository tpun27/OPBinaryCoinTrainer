public class CumulativeGameInfo {
    String name;
    int questionNumber;
    int questionsCorrect;
    int questionTotal;

    public String generateScoreText() {
        return "Score: " + Integer.toString(questionsCorrect) + "/" + Integer.toString(questionTotal);
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

    public int getQuestionTotal() {
        return questionTotal;
    }

    public void setQuestionTotal(int questionTotal) {
        this.questionTotal = questionTotal;
    }
}
