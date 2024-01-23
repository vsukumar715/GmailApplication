public class Viewer {

	static void gmailMenu() {
		System.out.println("\t---------- Gmail App ----------");
		System.out.println("\t1. Create Account");
		System.out.println("\t2. Sign In");
		System.out.println("\t3. Sign Out");
		System.out.println("\t4. Exit");
		System.out.println("\t-------------------------------");
	}

	static void viewGmailBox() {
		System.out.println("\t---------- Gmail Box ----------");
		System.out.println("\t1. Search Mail");
		System.out.println("\t2. Compose Mail");
		System.out.println("\t3. Inbox");
		System.out.println("\t4. Sent");
		System.out.println("\t5. Drafts");
		System.out.println("\t6. Trash");
		System.out.println("\t7. Back");
		System.out.println("\t-------------------------------");
	}

	public static void viewFolder(Folder folder) {

		System.out.println("--------------" + folder.getFolderName().toUpperCase() + "---------------");

		for (Mail mail : folder.getMailMap().values()) {
			System.out.println("-------------- Mail-Id" + mail.getMailId() + "---------------");
			for (Message message : mail.getMessageMap().values()) {
				String sender = message.getSender().getEmailId();
				String dateAndTime = GmailUtil.getInstance().getDateAndTime(message.getSendingTime());
				System.out.println("From : " + sender + " Date and Time : " + dateAndTime);
			}
			System.out.println("------------------------------------------------------");
		}
		System.out.println("\t1. View Mail");
		System.out.println("\t2. Back");

	}

	public static void viewMail(Mail mail) {

		System.out.println("-------------------------------------------------------------- ");
		for (Message message : mail.getMessageMap().values()) {
			System.out.println(" Message Id : " + message.getMessageId() + " Date and Time : "
					+ GmailUtil.getInstance().getDateAndTime(message.getSendingTime()));

			System.out.println(" From       : " + message.getSender().getEmailId());
			System.out.println(" To         : " + message.getReceiver().getEmailId());
			System.out.println(" Subject    : " + message.getSubject());
			System.out.println(" Message    : " + message.getMessage());
		}
		System.out.println("---------------------------------------------------------------");
		System.out.println("\t1. Reply");
		System.out.println("\t2. Move to Trash");
		System.out.println("\t3. Back");

	}

	static void viewMailAction() {
		System.out.println("\t1. Send mail   \n" + "\t2. Move to draft \n" + "\t3. Back");

	}

	public static void viewDraftMail(Mail mail) {
		
		System.out.println("-------------------------------------------------------------- ");
		for (Message message : mail.getMessageMap().values()) {
			System.out.println(" Message Id : " + message.getMessageId() + " Date and Time : "
					+ GmailUtil.getInstance().getDateAndTime(message.getSendingTime()));

			System.out.println(" From       : " + message.getSender().getEmailId());
			System.out.println(" To         : " + message.getReceiver().getEmailId());
			System.out.println(" Subject    : " + message.getSubject());
			System.out.println(" Message    : " + message.getMessage());
		}
		System.out.println("---------------------------------------------------------------");
		System.out.println("\t1. Edit");
		System.out.println("\t2. Back");
		
	}

	public static void viewTrashMail(Mail mail) {
		System.out.println("-------------------------------------------------------------- ");
		for (Message message : mail.getMessageMap().values()) {
			System.out.println(" Message Id : " + message.getMessageId() + " Date and Time : "
					+ GmailUtil.getInstance().getDateAndTime(message.getSendingTime()));

			System.out.println(" From       : " + message.getSender().getEmailId());
			System.out.println(" To         : " + message.getReceiver().getEmailId());
			System.out.println(" Subject    : " + message.getSubject());
			System.out.println(" Message    : " + message.getMessage());
		}
		System.out.println("---------------------------------------------------------------");
		System.out.println("\t1. Delete");
		System.out.println("\t2. Back");
		
	}
}

