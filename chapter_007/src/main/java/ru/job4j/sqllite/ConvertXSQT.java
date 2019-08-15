package ru.job4j.sqllite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Класс для конвертации xml по средствам XSQT.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class ConvertXSQT {

    /**
     * Логер.
     */
    private static final Logger LOG = LogManager.getLogger(ConvertXSQT.class.getName());

    /**
     * Читает файл source и преобразовывает его в файл dest за счет XSTL схемы scheme.
     * @param source - XML файл, который нужно преобразовать.
     * @param dest - преобразованный XML файл.
     * @param scheme - схема преобразования XSL.
     */
    public void convert(File source, File dest, File scheme) {
        Source xsl = new StreamSource(scheme);
        Source xml = new StreamSource(source);
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer(xsl);
            transformer.transform(xml, new StreamResult(dest));
        } catch (TransformerException e) {
            LOG.error("In convert {}", e);
        }
    }
}
