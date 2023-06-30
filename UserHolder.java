package application;

public final class UserHolder {
	  
	  private String user;
	  private final static UserHolder INSTANCE = new UserHolder();
	  
	  UserHolder() {}
	  
	  public static UserHolder getInstance() {
	    return INSTANCE;
	  }
	  
	  public void setUser(String u) {
	    this.user = u;
	  }
	  
	  public String getUser() {
	    return this.user;
	  }
	}