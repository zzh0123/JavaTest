package com.javatest.proxytest;

import android.content.Context;
import android.util.Log;

import com.javatest.ToastUtils;

/**
 * Author: zzhh
 * Date: 2019/8/5 11:12
 * Description: 学生代理类，实现同样的接口，保存一个学生实体，这样可以代理学生产生的行为
 */
public class StudentProxy implements Person {

    // 被代理的学生
    private Student student;
    public StudentProxy(Person student){
        // 只代理学生类
        if (student.getClass() == Student.class){
            this.student = (Student) student;
        }
    }

    // 代理上交班费，调用被代理学生的上交班费行为
    @Override
    public void giveMoney() {
        // 代理模式就是在访问实际对象时引入一定程度的间接性，因为这种间接性，可以附加多种用途。
        Log.i("-----", "张三最近学习进步");
        student.giveMoney();
    }
}
