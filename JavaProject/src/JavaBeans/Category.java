package JavaBeans;

public enum Category {
	food ,
	Shopping,
	Restaurant,
	Hotel,
	Pool,
	Amusement_Park;
	
	public static final Category values[] = values();

	public static Category values(int j) {
		return values[j];
	}

}
