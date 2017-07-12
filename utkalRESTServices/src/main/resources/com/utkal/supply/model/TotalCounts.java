package com.utkal.supply.model;

import java.util.List;

public class TotalCounts {
	
	private int totalNormalJarOrdered;
	private int totalNormalJarReturnedEmpty;
	private int totalNormalJarReturnedFilled;
	
	private int totalColdJarOrdered;
	private int totalColdJarReturnedEmpty;
	private int totalColdJarReturnedFilled;
	
	private int totalContainerOrdered;
	private int totalContainerReturned;
	
	private int totalBillForDtRange;
	private int totalPayment;
	
	private List<Order> orderList;
	
	
	
	
	
	
	public int getTotalBillForDtRange() {
		return totalBillForDtRange;
	}
	public void setTotalBillForDtRange(int totalBillForDtRange) {
		this.totalBillForDtRange = totalBillForDtRange;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	public int getTotalNormalJarOrdered() {
		return totalNormalJarOrdered;
	}
	public void setTotalNormalJarOrdered(int totalNormalJarOrdered) {
		this.totalNormalJarOrdered = totalNormalJarOrdered;
	}
	public int getTotalNormalJarReturnedEmpty() {
		return totalNormalJarReturnedEmpty;
	}
	public void setTotalNormalJarReturnedEmpty(int totalNormalJarReturnedEmpty) {
		this.totalNormalJarReturnedEmpty = totalNormalJarReturnedEmpty;
	}
	public int getTotalNormalJarReturnedFilled() {
		return totalNormalJarReturnedFilled;
	}
	public void setTotalNormalJarReturnedFilled(int totalNormalJarReturnedFilled) {
		this.totalNormalJarReturnedFilled = totalNormalJarReturnedFilled;
	}
	public int getTotalColdJarOrdered() {
		return totalColdJarOrdered;
	}
	public void setTotalColdJarOrdered(int totalColdJarOrdered) {
		this.totalColdJarOrdered = totalColdJarOrdered;
	}
	public int getTotalColdJarReturnedEmpty() {
		return totalColdJarReturnedEmpty;
	}
	public void setTotalColdJarReturnedEmpty(int totalColdJarReturnedEmpty) {
		this.totalColdJarReturnedEmpty = totalColdJarReturnedEmpty;
	}
	public int getTotalColdJarReturnedFilled() {
		return totalColdJarReturnedFilled;
	}
	public void setTotalColdJarReturnedFilled(int totalColdJarReturnedFilled) {
		this.totalColdJarReturnedFilled = totalColdJarReturnedFilled;
	}
	public int getTotalContainerOrdered() {
		return totalContainerOrdered;
	}
	public void setTotalContainerOrdered(int totalContainerOrdered) {
		this.totalContainerOrdered = totalContainerOrdered;
	}
	public int getTotalContainerReturned() {
		return totalContainerReturned;
	}
	public void setTotalContainerReturned(int totalContainerReturned) {
		this.totalContainerReturned = totalContainerReturned;
	}
	public int getTotalBill() {
		return totalBillForDtRange;
	}
	public void setTotalBill(int totalBill) {
		this.totalBillForDtRange = totalBill;
	}
	public int getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}
	
	
	

}
