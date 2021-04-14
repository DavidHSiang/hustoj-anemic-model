package com.zjc.hustoj.problem.xml.element.testcase;

import java.util.ArrayList;

/**
 * @author David Hsiang
 * @date 2021/04/13/4:15 下午
 */
public class TestCaseList extends ArrayList<TestCaseElement> {

    public boolean add(TestCase testCase) {
        super.add(testCase.getTestInput());
        super.add(testCase.getTestOutput());
        return true;
    }
}
