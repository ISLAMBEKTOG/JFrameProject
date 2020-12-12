import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    private static Socket socket;
    private static ObjectOutputStream objectOutputStream;
    private static ObjectInputStream inputStream;

    public static MainFrame frame;

    public static void main(String[] args) {
        connectToServer();

        frame = new MainFrame();
        frame.setVisible(true);
    }

    public static void addStudent(Student student) {
        PackageData pd = new PackageData();
        pd.setOperationType("ADD");
        pd.setStudent(student);

        try {
            objectOutputStream.writeObject(pd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object[][] objects;

    public static Object[][] getList() {
        PackageData pd = new PackageData();
        pd.setOperationType("LIST");

        try {
            objectOutputStream.writeObject(pd);

            PackageData pdRes = new PackageData();
            if ((pdRes = (PackageData) inputStream.readObject()) != null) {
                objects = new Object[pdRes.getStudents().size()][4];


                for (int i = 0; i < pdRes.getStudents().size(); i++) {
                    objects[i][0] = pdRes.getStudents().get(i).getId();
                    objects[i][1] = pdRes.getStudents().get(i).getName();
                    objects[i][2] = pdRes.getStudents().get(i).getSurname();
                    objects[i][3] = pdRes.getStudents().get(i).getAge();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    private static Object[][] newobjects;

    public static Object[][] sortList() {
        PackageData pd = new PackageData();
        pd.setOperationType("SORT");

        try {
            objectOutputStream.writeObject(pd);

            PackageData pdRes = new PackageData();

            if ((pdRes = (PackageData) inputStream.readObject()) != null) {
                ArrayList<Student> students = pdRes.getStudents();
                students.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Student a, Student b) {
                        if (a.getName().equals(b.getName())) {
                            return a.getSurname().compareTo(b.getSurname());
                        } else {
                            return a.getName().compareTo(b.getName());
                        }
                    }
                });

                newobjects = new Object[students.size()][4];

                for (int i = 0; i < students.size(); i++) {
                    newobjects[i][0] = students.get(i).getId();
                    newobjects[i][1] = students.get(i).getName();
                    newobjects[i][2] = students.get(i).getSurname();
                    newobjects[i][3] = students.get(i).getAge();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newobjects;
    }

    public static Predicate<Student> isAgeMoreThan(Integer age) {
        return p -> p.getAge() > age;
    }

    public static List<Student> filterEmployees(ArrayList<Student> students,
                                                Predicate<Student> predicate) {
        return students.stream()
                .filter(predicate)
                .collect(Collectors.<Student>toList());
    }

    private static Object[][] newobjects1;

    public static Object[][] morethann() {
        PackageData pd = new PackageData();
        pd.setOperationType("MORE");

        ArrayList<Student> arrayList = new ArrayList<>();

        try {
            objectOutputStream.writeObject(pd);

            PackageData pdRes = new PackageData();
            if ((pdRes = (PackageData) inputStream.readObject()) != null) {
                arrayList = (ArrayList<Student>) filterEmployees(pdRes.getStudents(), isAgeMoreThan(19));

                newobjects1 = new Object[arrayList.size()][4];


                for (int i = 0; i < arrayList.size(); i++) {
                    newobjects1[i][0] = arrayList.get(i).getId();
                    newobjects1[i][1] = arrayList.get(i).getName();
                    newobjects1[i][2] = arrayList.get(i).getSurname();
                    newobjects1[i][3] = arrayList.get(i).getAge();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newobjects1;
    }

    public static void connectToServer() {
        try {
            socket = new Socket("127.0.0.1", 8083);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}