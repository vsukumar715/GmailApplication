
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

/**
 * Represents a folder containing a list of mails.
 */
public class Folder {
    private final String folderName;
    private final List<Mail> mailList;
    private Map<Integer,Mail>mailMap;

    /**
     * Constructs a new folder with the given name.
     *
     * @param folderName The name of the folder.
     */
    public Folder(String folderName) {
        this.folderName = folderName;
        this.mailList = new ArrayList<>();
        mailMap= new HashMap<>();
    }

    /**
     * Gets the name of the folder.
     *
     * @return The folder name.
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     * Gets an unmodifiable view of the Map of mails in the folder.
     *
     * @return The Map of mails.
     */
    public List<Mail> getMailList() {
        return Collections.unmodifiableList(mailList);
    }
    
    public Map<Integer,Mail> getMailMap() {
        return Collections.unmodifiableMap(mailMap);
    }

	public void addMail(int mailId, Mail mail) {
		mailMap.put(mailId,mail);
	}
}
