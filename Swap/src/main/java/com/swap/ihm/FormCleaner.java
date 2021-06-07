package com.swap.ihm;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormCleaner {
	public final static String PWREG = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$",
			STREETREG = "^\\d*[-\\s]?(?:\\d{4})?$",
			NAMEREG = "^[\\w'-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:\\[\\]]{2,30}$",
			UNREG = "^(?=[a-zA-Z0-9._]{6,30}$)(?!.*[_.]{2})[^_.].*[^_.]$", POSTREG = "^\\d{5}[-\\s]?(?:\\d{4})?$",
			MAILREG = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
			TELREG = "^[0-9]{6,15}$", DATEREG = "[-/0-9]{6,11}$", IDREG = "^[0-9]{1,20}$";

	private static boolean isSafe(String inputValue) {
		// TODO : check for special chars, js, java, and sql injections
		return true;
	}

	public static String encode(String password) {
		// TODO : logic here
		return password;
	}

	public static String cleanEmail(String email) {
		Pattern p = Pattern.compile(MAILREG);
		Matcher m = p.matcher(email.trim());
		return (isSafe(email) && m.matches()) ? email.trim() : null;
	}

	public static LocalDate cleanDate(String date) {
		Pattern p = Pattern.compile(DATEREG);
		Matcher m = p.matcher(date.trim());
		return (isSafe(date) && m.matches()) ? LocalDate.parse(date) : null;
	}

	public static String cleanPassword(String password) {
		Pattern p = Pattern.compile(PWREG);
		Matcher m = p.matcher(password.trim());
		return (isSafe(password) && m.matches()) ? password : null;
	}

	public static String cleanTel(String tel) {
		Pattern p = Pattern.compile(TELREG);
		Matcher m = p.matcher(tel.trim());
		return (isSafe(tel) && m.matches()) ? tel : null;
	}

	public static String cleanUsername(String name) {
		Pattern p = Pattern.compile(UNREG);
		Matcher m = p.matcher(name.trim());
		return (isSafe(name) && m.matches()) ? name.trim() : null;
	}

	public static String cleanName(String name) {
		Pattern p = Pattern.compile(NAMEREG);
		Matcher m = p.matcher(name.trim());
		return (isSafe(name) && m.matches()) ? name.trim() : null;
	}

	public static String cleanStreet(String street) {
		Pattern p = Pattern.compile(STREETREG);
		Matcher m = p.matcher(street.trim());
		return (isSafe(street) && m.matches()) ? street.trim() : null;
	}

	public static String cleanPostcode(String postcode) {
		Pattern p = Pattern.compile(POSTREG);
		Matcher m = p.matcher(postcode.trim());
		return (isSafe(postcode) && m.matches()) ? postcode.trim() : null;
	}

	public static int cleanId(String strId) {
		Pattern p = Pattern.compile(IDREG);
		Matcher m = p.matcher(strId.trim());
		return (isSafe(strId) && m.matches()) ? Integer.parseInt(strId.trim()) : null;
	}

}
