package com.longfeili.discount.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

public class CartInput {

	public static Vector<String> productList() {
		Vector<String> productSKU = new Vector<String>();
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));
		System.out.println("Please input the SKU number of product which you like and input TOTAL to finish your shopping");
		String line = null;
		try {
			while ((line = bufr.readLine()) != null) {
				if ("total".equals(line)||"TOTAL".equals(line))
					break;
				if (line.length() > 14) {
					System.out.println("THIS IS INVAID NUMBER !!! Please following above SKU numbers");
				} else {
					productSKU.add(line);
				}
				bufw.newLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productSKU;
	}
}
