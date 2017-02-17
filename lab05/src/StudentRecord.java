/**
 * Created by miral on 18/01/17.
 */
public class StudentRecord {

    private String ID;
    private float assignment;
    private float midterm;
    private float exam;


    private double mark;
    private char grade;

    public StudentRecord(String ID, float assignment, float midterm, float exam) {
        this.ID = ID;
        this.assignment = assignment;
        this.midterm = midterm;
        this.exam = exam;

        mark = assignment*0.2 + midterm *0.3 + exam *0.5;
    }

    public String getID() { return ID; }

    public float getMidterm() { return midterm; }

    public float getExam() { return exam; }

    public float getAssignment() { return assignment; }
    public double getMark() { return mark; }

    public char getGrade() {
        if (mark >= 80 && mark <= 100)
            grade = 'A';
        else if (mark >= 70 && mark < 80)
            grade = 'B';
        else if (mark >= 60 && mark < 70)
            grade = 'C';
        else if (mark >= 50 && mark < 60)
            grade = 'D';
        else
            grade = 'F';

        return grade;
    }

}
