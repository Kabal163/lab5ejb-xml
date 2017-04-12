package ru.wow.ejb.interfaces.impls;

import ru.wow.dao.interfaces.impls.CrudDatabaseDao;
import ru.wow.ejb.interfaces.EquipmentHandler;
import ru.wow.models.Equipment;
import ru.wow.models.Personage;

import javax.ejb.*;
import java.io.Serializable;
import java.util.List;

@Stateless
public class EquipmentBean implements EquipmentHandler, Serializable{

    @Override
    public boolean createEquipment(Equipment equipment) {
        countPrice(equipment);
        return new CrudDatabaseDao<Equipment>(Equipment.class).createItem(equipment);
    }

    @Override
    public boolean removeEquipment(Equipment equipment) {
        return false;
    }

    @Override
    public boolean updateEquipment(Equipment equipment) {
        countPrice(equipment);
        return new CrudDatabaseDao<Equipment>().updateItem(equipment);
    }

    @Override
    public Equipment findEquipment(long id) {
        return new CrudDatabaseDao<Equipment>(Equipment.class).getById(id);
    }

    @Override
    public List<Equipment> findAllEquipment() {
        return new CrudDatabaseDao<Equipment>(Equipment.class).findAllItems();
    }

    @Override
    public boolean removeById(long id){
        Serializable serializedId = new Long(id);
        return new CrudDatabaseDao<Equipment>(Equipment.class).deleteById(serializedId);
    }

    private void countPrice(Equipment equipment){
        int protection = equipment.getProtection();
        int speed = equipment.getSpeed();
        int parry = equipment.getParry();
        equipment.setPrice(protection * 3 + speed * 4 + parry * 3);
    }
}
