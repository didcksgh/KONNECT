//
//package coms309.people;
//
//public class Person {
//
//    private String firstName;
//    private String lastName;
//    private String address;
//    private String telephone;
//    private String gender;
//    private String eyeColor;
//    private String hobby; // Added hobby field
//    private String profession; // Added profession field
//
//    // Default constructor
//    public Person() {
//    }
//
//    // Constructor with all fields
//    public Person(String firstName, String lastName, String address, String telephone,
//                  String gender, String eyeColor, String hobby, String profession) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.telephone = telephone;
//        this.gender = gender;
//        this.eyeColor = eyeColor;
//        this.hobby = hobby;
//        this.profession = profession;
//    }
//
//    // Getters and setters
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getTelephone() {
//        return telephone;
//    }
//
//    public void setTelephone(String telephone) {
//        this.telephone = telephone;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getEyeColor() {
//        return eyeColor;
//    }
//
//    public void setEyeColor(String eyeColor) {
//        this.eyeColor = eyeColor;
//    }
//
//    public String getHobby() {
//        return hobby;
//    }
//
//    public void setHobby(String hobby) {
//        this.hobby = hobby;
//    }
//
//    public String getProfession() {
//        return profession;
//    }
//
//    public void setProfession(String profession) {
//        this.profession = profession;
//    }
//
//    @Override
//    public String toString() {
//        return "Person{" +
//                "firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", address='" + address + '\'' +
//                ", telephone='" + telephone + '\'' +
//                ", gender='" + gender + '\'' +
//                ", eyeColor='" + eyeColor + '\'' +
//                ", hobby='" + hobby + '\'' +
//                ", profession='" + profession + '\'' +
//                '}';
//    }
//}
package coms309.people;

public class Person {

    private String firstName;
    private String lastName;
    private String address;
    private String telephone;
    private String gender;
    private String eyeColor;
    private String hobby;
    private String profession;
    private int age; // Declare the age field

    // Default constructor
    public Person() {
    }

    // Constructor with all fields including age
    public Person(String firstName, String lastName, String address, String telephone,
                  String gender, String eyeColor, String hobby, String profession, int age) { // Add age parameter
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.gender = gender;
        this.eyeColor = eyeColor;
        this.hobby = hobby;
        this.profession = profession;
        this.age = age; // Initialize the age field
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getAge() { // Getter for age
        return age;
    }

    public void setAge(int age) { // Setter for age
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", gender='" + gender + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", hobby='" + hobby + '\'' +
                ", profession='" + profession + '\'' +
                ", age=" + age + // Include age in the toString output
                '}';
    }
}
