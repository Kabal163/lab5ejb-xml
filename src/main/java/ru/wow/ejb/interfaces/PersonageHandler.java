package ru.wow.ejb.interfaces;

import ru.wow.models.Personage;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PersonageHandler {

    boolean createPersonage(Personage personage);

    boolean removePersonage(Personage personage);

    boolean removeById(long id);

    boolean updatePersonage(Personage personage);

    Personage findPersonage(long id);

    String getPersonageAsXmlById(long id);

    List<Personage> findAllPersonage();
}
