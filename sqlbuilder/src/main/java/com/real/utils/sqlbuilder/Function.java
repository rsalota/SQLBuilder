package com.real.utils.sqlbuilder;

import org.apache.commons.lang.Validate;

public class Function {
	private final String function;

	public Function(String function) {
		Validate.notEmpty(function);
		this.function = function;
	}

	public String getFunction() {
		return function;
	}

}
