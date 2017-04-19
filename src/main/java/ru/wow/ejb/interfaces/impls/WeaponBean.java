package ru.wow.ejb.interfaces.impls;

import ru.wow.cdiComponents.XmlTransformer;
import ru.wow.dao.interfaces.impls.CrudDatabaseDao;
import ru.wow.ejb.interfaces.WeaponHandler;
import ru.wow.models.Weapon;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.*;
import java.util.Collection;
import java.util.List;

@Stateless
public class WeaponBean implements WeaponHandler{

    @Inject
    private XmlTransformer transformer;

    @Override
    public boolean createWeapon(Weapon weapon) {
        countPrice(weapon);
        return new CrudDatabaseDao<Weapon>(Weapon.class).createItem(weapon);
    }

    @Override
    public boolean removeWeapon(Weapon weapon) {
        return false;
    }

    @Override
    public boolean updateWeapon(Weapon weapon) {
        countPrice(weapon);
        return new CrudDatabaseDao<Weapon>().updateItem(weapon);
    }

    @Override
    public Weapon findWeapon(long id) {
        return new CrudDatabaseDao<Weapon>(Weapon.class).getById(id);
    }

    @Override
    public String getWeaponAsHtmlById(long id) {
        Weapon weapon = findWeapon(id);
        String weaponXml = transformer.itemToXml(weapon);
        if(transformer.validateXml(weaponXml, "xsd/weapon.xsd")){
            return transformer.transformXmlToHtml(weaponXml);
        } else {
            return null;
        }
    }

    @Override
    public String getWeaponAsHtmlByName(String name) {
        Collection<Weapon> weapons = new CrudDatabaseDao<Weapon>(Weapon.class).getByName(name);
        String weaponsXml = transformer.collectionToXml(weapons, "weapons", Weapon.class);
        if (weapons.size() > 0 && transformer.validateXml(weaponsXml, "xsd/weaponCollection.xsd")) {
            return transformer.transformXmlToHtml(weaponsXml);
        } else {
            return null;
        }
    }

    @Override
    public List<Weapon> findAllWeapon() {
        return new CrudDatabaseDao<Weapon>(Weapon.class).findAllItems();
    }

    @Override
    public String getAllWeaponAsHtml() {
        Collection<Weapon> allWeapon = new CrudDatabaseDao<Weapon>(Weapon.class).findAllItems();
        String allWeaponXml = transformer.collectionToXml(allWeapon, "weapons", Weapon.class);
        if(allWeapon.size() > 0 && transformer.validateXml(allWeaponXml, "xsd/weaponCollection.xsd")){
            return transformer.transformXmlToHtml(allWeaponXml);
        } else {
            return null;
        }
    }

    @Override
    public boolean removeById(long id){
        Serializable serializedId = new Long(id);
        return new CrudDatabaseDao<Weapon>(Weapon.class).deleteById(serializedId);
    }

    private void countPrice(Weapon weapon){
        int power = weapon.getPower();
        int parry = weapon.getParry();
        weapon.setPrice(power * 3 + parry * 2);
    }

}
