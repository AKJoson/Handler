package com.xcyyp.www;

public class Client {
	
	public static void Main() throws Exception {
		//do some init .. just like ActivityThread.main() method.
		Looper.prePareLooper();
		
		Looper.looper();
	}

}
