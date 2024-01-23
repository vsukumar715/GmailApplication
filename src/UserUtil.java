
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserUtil {
	private static UserUtil util;
	private UserUtil() {
		
	}

	static UserUtil getInstance() {
		if (util == null) {
			util = new UserUtil();
		}
		return util;
	}
	private Scanner scanner = new Scanner(System.in);
	
	private Map<String, User> userMap = new HashMap<>();
	
	public boolean addUser(User user) {
		userMap.put(user.getEmailId(),user);
		return true;
	}
	public User getUser(String emailId)
	{
		return userMap.get(emailId);
	}
	
	public boolean isExistsUser(String emailId) {
		return userMap.containsKey(emailId);
	}
	int getIntegerInput() {
		int num;
		do {
			if (scanner.hasNextInt()) {
				num = scanner.nextInt();
				scanner.nextLine();
				break;
			} else {
				System.out.println("Enter Valid Input");
				scanner.nextLine();
			}
		} while (true);
		return num;
	}

	byte getByteInput() {
		byte num;
		do {
			if (scanner.hasNextByte()) {
				num = scanner.nextByte();
				scanner.nextLine();
				break;
			} else {
				System.out.println("Enter Valid Input");
				scanner.nextLine();
			}
		} while (true);
		return num;
	}

	String getStringInput() {
		return scanner.nextLine();
	}

	String getValidDataOfBirth() {
		String dateOfBirth, regex = "[0-9-_]+";
		do {
			dateOfBirth = getStringInput();
			if (dateOfBirth.matches(regex))
				break;
			else
				System.out.println("Enter Valid Date(DD-MM-YYYY)");
		} while (true);

		return dateOfBirth;
	}

	String getValidGender() {

		String gender;
		do {
			byte option = getByteInput();
			if (option == 1) {
				gender = "Male";
				break;
			} else if (option == 2) {
				gender = "Female";
				break;
			} else if (option == 3) {
				gender = "Other";
				break;
			} else
				System.out.println("Enter Valid Option");
		} while (true);
		return gender;
	}

	String getValidEmailId() {
		String emailId, regex = "[a-zA-Z0-9_-]{4,}@gmail.com";
		do {
			emailId = getStringInput();
			if (emailId.matches(regex))
				break;
			else
				System.out.println("Enter Valid Email-Id");
		} while (true);
		return emailId;
	}

	String getValidPassword() {
		String password, regex = "[a-zA-Z0-9]+";
		do {
			password = getStringInput();
			if (password.matches(regex))
				break;
		} while (true);

		return password;
	}
    User getCreateAccountInput(){
    	System.out.println("---------- Create New Account -----------");

		System.out.println("Enter the First Name");
		String firstName = UserUtil.getInstance().getStringInput();

		System.out.println("Enter the Last Name");
		String lastName = UserUtil.getInstance().getStringInput();

		System.out.println("Enter the Date of Birth");
		String dateOfBirth = UserUtil.getInstance().getValidDataOfBirth();

		System.out.println("Gender\n1. Male\n2. Female\n3. Other");
		String gender = UserUtil.getInstance().getValidGender();

		System.out.println("Enter the new email id");
		String newEmailId = UserUtil.getInstance().getValidEmailId();

		System.out.println("Enter the new password");
		String newPassword = UserUtil.getInstance().getValidPassword();
		
		System.out.println("-----------------------------------------");
		return new User(firstName, lastName, dateOfBirth, gender, newEmailId, newPassword);

    }

	
}
