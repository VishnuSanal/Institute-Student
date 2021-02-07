package phone.vishnu.institute.model;

public class CourseApplication {

    String studentName;
    String courseInterested;
    String studentEmail;

    public CourseApplication(String studentName, String courseInterested, String studentEmail) {
        this.studentName = studentName;
        this.courseInterested = courseInterested;
        this.studentEmail = studentEmail;
    }

    public CourseApplication() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseInterested() {
        return courseInterested;
    }

    public void setCourseInterested(String courseInterested) {
        this.courseInterested = courseInterested;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
