package com.longfeili.discount.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.longfeili.discount.beans.ProductList;

public class Cart {

	public static void main(String[] args) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("cart.xml");
		ProductList datalist = (ProductList) ctx.getBean("productList");
		Display.ProductDisplay(datalist.getDatasource());

		Vector<String> productSKU = CartInput.productList();
		Map<String, Integer> result = new HashMap<String, Integer>();
		Display.FinalResult(datalist.getDatasource(), productSKU, result);
	}
}