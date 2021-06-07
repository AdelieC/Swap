package com.swap.ihm;

public enum FormError {
	// TODO : add all errors for other forms
	USERNAME("Username must be 6 characters minimum, 30 characters max, and only contains letters, numbers, ?-._@$#."),
	PASSWORD("Sorry, either your passwords didn't match or you forgot some required characters."),
	LASTNAME("Last name must be 30 letters max and can contain white space, ' or -."),
	FIRSTNAME("Last name must be 30 letters max and can contain white space, ' or -."),
	EMAIL("Email must look like : blablabla@messagery-provider.domain"),
	TELEPHONE("Telephone must contain only digits and whites spaces"),
	STREET("Street name must contain letters, white space, numbers, ' or -."),
	POSTCODE("Postcode must be less than 10 digits and letters"),
	CITY("City must be less than 30 letters, ' or -.");

	String msg;

	FormError(String msg) {
		this.msg = msg;
	}

	public static FormError getError(String errorName) {
		FormError matchingError = null;
		for (FormError error : FormError.values()) {
			if (error.name().equals(errorName.toUpperCase()))
				matchingError = error;
		}
		return matchingError;
	}

	@Override
	public String toString() {
		return this.msg;
	}
}
