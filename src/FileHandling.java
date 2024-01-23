import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandling {
	private static File users = new File("/home/sukumar/eclipse-workspace/GmailApplication/usersFile.txt");

	public static boolean addUser(User newUser) throws IOException {
		FileWriter fwriter = new FileWriter(users, true);
		BufferedWriter bwriter = new BufferedWriter(fwriter);
		String line = "" + newUser.getEmailId() + "-" + newUser.getFirstName() + "," + newUser.getLastName() + ","
				+ newUser.getDateOfBirth() + "," + newUser.getGender() + "," + newUser.getEmailId() + ","
				+ newUser.getPassword();
		bwriter.write(line);
		bwriter.newLine();
		bwriter.close();
		fwriter.close();

		return true;
	}

	public static void createGmailWorkspace(String emailId) {
		String folderName = emailId.substring(0, emailId.indexOf("@"));
		File folder = new File("/home/sukumar/eclipse-workspace/GmailApplication/" + folderName);
		if (folder.mkdirs()) {
			folder.getPath();
			File inbox = new File(folder.getPath() + "/" + "inbox");
			File sent = new File(folder.getPath() + "/" + "sent");
			File draft = new File(folder.getPath() + "/" + "draft");
			File trash = new File(folder.getPath() + "/" + "trash");
			System.out.println(folderName + " is created");
			if (inbox.mkdirs()) {
				System.out.println("inbox is created");
			}
			if (sent.mkdirs()) {
				System.out.println("sent is created");
			}
			if (draft.mkdirs()) {
				System.out.println("draft is created");
			}
			if (trash.mkdirs()) {
				System.out.println("trash is created");
			}

		}
	}

}
