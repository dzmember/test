package com.company.test;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.junit.Test;

public class TomcatTests {
	@Test//嵌入式tomcat应用
	public void testTomcat() throws LifecycleException {
		//1.构建tomcat对象,启动tomcat的入口对象
		Tomcat t=new Tomcat();
		//2.构建Connector对象(连接器),负责协议配置,端口配置等
		Connector conn = new Connector("HTTP/1.1");
		//设置服务器的监听端口,传递给ServerSocket(http协议中的内容) ?
		conn.setPort(80);
		//将Connector注册到service中
		t.getService().addConnector(conn);
		//3.启动tomcat
		t.start();
		//4.阻塞当前线程,相当于守护线程不让线程关闭
		System.out.println(Thread.currentThread().getName());//打印当前线程
		t.getServer().await();
	}
	@Test//嵌入式tomcat应用
	public void testServlet() throws LifecycleException {
		//1.构建tomcat对象,启动tomcat的入口对象 然后需要一个service对象,然后再需要connection对象
		Tomcat t=new Tomcat();
		//2.构建Connector对象(连接连接器),负责协议配置,端口配置等
		Connector conn = new Connector("HTTP/1.1");
		//设置服务器的监听端口,传递给ServerSocket(http协议中的内容) ?//为什么8080被占用??
		conn.setPort(80);
		conn.setURIEncoding("UTF-8");
		//3.将Connector注册到service中
		t.getService().addConnector(conn);
		
		//4.配置/部署servlet对象
		//4.1构建Context对象,并设置path为"/"
		Context ctx=t.addContext("/", null);//context是添加的项目 第一个是项目的访问路径,第二个是项目实际路径
		//4.2注册servlet
		Tomcat.addServlet(ctx, "HelloServlet", "com.company.java.servlet.HelloServlet");
		//4.3映射servlet
		ctx.addServletMappingDecoded("/hello", "HelloServlet");
		
		//5.启动tomcat
		t.start();
		//6.阻塞当前线程
		System.out.println(Thread.currentThread().getName());//打印当前线程
		t.getServer().await();//本身是由一个守护进程来防止结束的的
	}
}
