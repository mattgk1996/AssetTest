package com.functions;

public class FutureReturns {
	
	public static double[] getFutureReturns(double equityExpectedReturn, double equitySTD, double bondExpectedReturn, double bondSTD, double commodityExpectedReturn, double commoditySTD)
	{
		double returns[] = new double[4];
		double plusMinus = Math.random();
		if(plusMinus>0.5)
		{
			returns[1] = equityExpectedReturn + Math.random()*equitySTD;
			returns[0] = bondExpectedReturn + Math.random()*bondSTD;
			returns[2] = commodityExpectedReturn - Math.random()*commoditySTD;
//			System.out.println("bonds: "+returns[0]+"\tequity: "+returns[1]+"\tcommodities: "+returns[2]);
		
		}
		else
		{
			returns[1] = equityExpectedReturn - Math.random()*equitySTD;
			returns[0] = bondExpectedReturn - Math.random()*bondSTD;
			returns[2] = commodityExpectedReturn + Math.random()*commoditySTD;
//			System.out.println("bonds: "+returns[0]+"\tequity: "+returns[1]+"\tcommodities: "+returns[2]);
		}
		returns[3] = 3.2;
		return returns;
	}
   
}
