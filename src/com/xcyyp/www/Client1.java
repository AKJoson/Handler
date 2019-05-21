package com.xcyyp.www;

public class Client1 {
	public static void main(String[] args) throws Exception {
		Client.Main();
		Handler handler = new Handler(new HandlerCallBack() {
			
			@Override
			public void handlerCallBack(Message message) {
				System.out.println("-----handlerCallBack1--"+Thread.currentThread().getName()+"---msg what"+message.what);
			}
		});
		Message message = new Message();
		message.what = 1;
		handler.sendMessage(message,2000);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Looper.prePareLooper();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Handler mH = new Handler(new HandlerCallBack() {
					
					@Override
					public void handlerCallBack(Message message) {
						// TODO Auto-generated method stub
						System.out.println("-----handlerCallBack2--"+Thread.currentThread().getName()+"---msg what"+message.what);
					}
				});
				Message message = new Message();
				message.what = 3;
				mH.sendMessage(message);
				Looper.looper(); //iterator the Thread MessageQueue.
			}
		}).start();
		
		Message message2 = new Message();
		message2.what = 5;
		handler.sendMessage(message2,100);
		Message message3 = new Message();
		message3.what = 8;
		handler.sendMessage(message3,300);
		Message message4 = new Message();
		message4.what = 18;
		handler.sendMessage(message4,600);
		Looper.looper();       //iterator the Thread MessageQueue.       the two messageQueue is not same.
	}
	
	
	
}
