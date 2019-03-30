package javabeans;

public class Company {
	
//------------------Properties----------------------
	
	private Long id = (long) 1;
	private String name = "MyCompany";

	
//------------------Constructor----------------------
	
	public Company(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Company(String name) {
		super();
		this.name = name;
	}
	
	public Company() {}
	
	
//------------------Getters/Setters----------------------
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



//------------------Methods ----------------------
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}





	


}
