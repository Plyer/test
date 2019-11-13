package org.ljl.test.test;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lvjinglu
 * created at 2019/11/13
 */
public class MyClosableClass implements AutoCloseable {

    private static Integer id = 0;

    public MyClosableClass() {
        id++;
    }

    public void doIt(int i) {
        System.out.println("do it: " + i);
    }

    @Override
    public void close() throws Exception {
        System.out.println( id+ " 关闭成功");
//        throw new RuntimeException(id + "关闭异常");
    }

    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("/Users/abc/Documents/1.txt");
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            bos.write(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Buffer implements AutoCloseable{
    private MyClosableClass myClosableClass;

    public Buffer(MyClosableClass myClosableClass) {
        this.myClosableClass = myClosableClass;
    }

    @Override
    public void close() throws Exception {
        if (myClosableClass != null) {
            myClosableClass.close();
        }
    }
}
