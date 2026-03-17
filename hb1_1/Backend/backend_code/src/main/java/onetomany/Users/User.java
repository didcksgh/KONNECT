
package onetomany.Users;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import onetomany.Group.Group;
import onetomany.Reports.Reports;
import onetomany.hobbies.Hobbies;
import onetomany.userLogIn.userLogin;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String emailId;
    private Date joiningDate;
    private Date birthday;
    private String gender;
    private boolean ifActive;



    private int age;
    private String UserPassword;
    private Date lastLoggin;
    public int appearences =1;
    @Column(unique = true)
    private String username;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_hobbies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "hobby_id")
    )
    private Set<Hobbies> hobbies = new HashSet<>();
    private String UserBio;
    private int viewCount= 1;
    private int acceptanceCount=1;
    @Lob
    private byte[] profileImage;
    @ElementCollection
    private List<Integer> UserHobbiesLists;




    @OneToMany(mappedBy = "user")
    List<Reports> userReports;



    @OneToOne(cascade = CascadeType.ALL)
    userLogin  userLogin;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    List<Group> userGropus;



    // =============================== Constructors ================================== //


    public User(String name, String emailId, String userPassword,String userName, Date birthday, int age, String gender  ) {
        this.name = name;
        this.emailId = emailId;
        this.joiningDate = new Date();
        this.ifActive = true;
        userReports = new ArrayList<>();
        this.UserPassword= userPassword;

        this.username = userName;
        this.age= age;
        this.lastLoggin=new Date();
        this.gender=gender;
        this.birthday= birthday;
        this.UserBio=UserBio;

    }

    public User() {
        userReports = new ArrayList<>();
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId(){
        return emailId;
    }

    public void setEmailId(String emailId){
        this.emailId = emailId;
    }

    public Date getJoiningDate(){
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate){
        this.joiningDate = joiningDate;
    }




    public boolean isIfActive() {
        return ifActive;
    }

    public void setIfActive(boolean ifActive){
        this.ifActive = ifActive;
    }

    public String getUserPassword(){
        return this.UserPassword;
    }

    public void setUserPassword(String userPassword) {
        this.UserPassword = userPassword;
    }

    public void setLastLoggin(){
        lastLoggin= new Date();
    }

    @JsonIgnore
    public Set<Hobbies> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobbies> hobbies) {
        this.hobbies = hobbies;
    }

    public void setUserReports(Reports report){
        userReports.add(report);
    }

    @JsonIgnore
    public List<Reports> getReports() {
        return userReports;
    }

    public void setReports(List<Reports> reports) {
        this.userReports = reports;
    }

    public void addReport(Reports report){
        this.userReports.add(report);
    }



    public Date getLastLoggin(){
        return this.lastLoggin;
    }

    public void setUserLogin(userLogin userLogin) {
        this.userLogin = userLogin;
    }

    public int getAge() {
        return age;
    }

    public int getViewCount() {
        return viewCount;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserBio() {
        return UserBio;
    }

    public void setUserBio(String userBio) {
        UserBio = userBio;
    }
    public void addHobbie(Hobbies hobbie){
        this.hobbies.add(hobbie);
    }
    public void addCount(){
        this.viewCount++;
    }
    public int getRate(){
        if(this.acceptanceCount ==0 || this.viewCount ==0 )
            return 1;
        return this.acceptanceCount/this.viewCount;
    }



    @JsonIgnore
    public List<User> getMatches(){
        List<Hobbies> list = new ArrayList<>();
        List<User> list1= new ArrayList<>();
        for (Hobbies hobbie: this.hobbies) {
            for (User user: hobbie.getUsers()) {
                if(user.getUsername() != this.username) {
                    user.addCount();
                    if (!list1.contains(user))
                        list1.add(user);
                    else {
                        int temp = list1.indexOf(user);
                        list1.get(temp).appearences++;
                    }
                }
            }
        }

        Collections.sort(list1, new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                return Integer.compare(u2.appearences, u1.appearences);
            }
        });

        for(int i=0; i< list1.size()-1; i++){
            if(list1.get(i).appearences == list1.get(i+1).appearences  && list1.get(i).getRate() < list1.get(i+1).getRate()){
                User temp= list1.get(i);
                list1.set(i, list1.get(i+1));
                list1.set(i+1, temp);
            }
        }


        return list1;
    }
    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
    public void removeReport(Reports reports){
        this.userReports.remove(reports);
    }

    public void removeHobbie(Hobbies hobbie){
        this.hobbies.remove(hobbie);
    }

    public void addGroup(Group group){
        this.userGropus.add(group);
    }

    public User getUser(User user){
        User temp = new User();
        return temp;
    }

    public List<Group> getUserGropus() {
        return userGropus;
    }

    public onetomany.userLogIn.userLogin getUserLogin() {
        return userLogin;
    }
    public void removeGroup(Group group){
        this.userGropus.remove(group);
    }
}
