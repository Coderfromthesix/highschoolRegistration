package DaniilBacalear;

/**
 * Created by Daniil.Bacalear on 2017-10-31.
 */
public class PersonalInformation {
    private String firstName,lastName,address;
    public PersonalInformation(String firstName,String lastName,String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
    public PersonalInformation(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = "Empty";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return ("First Name: " + firstName +  "     " + " Last Name: " + lastName + "     " +" Address: " + address + "     ");
    }
}
