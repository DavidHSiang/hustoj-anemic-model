package com.zjc.hustoj.problem.xml.element.testcase;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author David Hsiang
 * @date 2021/04/13/4:11 下午
 */
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="test_output")
@Data
public class TestOutput implements TestCaseElement {

    @XmlValue
    private String value;

    public TestOutput(String value) {
        this.value = value;
    }

    public TestOutput() {
    }
}