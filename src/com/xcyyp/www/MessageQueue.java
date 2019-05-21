package com.xcyyp.www;

public class MessageQueue  {
	
	private Message mMessages;
	
	public MessageQueue() {
	}
	
	
	public boolean next() {
		if (mMessages == null) 
			 return false;
		else 
			return true;
		
	}
	public Message getMessage() {
		Message firstMessage = mMessages;
		mMessages = mMessages.p;
		return firstMessage;
	}

	public void enqueueMessage(Message message, Long runTimes) {
		message.when = runTimes;
		if (mMessages == null) {
			mMessages = message;
		}else {
			Message message2 = mMessages;
			Message message3 = null;
			for(;;) {
				if (message2.when < message.when) {
					if (message2.p != null) {
						message3 = message2;
						message2 = message2.p;
						continue;
					}else {
						message2.p = message;
						break;
					}
			}else {
				if (message3==null) {
					// this message want set in the first.
					message.p = message2;
					mMessages = message;
				}else {
					message.p = message3.p;
					message3.p = message;
				}
				break;
			}
		}
	
		
	}
	}


}
