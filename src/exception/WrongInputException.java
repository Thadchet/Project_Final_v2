package exception;

public class WrongInputException extends Exception {

	private static final long serialVersionUID = 1L;

	public String getErrorMessage() {
		return "Your input is wrong. You must input in English alphabet";
	}
}
