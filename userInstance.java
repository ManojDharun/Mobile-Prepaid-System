package application;
import java.time.LocalDate;
public class userInstance {
	private LocalDate user;
	  private final static userInstance INSTANCE = new userInstance();
	  
	  private userInstance() {}
	  
	  public static userInstance getInstance() {
	    return INSTANCE;
	  }
	  
	  public void setUser(LocalDate date1) {
	    this.user = date1;
	  }
	  
	  public LocalDate getUser() {
	    return this.user;
	  }
}
