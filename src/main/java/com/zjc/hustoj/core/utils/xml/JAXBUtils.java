package com.zjc.hustoj.core.utils.xml;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.internal.bind.marshaller.NoEscapeHandler;
import com.zjc.hustoj.core.constant.MemoryFileOutputStream;
import com.zjc.hustoj.core.exception.ServiceException;
import com.zjc.hustoj.problem.xml.element.ProblemXmlBody;
import com.zjc.hustoj.problem.xml.element.ProblemXmlEntity;
import com.zjc.hustoj.problem.xml.element.testcase.TestCase;
import com.zjc.hustoj.problem.xml.element.testcase.TestCaseList;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.XmlStreamWriter;
import org.xml.sax.ContentHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.soap.Node;
import javax.xml.stream.XMLEventWriter;
import javax.xml.transform.Result;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/12/11:23 下午
 */

public class JAXBUtils {
    
    private static CharacterEscapeHandler escapeHandler = NoEscapeHandler.theInstance;

    private Marshaller marshaller ;
    private Object entity;

    private JAXBUtils(Object entity) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(entity.getClass());
        this.marshaller = jaxbContext.createMarshaller();
        this.marshaller.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler", escapeHandler);
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        this.marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        this.marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);

        this.entity = entity;
    }

    public static JAXBUtils entity(Object entity) throws JAXBException {
        return new JAXBUtils(entity);
    }

    public Result writeTo(Result result) throws JAXBException {
        marshaller.marshal(this.entity, result);
        return result;
    }

    public <T extends OutputStream> T writeTo(T outputStream) throws JAXBException {
        marshaller.marshal(this.entity, outputStream);
        return outputStream;
    }

    public <T extends File> T writeTo(T file) throws JAXBException {
        marshaller.marshal(this.entity, file);
        return file;
    }

    public <T extends Writer> T writeTo(T writer) throws JAXBException {
        marshaller.marshal(this.entity, writer);
        return writer;
    }

    public <T extends ContentHandler> T writeTo(T handler) throws JAXBException {
        marshaller.marshal(this.entity, handler);
        return handler;
    }

    public <T extends Node> T writeTo(T node) throws JAXBException {
        marshaller.marshal(this.entity, node);
        return node;
    }

    public <T extends XmlStreamWriter> T writeTo(T streamWriter) throws JAXBException {
        marshaller.marshal(this.entity, streamWriter);
        return streamWriter;
    }

    public <T extends XMLEventWriter> T writeTo(T eventWriter) throws JAXBException {
        marshaller.marshal(this.entity, eventWriter);
        return eventWriter;
    }
}