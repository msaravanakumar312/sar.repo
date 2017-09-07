package com.demo.springannotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Pancard {
	@Value("#{employee.employeeName}")
private String panHolderName;
	@Value("CEKPM5257H")
private String panholdeNo;
public String getPanHolderName() {
	return panHolderName;
}
public void setPanHolderName(String panHolderName) {
	this.panHolderName = panHolderName;
}
public String getPanholdeNo() {
	return panholdeNo;
}
public void setPanholdeNo(String panholdeNo) {
	this.panholdeNo = panholdeNo;
}
}
