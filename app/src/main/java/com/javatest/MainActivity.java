package com.javatest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.javatest.proxytest.Person;
import com.javatest.proxytest.Student;
import com.javatest.proxytest.StudentInvocationHandler;
import com.javatest.proxytest.StudentProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_java_static_proxy)
    public TextView tv_java_static_proxy;

    @BindView(R.id.tv_java_dynamic_proxy)
    public TextView tv_java_dynamic_proxy;

    @BindView(R.id.tv_reflect)
    public TextView tv_reflect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){

    }

    @OnClick({R.id.tv_java_static_proxy, R.id.tv_java_dynamic_proxy, R.id.tv_reflect})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_java_static_proxy:
//                Log.i("tv_java_static_proxy", "--tv_java_static_proxy--");
                // 被代理的学生张三，他的上交班费的行为由班长monitor完成
                Student student = new Student(this, "张三");
                // 生成代理对象，并把对象传给代理对象
                Person monitor = new StudentProxy(student);
                // 班长代理上交班费
                monitor.giveMoney();
                break;
            case R.id.tv_java_dynamic_proxy:
                //创建一个实例对象，这个对象是被代理的对象
                Person zhangsan = new Student(this, "张三");
                //创建一个与代理对象相关联的InvocationHandler
                InvocationHandler studentInvocationHandler = new StudentInvocationHandler<Person>(zhangsan);
                //创建一个代理对象stuProxy来代理zhangsan，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
                Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class},
                        studentInvocationHandler);
                //代理执行上交班费的方法
                stuProxy.giveMoney();
                break;
            case R.id.tv_reflect:
                // Class 对象是类加载时，类加载器自动创建，有且只有一个，在运行期间，一个类，只有一个Class对象产生。
                //第一种方式获取Class对象
                Student stu1 = new Student(this, "李四");//这一new 产生一个Student对象，一个Class对象。
                Class stuClass = stu1.getClass();//获取Class对象
                System.out.println(stuClass.getName());

                //第二种方式获取Class对象
                Class stuClass2 = Student.class;
                System.out.println(stuClass == stuClass2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

                //第三种方式获取Class对象
                try {
                    Class stuClass3 = Class.forName("com.javatest.proxytest.Student");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
                    System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
