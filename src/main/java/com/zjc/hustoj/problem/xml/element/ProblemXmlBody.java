package com.zjc.hustoj.problem.xml.element;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/13/9:19 下午
 */
@Setter
@XmlRootElement(name = "fps")
@NoArgsConstructor
public class ProblemXmlBody {

    private List<ProblemXmlEntity> entities;

    @XmlElements(value = { @XmlElement(name = "item", type = ProblemXmlEntity.class) })
    public List<ProblemXmlEntity> getEntities() {
        return entities;
    }

    private ProblemXmlBody(List<ProblemXmlEntity> entities) {
        this.entities = entities;
    }

    public static ProblemXmlBody create(List<ProblemXmlEntity> entities){
        return new ProblemXmlBody(entities);
    }
}
