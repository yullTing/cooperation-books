package com.intermediary;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
* 动态代理：在动态代理模式中，被代理对象（即目标对象、正主）仍需要实现接口，但代理对象（即代理）不需要实现接口。
* BorrowReturnInter代理对象类，继承BorrowReturnImpl接口，重写方法，后续通过调用代理对象实现借阅图书
* */
public class Intermediary {
    private Object host;
    public Intermediary(Object host) {
        this.host = host;
    }

    // 给目标对象（正主）生成一个代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(host.getClass().getClassLoader(),
                host.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //System.out.println("动态代理开始…………");
                        Object result = method.invoke(host, args);
                        //System.out.println("结束动态代理…………");
                        return result;
                    }
                });
    }

}
