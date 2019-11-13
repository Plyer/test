package org.ljl.test.son;

import org.ljl.test.father.Father;

/**
 * @author lvjinglu
 * created at 2019/10/23
 */
public class Son extends Father {
    protected String name = "son";

    public static void main(String[] args) {
        Son son = new Son();
        System.out.println(son.getName());
        son.setName("father1");
        System.out.println(son.getName());
    }
}
