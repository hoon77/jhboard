package model;

import java.util.Date;

public class sobi {

	private Date r_date;
	private int balance;
	public Date getR_date() {
		return r_date;
	}
	public void setR_date(Date r_date) {
		this.r_date = r_date;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "sobi [r_date=" + r_date + ", balance=" + balance + "]";
	}
	
	
}
