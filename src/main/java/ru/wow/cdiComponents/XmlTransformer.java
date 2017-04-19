package ru.wow.cdiComponents;


import org.hibernate.proxy.HibernateProxy;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.wow.cdiAnnotations.ServerUri;
import ru.wow.models.Model;
import ru.wow.models.Weapon;

import javax.inject.Inject;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
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
import java.lang.reflect.Array;
import java.util.Collection;

public class XmlTransformer {

    @Inject @ServerUri
    private String serverUri;

    public String itemToXml(Object item){
        String itemXml = null;
        StringWriter writer = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(getUnproxyModel(item).getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(item, writer);
            itemXml = writer.toString();
        } catch (JAXBException e) {
            System.out.println("Marshalling exception");
            e.printStackTrace();
        }
        return itemXml;
    }

    public <T> String collectionToXml(Collection<T> items, String qName, Class<T> elementClass){
        String itemsXml = null;
        StringWriter writer = new StringWriter();
        try {
            T[] array = (T[]) Array.newInstance(elementClass, items.size());
            items.toArray(array);
            JAXBContext jaxbContext = JAXBContext.newInstance(array.getClass());
            JAXBElement<T[]> root = new JAXBElement<T[]>(new QName(qName), (Class<T[]>) array.getClass(), array);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(root, writer);
            itemsXml = writer.toString();
        } catch (JAXBException e) {
            System.out.println("Collection marshalling exception");
            e.printStackTrace();
        }
        return itemsXml;
    }

    public boolean validateXml(String itemXml, String schemaFileName){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classloader.getResourceAsStream(schemaFileName);) {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new ByteArrayInputStream(itemXml.getBytes("UTF-8"))));
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source constraint = new StreamSource(is);
            Schema schema = schemaFactory.newSchema(constraint);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println("Validation error: " + e);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String transformXmlToHtml(String weaponXml){
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
            transformer.setParameter("serverUri", serverUri);
            transformer.transform(xml, html);
            resultHtml = writer.toString();
        } catch (TransformerException | IOException e) {
            System.out.println("Exception in xml transforming: " + e);
        }
        return resultHtml;
    }

    private Object getUnproxyModel(Object item) {
        if (HibernateProxy.class.isAssignableFrom(item.getClass())) {
            return ((HibernateProxy)item).getHibernateLazyInitializer().getImplementation();
        }
        return item;
    }
}
