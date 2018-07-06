package DaniilBacalear;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.*;

/*
File Name: StlRegistration2017.java.java
Description: STL Registration
Name: Daniil Bacalear
Date Created:11/06/17
Last Modified:11/16/17
 */
public class StlRegistration2017 {
    static ArrayList<Student> studentList = new ArrayList<Student>();
    static ArrayList<Courses> coursesList = new ArrayList<Courses>();
    static File file = new File("src/testing.txt", true);
    static InputStreamReader in = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(in);
    static String input;
    static ArrayList<Integer> studentNumberList = studentNumberGenerator();

    public static void main(String[] args) throws IOException {
        generateCourses();
        if (openFileChoice() == false) {
            file.eraseFileContent("src/testing.txt");
            fileToStudent();
        } else fileToStudent();
        while (true) {
            int mainMenuUserSelection;
            mainMenuUserSelection = mainMenuSelection();
            if (mainMenuUserSelection == 0) break;
            if (mainMenuUserSelection == 1) {
                String firstName, lastName;
                System.out.println("Enter Students First Name");
                firstName = br.readLine();
                System.out.println("Enter Students Last Name");
                lastName = br.readLine();
                displayStudent(firstName, lastName);
            }
            if (mainMenuUserSelection == 2) displayAllStudents();
            if (mainMenuUserSelection == 3) createStudent();
            if (mainMenuUserSelection == 4) {
                String firstName, lastName;
                System.out.println("Enter Students First Name");
                firstName = br.readLine();
                System.out.println("Enter Students Last Name");
                lastName = br.readLine();
                deleteStudent(firstName, lastName);
            }
            if (mainMenuUserSelection == 5) editStudentMenu();
            if(mainMenuUserSelection==6) addCourse();
            if(mainMenuUserSelection==7) removeCourse();
            if(mainMenuUserSelection==8) displayCourseEnrollment();
        }
    }

    public static void displayStudent(String firstName, String lastName) {
        int counter = 0;
        for (Student student : studentList) {
            if (student.getPersonalInfo().getFirstName().equalsIgnoreCase(firstName) && student.getPersonalInfo().getLastName().equalsIgnoreCase(lastName)) {
                counter++;
                System.out.print(student);
                System.out.println();
            }
        }
        if (counter < 1) System.out.println("Student Not Found");
    }//this method displays all of a given students information, by getting it from the arraylist of students

    public static void displayAllStudents() {
        for (Student student : studentList) {
            System.out.print(student);
        }
    }//displays all students and all of their information

    public static void createStudent() throws IOException {
        int age, grade, index, studentNumber;
        index = studentList.size();
        String firstName, lastName;
        System.out.println("Enter Students First Name (required)");
        firstName = br.readLine();
        System.out.println("Enter Students Last Name (required)");
        lastName = br.readLine();
        System.out.println("Enter Students Age (required)");
        input = br.readLine();
        age = Integer.parseInt(input);
        System.out.println("Enter Students Grade (required)");
        input = br.readLine();
        grade = Integer.parseInt(input);
        studentNumber = studentNumberList.get(index);
        Student createdStudent = new Student(age, studentNumber, grade, new PersonalInformation(firstName, lastName), new LockerInformation(), studentEmptyCourseInitializer());
        studentList.add(createdStudent);
        file.writeToFile(createdStudent.toString());
    }//Creates student object and places object in student Arraylist. Base student is initialized with 8 empty courses and assigned unique student number

    public static void deleteStudent(String firstName, String lastName) throws IOException {
        int studentNumber;
        displayStudent(firstName, lastName);
        System.out.println("Please Enter The Student Number Of The Selected Student Which Can Be Found After Looking Through The Students Information Above.");
        input = br.readLine();
        studentNumber = Integer.parseInt(input);
        for (Student student : studentList) {
            if (student.getStudentNumber() == studentNumber) {
                studentList.remove(student);
                System.out.println(firstName + " " + lastName + " has been removed");
                break;
            }
        }
    }//deletes a given student object from student arraylist

    public static void editStudentMenu() throws IOException {
        int userSelection;
        while (true) {
            System.out.println("Choose What Student Information You Want To Edit\n1) Address\n2) Grade\n3) Age\n4) Courses\n5) Locker Information");
            input = br.readLine();
            userSelection = Integer.parseInt(input);
            if (userSelection == 1) {
                editStudentAddress();
                break;
            }
            if (userSelection == 2) {
                editStudentGrade();
                break;
            }
            if (userSelection == 3) {
                editStudentAge();
                break;
            }
            if (userSelection == 4) {
                editStudentCourseInfo();
                break;
            }
            if (userSelection == 5) {
                editStudentLockerInfo();
                break;
            } else System.out.println("Enter A Valid Number");
        }
    }//menu with pathway for editing student

    public static boolean openFileChoice() throws IOException {
        boolean openFile = false;
        int choice;
        while (true) {
            System.out.println("Do you want to load previous student file\nEnter 0 to load file\nEnter 1 to start new file");
            input = br.readLine();
            choice = Integer.parseInt(input);
            if (choice == 0) {
                openFile = true;
                System.out.println("File Loaded");
                break;
            }
            if (choice == 1) {
                System.out.println("New File Initialized");
                break;
            }
        }
        return openFile;
    }//boolean which asks user if they want to preload existing students

    public static void fileToStudent() throws IOException {
        for (int i = 1; i <= file.lineCount(); i++) {
            String fileLineString = file.fileGetLine(i).replaceAll("\\s+", "");
            String firstName, lastName, address, courseName, courseCode, lockerNumber, lockerCombination;
            ArrayList<Courses> loadCourses = new ArrayList<Courses>();
            int grade, age, studentNumber, rangeStart, rangeEnd;
            rangeStart = 10;
            rangeEnd = fileLineString.indexOf("LastName:");
            firstName = fileLineString.substring(rangeStart, rangeEnd);
            rangeStart = fileLineString.indexOf("LastName:");
            rangeEnd = fileLineString.indexOf("Address:");
            lastName = fileLineString.substring(rangeStart, rangeEnd).replaceAll("LastName:", "");
            rangeStart = fileLineString.indexOf("Address:");
            rangeEnd = fileLineString.indexOf("Grade:");
            address = fileLineString.substring(rangeStart, rangeEnd).replaceAll("Address:", "");
            rangeStart = fileLineString.indexOf("Grade:");
            rangeEnd = fileLineString.indexOf("Age:");
            input = fileLineString.substring(rangeStart, rangeEnd).replaceAll("Grade:", "");
            grade = Integer.parseInt(input);
            rangeStart = fileLineString.indexOf("Age:");
            //..........................................................................
            rangeEnd = fileLineString.indexOf("CourseName1:");
            input = fileLineString.substring(rangeStart, rangeEnd).replaceAll("Age:", "");
            age = Integer.parseInt(input);
            rangeStart = fileLineString.indexOf("CourseName1:");
            rangeEnd = fileLineString.indexOf("CourseCode1:");
            courseName = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseName1:", "");
            rangeStart = fileLineString.indexOf("CourseCode1:");
            rangeEnd = fileLineString.indexOf("CourseName2:");
            courseCode = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseCode1:", "");
            Courses course1 = new Courses(courseName, courseCode);
            loadCourses.add(course1);
            rangeStart = fileLineString.indexOf("CourseName2:");
            rangeEnd = fileLineString.indexOf("CourseCode2:");
            courseName = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseName2:", "");
            rangeStart = fileLineString.indexOf("CourseCode2:");
            rangeEnd = fileLineString.indexOf("CourseName3:");
            courseCode = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseCode2:", "");
            Courses course2 = new Courses(courseName, courseCode);
            loadCourses.add(course2);
            rangeStart = fileLineString.indexOf("CourseName3:");
            rangeEnd = fileLineString.indexOf("CourseCode3:");
            courseName = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseName3:", "");
            rangeStart = fileLineString.indexOf("CourseCode3:");
            rangeEnd = fileLineString.indexOf("CourseName4:");
            courseCode = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseCode3:", "");
            Courses course3 = new Courses(courseName, courseCode);
            loadCourses.add(course3);
            rangeStart = fileLineString.indexOf("CourseName4:");
            rangeEnd = fileLineString.indexOf("CourseCode4:");
            courseName = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseName4:", "");
            rangeStart = fileLineString.indexOf("CourseCode4:");
            rangeEnd = fileLineString.indexOf("CourseName5:");
            courseCode = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseCode4:", "");
            Courses course4 = new Courses(courseName, courseCode);
            loadCourses.add(course4);
            rangeStart = fileLineString.indexOf("CourseName5:");
            rangeEnd = fileLineString.indexOf("CourseCode5:");
            courseName = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseName5:", "");
            rangeStart = fileLineString.indexOf("CourseCode5:");
            rangeEnd = fileLineString.indexOf("CourseName6:");
            courseCode = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseCode5:", "");
            Courses course5 = new Courses(courseName, courseCode);
            loadCourses.add(course5);
            rangeStart = fileLineString.indexOf("CourseName6:");
            rangeEnd = fileLineString.indexOf("CourseCode6:");
            courseName = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseName6:", "");
            rangeStart = fileLineString.indexOf("CourseCode6:");
            rangeEnd = fileLineString.indexOf("CourseName7:");
            courseCode = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseCode6:", "");
            Courses course6 = new Courses(courseName, courseCode);
            loadCourses.add(course6);
            rangeStart = fileLineString.indexOf("CourseName7:");
            rangeEnd = fileLineString.indexOf("CourseCode7:");
            courseName = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseName7:", "");
            rangeStart = fileLineString.indexOf("CourseCode7:");
            rangeEnd = fileLineString.indexOf("CourseName8:");
            courseCode = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseCode7:", "");
            Courses course7 = new Courses(courseName, courseCode);
            loadCourses.add(course7);
            rangeStart = fileLineString.indexOf("CourseName8:");
            rangeEnd = fileLineString.indexOf("CourseCode8:");
            courseName = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseName8:", "");
            rangeStart = fileLineString.indexOf("CourseCode8:");
            rangeEnd = fileLineString.indexOf("LockerNumber:");
            courseCode = fileLineString.substring(rangeStart, rangeEnd).replaceAll("CourseCode8:", "");
            Courses course8 = new Courses(courseName, courseCode);
            loadCourses.add(course8);
            //.....................................................................................
            rangeStart = fileLineString.indexOf("LockerNumber:");
            rangeEnd = fileLineString.indexOf("LockerCombination:");
            lockerNumber = fileLineString.substring(rangeStart, rangeEnd).replaceAll("LockerNumber:", "");
            rangeStart = fileLineString.indexOf("LockerCombination:");
            rangeEnd = fileLineString.indexOf("StudentNumber:");
            lockerCombination = fileLineString.substring(rangeStart, rangeEnd).replaceAll("LockerCombination:", "");
            rangeStart = fileLineString.indexOf("StudentNumber:");
            input = fileLineString.substring(rangeStart).replaceAll("StudentNumber:", "");
            studentNumber = Integer.parseInt(input);
            //.................................................................................................................

            Student savedStudent = new Student(age, studentNumber, grade, new PersonalInformation(firstName, lastName, address), new LockerInformation(lockerNumber, lockerCombination), loadCourses);
            studentList.add(savedStudent);
        }
    }//loads text file by converting lines in text file to student objects. Then populates arraylist of students.

    public static void editStudentAge() throws IOException {
        String firstName, lastName, studentNumberString;
        int age, studentNumber;
        System.out.println("Enter Students First Name");
        firstName = br.readLine();
        System.out.println("Enter Students Last Name");
        lastName = br.readLine();
        displayStudent(firstName, lastName);
        System.out.println("Please Check the students that you entered information which can be seen above and enter their student number");
        studentNumberString = br.readLine();
        studentNumber = Integer.parseInt(studentNumberString);
        System.out.println("Enter Students New Age");
        input = br.readLine();
        age = Integer.parseInt(input);
        for (Student student : studentList) {
            if (student.getStudentNumber() == studentNumber) {
                student.setAge(age);
                file.deleteLineInFile(studentNumberString);
                file.writeToFile(student.toString());
            }
        }
        System.out.println(firstName + " " + lastName + "'s age has been changed");
    }//edits students age

    public static void editStudentGrade() throws IOException {
        String firstName, lastName, studentNumberString;
        int grade, studentNumber;
        System.out.println("Enter Students First Name");
        firstName = br.readLine();
        System.out.println("Enter Students Last Name");
        lastName = br.readLine();
        displayStudent(firstName, lastName);
        System.out.println("Please Enter The Student Number Of The Selected Student Which Can Be Found After Looking Through The Students Information Above.");
        studentNumberString = br.readLine();
        studentNumber = Integer.parseInt(studentNumberString);
        System.out.println("Enter Students New Grade");
        input = br.readLine();
        grade = Integer.parseInt(input);
        for (Student student : studentList) {
            if (student.getStudentNumber() == studentNumber) {
                student.setGrade(grade);
                file.deleteLineInFile(studentNumberString);
                file.writeToFile(student.toString());
            }
        }
        System.out.println(firstName + " " + lastName + "'s grade has been changed");
    }//edit students grade

    public static void editStudentAddress() throws IOException {
        String firstName, lastName, address, studentNumberString;
        int studentNumber;
        System.out.println("Enter Students First Name");
        firstName = br.readLine();
        System.out.println("Enter Students Last Name");
        lastName = br.readLine();
        displayStudent(firstName, lastName);
        System.out.println("Please Enter The Student Number Of The Selected Student Which Can Be Found After Looking Through The Students Information Above.");
        studentNumberString = br.readLine();
        studentNumber = Integer.parseInt(studentNumberString);
        System.out.println("Enter Students Address");
        address = br.readLine();
        for (Student student : studentList) {
            if (student.getStudentNumber() == studentNumber) {
                student.getPersonalInfo().setAddress(address);
                file.deleteLineInFile(studentNumberString);
                file.writeToFile(student.toString());
            }
        }
        System.out.println(firstName + " " + lastName + "'s address has been changed");
    }//edits students address

    public static void editStudentLockerInfo() throws IOException {
        String firstName, lastName, lockerNumber, lockerCombination, studentNumberString;
        int studentNumber;
        System.out.println("Enter Students First Name");
        firstName = br.readLine();
        System.out.println("Enter Students Last Name");
        lastName = br.readLine();
        displayStudent(firstName, lastName);
        System.out.println("Please Enter The Student Number Of The Selected Student Which Can Be Found After Looking Through The Students Information Above.");
        studentNumberString = br.readLine();
        studentNumber = Integer.parseInt(studentNumberString);
        System.out.println("Enter Students New Locker Number");
        lockerNumber = br.readLine();
        System.out.println("Enter Students New Locker Combination");
        lockerCombination = br.readLine();
        for (Student student : studentList) {
            if (student.getStudentNumber() == studentNumber) {
                student.getLockerInfo().setLockerNumber(lockerNumber);
                student.getLockerInfo().setLockerCombination(lockerCombination);
                file.deleteLineInFile(studentNumberString);
                file.writeToFile(student.toString());
            }
        }
        System.out.println(firstName + " " + lastName + "'s locker information has been changed");
    }//edits students locker information

    public static void editStudentCourseInfo() throws IOException {
        String firstName, lastName, courseName, courseToReplace, studentNumberString;
        int numberOfCourses, choice, studentNumber;
        System.out.println("Enter Students First Name");
        firstName = br.readLine();
        System.out.println("Enter Students Last Name");
        lastName = br.readLine();
        displayStudent(firstName, lastName);
        System.out.println("Please Enter The Student Number Of The Selected Student Which Can Be Found After Looking Through The Students Information Above.");
        studentNumberString = br.readLine();
        studentNumber = Integer.parseInt(studentNumberString);
        System.out.println("Enter Number Of Courses To Edit For Chosen Student");
        input = br.readLine();
        numberOfCourses = Integer.parseInt(input);
        for (int i = 1; i <= numberOfCourses; i++) {
            System.out.println("Are you replacing an existing course or are you adding a brand new one?\nEnter 1 if you are replacing existing course\nEnter 2 if you are adding a brand new course");
            input = br.readLine();
            choice = Integer.parseInt(input);
            if (choice == 1) {
                while (true) {
                    displayCourseChoices();
                    System.out.println("Enter course code of course that you are replacing(Select from list above)");
                    courseToReplace = br.readLine();
                    if (doesStudentHaveCourse(courseToReplace, studentNumber) == true && doesCourseExist(courseToReplace) == true)
                        break;
                    System.out.println("Enter Valid Course Code");
                }
                while (true) {
                    displayCourseChoices();
                    System.out.println("Enter course code of course you are adding (Select from list above)");
                    courseName = br.readLine();
                    if (doesStudentHaveCourse(courseName, studentNumber) == false && doesCourseExist(courseName) == true)
                        break;
                    System.out.println("Enter Valid Course Code");
                }
                for (Student student : studentList) {
                    if (student.getStudentNumber() == studentNumber) {
                        student.removeCourse(courseToReplace);
                        getElementOfCoursesList(courseToReplace).subtractCourseCapacity();
                        student.addCourse(getElementOfCoursesList(courseName));
                        getElementOfCoursesList(courseName).addCourseCapacity();
                        file.deleteLineInFile(studentNumberString);
                        file.writeToFile(student.toString());
                    }
                }
            }
            if (choice == 2) {
                while (true) {
                    displayCourseChoices();
                    System.out.println("Enter course code of course you are adding (Select from list above)");
                    courseName = br.readLine();
                    if (doesStudentHaveCourse(courseName, studentNumber) == false && doesCourseExist(courseName) == true)
                        break;
                    System.out.println("Enter a Valid Course");
                }
                if (doesStudentHaveCourse("Empty", studentNumber) == true) {
                    for (Student student : studentList) {
                        if (student.getStudentNumber() == studentNumber) {
                            student.removeCourse("Empty");
                            student.addCourse(getElementOfCoursesList(courseName));
                            getElementOfCoursesList(courseName).addCourseCapacity();
                            file.deleteLineInFile(studentNumberString);
                            file.writeToFile(student.toString());
                        }
                    }
                }
                else System.out.println("ERROR: Max Capacity Of Courses Reached");
            }

            System.out.println(firstName + " " + lastName + "'s courses have been changed");
        }
    }//Method which allows user to edit a given students courses

    public static int mainMenuSelection() throws IOException {
        int selection;
        while (true) {
            System.out.println();
            System.out.println("1) Search Student\n2)Display All Students\n3)Create Student\n4)Remove Student\n5)Edit Student\n6) Add Course\n7)Remove Course\n8)Course Enrollment\nEnter selection number (Enter 0 to exit STL Registration)");
            input = br.readLine();
            selection = Integer.parseInt(input);
            if (selection > 8 || selection < 0) System.out.println("Enter Valid Number");
            else break;
        }
        return selection;
    }//main menu selection pathway

    public static void generateCourses() {
        Courses math12 = new Courses("Advanced Functions", "MHF4U1");
        Courses math11 = new Courses("Functions", "MCR3U1");
        Courses math10 = new Courses("Math 11", "MPM2D1");
        Courses math9 = new Courses("Math 9", "MPM1D1");
        Courses calc = new Courses("Calculus", "MCV4U1");
        Courses english12 = new Courses("English 12", "ENG4U1");
        Courses english11 = new Courses("English 11", "ENG3U1");
        Courses english10 = new Courses("English 10", "ENG2U1");
        Courses english9 = new Courses("English", "ENG1U1");
        Courses religion12 = new Courses("Religion 12", "HRE4M1");
        Courses religion11 = new Courses("Religion 11", "HRE3M1");
        Courses religion10 = new Courses("Religion 10", "HRE2O1");
        Courses religion9 = new Courses("Religion 9", "HRE1O1");
        Courses comsci12 = new Courses("Computer Science 12", "ICS4U1");
        Courses comsci11 = new Courses("Computer Science 11", "ICS3U1");
        Courses comsci10 = new Courses("Computer Science 10", "ICS2O1");
        Courses physics12 = new Courses("Physics 12", "SPH4U1");
        Courses physics11 = new Courses("Physics 11", "SPH3U1");
        Courses chemistry12 = new Courses("Chemistry 12", "SCH4U1");
        Courses chemistry11 = new Courses("Chemistry 11", "SCH3U1");
        Courses biology12 = new Courses("Biology 12", "SBI4U1");
        Courses biology11 = new Courses("Biology 11", "SBI3U1");
        Courses science10 = new Courses("Science 10", "SNC2D1");
        Courses science9 = new Courses("Science 10", "SNC1D1");
        Courses spare = new Courses("Spare", "SPARE");
        coursesList.add(math12);
        coursesList.add(math11);
        coursesList.add(math10);
        coursesList.add(math9);
        coursesList.add(calc);
        coursesList.add(english12);
        coursesList.add(english11);
        coursesList.add(english10);
        coursesList.add(english9);
        coursesList.add(religion12);
        coursesList.add(religion11);
        coursesList.add(religion10);
        coursesList.add(religion9);
        coursesList.add(comsci12);
        coursesList.add(comsci11);
        coursesList.add(comsci10);
        coursesList.add(physics12);
        coursesList.add(physics11);
        coursesList.add(chemistry12);
        coursesList.add(chemistry11);
        coursesList.add(biology12);
        coursesList.add(biology11);
        coursesList.add(science10);
        coursesList.add(science9);
        coursesList.add(spare);
    }//generates initial courses to populate courses Arraylist which can be assigned to a student.

    public static void displayCourseChoices() {
        for (int i = 0; i < coursesList.size(); i++) {
            System.out.println(i + ") Course Name: " + coursesList.get(i).getCourseName() + " Course Code: " + coursesList.get(i).getCourseCode());
        }
    }//displays possible courses that can be added to student

    public static void displayCourseEnrollment() throws IOException {
        String courseCode, courseEnrollmentString = "";
        int courseEnrollment;

        while (true) {
            displayCourseChoices();
            System.out.println("Enter Course Code (Select From List Above)");
            courseCode = br.readLine();
            if (doesCourseExist(courseCode) == true) break;
            System.out.println("Enter Valid Course");
        }
        for (Courses element : coursesList) {
            if (element.getCourseCode().equalsIgnoreCase(courseCode)) {
                courseEnrollment = element.getCourseCapacity();
                courseEnrollmentString = Integer.toString(courseEnrollment);
            }
        }
        System.out.println(courseCode + " Enrollment is " + courseEnrollmentString);
    }//displays the number of students in a given course

    public static ArrayList<Integer> studentNumberGenerator() {
        ArrayList<Integer> studentNumber = new ArrayList<Integer>();
        for (int i = 1000; i < 10000; i++) {
            studentNumber.add(i);
        }
        Collections.shuffle(studentNumber);
        return studentNumber;
    }//generates a random unique student number

    public static void removeCourse()throws IOException {
        String courseCode;
        while (true) {
            displayCourseChoices();
            System.out.println("Enter Course Code of Course To Remove (Check list above)");
            courseCode = br.readLine();
            if (doesCourseExist(courseCode) == true) break;
        }
        for (Student element : studentList) {
            for (int i = 0; i < element.getStudentCourseList2().size(); i++) {
                if (element.getStudentCourseList2().get(i).toString().contains(courseCode)) {
                    Courses replace = new Courses("Empty", "Empty");
                    element.getStudentCourseList2().set(i, replace);
                }
            }
        }
        int counter = -1;
        for(Courses element:coursesList){
            counter ++;
            if(element.getCourseCode().equalsIgnoreCase(courseCode)){
                coursesList.remove(counter);
                break;
            }
        }
        System.out.println("Course Has Been Deleted");

    }//removes a course from course list ArrayList and if student has course which is to be removed, removes it and replaces that students course slot with "Empty"

    public static void addCourse()throws IOException{
        String courseCode,courseName;
        System.out.println("Enter Course Code");
        courseCode = br.readLine();
        System.out.println("Enter Course Name");
        courseName = br.readLine();
        Courses newCourse = new Courses(courseName,courseCode);
        coursesList.add(newCourse);
        System.out.println("Course Has Been Added");
    }//adds course to Arraylist


    public static Courses getElementOfCoursesList(String courseCode) {
        Courses course = null;
        for (Courses element : coursesList) {
            if (element.getCourseCode().equalsIgnoreCase(courseCode)) {
                course = element;
                break;
            }
        }
        return course;
    }//returns course

    public static ArrayList<Courses> studentEmptyCourseInitializer() {
        ArrayList<Courses> studentPersonalCourses = new ArrayList<Courses>();
        Courses empty = new Courses("Empty", "Empty");
        for (int i = 0; i < 8; i++) {
            studentPersonalCourses.add(empty);
        }
        return studentPersonalCourses;
    }//When student object is initialized, populates students courses with arraylist of courses of course name and code "Empty"

    public static boolean doesStudentHaveCourse(String courseCode, int studentNumber) {
        boolean studentHasCourse = false;
        for (Student student : studentList) {
            if (student.getStudentNumber() == studentNumber) {
                for (int i = 0; i < student.getStudentCourseList2().size(); i++) {
                    if (student.getStudentCourseList2().get(i).getCourseCode().equalsIgnoreCase(courseCode))
                        studentHasCourse = true;
                }
            }
        }
        return studentHasCourse;
    }//boolean which checks if a student has a specific course

    public static boolean doesCourseExist(String courseCode) {
        boolean courseExists = false;
        for (Courses course : coursesList) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) courseExists = true;
        }
        return courseExists;
    }//checks course list to see if inputted course exists
}
