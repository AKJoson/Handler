package com.xcyyp.www;

public class Looper {
	
	public static ThreadLocal<Looper> mThreadLocal = new ThreadLocal<>();
	public MessageQueue mQueue;
	
	public Looper(MessageQueue messageQueue) {
		mQueue = messageQueue;
	}
	
	
	public static void prePareLooper() throws Exception {
		if (mThreadLocal.get() != null) {
			throw new RuntimeException("the MainThread looper just can init onece..");
		}
		
		mThreadLocal.set(new Looper(new MessageQueue()));
	}
	
	
	public static void looper() {
		Looper looper = mThreadLocal.get();
		if (looper == null) {
			throw new RuntimeException("before use Handler,you should call loop.prePareLooper()");
		}
		MessageQueue messageQueue = looper.mQueue; // start to iteration
		while (messageQueue.next()) {
			Message message = messageQueue.getMessage();
			message.target.handlerCallBack(message);;
		}
		
	}

}
