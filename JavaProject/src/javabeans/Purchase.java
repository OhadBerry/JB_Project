package javabeans;

public class Purchase {
	
	//Parameters
	private long purchase_ID;
	private long customer_ID;
	private long coupon_ID;
	private int amount;
	
	
	//Constructors		
	public Purchase() {
		super();
	}

	public Purchase(long purchase_ID, long customer_ID, long coupon_ID, int amount) {
		super();
		this.purchase_ID = purchase_ID;
		this.customer_ID = customer_ID;
		this.coupon_ID = coupon_ID;
		this.amount = amount;
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

	public long getPurchase_ID() {
		return purchase_ID;
	}

	public void setPurchase_ID(long purchase_ID) {
		this.purchase_ID = purchase_ID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setCoupon_id(long coupon_id) {
		this.coupon_ID = coupon_id;
	}


	
	//Methods
	@Override
	public String toString() {
		return "Purchase [purchase_ID=" + purchase_ID + ", customer_ID=" + customer_ID + ", coupon_ID=" + coupon_ID
				+ ", amount=" + amount + "]";
	}

}
