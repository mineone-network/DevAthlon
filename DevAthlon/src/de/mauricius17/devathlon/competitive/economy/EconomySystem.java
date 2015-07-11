package de.mauricius17.devathlon.competitive.economy;

public class EconomySystem {

	double money;
	
	public EconomySystem(double money) {
		 this.money = money;
	}
	
	public double getMoney() {
		return money;
	}
	
	public void setMoney(double money) {
		this.money = money;
	}
	
	public void addMoney(double money) {
		this.money = this.money + money;
	}
	
	public void removeMoney(double money) {
		this.money = this.money - money;
	}
	
	public static double getPart(double percent, double input) {
		return (input / 100) * percent;
	}
}