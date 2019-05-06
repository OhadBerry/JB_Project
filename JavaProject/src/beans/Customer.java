package beans;

import enums.ClientType;

public class Customer {

//------------------Properties----------------------

	private User user;
	private String firstName;
	private String lastName;
	
//------------------Constructor----------------------	
	
	public Customer(String firstName, String lastName, User user) {
		super();
		this.user = user;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customer(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customer(long id, String firstName, String lastName) {
		super();
		setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customer(long id, String firstName, String lastName, User user) {
		super();
		this.user = user;
		setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customer() {}


//------------------Getters/Setters----------------------	
	
	public long getId() {
		return user.getId();
	}

	public void setId(long id) {
		user.setId(id);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
	//------------------Methods ----------------------
	
	@Override
	public String toString() {
		return "Customer [user=" + user + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}


}
