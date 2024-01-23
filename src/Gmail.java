import java.io.File;
import java.io.IOException;
import java.util.*;

public class Gmail {
	private static User CURRENT_USER;
	private static Map<String, Folder> GMAIL_FOLDERS = new HashMap<>();
	private final static byte SEARCH_MAIL = 1;
	private final static byte COMPOSE_MAIL = 2;
	private final static byte INBOX_FOLDER = 3;
	private final static byte SENT_FOLDER = 4;
	private final static byte DRAFT_FOLDER = 5;
	private final static byte TRASH_FOLDER = 6;
	private final static byte BACK_GMAIL = 7;

	private final static byte SEND_MAIL = 1;
	private final static byte MOVE_TO_TRASH = 2;
	private final static byte MOVE_TO_DRAFT = 2;
	private final static byte BACK_COMPOSE = 3;

	private final static byte VIEW_MAIL = 1;
	private final static byte BACK_INBOX_FOLDER = 2;
	private final static byte REPLY_MAIL = 1;
	private final static byte BACK_VIEW_MAIL = 2;

	private final static byte BACK_SENT_FOLDER = 2;
	private final static byte EDIT_MAIL = 1;
	private final static byte DELETE_MAIL = 1;

	public static boolean createAccount() {

		User user = UserUtil.getInstance().getCreateAccountInput();
		try {
			if(FileHandling.addUser(user)){
				FileHandling.createGmailWorkspace(user.getEmailId());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserUtil.getInstance().addUser(user);
		GmailUtil.getInstance().addUserMailFolders(user.getEmailId());

		return true;
	}

	public static boolean signIn(String emailId, String password) {

		if (UserUtil.getInstance().isExistsUser(emailId)) {
			
			User user = UserUtil.getInstance().getUser(emailId);
			
			if (user.getPassword().equals(password)) {
				CURRENT_USER = user;
				loadFoldersAndMails();
				return true;
			}
		}
		return false;
	}

	public static boolean signOut() {
		CURRENT_USER = null;
		return true;
	}

	private static void loadFoldersAndMails() {

		GMAIL_FOLDERS = GmailUtil.getInstance().getUserMailFolders(CURRENT_USER.getEmailId());
		List<Mail> myMails = GmailUtil.getInstance().getMailsForUser(CURRENT_USER.getEmailId());

		for (Mail mail : myMails) {
			if(mail.isTrash()) {
				moveToFolder("trash",mail);
				continue;
			}
			for (String folderName : mail.getReceiverFolderNames()) {
				moveToFolder(folderName, mail);
			}
		}
	}

	private static void moveToFolder(String folderName, Mail mail) {

		Folder selectedFolder = GMAIL_FOLDERS.get(folderName);

		if (!selectedFolder.getMailMap().containsKey(mail.getMailId()))
			selectedFolder.addMail(mail.getMailId(), mail);

		System.out.println(selectedFolder.getMailMap().values());
	}

	static void viewGmail() {

		boolean flag = true;
		do {
			Viewer.viewGmailBox();

			byte option = UserUtil.getInstance().getByteInput();

			switch (option) {
			case SEARCH_MAIL -> {
				System.out.println("Write implement later.....");
			}
			case COMPOSE_MAIL -> {
				compose();
			}

			case INBOX_FOLDER -> {

				Folder inbox = GMAIL_FOLDERS.get("inbox");
				if (inbox.getMailMap().isEmpty())
					System.out.println("Inbox Is Empty ");
				else
					handleInboxFolder(inbox);
			}
			case SENT_FOLDER -> {
				Folder sent = GMAIL_FOLDERS.get("sent");
				if (sent.getMailMap().isEmpty())
					System.out.println("Sent Is Empty ");
				else
					handleSentFolder(sent);

			}
			case DRAFT_FOLDER -> {
				Folder draft = GMAIL_FOLDERS.get("draft");
				if (draft.getMailMap().isEmpty())
					System.out.println("Draft Is Empty ");
				else
					handleDraftFolder(draft);
			}
			case TRASH_FOLDER -> {
				Folder trash = GMAIL_FOLDERS.get("trash");
				if (trash.getMailMap().isEmpty())
					System.out.println("Trash Is Empty ");
				else
					handleTrashFolder(trash);
			}

			case BACK_GMAIL -> {
				flag = false;
			}
			}
		} while (flag);
	}

	private static void handleTrashFolder(Folder trash) {
		boolean trashFlag = true;
		do {
			Viewer.viewFolder(trash);
			byte choice = UserUtil.getInstance().getByteInput();

			if (choice == VIEW_MAIL) {

				System.out.println("Enter the mail-id");
				int mailId = UserUtil.getInstance().getIntegerInput();
				Mail selectedMail = null;

				if (trash.getMailMap().containsKey(mailId)) {
					selectedMail = trash.getMailMap().get(mailId);
					handleViewTrashMail(selectedMail);
				} else
					System.out.println("Mail Id Not Fosund");

			} else if (choice == BACK_SENT_FOLDER)
				trashFlag = false;

		} while (trashFlag);
	}



	private static void handleDraftFolder(Folder draft) {
		boolean draftFlag = true;
		do {
			Viewer.viewFolder(draft);
			byte choice = UserUtil.getInstance().getByteInput();

			if (choice == VIEW_MAIL) {

				System.out.println("Enter the mail-id");
				int mailId = UserUtil.getInstance().getIntegerInput();
				Mail selectedMail = null;

				if (draft.getMailMap().containsKey(mailId)) {
					selectedMail = draft.getMailMap().get(mailId);
					handleViewDraftMail(selectedMail);
				} else
					System.out.println("Mail Id Not Fosund");

			} else if (choice == BACK_SENT_FOLDER)
				draftFlag = false;

		} while (draftFlag);
	}

	private static void handleSentFolder(Folder sent) {
		boolean sentFlag = true;
		do {
			Viewer.viewFolder(sent);
			byte choice = UserUtil.getInstance().getByteInput();

			if (choice == VIEW_MAIL) {

				System.out.println("Enter the mail-id");
				int mailId = UserUtil.getInstance().getIntegerInput();
				Mail selectedMail = null;

				if (sent.getMailMap().containsKey(mailId)) {
					selectedMail = sent.getMailMap().get(mailId);
					handleViewMail("sent", selectedMail);
				} else
					System.out.println("Mail Id Not Fosund");

			} else if (choice == BACK_SENT_FOLDER)
				sentFlag = false;

		} while (sentFlag);

	}

	private static void handleInboxFolder(Folder inbox) {
		boolean inboxFlag = true;
		do {
			Viewer.viewFolder(inbox);
			byte choice = UserUtil.getInstance().getByteInput();

			if (choice == VIEW_MAIL) {

				System.out.println("Enter the mail-id");
				int mailId = UserUtil.getInstance().getIntegerInput();
				Mail selectedMail = null;

				if (inbox.getMailMap().containsKey(mailId)) {
					selectedMail = inbox.getMailMap().get(mailId);
					handleViewMail("inbox", selectedMail);
				} else
					System.out.println("Mail Id Not Fosund");

			} else if (choice == BACK_INBOX_FOLDER)
				inboxFlag = false;

		} while (inboxFlag);

	}

	private static void handleViewMail(String folderName, Mail mail) {
		Viewer.viewMail(mail);
		byte choice = UserUtil.getInstance().getByteInput();
		if (choice == REPLY_MAIL) {
			System.out.println("write implement later...");
		} else if (choice == MOVE_TO_TRASH) {
			mail.setTrash(true);
			moveToFolder("trash", mail);
			System.out.println("Moved trash");
		} else if (choice == BACK_VIEW_MAIL) {

		}
	}

	private static void handleViewDraftMail(Mail mail) {
		Viewer.viewDraftMail(mail);
		byte choice = UserUtil.getInstance().getByteInput();
		if(choice == SEND_MAIL) {
//			System.out.printl
//			newMessage.setSendingTime(System.currentTimeMillis());
//			if (sendMail(mail)) {
//				System.out.println("Message sent successfully.");
//				moveToFolder("sent", newMail);
//				mailFlag = false;
//			}
		}
		if (choice == EDIT_MAIL) {
			editMail(mail);

		} else if (choice == BACK_VIEW_MAIL) {

		}
	}
	private static void handleViewTrashMail(Mail mail) {
		Viewer.viewTrashMail(mail);
		byte choice = UserUtil.getInstance().getByteInput();
		if (choice == DELETE_MAIL) {
			if(GmailUtil.getInstance().deleteMail(mail.getMailId()))
				System.out.println("Deleted Successfully");

		} else if (choice == BACK_VIEW_MAIL) {

		}
		
	}
	private static void editMail(Mail mail) {
        
		System.out.println("write implement later...");
	}

	private static void compose() {
		System.out.println("----- New Mail -----");

		System.out.println("Enter the receiver email-Id");
		User receiver = null;

		do {
			String email_id = UserUtil.getInstance().getValidEmailId();
			if (UserUtil.getInstance().isExistsUser(email_id)) {
				receiver = UserUtil.getInstance().getUser(email_id);
				break;
			} else
				System.out.println("Invalid EmailId,Try Again");
		} while (true);

		System.out.println("Enter the subject ");
		String subject = UserUtil.getInstance().getStringInput();

		System.out.println("Enter compose Mail");
		String content = UserUtil.getInstance().getStringInput();

		Mail newMail = new Mail(GmailUtil.getInstance().get_new_mail_id());

		Message newMessage = new Message(GmailUtil.getInstance().get_new_message_id(), CURRENT_USER, receiver, subject,
				content);

		newMail.addMessage(newMessage);
		handleMailAction(newMail, newMessage);

	}

	private static void handleMailAction(Mail newMail, Message newMessage) {
		boolean mailFlag = true;
		do {
			Viewer.viewMailAction();

			byte option = UserUtil.getInstance().getByteInput();
			switch (option) {

			case SEND_MAIL -> {
				newMessage.setSendingTime(System.currentTimeMillis());
				if (sendMail(newMail)) {
					System.out.println("Message sent successfully.");
					moveToFolder("sent", newMail);
					mailFlag = false;
				}
			}
			case MOVE_TO_DRAFT -> {

				newMessage.setSendingTime(System.currentTimeMillis());

				moveToFolder("draft", newMail);
				System.out.println("Draft saved. You can continue editing later.");
				mailFlag = false;
			}
			case BACK_COMPOSE -> {
				mailFlag = false;
			}
			}
		} while (mailFlag);

	}
	private static boolean sendMail(Mail newMail) {
		GmailUtil.getInstance().addMail(newMail.getMailId(), newMail);
		return true;
	}

}
