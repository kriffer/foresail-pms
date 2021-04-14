package fi.foresail.pms.service;


import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordGenerator {

	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";

	private static final String PASSWORD_ALLOW_BASE = CHAR_LOWER + CHAR_UPPER + NUMBER;

	private static final String PASSWORD_ALLOW_BASE_SHUFFLE = shuffleString(PASSWORD_ALLOW_BASE);
	private static final String PASSWORD_ALLOW = PASSWORD_ALLOW_BASE_SHUFFLE;

	private static SecureRandom random = new SecureRandom();



	public String generateRandomPassword() {


		StringBuilder sb = new StringBuilder(8);
		for (int i = 0; i < 8; i++) {

			int rndCharAt = random.nextInt(PASSWORD_ALLOW.length());
			char rndChar = PASSWORD_ALLOW.charAt(rndCharAt);

			// debug
			System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

			sb.append(rndChar);

		}

		return sb.toString();

	}


	public static String shuffleString(String string) {
		List<String> letters = Arrays.asList(string.split(""));
		Collections.shuffle(letters);
		return letters.stream().collect(Collectors.joining());
	}

}