package JavaBeans;
import java.util.ArrayList;

public class Company {
	
//------------------Properties----------------------
	
	private int id = 0;
	private String name = "MyCompany";
	private String email = "MyCompanyEmail@Email.com";
	private String password = "MyCompanyPassword";
	private ArrayList<Coupon> coupons = null;

	
//------------------Constructor----------------------
	
	public Company(int id, String name, String email, String password, ArrayList<Coupon> coupons) {
		super();
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setCoupons(coupons);
	}
	
	public Company(String name, String email, String password, ArrayList<Coupon> coupons) {
		super();
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setCoupons(coupons);
	}
	
	public Company() {}
	
	
//------------------Getters/Setters----------------------
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
	

//------------------Methods ----------------------

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", coupons="
				+ coupons + "]";
	}
	
	
	


}
