package com.swap.bo;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PasswordUtils {
	private final static char[] PEPPERS = { '1', 'B', 'C', 'D', 'E', 'F', '7', 'H', 'i', 'J', 'K', 'L', '2', 'n', 'O',
			'P', '3', 'R', 'S', '9', 'u', 'V', 'W', '5', 'Y', 'Z' };
	private static final Random RAND = new SecureRandom();
	private static final int ITERATIONS = 65536;
	private static final int KEY_LENGTH = 512;
	private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
	private static final int LENGTH = 16;

	private PasswordUtils() {

	}

	public static String generateSalt() {
		byte[] salt = new byte[LENGTH];
		RAND.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	private static char generatePepper() {
		return PEPPERS[RAND.nextInt(PEPPERS.length)];
	}

	private static String hash(String password, String salt, char pepper) throws BOException {
		char[] pw = (password + pepper).toCharArray();
		byte[] s = salt.getBytes();
		String encoded = "";
		PBEKeySpec spec = new PBEKeySpec(pw, s, ITERATIONS, KEY_LENGTH);
		Arrays.fill(pw, Character.MIN_VALUE);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
			encoded = Base64.getEncoder().encodeToString(skf.generateSecret(spec).getEncoded());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new BOException("Password error", e);
		} finally {
			spec.clearPassword();
		}
		return encoded;
	}

	private static boolean tryPeppers(String providedPassword, String password, String salt) throws BOException {
		boolean isEqual = false;
		int i = 0;
		do {
			isEqual = password.equals(hash(providedPassword, salt, PEPPERS[i]));
			i++;
		} while (!isEqual && i < PEPPERS.length);
		return isEqual;
	}

	public static String getSecuredPassword(String password, String salt) throws BOException {
		char pepper = generatePepper();
		return hash(password, salt, pepper);
	}

	public static boolean isPasswordCorrect(String providedPassword, String password, String salt) throws BOException {
		return tryPeppers(providedPassword, password, salt);
	}
}
