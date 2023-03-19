import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.List;

public class EmployeeDAOimpl implements  EmployeeDAO{
    private Connection connection;

    public EmployeeDAOimpl() {
        this.connection=connection;
    }

    @Override
    public void create(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }
    @Override
    public Employee read(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);
    }
    @Override
    public List<Employee> readAll() {
        List<Employee> employees = (List<Employee>) HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("SELECT emp FROM Employee emp").getResultList();
        return employees;
    }


    }

