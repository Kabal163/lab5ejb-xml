package ru.wow.ejb.interfaces;

import ru.wow.models.Personage;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PersonageHandler {

    boolean createPersonage(Personage personage);

    boolean removeById(long id);

    boolean updatePersonage(Personage personage);

    Personage findPersonage(long id);

    String getPersonageAsXmlById(long id);

    String getPersonageAsHtmlByNickname(String nickname);

    String getPersonageAsHtmlByLevel(int level);

    List<Personage> findAllPersonage();

    String getAllPersonagesAsHtml();
}
