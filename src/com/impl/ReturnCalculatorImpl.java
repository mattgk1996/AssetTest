package com.impl;

import com.interfaces.ReturnCalculator;

public class ReturnCalculatorImpl implements ReturnCalculator {

	@Override
	public double goalReturnCalculator(double currentval, int years) {
		// TODO Auto-generated method stub
		
		double futureval = currentval*(Math.pow(1.03, years));
		
		
		return futureval;
	}

}
