import java.util.List;

public interface EmployeeDAO {
    void create(Employee employee);

    Employee read(int id);

    List<Employee> readAll();

}
