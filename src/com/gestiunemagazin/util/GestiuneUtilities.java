package com.gestiunemagazin.util;



public class GestiuneUtilities {

	private static GestiuneUtilities INSTANCE = new GestiuneUtilities();

	
	private GestiuneUtilities() {

	}

	

	public static GestiuneUtilities getInstance() {
		return INSTANCE;
	}

	
	public boolean isNullOrEmpty(String string) {
		if (string == null || "".equals(string.trim())) {
			return true;
		}
		return false;
	}
}
