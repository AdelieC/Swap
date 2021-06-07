package com.swap.bo;

public class BOCleaner {
	// TODO : expressions for regex
	// TODO : test every line of code that's commented out!
	/*
	 * private enum Regex { BASIC(""), ASCII(""), ALPHA_NUM(""), TEL(""), EMAIL(""),
	 * PASSWORD("");
	 * 
	 * private String expression;
	 * 
	 * private Regex(String expression) { this.expression = expression; }
	 * 
	 * public String toString() { return this.expression; } }
	 */

	private BOCleaner() {

	}

	public static String cleanString(String str, int maxLength) throws BOException {
		/*
		 * if (str.isBlank()) throw new
		 * BOException("Cannot set this attribute as empty"); String cleaned =
		 * str.trim(); if (cleaned.length() >= maxLength) throw new BOException(
		 * "The String you're trying to set is too long - it would be truncated if it were inserted in db!"
		 * ); if (!str.matches(Regex.BASIC.toString())) throw new BOException(
		 * "The String must only contain alphanumerical characters and special characters \"- _ .\""
		 * );
		 */
		return str;
	}

	public static String cleanEmail(String email) throws BOException {
		/*
		 * if (email.isBlank()) throw new
		 * BOException("Cannot set this attribute as empty"); String cleaned =
		 * email.trim(); if (cleaned.length() >= 20) throw new BOException(
		 * "The email you're trying to set is too long - it would be truncated if it were inserted in db!"
		 * ); if (!email.matches(Regex.EMAIL.toString())) throw new
		 * BOException("Invalid email adress");
		 */
		return email;
	}

	public static String cleanTelephone(String telephone) throws BOException {
		/*
		 * if (telephone.isBlank()) throw new
		 * BOException("Cannot set this attribute as empty"); String cleaned =
		 * telephone.trim().replaceAll(" ", "").replaceAll("-", "").replaceAll("+",
		 * "00"); if (cleaned.length() >= 15) throw new BOException(
		 * "The telephone you're trying to set is too long - it would be truncated if it were inserted in db!"
		 * ); if (!cleaned.matches(Regex.TEL.toString())) throw new
		 * BOException("Invalid phone number");
		 */
		return telephone;
	}

	public static String cleanASCII(String str, int maxLength) throws BOException {
		/*
		 * if (str.isBlank()) throw new
		 * BOException("Cannot set this attribute as empty"); String cleaned =
		 * str.trim(); if (cleaned.length() >= maxLength) throw new BOException(
		 * "The telephone you're trying to set is too long - it would be truncated if it were inserted in db!"
		 * ); if (!cleaned.matches(Regex.ASCII.toString())) throw new
		 * BOException("Invalid phone number");
		 */
		return str;// TODO : change to cleaned
	}

	public static String cleanPassword(String password) throws BOException {
		/*
		 * if (password.isBlank()) throw new BOException("Password cannot be empty!");
		 * String cleaned = password.trim(); if (cleaned.length() >= 30) throw new
		 * BOException("Password is too long for database!"); if
		 * (!cleaned.matches(Regex.PASSWORD.toString())) throw new
		 * BOException("Invalid phone number");
		 */
		return password;// TODO : change to cleaned
	}

	public static int cleanAmount(int credit, int max) throws BOException {
		if (credit < 0)
			throw new BOException("Cannot set this attribute to a negative value");
		if (credit > max)
			throw new BOException("Value for this attribute cannot exceed " + max);
		return credit;
	}

}