package javabeans;

public class Customer {

//------------------Properties----------------------

	private long customer_id = 1;
	private String firstName;
	private String lastName;
	
//------------------Constructor----------------------	
	
	public Customer(long customer_id, String firstName, String lastName) {
		super();
		this.customer_id = customer_id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customer(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Customer() {}


//------------------Getters/Setters----------------------	
	
	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
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



	
	
	//------------------Methods ----------------------
	
	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	};
	

}
