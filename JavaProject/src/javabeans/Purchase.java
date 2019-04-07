package javabeans;

public class Purchase {
	
	//Parameters
	private long customer_ID;
	private long coupon_ID;
	
	
	//Constructors	
	public Purchase(long customer_id, long coupon_id) {
		super();
		this.customer_ID = customer_id;
		this.coupon_ID = coupon_id;
	}
	
	public Purchase() {
		super();
	}

	//Setters and Getters
	public long getCustomer_id() {
		return customer_ID;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_ID = customer_id;
	}

	public long getCoupon_id() {
		return coupon_ID;
	}

	public void setCoupon_id(long coupon_id) {
		this.coupon_ID = coupon_id;
	}
	
	//Methods
	@Override
	public String toString() {
		return "Purchase [customer_id=" + customer_ID + ", coupon_id=" + coupon_ID + "]";
	}
	
	

}
