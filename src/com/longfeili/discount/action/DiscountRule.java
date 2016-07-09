package com.longfeili.discount.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.longfeili.discount.beans.ProductInfo;

public class DiscountRule {

	private static int TheMythicalManMonthnumber = 10;
	private static int CodersatWorknumber = 3;

	public static double Rule(List<ProductInfo> datasource, Map<String, Integer> result) {
		double totalPrice = 0.0;
		int skuNumber;

		// judge purchase situation of Game of Thrones Season 1
		boolean flag = false;
		for (ProductInfo productSource : datasource) {
			Iterator<String> it = result.keySet().iterator();
			while (it.hasNext()) {
				String sourceSKU;
				sourceSKU = it.next().toString();
				if (sourceSKU.equals(productSource.getSKU())) {
					skuNumber = result.get(sourceSKU);

					switch (sourceSKU) {
					case "9325336130810":// Game of Thrones: Season 1
						totalPrice = totalPrice + productSource.getPrice() * skuNumber;
						flag = true;
						break;
					case "9325336028278":// The Fresh Prince of Bel-Air
						if (!flag)
							totalPrice = totalPrice + productSource.getPrice() * skuNumber;
						break;
					case "9780201835953":// The Mythical Man-Month
						// Buy 10 or more copies of The Mythical Man-Month,
						// and receive them at the discounted price of $21.99

						if (skuNumber >= TheMythicalManMonthnumber) {
							totalPrice = totalPrice + productSource.getDiscountPrice() * skuNumber;
						} else {
							totalPrice = totalPrice + productSource.getPrice() * skuNumber;
						}
						break;
					case "9781430219484":// Coders at Work
						// Buy 3 get 1 free
						totalPrice = totalPrice
								+ productSource.getPrice() * (skuNumber - skuNumber / CodersatWorknumber)
								+ productSource.getPrice() * (skuNumber % CodersatWorknumber);
						break;
					case "9780132071482":// Artificial Intelligence

						totalPrice = totalPrice + productSource.getPrice() * skuNumber;
						break;
					}
				}
			}
		}
		return totalPrice;
	}
}
