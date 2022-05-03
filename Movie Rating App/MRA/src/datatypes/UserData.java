package datatypes;

public class UserData {
    private String userName;
    private String email;
    private int age;

    public String getUserName() {
        return userName;
    }
    public void setName(String name) {
        this.userName = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public UserData(String userName, String email, int age) {
        super();
        this.userName = userName;
        this.email = email;
        this.age = age;
    } 

public UserData() {
    }
}
