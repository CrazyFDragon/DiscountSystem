package com.longfeili.discount.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.longfeili.discount.beans.ProductInfo;

public class Display {

	/**
	 * Display all Products information.
	 * 
	 */
	public static void ProductDisplay(List<ProductInfo> datasource) {
		System.out.println("      SKU        |        NAME                |     PRICE     ");
		System.out.println("-----------------|----------------------------|---------------");
		for (ProductInfo source : datasource) {
			System.out.println("  " + source.getSKU() + "  |  " + source.getName() + "  |  " + "$" + source.getPrice());
		}
		System.out.println("-----------------|----------------------------|---------------");
		System.out.println("");
	}

	/**
	 * Display final cart information and total cart price.
	 * 
	 */
	public static void FinalResult(List<ProductInfo> datasource, Vector<String> productSKU,
			Map<String, Integer> result) {
		int productNumber = 0;
		String sourceSKU;
		// calculate total number of each product in the cart.
		for (int i = 0; i < productSKU.size(); i++) {
			boolean existflg = false;
			sourceSKU = productSKU.get(i);
			// void not existing product
			for (ProductInfo productSource : datasource) {
				if (sourceSKU.equals(productSource.getSKU())) {
					existflg = true;
				}
			}
			if (existflg) {
				if (result.get(sourceSKU) != null) {
					productNumber = result.get(sourceSKU);
					productNumber++;
				} else {
					productNumber++;
				}
				result.put(sourceSKU, productNumber);
				productNumber = 0;
			}

		}
		// calculate final price following discount rule.
		double finalprice = DiscountRule.Rule(datasource, result);
		// output result.
		System.out.print("Products in cart: ");
		Iterator<String> it = result.keySet().iterator();
		while (it.hasNext()) {
			sourceSKU = it.next().toString();
			if (result.get(sourceSKU) > 1) {
				System.out.print(sourceSKU + (" X ") + result.get(sourceSKU) + (", "));
			} else {
				System.out.print(sourceSKU + (", "));
			}
		}
		System.out.println(" ");
		System.out.println("Expected total: $" + finalprice);
	}
}
