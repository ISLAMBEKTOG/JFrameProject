import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class Server {
    private static Connection connection;

    public static void main(String[] args) {
        connectToDb();

        try{
            ServerSocket serverSocket = new ServerSocket(8083);
            System.out.println("Waiting for client...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected!!!");

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            PackageData pd = new PackageData();

            while ((pd = (PackageData)inputStream.readObject()) != null){
                if(pd.getOperationType().equals("ADD")){
                    addStudentToDb(pd.getStudent());
                }
                else if(pd.getOperationType().equals("LIST")){
                    PackageData pdRes = new PackageData();
                    pdRes.setStudents(getList());
                    outputStream.writeObject(pdRes);
                }else if(pd.getOperationType().equals("SORT")){
                    PackageData pdRes = new PackageData();
                    pdRes.setStudents(getList());
                    outputStream.writeObject(pdRes);
                }else if(pd.getOperationType().equals("MORE")){
                    PackageData pdRes = new PackageData();
                    pdRes.setStudents(getList());
                    outputStream.writeObject(pdRes);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void addStudentToDb(Student student){
        try{
            Statement st = connection.createStatement();
            String s1 = " insert into stud (name, surname, age) values (\""+student.getName()+"\",\""+student.getSurname()+"\", \""+student.getAge()+"\") ";
            st.executeUpdate(s1);
            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> getList(){
        ArrayList<Student> arrayList = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement(" select * from stud ");
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");

                Student student = new Student(id, name, surname, age);
                arrayList.add(student);
            }
            st.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return arrayList;
    }

    public static void connectToDb(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8112/testing_db","root","");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
