package ru.wow.ejb.interfaces.impls;

import ru.wow.cdiComponents.XmlTransformer;
import ru.wow.dao.interfaces.impls.CrudDatabaseDao;
import ru.wow.ejb.interfaces.PersonageHandler;
import ru.wow.models.Personage;
import ru.wow.models.Weapon;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
public class PersonageBean implements PersonageHandler{

    @Inject
    private XmlTransformer transformer;

    @Override
    public boolean createPersonage(Personage personage) {
        countPesonageStatistics(personage);
        return new CrudDatabaseDao<Personage>().createItem(personage);
    }

    @Override
    public boolean removePersonage(Personage personage) {
        return false;
    }

    @Override
    public boolean updatePersonage(Personage personage) {
        countPesonageStatistics(personage);
        return new CrudDatabaseDao<Personage>().updateItem(personage);
    }

    @Override
    public Personage findPersonage(long id) {
        Personage personage = new CrudDatabaseDao<Personage>(Personage.class).getById(id);
        countPesonageStatistics(personage);
        return personage;
    }

    @Override
    public String getPersonageAsXmlById(long id) {
        Personage personage = findPersonage(id);
        String personageXml = transformer.itemToXml(personage);
        if(transformer.validateXml(personageXml, "personage.xsd")){
            return transformer.transformXmlToHtml(personageXml);
        } else {
            return null;
        }
    }

    @Override
    public List<Personage> findAllPersonage() {
        return new CrudDatabaseDao<Personage>(Personage.class).findAllItems();
    }

    @Override
    public boolean removeById(long id){
        Serializable serializedId = new Long(id);
        return new CrudDatabaseDao<Personage>(Personage.class).deleteById(serializedId);
    }

    private void countPesonageStatistics(Personage personage){
        int power = personage.getWeapon().getPower();
        int parry = personage.getWeapon().getParry() + personage.getEquipment().getParry();
        int speed = personage.getEquipment().getSpeed();
        int protection = personage.getEquipment().getProtection();
        personage.setPower(power*31);
        personage.setParry(parry*17);
        personage.setSpeed(speed*19);
        personage.setProtection(protection*27);
    }
}
