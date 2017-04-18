package ru.wow.dao.interfaces.impls;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.QueryParameter;
import ru.wow.dao.interfaces.CrudDao;
import ru.wow.models.Personage;
import ru.wow.util.HibernateUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;


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
            session.beginTransaction();
            item = (T)session.load(genericType, id);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return item;
    }

    @Override
    public Collection<T> getByName(String name) {
        Collection<T> items = null;
        String byName = "from " + genericType.getName() + " I where I.name = :name";
        String byNickName = "from " + genericType.getName() + " I where I.nickName = :nickName";
        try (Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            Query query = null;
            if(genericType.getName().equals(Personage.class.getName())){
                query = session.createQuery(byNickName);
            } else {
                query = session.createQuery(byName);
            }
            query.setParameter("name", name);
            items = query.list();
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return items;
    }

    @Override
    public List<T> findAllItems() {
        List<T> allItems;
        try (Session session = HibernateUtil.getSession()){
            session.beginTransaction();
            allItems = session.createQuery("from " + genericType.getName()).list();
            session.getTransaction().commit();
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
