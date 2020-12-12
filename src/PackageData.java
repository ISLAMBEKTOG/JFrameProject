import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable{
    private ArrayList<Student> students;
    private Student student;
    private String operationType;

    public PackageData(){}

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student getStudent() {
        return student;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}