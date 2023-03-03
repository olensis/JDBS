import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {

        final String user = "postgres";
        final String password = "2207ebs";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        // Создаем соединение с базой с помощью Connection
        // Формируем запрос к базе с помощью PreparedStatement
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE  id = (?)")) {

            // Подставляем значение вместо wildcard
            statement.setInt(1, 6);

            // Делаем запрос к базе и результат кладем в ResultSet
            final ResultSet resultSet = statement.executeQuery();

            // Методом next проверяем есть ли следующий элемент в resultSet
            // и одновременно переходим к нему, если таковой есть
            while (resultSet.next()) {

                // С помощью методов getInt и getString получаем данные из resultSet
                String firstName = "FirstName: " + resultSet.getString("first_name");
                String lastName = "LastName: " + resultSet.getString("last_name");
                String gender = "Gender: " + resultSet.getString("gender");
                String age = resultSet.getString(5);

                // Выводим данные в консоль
                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(gender);
                System.out.println("Age: " + age);
            }
            EmployeeDAO employeeDAO = new EmployeeDAOimpl(connection);
            Employee employee = new Employee(1, "Olia","Kotova","woman",5,60);
            City city = new City(1,"Москва");
            employeeDAO.create(employee);

        }



    }
}
