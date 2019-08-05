package com.javatest.proxytest;

import android.content.Context;
import android.widget.Toast;

import com.javatest.ToastUtils;

/**
 * Author: zzhh
 * Date: 2019/8/5 11:08
 * Description:
 */
public class Student implements Person {
    private String name;
    private Context context;

    public Student(Context context, String name){
        this.context = context;
        this.name = name;
    }
    @Override
    public void giveMoney() {
        try {
            //假设数钱花了一秒时间
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ToastUtils.showShort(context, name + "上交班费10元");
    }

    //—————构造方法——————-  
    //（默认的构造方法）  
    Student(String str){
        System.out.println("(默认)的构造方法 s = " + str);
    }

    //无参构造方法  
    public Student(){
        System.out.println("调用了公有、无参构造方法执行了。。。");
    }

    //有一个参数的构造方法  
    public Student(char name){
        System.out.println("姓名：" + name);
    }

    //有多个参数的构造方法  
    public Student(String name ,int age){
        System.out.println("姓名："+name+"年龄："+ age);//这的执行效率有问题，以后解决。
    }

    //受保护的构造方法  
    protected Student(boolean n){
        System.out.println("受保护的构造方法 n = " + n);
    }

    //私有构造方法  
    private Student(int age){
        System.out.println("私有的构造方法   年龄："+ age);
    }
}
