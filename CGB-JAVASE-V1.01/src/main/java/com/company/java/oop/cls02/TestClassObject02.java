package com.company.java.oop.cls02;

/**
 * 案例:外部类加载时会加载内部类吗?不会,无论是静态还是非静态
 * 内部类加载时会加载外部类吗?也不会
 * 只有用new的时候才会加载
 */
 class Outer{
	class Inner01{}//实例内部类
	static class Inner02{}//静态内部类 //外部类是不允许静态的
}
public class TestClassObject02 {
	public static void main(String[] args) throws Exception {
		/*此类会被加载,但Inner不会,加载内部类外部类也不会被加载.(用获取class对象的方法加载class对象)
		*/
		//Class<?> c1=Outer.class;//static类,加载外部类的时候也不会加载内部类.
		Class<?> c2=Outer.Inner01.class;//加载内部类,外部类也不会被加载 隐式加载
		Class<?> c3=Class.forName("com.company.java.oop.cls02.Outer$Inner01");//显式加载
		
		System.out.println(c2==c3);
		Class<?> c4=Outer.Inner02.class;
		Class<?> c5=Class.forName("com.company.java.oop.cls02.Outer$Inner02");
		System.out.println(c4==c5);
		new Outer().new Inner01();//构建实例内部类对象,会加载外部类和内部类
		new Outer.Inner02();//构建静态内部类对象,只会加载内部类
		//new Outer.Inner01();//会报错
		
	}
	
}
