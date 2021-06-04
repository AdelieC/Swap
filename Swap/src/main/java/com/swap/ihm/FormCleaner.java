package com.swap.ihm;

import java.time.LocalDate;

public class FormCleaner {
	private static boolean isSafe(String inputValue) {
		// TODO : check for special chars, js, java, and sql injections
		return true;
	}

	private static String encode(String password) {
		// TODO : logic here
		return password;
	}

	public static String cleanInputEmail(String email) {
		return (isSafe(email) && email.trim().matches("^[A-Z0-9@._%+-]{6,254}$")) ? email.trim() : null;
	}

	public static LocalDate cleanInputDate(String date) {
		return (isSafe(date) && date.matches("^[0-9-]{11}$")) ? LocalDate.parse(date) : null;
	}

	public static String cleanInputPassword(String password) {
		return (isSafe(password) && password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%&.?]).{8,32}$"))
				? encode(password)
				: null;
	}

	public static String cleanInputTel(String tel) {
		return (isSafe(tel) && tel.matches("^[0-9-+]{15}$")) ? tel : null;
	}

	public static String cleanInputText(String text, int max) {
		return (isSafe(text) && text.trim().length() < max) ? text.trim() : null;
	}

}
