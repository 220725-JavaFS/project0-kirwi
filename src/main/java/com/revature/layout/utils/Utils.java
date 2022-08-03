package com.revature.layout.utils;

import java.util.Arrays;

public class Utils {
	
	public static String[] reverse(String[] strArray) {
		String[] reversed = new String[strArray.length];
		for (int i=0; i<strArray.length; i++) {
			reversed[i] = strArray[strArray.length-1 - i];
		}
		return reversed;
	}
	
	public static String stringMult(String str, int m) {
		StringBuilder sb = new StringBuilder(str);
		int i = 1;
		while (i < m) {
			sb.append(str);
			i++;
		}
		return new String(sb);
	}
	
	public static String[] arrConcat(String[] a, String[] b) {
		String[] out = new String[a.length + b.length];
		int i = 0;
		while (i < (a.length + b.length)) {
			if (i < a.length) {
				out[i] = a[i];
			} else {
				out[i] = b[i-a.length-1];
			}
		}
		return out;
	}
	
	public static String[] arrZipConcat(String[] a, String[] b) {
		String[] out = new String[a.length];
		int i = 0;
		while (i < a.length) {
			out[i] = a[i] + b[i];
			i++;
		}
		return out;
	}
	
	public static String[] arrFill(String str, int len) {
		String[] out = new String[len];
		Arrays.fill(out, str);
		return out;
	}
	
	public static String[] arrFill(char c, int multiplier, int len) {
		String[] out = new String[len];
		Arrays.fill(out,  stringMult(Character.toString(c), multiplier));
		return out;
	}
}
