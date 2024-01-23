
import java.util.*;

public class Mail {

	private int mailId;
	
    private boolean isTrash;
	
    private Map<Integer, Message> messageMap = new LinkedHashMap<>();
	
	private List<String> senderFolderNames = new ArrayList<>();
	private List<String> receiverFolderNames = new ArrayList<>();

	public Mail(int mailId) {
		this.mailId = mailId;
		this.senderFolderNames.add("sent");
		this.receiverFolderNames.add("inbox");
	}

	public int getMailId() {
		return mailId;
	}

    public Map<Integer,Message> getMessageMap() {
    	return messageMap;
    }
	public Message getMessage(int messageId) {

		return messageMap.get(messageId);
	}

	public void addMessage(Message message) {
		messageMap.put(message.getMessageId(), message);
	}

	boolean isMessage(int id) {
		return messageMap.containsKey(id);
	}

	public List<String> getSenderFolderNames() {
		return Collections.unmodifiableList(senderFolderNames);
	}

	public List<String> getReceiverFolderNames() {
		return Collections.unmodifiableList(receiverFolderNames);
	}

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}

}
