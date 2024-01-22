package Main;

public class Sale {
	 private int year;
	    private int month;
	    private int sales;

		public Sale(int year, int month, int sales) {
	        this.year = year;
	        this.month = month;
	        this.sales = sales;
	    }

	    // Getter and setter methods for year, month, and sales
	    public int getYear() {
	        return year;
	    }

	    public void setYear(int year) {
	        this.year = year;
	    }

	    public int getMonth() {
	        return month;
	    }

	    public void setMonth(int month) {
	        this.month = month;
	    }

	    public int getSales() {
	        return sales;
	    }

	    public void setSales(int sales) {
	        this.sales = sales;
	    }
}
