package DaniilBacalear;

import java.util.ArrayList;

/**
 * Created by Daniil.Bacalear on 2017-10-31.
 */
public class Courses {
    private String courseName,courseCode;
    private int courseCapacity = 0;
    public Courses(String courseName, String courseCode){
        this.courseName = courseName;
        this.courseCode = courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public int getCourseCapacity() {
        return courseCapacity;
    }
    public void addCourseCapacity() {
        courseCapacity++;
    }
    public void subtractCourseCapacity() {
        courseCapacity--;
    }
    @Override
    public String toString() {
        return "Course Name: " + courseName + "     " + "Course Code: " + courseCode + "     ";
    }
}





