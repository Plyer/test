package org.ljl.test.father;

/**
 * @author lvjinglu
 * created at 2019/10/23
 */
public class Father {
    protected String name = "father";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Father self() {
        return this;
    }
}
