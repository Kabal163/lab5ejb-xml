package ru.wow.dao.interfaces;

import ru.wow.models.Equipment;
import ru.wow.models.Personage;
import ru.wow.models.Weapon;

import java.io.Serializable;
import java.util.List;

public interface CrudDao<T> {

    boolean createItem(T item);

    boolean removeItem(T item);

    boolean deleteById(Serializable id);

    boolean updateItem(T item);

    T getById(long id);

    List<T> findAllItems();
}
