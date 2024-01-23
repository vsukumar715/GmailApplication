
//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

import java.io.IOException;

public class Main {

	final static byte CREATE_ACCOUNT = 1;
	final static byte SIGN_IN = 2;
	final static byte SIGN_OUT = 3;
	final static byte EXIT = 4;

	private static void loadObject() {
		UserUtil.getInstance().addUser(new User("Sasi ", "Kumar", "25-11-1999", "Male", "sasi@gmail.com", "sasi123"));
		UserUtil.getInstance().addUser(new User("Maaran ", "Suku", "22-03-2001", "Male", "suku@gmail.com", "suku123"));
        FileHandling.createGmailWorkspace("sasi@gmail.com");
        FileHandling.createGmailWorkspace("suku@gmail.com");
		GmailUtil.getInstance().addUserMailFolders("sasi@gmail.com");
		GmailUtil.getInstance().addUserMailFolders("suku@gmail.com");
		try {
			boolean f =FileHandling.addUser(new User("Maaran ", "Suku", "22-03-2001", "Male", "suku@gmail.com", "suku123"));
			System.out.println(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Main.loadObject();
		boolean mainFlag = true;
		do {
			Viewer.gmailMenu();
			System.out.println("Enter...");
			byte option = UserUtil.getInstance().getByteInput();

			switch (option) {

			case CREATE_ACCOUNT -> {
				
				if (Gmail.createAccount())
					System.out.println("Congratulations! Your account has been created");
				else
					System.out.println("Oops! Something went wrong. Please try again.");
			}
			case SIGN_IN -> {
				System.out.println("Enter the Email-Id");
				String emailId = UserUtil.getInstance().getStringInput();

				System.out.println("Enter the Password");
				String password = UserUtil.getInstance().getStringInput();

				if (Gmail.signIn(emailId, password)) {
					System.out.println("Welcome back, " + UserUtil.getInstance().getUser(emailId).getFirstName()
							+ "! You are now signed in.");
					Gmail.viewGmail();
				} else
					System.out.println("Error: Invalid credentials. Make sure your username and password are correct");

			}
			case SIGN_OUT -> {
				if (Gmail.signOut())
					System.out.println("Sign-out complete. Thank you for using our services");
			}

			case EXIT -> mainFlag = false;

			default -> System.out.println("Enter Valid Option");

			}

		} while (mainFlag);
	}
}