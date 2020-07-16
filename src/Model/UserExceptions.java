package Model;

public class UserExceptions extends Exception {
	private String msg;
	
	public UserExceptions(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
}