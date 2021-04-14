package com.zjc.hustoj.problem.xml.element.testcase;

import lombok.Data;
import lombok.ToString;

/**
 * @author David Hsiang
 * @date 2021/04/13/3:08 下午
 */
@ToString
@Data
public class TestCase
{
    public static final int FIELD_NUM = 2;

    private TestInput testInput;

    private TestOutput testOutput;

    public TestCase(String testInputValue, String testOutputValue) {
        this.testInput = new TestInput(testInputValue);
        this.testOutput = new TestOutput(testOutputValue);
    }
}