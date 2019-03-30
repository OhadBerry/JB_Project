package javabeans;

import java.sql.Date;

public class Coupon {
	
//------------------Properties----------------------	
	
	private long id = 1;
	private long company_id;
	private Category category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;
	private String image;
	
//------------------Constructor----------------------	
	
	public Coupon(long id, long company_id, Category category, String title, String description, Date startDate,
			Date endDate, int amount, double price, String image) {
		
		super();
		this.setId(id);
		this.setCompany_id(company_id);
		this.setCategory_id(category);
		this.setTitle(title);
		this.setDescription(description);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setAmount(amount);		
		this.setPrice(price);
		this.setImage(image);
		
	}
	
	public Coupon(long company_id, Category category, String title, String description, Date startDate,
			Date endDate, int amount, double price, String image) {
		super();
		this.setCompany_id(company_id);
		this.setCategory_id(category);
		this.setTitle(title);
		this.setDescription(description);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setAmount(amount);		
		this.setPrice(price);
		this.setImage(image);
	}
	
	public Coupon() {};

//------------------Getters/Setters----------------------	

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCompany_id() {
		return company_id;
	}
	public void setCompany_id(long company_id) {
		this.company_id = company_id;
	}
	public Category getCategory() {
		return this.category;
	}
	
	public long getCategory_id() {
		return this.category.ordinal();
	}
	public void setCategory_id(Category category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	
	
	//------------------Methods ----------------------
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", company_id=" + company_id + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}
	

	
	
}
