package com.company.java.oop.cls02;

import java.util.HashMap;
import java.util.Map;

/**
 * 案例:考察类变量的初始化顺序(从上到下依次执行)
 * @author soft01
 *
 */
class ClassD{
	static Map<String,Object> map=new HashMap<>(); //实例变量初始化会在构造方法之前执行,实例代码块呢?
	static ClassD instance= new ClassD();
	public ClassD() {
		map.put("A", 100);
		map.put("B", 200);
	}
}
public class TestClassObject07 {
	public static void main(String[] args) throws ClassNotFoundException {
		//会触发类加载
		//类加载阶段:加载,连接(准备,验证,解析(进行默认初始化)),初始化赋值(课后了解)
		System.out.println(ClassD.map);
	}
}
