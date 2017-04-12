package ru.wow.dao.interfaces.impls;

import org.hibernate.Session;
import org.springframework.core.GenericTypeResolver;
import ru.wow.dao.interfaces.CrudDao;
import ru.wow.models.Weapon;
import ru.wow.util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CrudDatabaseDao<T> implements CrudDao<T>{

    private Class<T> genericType;

    public CrudDatabaseDao() {}

    public CrudDatabaseDao(Class<T> genericType){
        this.genericType = genericType;
    }

    @Override
    public boolean createItem(T item) {
        try (Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean removeItem(T item) {
        try (Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateItem(T item) {
        try (Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public T getById(long id) {
        T item = null;
        try (Session session = HibernateUtil.getSession()){
            item = (T)session.load(genericType, id);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return item;
    }

    @Override
    public List<T> findAllItems() {
        List<T> allItems;
        try (Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            allItems = session.createQuery("from " + genericType.getName()).list();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return allItems;
    }

    @Override
    public boolean deleteById(Serializable id){
        try (Session session = HibernateUtil.getSession()){
            T item = session.byId(genericType).load(id);
            if(item != null){
                session.beginTransaction();
                session.delete(item);
                session.getTransaction().commit();
                return true;
            }
            return false;
        }
    }
}
