package com.zjc.hustoj.core.utils.xml;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.internal.bind.marshaller.NoEscapeHandler;
import org.apache.commons.io.output.XmlStreamWriter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.xml.sax.ContentHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.soap.Node;
import javax.xml.stream.XMLEventWriter;
import javax.xml.transform.Result;
import java.io.*;

/**
 * @author David Hsiang
 * @date 2021/04/12/11:23 下午
 */

public class JaxbUtils <E> {

    private static CharacterEscapeHandler escapeHandler = NoEscapeHandler.theInstance;

    private Marshaller marshaller ;

    public static <E> JaxbUtils<E> create(Class<E> clazz) throws JAXBException {
        return new JaxbUtils(clazz);
    }

    public static InputStream getInputStream(Object entity) throws JAXBException {
        return new ByteArrayInputStream(JaxbUtils.getBytes(entity));
    }

    public static <T> byte[] getBytes(T entity) throws JAXBException {
        JaxbUtils jaxbUtils = JaxbUtils.create(entity.getClass());
        return jaxbUtils.toByteArray(entity);
    }

    private JaxbUtils(Class<E> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        this.marshaller = jaxbContext.createMarshaller();
        this.marshaller.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler", escapeHandler);
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        this.marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        this.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
    }

    public <R extends Result> R write(E entity, R result) throws JAXBException {
        marshaller.marshal(entity, result);
        return result;
    }

    public <R extends OutputStream> R write(E entity, R outputStream) throws JAXBException {
        marshaller.marshal(entity, outputStream);
        return outputStream;
    }

    public <R extends File> R write(E entity, R file) throws JAXBException {
        marshaller.marshal(entity, file);
        return file;
    }

    public <R extends Writer> R write(E entity, R writer) throws JAXBException {
        marshaller.marshal(entity, writer);
        return writer;
    }

    public <R extends ContentHandler> R write(E entity, R handler) throws JAXBException {
        marshaller.marshal(entity, handler);
        return handler;
    }

    public <R extends Node> R write(E entity, R node) throws JAXBException {
        marshaller.marshal(entity, node);
        return node;
    }

    public <R extends XmlStreamWriter> R write(E entity, R streamWriter) throws JAXBException {
        marshaller.marshal(entity, streamWriter);
        return streamWriter;
    }

    public <R extends XMLEventWriter> R write(E entity, R eventWriter) throws JAXBException {
        marshaller.marshal(entity, eventWriter);
        return eventWriter;
    }

    public InputStream toInputStream(E entity) throws JAXBException {
        return new ByteArrayInputStream(toByteArray(entity));
    }

    public byte[] toByteArray(E entity) throws JAXBException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        this.write(entity, bos);
        return bos.toByteArray();
    }
}