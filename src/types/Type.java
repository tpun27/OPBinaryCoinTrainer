package types;

public abstract class Type {
    String name;
    String type;

    String[] saviorFunctions = new String[2];
    String[] demonFunctions = new String[2];
    char[] saviorAnimals = new char[2];
    char[] demonAnimals = new char[2];

    boolean singleDecider;
    boolean selfAboveTribe;
    boolean organizeAboveGather;
    boolean reasonAboveValue;
    boolean abstractOverPhysical;
    boolean consumeOverBlast;
    boolean sleepOverPlay;
    boolean masculineSensing;
    boolean masculineExtrovertedDecider;

    Type(String name, String type) {
        this.name = name;
        this.type = type;
        TypeParser.parseRawType(this);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String[] getSaviorFunctions() {
        return saviorFunctions;
    }

    public String[] getDemonFunctions() {
        return demonFunctions;
    }

    public char[] getSaviorAnimals() {
        return saviorAnimals;
    }

    public char[] getDemonAnimals() {
        return demonAnimals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSingleDecider() {
        return singleDecider;
    }

    public void setSingleDecider(boolean singleDecider) {
        this.singleDecider = singleDecider;
    }

    public boolean isSelfAboveTribe() {
        return selfAboveTribe;
    }

    public void setSelfAboveTribe(boolean selfAboveTribe) {
        this.selfAboveTribe = selfAboveTribe;
    }

    public boolean isOrganizeAboveGather() {
        return organizeAboveGather;
    }

    public void setOrganizeAboveGather(boolean organizeAboveGather) {
        this.organizeAboveGather = organizeAboveGather;
    }

    public boolean isReasonAboveValue() {
        return reasonAboveValue;
    }

    public void setReasonAboveValue(boolean reasonAboveValue) {
        this.reasonAboveValue = reasonAboveValue;
    }

    public boolean isAbstractOverPhysical() {
        return abstractOverPhysical;
    }

    public void setAbstractOverPhysical(boolean abstractOverPhysical) {
        this.abstractOverPhysical = abstractOverPhysical;
    }

    public boolean isConsumeOverBlast() {
        return consumeOverBlast;
    }

    public void setConsumeOverBlast(boolean consumeOverBlast) {
        this.consumeOverBlast = consumeOverBlast;
    }

    public boolean isSleepOverPlay() {
        return sleepOverPlay;
    }

    public void setSleepOverPlay(boolean sleepOverPlay) {
        this.sleepOverPlay = sleepOverPlay;
    }

    public boolean isMasculineSensing() {
        return masculineSensing;
    }

    public void setMasculineSensing(boolean masculineSensing) {
        this.masculineSensing = masculineSensing;
    }

    public boolean isMasculineExtrovertedDecider() {
        return masculineExtrovertedDecider;
    }

    public void setMasculineExtrovertedDecider(boolean masculineExtrovertedDecider) {
        this.masculineExtrovertedDecider = masculineExtrovertedDecider;
    }
}
