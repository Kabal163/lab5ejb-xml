package ru.wow.ejb.interfaces.impls;

import ru.wow.cdiComponents.XmlTransformer;
import ru.wow.dao.interfaces.impls.CrudDatabaseDao;
import ru.wow.ejb.interfaces.EquipmentHandler;
import ru.wow.models.Equipment;
import ru.wow.models.Personage;

import javax.ejb.*;
import javax.inject.Inject;
import java.io.*;
import java.util.List;

@Stateless
public class EquipmentBean implements EquipmentHandler, Serializable{

    @Inject
    private XmlTransformer transformer;

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
    public String getEquipmentAsHtmlById(long id) {
        Equipment equipment = findEquipment(id);
        String equipmentXml = transformer.itemToXml(equipment);
        if(transformer.validateXml(equipmentXml, "equipment.xsd")){
            return transformer.transformXmlToHtml(equipmentXml);
        } else {
            return null;
        }
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
