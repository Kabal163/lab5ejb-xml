package ru.wow.ejb.interfaces.impls;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.wow.dao.interfaces.impls.CrudDatabaseDao;
import ru.wow.ejb.interfaces.WeaponHandler;
import ru.wow.models.Personage;
import ru.wow.models.Weapon;

import javax.ejb.Stateless;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.util.List;

@Stateless
public class WeaponBean implements WeaponHandler{

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
    public String getWeaponAsXmlById(long id) {
        Weapon weapon = findWeapon(id);
        String weaponXml = weaponToXml(weapon);
        if(validateXml(weaponXml)){
            return transformXmlToHtml(weaponXml);
        } else {
            return null;
        }
    }

    @Override
    public List<Weapon> findAllWeapon() {
        return new CrudDatabaseDao<Weapon>(Weapon.class).findAllItems();
    }

    @Override
    public List<Personage> getAllPersWithSuchWeapon(long weaponId) {
        return null;
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

    private boolean validateXml(String weaponXml){
        System.out.println(weaponXml);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("weapon.xsd");) {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new ByteArrayInputStream(weaponXml.getBytes("UTF-8"))));
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source constraint = new StreamSource(is);
            Schema schema = schemaFactory.newSchema(constraint);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));
            System.out.println("Document validated fine");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println("Validation error: " + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String weaponToXml(Weapon weapon){
        String weaponXml = null;
        StringWriter writer = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Weapon.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(weapon, writer);
            weaponXml = writer.toString();
        } catch (JAXBException e) {
            System.out.println("Weapon marshalling exception");
        }
        return weaponXml;
    }

    private String transformXmlToHtml(String weaponXml){
        String resultHtml = null;
        StringReader reader = new StringReader(weaponXml);
        StringWriter writer = new StringWriter();
        TransformerFactory factory = TransformerFactory.newInstance();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream("itemsStylesheet.xsl")){
            Source xslt = new StreamSource(is);
            Source xml = new StreamSource(reader);
            Result html = new StreamResult(writer);
            Transformer transformer = factory.newTransformer(xslt);
            transformer.transform(xml, html);
            resultHtml = writer.toString();
            System.out.println(resultHtml);
        } catch (TransformerException | IOException e) {
            System.out.println("Exception in xml transforming: " + e);
        }
        return resultHtml;
    }
}
