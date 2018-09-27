package com.functions;

import java.io.PrintWriter;

import javax.xml.ws.RespectBinding;

public class CashFlow {
	private double bond;
	private double equity;
	private double commodity;
	private double tbills;
	private int year;
	private double portfolio_total;
	
	public CashFlow(double bond, double equity, double commodity, double tbills, int year) {
		super();
		this.bond = bond;
		this.equity = equity;
		this.commodity = commodity;
		this.tbills = tbills;
		this.year = year;
		this.portfolio_total = bond+equity+commodity+tbills;
	}
	
	
	
	
	public void PrintCashFlow() {
/*		PrintWriter writer = response.getWriter();
		writer.println("Bonds: "+ bond);
		writer.println("Equity: "+equity);
		writer.println("Commodity: "+commodity);
		writer.println("Deposits: "+ tbills);
*/	
		
	
		
		System.out.println();	
		System.out.println("Bonds: "+ bond);
		System.out.println("Equity: "+equity);
		System.out.println("Commodity: "+commodity);
		System.out.println("Deposits: "+ tbills);
		System.out.println("Year: "+year);
		System.out.println("Total Holdings: "+ portfolio_total);
	}
	
	
}
