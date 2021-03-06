package ru.wow.ejb.interfaces;

import ru.wow.models.Equipment;
import ru.wow.models.Personage;
import ru.wow.models.Weapon;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EquipmentHandler {

    boolean createEquipment(Equipment equipment);

    boolean removeById(long id);

    boolean updateEquipment(Equipment equipment);

    Equipment findEquipment(long id);

    String getEquipmentAsHtmlById(long id);

    String getEquipmentAsHtmlByName(String name);

    String getEquipmentAsHtmlByLevel(int level);

    List<Equipment> findAllEquipment();

    String getAllEquipmentAsHtml();
}
