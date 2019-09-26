package kr.co.itcen.mysite.exception;

public class GuestBookDaoException  extends RuntimeException{
	public GuestBookDaoException() {
		super("GuestBookDaoException Occurs");
	}
	
	public GuestBookDaoException(String message) {
		super(message);
	}
}
