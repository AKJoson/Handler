#### 使用java手写Handler，模拟Andoid系统中的Handler进行消息分发.
#### 主要涉及到：

	ThreadLocal  ---> 存储Looper,保证一个线程中有且只有一个Looper;
	Looper       ---> 为当前线程创建MessageQueue,提供迭代MessageQueue的looper方法；
	MessageQueue ---> 消息队列 【其实不是队列，只是一个单链表】;
	Message      ---> MessageQueue中的消息实体;
	Handler      ---> 发出消息，以及处理消息。

---

#### 实现效果1

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Looper.prePareLooper(); //子线程中创建Looper
				} catch (Exception e) {
					e.printStackTrace();
				}
				Handler mH = new Handler(new HandlerCallBack() {
					@Override
					public void handlerCallBack(Message message) {
						//子线程中收消息，可见，消息的接收依然是在子线程中
						System.out.println("-----handlerCallBack2--"+Thread.currentThread().getName()+"---msg what"+message.what);
					}
				});
				Message message = new Message();
				message.what = 3;
				mH.sendMessage(message);
				Looper.looper(); //iterator the Thread MessageQueue.
			}
		}).start();

---
#### 实现效果2，发送延时消息，主要是MessageQueue中入单链表时，进行了重排列来实现。

		Message message = new Message();
		message.what = 1;
		handler.sendMessage(message,2000);

		Message message2 = new Message();
		message2.what = 5;
		handler.sendMessage(message2,100);

		Message message3 = new Message();
		message3.what = 8;
		handler.sendMessage(message3,300);

		Message message4 = new Message();
		message4.what = 18;
		handler.sendMessage(message4,600);

		Looper.looper();   
	### 输出：
	-----handlerCallBack1--main---msg what5
	-----handlerCallBack1--main---msg what8
	-----handlerCallBack1--main---msg what18
	-----handlerCallBack1--main---msg what1
