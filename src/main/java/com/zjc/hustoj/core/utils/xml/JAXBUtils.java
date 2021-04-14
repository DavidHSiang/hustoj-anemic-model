package com.zjc.hustoj.core.utils.xml;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.internal.bind.marshaller.NoEscapeHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/12/11:23 下午
 */
public class JAXBUtils {

    public static void generateXML(Object entity, OutputStream output) {

        // File file = new File("/Users/david/Documents/person.xml");
        JAXBContext jc = null;
        try {
            //根据Person类生成上下文对象
            jc = JAXBContext.newInstance(entity.getClass());
            //从上下文中获取Marshaller对象，用作将bean编组(转换)为xml
            Marshaller ma = jc.createMarshaller();

            CharacterEscapeHandler escapeHandler = NoEscapeHandler.theInstance;
            ma.setProperty("com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler", escapeHandler);

            //以下是为生成xml做的一些配置
            //格式化输出，即按标签自动换行，否则就是一行输出
            ma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //设置编码（默认编码就是utf-8）
            ma.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //是否省略xml头信息，默认不省略（false）
            ma.setProperty(Marshaller.JAXB_FRAGMENT, false);

            //编组
            // ma.marshal(entity, file);
            ma.marshal(entity,output);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}