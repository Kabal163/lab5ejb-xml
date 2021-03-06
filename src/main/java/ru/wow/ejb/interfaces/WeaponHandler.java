package ru.wow.ejb.interfaces;

import ru.wow.models.Personage;
import ru.wow.models.Weapon;

import javax.ejb.Local;
import java.util.List;

@Local
public interface WeaponHandler {

    boolean createWeapon(Weapon weapon);

    boolean removeById(long id);

    boolean updateWeapon(Weapon weapon);

    Weapon findWeapon(long id);

    String getWeaponAsHtmlById(long id);

    String getWeaponAsHtmlByName(String name);

    String getWeaponAsHtmlByLevel(int level);

    List<Weapon> findAllWeapon();

    String getAllWeaponAsHtml();
}
