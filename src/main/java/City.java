import javax.persistence.*;
import java.sql.Connection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cityId;
    @Column(name = "city_name")
    private String cityName;
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "city")
    private Set<Employee> employees;
    public City(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public City() {

    }

    public Set<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }


    public City(Connection connection) {

    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return cityId == city.cityId && Objects.equals(cityName, city.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, cityName);
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                '}';
    }

}
