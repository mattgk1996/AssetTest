package com.functions;

import java.io.IOException;

import com.impl.PortfolioPointImpl;
import com.pojo.PortfolioPoint;

public class OptimumPoint {
	

	private static PortfolioPoint opt = new PortfolioPoint();
	private double riskyAssetWeight ;
	
	public static PortfolioPoint findPoint() throws IOException { 
		ExtractData e = new ExtractData();
		CalculateReturnsAndVolatility calc = new CalculateReturnsAndVolatility();
		PortfolioPointImpl impl = new PortfolioPointImpl();
		
		double[] equityValues;
		double[] bondValues; 
		double[] commValues = {16,40};
		
		double[] equityReturnsArray = e.getDataArray("DowJonesEquity.csv");
        double[] bondReturnsArray = e.getDataArray("USBonds.csv");
      
      equityValues = calc.calculateReturnAndVolatility("week", equityReturnsArray);
      bondValues = calc.calculateReturnAndVolatility("week", bondReturnsArray);
      
      //HardCode values for returns
      opt = impl.generateFrontier(8, equityValues[1], bondValues[0], bondValues[1], commValues[0], commValues[1], -0.16147883741320435, -0.353, -0.34, 2.0);     


 //     opt = impl.generateFrontier(8, 12, 3.7,3.5, 15,30, -0.16147883741320435, -0.353, -0.34, 2.0);     

      return opt;
	
	}
	
	
	
	
	
      
}
