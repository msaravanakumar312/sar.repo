package com.demo.springannotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppDemo {
public static void main(String[] args) {
	ApplicationContext applicationContext=new ClassPathXmlApplicationContext("Spring-Module.xml");
	Employee employee=applicationContext.getBean("employee", Employee.class);
	System.out.println(employee.getEmployeeId()+"......."+employee.getEmployeeName());
	Pancard pancard=employee.getPancard();
	System.out.println(pancard.getPanholdeNo()+"...."+pancard.getPanHolderName());
}
}
