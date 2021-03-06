package ru.wow.ejb.interfaces.impls;

import ru.wow.cdiComponents.XmlTransformer;
import ru.wow.dao.interfaces.impls.CrudDatabaseDao;
import ru.wow.ejb.interfaces.EquipmentHandler;
import ru.wow.models.Equipment;

import javax.ejb.*;
import javax.inject.Inject;
import java.io.*;
import java.util.Collection;
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
        if(transformer.validateXml(equipmentXml, "xsd/equipment.xsd")){
            return transformer.transformXmlToHtml(equipmentXml);
        } else {
            return null;
        }
    }

    @Override
    public String getEquipmentAsHtmlByName(String name) {
        Collection<Equipment> equipments = new CrudDatabaseDao<Equipment>(Equipment.class).getByName(name);
        return validateAndTransform(equipments);
    }

    @Override
    public String getEquipmentAsHtmlByLevel(int level) {
        Collection<Equipment> equipments = new CrudDatabaseDao<Equipment>(Equipment.class).getByLevel(level);
        return validateAndTransform(equipments);
    }

    @Override
    public List<Equipment> findAllEquipment() {
        return new CrudDatabaseDao<Equipment>(Equipment.class).findAllItems();
    }

    @Override
    public String getAllEquipmentAsHtml() {
        Collection<Equipment> allEquipment = new CrudDatabaseDao<Equipment>(Equipment.class).findAllItems();
        return validateAndTransform(allEquipment);
    }

    @Override
    public boolean removeById(long id){
        Serializable serializedId = new Long(id);
        return new CrudDatabaseDao<Equipment>(Equipment.class).deleteById(serializedId);
    }

    private String validateAndTransform(Collection<Equipment> equipments) {
        String allEquipmentXml = transformer.collectionToXml(equipments, "equipments", Equipment.class);
        if(equipments.size() > 0 && transformer.validateXml(allEquipmentXml, "xsd/equipmentCollection.xsd")){
            return transformer.transformXmlToHtml(allEquipmentXml);
        } else {
            return null;
        }
    }

    private void countPrice(Equipment equipment){
        int protection = equipment.getProtection();
        int speed = equipment.getSpeed();
        int parry = equipment.getParry();
        equipment.setPrice(protection * 3 + speed * 4 + parry * 3);
    }
}
