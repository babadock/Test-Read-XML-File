package com.example.testreadxmlfile;

public class FileStorage {
	public FileStorage() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 1. what's native fuction need for filestorage
	 */
	/*public static void CalcFeatures(String location) {
		CalcFeatures(location);
	}*/
	public static void SayHello() {
		nativeSayHello();
	}

	//private static native void nativeCalcFeatures(String location);
	private static native void nativeSayHello();
}
