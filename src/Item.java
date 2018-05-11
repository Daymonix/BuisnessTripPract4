public class Item implements Comparable<Item> {
	private boolean full = false;
	
	private int id = -1;
	private String account = "";
	private double transport = -1;
	private int days = -1;
	
	public Item(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public void setTransport(double transport) {
		this.transport = transport;
	}
	
	public void setDays(int days) {
		this.days = days;
	}
	
	public boolean isFull() {
		if(full) {
			return full;
		}
		if(!account.isEmpty() && transport != -1 && days != -1) {
			full = true;
		}
		return full;
	}
	
	public double totalCost() {
		return days * transport;
	}

	@Override
	public int compareTo(Item i) {
		if(totalCost() < i.totalCost()) {
			return -1;
		}
		if(totalCost() > i.totalCost()) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public String toString() {
		return id + ", " + account + ", " + transport + ", " + days + ", " + totalCost() + ";";
	}
}
