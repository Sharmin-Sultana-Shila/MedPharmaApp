package med.lib;

public class DisplayItem {
	
	    private String name;
	    private String manufacturer;
	    private double unitPrice;
	    private int quantity;
	    private String modelNo; // For accessories
	    private String unit;    // For medicines
	    private double dose;    // For medicines
	    private String expirationDate; // For medicines

	    public DisplayItem(String name, String manufacturer, double unitPrice, String modelNo, String unit, double dose,int quantity, String expirationDate) {
	        this.name = name;
	        this.manufacturer = manufacturer;
	        this.unitPrice = unitPrice;
	        this.modelNo = modelNo;
	        this.quantity = quantity;
	        this.unit = unit;
	        this.dose = dose;
	        this.expirationDate = expirationDate;
	    }

	    // Getters for TableView
	    public String getName() { return name; }
	    public String getManufacturer() { return manufacturer; }
	    public double getUnitPrice() { return unitPrice; }
	    public String getModelNo() { return modelNo; }
	    public String getUnit() { return unit; }
	    public int getQuantity() {return quantity;}
	    public double getDose() { return dose; }
	    public String getExpirationDate() { return expirationDate; }

	
}
