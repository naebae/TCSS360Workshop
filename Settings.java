/***
 * Created by Matthew Burgess
 */
public class Settings {
    private String firstName;
    private String emailAddress;

    public Settings(String firstName, String emailAddress) {
        this.firstName = firstName;
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "firstName='" + firstName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
