package com.zjc.hustoj.problem.xml.element;

import com.zjc.hustoj.problem.xml.element.testcase.TestCaseElement;
import com.zjc.hustoj.problem.xml.element.testcase.TestInput;
import com.zjc.hustoj.problem.xml.element.testcase.TestOutput;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/13/8:16 下午
 */
@Setter
@XmlRootElement(name = "item")
@XmlType(propOrder = {"title", "timeLimit", "memoryLimit",
        "description", "input", "output" ,
        "sampleInput", "sampleOutput","dataList", "hint", "source"})
public class ProblemXmlEntity {;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 问题描述
     */
    private String description;

    /**
     * 输入描述
     */
    private String input;

    /**
     * 输出描述
     */
    private String output;

    /**
     * 输入示例
     */
    private String sampleInput;

    /**
     * 输出示例
     */
    private String sampleOutput;

    /**
     * 提示
     */
    private String hint;

    /**
     * 分类
     */
    private String source;

    /**
     * 限时 ( 秒 )
     */
    private BigDecimal timeLimit;

    /**
     * 空间限制 (MByte)
     */
    private Integer memoryLimit;

    /**
     * 测试用例
     */
    private List<TestCaseElement> dataList;

    @XmlElement
    public String getTitle() {
        return xmlFormat(title);
    }

    @XmlElement(name = "time_limit")
    public TimeLimit getTimeLimit() {
        return new TimeLimit(xmlFormat(timeLimit));
    }

    @XmlElement(name = "memory_limit")
    public MemoryLimit getMemoryLimit() {
        return new MemoryLimit(xmlFormat(memoryLimit));
    }

    @XmlElement
    public String getDescription() {
        return xmlFormat(description);
    }

    @XmlElement
    public String getInput() {
        return xmlFormat(input);
    }

    @XmlElement
    public String getOutput() {
        return xmlFormat(output);
    }

    @XmlElement
    public String getSampleInput() {
        return xmlFormat(sampleInput);
    }

    @XmlElement
    public String getSampleOutput() {
        return xmlFormat(sampleOutput);
    }

    @XmlElement
    public String getHint() {
        return xmlFormat(hint);
    }

    @XmlElement
    public String getSource() {
        return xmlFormat(source);
    }

    @XmlElements(value = { @XmlElement(name = "test_input", type = TestInput.class), @XmlElement(name = "test_output", type = TestOutput.class) })
    public List<TestCaseElement> getDataList() {
        return dataList;
    }

    private String xmlFormat(Object value){
        String str = value==null?"":value.toString();
        return "<![CDATA[" + str + "]]>";
    }

    /**
     * @author David Hsiang
     * @date 2021/04/13/2:29 下午
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name="time_limit")
    @NoArgsConstructor
    private static class TimeLimit {

        @XmlAttribute
        private String unit = "s";

        @XmlValue
        private String value;

        public TimeLimit(String value) {
            this.value = value;
        }
    }

    /**
     * @author David Hsiang
     * @date 2021/04/13/2:29 下午
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name="memory_limit")
    @NoArgsConstructor
    public static class  MemoryLimit {

        @XmlAttribute
        private String unit = "mb";

        @XmlValue
        private String value;

        public MemoryLimit(String value) {
            this.value = value;
        }
    }
}