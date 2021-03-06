package com.company.java.oop.cls02;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/**
 * 类加载时,假如Metaspace内存空间比较小,可能会导致OOM(内存溢出),类创建太多了,
 * 但是为什么稍微大一点就可以一直创建??
 */
class A {
	// 原本增强是用继承或者组合,现在用CGlib来进行动态加强
	public void show() {
	}
}

//-XX:MetaspaceSize=5m初始值    默认值 ??自己测一下
//-XX:MaxMetaspaceSize=10m   最大值默认 ??
//-XX:+PrintFlagsInitial 输出JVM中默认的参数配置和值 输出的参数要加"+"
public class TestClassObject09 {
	public static void main(String[] args) {
		// 如何创建Object的子类?
		// 1)任意定义类
		// 2)借助字节码增强技术创建一个类(Object的子类)

		// 2.借助CGLIB库中的API为Object类创建子类
		
		while (true) {
			// 构建Enhancer增强对象(通过此对象为目标类创建增强对象)
			Enhancer enhancer = new Enhancer();
			// 设置父类对象类型
			enhancer.setSuperclass(Object.class);
			//不使用缓存(不使用缓存中的字节码)因为如果不设置可能会用之前建立的对象的字节码信息(属性,构造方法等)
			enhancer.setUseCache(false);
			// 设置回调对象
			enhancer.setCallback(new MethodInterceptor() {
				// 对方法进行拦截
				// 调用子类对象(代理对象)方法时自动执行这个intercept方法
				@Override
				public Object intercept(Object obj, // 增强对象(目标对象的(代理)子类对象)
						Method method, // 方法对象
						Object[] args, // 
						MethodProxy proxy) throws Throwable {
					// TODO Auto-generated method stub
					System.out.println("intercept" + obj.getClass());
					long start = System.currentTimeMillis();
					// 执行目标对象(父类)方法
					Object result=proxy.invokeSuper(obj, args);
					long end = System.currentTimeMillis();
					System.out.println("excute time:" + (end - start));
					return result;
				}
			});
			// 为object创建子类对象
			Object obj = enhancer.create();
			// String a=obj.toString(); //为什么toString会打印两次
			System.out.println(obj.hashCode());
		}
		// 3.调用Object类的方法时输出方法的执行时长

	}
}
