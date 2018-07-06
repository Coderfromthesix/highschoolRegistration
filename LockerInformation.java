package DaniilBacalear;

/**
 * Created by Daniil.Bacalear on 2017-10-31.
 */
public class LockerInformation {
    private String lockerCombination,lockerNumber;
    public LockerInformation(){
        this.lockerCombination = "Empty";
        this.lockerNumber = "Empty";
    }
    public LockerInformation(String lockerNumber, String lockerCombination){
        this.lockerNumber = lockerNumber;
        this.lockerCombination = lockerCombination;
    }

    public String getLockerCombination() {
        return lockerCombination;
    }

    public void setLockerCombination(String lockerCombination) {
        this.lockerCombination = lockerCombination;
    }

    public String getLockerNumber() {
        return lockerNumber;
    }

    public void setLockerNumber(String lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    @Override
    public String toString() {
        return "Locker Number: " + getLockerNumber() + "     "+ "Locker Combination: " + getLockerCombination()+ "     ";
    }
}
