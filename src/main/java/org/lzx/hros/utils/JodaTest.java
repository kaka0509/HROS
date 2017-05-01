package org.lzx.hros.utils;

import org.joda.time.DateTime;

public class JodaTest {

	public static void main(String[] args) {
		DateTime dt = new DateTime();
		int month = dt.getMonthOfYear();
		System.out.println(month);
	}

}
