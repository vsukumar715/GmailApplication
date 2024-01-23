
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GmailUtil {

	private static GmailUtil util;

	static GmailUtil getInstance() {
		if (util == null)
			util = new GmailUtil();
		return util;
	}

	// MailId MailObj
	private Map<Integer, Mail> mailMap = new LinkedHashMap<>();

	// Add new mail to the mail map
	public void addMail(int mailId, Mail mail) {
		mailMap.put(mailId, mail);
	}

	// Method to delete a mail by mailId
	public boolean deleteMail(int mailId) {
		if (mailMap.containsKey(mailId)) {
			mailMap.remove(mailId);
			return true; // Mail deleted successfully
		} else {
			System.out.println("Mail with ID " + mailId + " not found.");
			return false; // Mail not found
		}
	}

	// Get mail given an ID
	public Mail getMailById(int mailId) {
		return mailMap.get(mailId);
	}

	// Method to get all mails for a specific user by email ID

	public List<Mail> getMailsForUser(String emailId) {

		List<Mail> userMails = new ArrayList<>();

		for (Mail mail : mailMap.values()) {
			for (Message message : mail.getMessageMap().values()) {
				if (message.getSender().getEmailId().equals(emailId)
						|| message.getReceiver().getEmailId().equals(emailId)) {
					userMails.add(mail);
					System.out.println("true");

				}
			}

		}

		return userMails;
	}

	private Map<String, Map<String, Folder>> userMailFoldersMap = new LinkedHashMap<>();

	void addUserMailFolders(String emailId) {

		userMailFoldersMap.put(emailId, initializeDefaultGmailFolders());
	}

	Map<String, Folder> getUserMailFolders(String emailId) {

		return userMailFoldersMap.get(emailId);
	}

	private Map<String, Folder> draftFolders = new LinkedHashMap<>();

	void loadObject() {

		draftFolders.put("sasi@gmail.com", new Folder("draft"));
		draftFolders.put("suku@gmail.com", new Folder("draft"));
	}

	private Map<String, Folder> initializeDefaultGmailFolders() {

		Map<String, Folder> defaultFolders = new LinkedHashMap<>();

		defaultFolders.put("inbox", new Folder("inbox"));
		defaultFolders.put("sent", new Folder("sent"));
		defaultFolders.put("trash", new Folder("trash"));
		defaultFolders.put("draft", new Folder("draft"));
		defaultFolders.put("starred", new Folder("starred"));

		return defaultFolders;

	}

	Folder getDraftsFolder(String emailId) {

		return draftFolders.get(emailId);
	}

	private int mailId = 1;

	int get_new_mail_id() {
		return mailId++;
	}

	private int messageId = 101;

	int get_new_message_id() {
		return messageId++;
	}

	long getMilliSeconds() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTimeInMillis();
	}

	String getDateAndTime(long milliSeconds) {
		DateFormat formatter = new SimpleDateFormat("dd MMM yyy hh:mm:ss");
		Date date = new Date(milliSeconds);
		return formatter.format(date);

	}
}
