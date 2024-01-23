import java.io.File;

public class Message {
    private final int messageId;
    private  User sender;
    private  User receiver;
    private  String subject;
    private  String message;
    private  File fileAttachment;
    private  long sendingTime;
    private  long receivingTime;

    public Message(int messageId, User sender, User receiver, String subject, String message) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
        this.fileAttachment = null; // Modify as needed
        this.sendingTime = System.currentTimeMillis(); // Set the sending time when the message is created
        this.receivingTime = 0; // Initialize receiving time to 0
    }

    public int getMessageId() {
        return messageId;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }
    

    public String getMessage() {
        return message;
    }

    public File getFileAttachment() {
        return fileAttachment;
    }
    public void setSendingTime(long sendingTime) {
    	this.sendingTime=sendingTime;
    }
    public long getSendingTime() {
        return sendingTime;
    }

    public long getReceivingTime() {
        return receivingTime;
    }
    public void attachFile(File fileAttachment) {
        if (this.fileAttachment == null) {
            this.fileAttachment = fileAttachment;
        } else {
            System.out.println("File attachment already exists for this message. Cannot attach another file.");
        }
    }

	public void setSubject(String subject) {
		this.subject=subject;	
	}
	public void setMessage(String message) {
		this.message=message;	
	}
}

