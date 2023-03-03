import java.util.Objects;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private int cityId;
    private  int age ;

    public Employee(int id, String firstName, String lastName, String gender, int cityId, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.cityId = cityId;
        this.age = age;
    }

    public Employee(String firstName, String lastName, String gender, int cityId, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.cityId = cityId;
        this.age = age;
    }

    public Employee() {
    }

    public Employee(int id, String firstName, City city, String lastName, String gender, int age) {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && cityId == employee.cityId && age == employee.age && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(gender, employee.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, cityId, age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", cityId=" + cityId +
                ", age=" + age +
                '}';
    }
}


