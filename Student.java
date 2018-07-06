package DaniilBacalear;

import java.util.ArrayList;

/**
 * Created by Daniil.Bacalear on 2017-10-31.
 */
public class Student {
    private int age,grade,studentNumber;
    private PersonalInformation personalInfo;
    private LockerInformation lockerInfo;
    private ArrayList<Courses> studentCourseList;



    public Student(int age, int studentNumber, int grade, PersonalInformation personalInfo, LockerInformation lockerInfo, ArrayList<Courses> studentCourseList) {
        this.age = age;
        this.studentNumber = studentNumber;
        this.grade = grade;
        this.personalInfo = personalInfo;
        this.lockerInfo = lockerInfo;
        this.studentCourseList = studentCourseList;
    }

    public PersonalInformation getPersonalInfo() {
        return personalInfo;
    }



    public ArrayList<Courses> getStudentCourseList2 (){
        return studentCourseList;
    }

    public void removeCourse(String courseCode){
        for(int i = 0;i<studentCourseList.size();i++){
            if(studentCourseList.get(i).getCourseCode().equalsIgnoreCase(courseCode)){
                studentCourseList.remove(i);
                break;
            }
        }
    }

    public void addCourse (Courses newCourse){
        studentCourseList.add(newCourse);
    }

    public String getStudentCourseList() {
        String courseList = "";
        int counter = 0;
        for(Courses element:studentCourseList){
            counter ++;
            courseList += " Course Name " + counter + ": " + element.getCourseName().toString() + "     " + " Course Code " + counter +": " + element.getCourseCode().toString() + "     ";
        }
        return courseList;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrade() {

        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }


    public LockerInformation getLockerInfo (){
        return lockerInfo;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    @Override
    public String toString() {
       String outGrade = Integer.toString(getGrade());
       String outAge = Integer.toString(getAge());
       String outStudentNumber = Integer.toString(studentNumber);
        return getPersonalInfo() + "Grade: " + outGrade +  "     "  + " Age: "  + outAge + "     " + getStudentCourseList() + getLockerInfo()+ " Student Number: " + outStudentNumber + "     ";

    }
}
