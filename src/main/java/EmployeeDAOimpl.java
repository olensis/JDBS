import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOimpl implements  EmployeeDAO {
    private Connection connection;

    public EmployeeDAOimpl(Connection connection) {
        this.connection=connection;
    }

    @Override
    public void create(Employee employee) {

        // Формируем запрос к базе с помощью PreparedStatement
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO city (city_name, city_id) VALUES ((?), (?))")) {

            // Подставляем значение вместо wildcard
            // первым параметром указываем порядковый номер wildcard
            // вторым параметром передаем значение
            statement.setString(1, employee.getFirstName());
            statement.setInt(2, employee.getId());
            statement.setString(1, employee.getLastName());
            statement.setString(2, employee.getGender());
            statement.setInt(1, employee.getCityId());
            statement.setInt(2, employee.getAge());

            // С помощью метода executeQuery отправляем запрос к базе
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee readById(int id) {

        Employee employee = new Employee();
        // Формируем запрос к базе с помощью PreparedStatement
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.city_id = city.city_id")) {

            // Подставляем значение вместо wildcard
            statement.setInt(1, id);

            // Делаем запрос к базе и результат кладем в ResultSet
            ResultSet resultSet = statement.executeQuery();

            // Методом next проверяем есть ли следующий элемент в resultSet
            // и одновременно переходим к нему, если таковой есть
            while (resultSet.next()) {

                // С помощью методов getInt и getString получаем данные из resultSet
                // и присваиваем их поля объекта
                employee.setId(Integer.parseInt(resultSet.getString("employee_id")));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setCityId(Integer.parseInt(resultSet.getString("cityId")));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {

        // Создаем список, в который будем укладывать объекты
        List<Employee> employeesList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM employee INNER JOIN city ON employee.city_id = city.city_id")) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                int id = Integer.parseInt(resultSet.getString("employee_id"));
                String firstName = resultSet.getString("firstName");
                City city = new City(resultSet.getInt("city_id"),
                        resultSet.getString("name_city"));
                String lastName = resultSet.getString("lastName");
                String gender = resultSet.getString("gender");
                int age = Integer.parseInt(resultSet.getString("age"));


                // Создаем объекты на основе полученных данных
                // и укладываем их в итоговый список
                employeesList.add(new Employee(id, firstName, city, lastName, gender, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeesList;
    }

    @Override
    public void updateAmountById(int id, String firstName) {

        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE employee SET firstName=(?) WHERE employee_id=(?)")) {
            statement.setInt(1, Integer.parseInt(firstName));
            statement.setInt(2, id);

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {

        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM employee WHERE employee_id=(?)")) {

            statement.setInt(1, id);
            statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
