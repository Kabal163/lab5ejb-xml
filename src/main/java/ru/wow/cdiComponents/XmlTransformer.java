package ru.wow.cdiComponents;


import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.wow.models.Model;

import javax.inject.Inject;
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

public class XmlTransformer {

    @Inject
    private String serverUri;

    public String itemToXml(Model item){
        String itemXml = null;
        StringWriter writer = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(item.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(item, writer);
            itemXml = writer.toString();
        } catch (JAXBException e) {
            System.out.println("Weapon marshalling exception");
        }
        return itemXml;
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
}
