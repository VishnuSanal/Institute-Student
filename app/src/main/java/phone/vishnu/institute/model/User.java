package phone.vishnu.institute.model;

public class User {

    String userCourse;
    String userId;
    String userName;

    public User() {
    }

    public User(String userCourse, String userId, String userName, Message message) {
        this.userCourse = userCourse;
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserCourse() {
        return userCourse;
    }

    public void setUserCourse(String userCourse) {
        this.userCourse = userCourse;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}


