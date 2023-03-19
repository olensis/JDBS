import java.sql.*;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "2207ebs";
        final String url = "jdbc:postgresql://localhost:5432/skypro";


        // Создаем соединение с базой с помощью Connection
        // Формируем запрос к базе с помощью PreparedStatement
        String firstName = null;
        String lastName = null;
        String gender = null;
        int age = 0;
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee_1 WHERE  id = (?)")) {

            // Подставляем значение вместо wildcard
            statement.setInt(1, 6);

            // Делаем запрос к базе и результат кладем в ResultSet
            final ResultSet resultSet = statement.executeQuery();

            // Методом next проверяем есть ли следующий элемент в resultSet
            // и одновременно переходим к нему, если таковой есть
            while (resultSet.next()) {

                // С помощью методов getInt и getString получаем данные из resultSet
                firstName = "FirstName: " + resultSet.getString("first_name");
                lastName = "LastName: " + resultSet.getString("last_name");
                gender = "Gender: " + resultSet.getString("gender");
                age = resultSet.getInt(25);
                EmployeeDAO employeeDAO = new EmployeeDAOimpl();
                CityDAO cityDAO = new CityDAOImpl();

                City moscow = cityDAO.read(4);

                Employee employee = new Employee();
                employee.setCity(moscow);
                employee.setAge(30);
                employee.setGender("pav");
                employee.setFirst_name("er");
                employee.setLast_name("av");

                employeeDAO.create(employee);


            }
        }

        // Выводим данные в консоль
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(gender);
        System.out.println("Age: " + age);
    }
}
