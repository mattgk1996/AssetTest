package com.functions;

import java.io.IOException;

public class LoadData {
	private  double equityValues[];
	private double bondValues[];
	private double goldValues[];
	
	public void data_values() {
		
		ExtractData e = new ExtractData();
		CalculateReturnsAndVolatility c = new CalculateReturnsAndVolatility();
		
	
    try {
        double[] equityReturnsArray = e.getDataArray("DowJonesEquity.csv");
	    double[] bondReturnsArray = e.getDataArray("USBonds.csv");
	    double[] goldReturnsArray = e.getDataArray("USGold.csv");
	    equityValues = c.calculateReturnAndVolatility("week", equityReturnsArray);
	    bondValues = c.calculateReturnAndVolatility("week", bondReturnsArray);
	    goldValues = c.calculateReturnAndVolatility("week", goldReturnsArray);	    
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

    
    
	}
	
	 

}
