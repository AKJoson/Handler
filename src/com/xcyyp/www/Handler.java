package com.xcyyp.www;


public class Handler {
	private Looper mLooper;
	
	public Handler() {
		
	}

	public Handler(HandlerCallBack handlerCallBack2) {
		// TODO Auto-generated constructor stub
		this.handlerCallBack = handlerCallBack2;
		mLooper = Looper.mThreadLocal.get();
	}

	private HandlerCallBack handlerCallBack;
	
	public void handlerCallBack(Message message) {
		handlerCallBack.handlerCallBack(message);
	}
	
	public void sendMessage(Message message) {
		this.sendMessage(message,0);
	}
	
	public void sendMessage(Message message,long runAfterTimes) {
		long runTimes = System.currentTimeMillis()+runAfterTimes;
		enqueueMessage(message,runTimes);
	}
	
	private void enqueueMessage(Message message, long runAfterTimes) {
		message.target = this;
		//get the mQueue and sort by the runtime
		mLooper.mQueue.enqueueMessage(message,runAfterTimes);
		// Call the looper to iterator. in Andorid it's called by system.
		// some error .you can't call this method in here, because maybe you will sendMessag in Thread.
	//	Looper.looper();
	}

	
}
