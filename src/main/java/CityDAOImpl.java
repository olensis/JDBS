import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDAOImpl implements CityDAO {
    @Override
    public void create(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
    }

    @Override
    public City read(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(City.class, id);
    }

    @Override
    public List<City> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            List<City> cities = (List<City>) session.createQuery("FROM City").getResultList();
            session.getTransaction().commit();
            return cities;
        }
    }

    @Override
    public void delete(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            session.delete(city);
            session.getTransaction().commit();
        }
    }
}
