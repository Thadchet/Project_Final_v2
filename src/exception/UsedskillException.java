package exception;

public class UsedskillException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getErrorMessage() {
		return "Can't use a skill because the skill is already used.";
	}

}
