package com.fithub.app.utils;

import com.fithub.app.exceptions.EmptyException;
import com.fithub.app.models.*;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Locale;

public class SpringUtils {

	public static boolean comprobAdmin(Collection<? extends GrantedAuthority> collection) {
		Boolean b = false;
		for (GrantedAuthority gr : collection) {
			if (gr.getAuthority().equals("ROLE_ADMIN"))
				b = true;
		}
		return b;
	}

	public static String getRoleSession(Authentication authentication) {
		Object[] rol = authentication.getAuthorities().toArray();
		String role = rol[0].toString();

		return role;
	}

	public static String returnRole() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		String role = null;
		if (authentication != null) {
			Object[] rol = authentication.getAuthorities().toArray();
			role = rol[0].toString();
		}

		return role;
	}

	public static String returnLogin() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		String login = null;
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			UserDetails user = (UserDetails) principal;
			login = user.getUsername();
		}

		return login;
	}

	public static String getExtension(String extension, Locale locale, MessageSource messageSource)
			throws NoSuchMessageException, EmptyException {

		String resultado = "";
		String option = extension.substring(extension.lastIndexOf(".")).toLowerCase();

		switch (option) {
		case ".pdf":
			resultado = extension.substring(extension.lastIndexOf("."));
			break;
		case ".docx":
			resultado = extension.substring(extension.lastIndexOf("."));
			break;
		case ".doc":
			resultado = extension.substring(extension.lastIndexOf("."));
			break;
		case ".odt":
			resultado = extension.substring(extension.lastIndexOf("."));
			break;
		case ".jpg":
			resultado = extension.substring(extension.lastIndexOf("."));
			break;
		case ".jpeg":
			resultado = extension.substring(extension.lastIndexOf("."));
			break;
		case ".png":
			resultado = extension.substring(extension.lastIndexOf("."));
			break;
		default:
			throw new EmptyException((messageSource.getMessage("msg.failure.getExtension", null, locale)).toString());
		}

		return resultado;

	}

	public static String generatePassayPassword() {
		PasswordGenerator gen = new PasswordGenerator();
		org.passay.CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
		CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
		lowerCaseRule.setNumberOfCharacters(2);

		org.passay.CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
		CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
		upperCaseRule.setNumberOfCharacters(2);

		org.passay.CharacterData digitChars = EnglishCharacterData.Digit;
		CharacterRule digitRule = new CharacterRule(digitChars);
		digitRule.setNumberOfCharacters(2);

		org.passay.CharacterData specialChars = new org.passay.CharacterData() {
			@Override
			public String getErrorCode() {
				return "error";
			}

			@Override
			public String getCharacters() {
				return "!@#$%^&*()_+";
			}
		};
		CharacterRule splCharRule = new CharacterRule(specialChars);
		splCharRule.setNumberOfCharacters(4);

		String password = gen.generatePassword(10, splCharRule, lowerCaseRule, upperCaseRule, digitRule);
		return password;
	}

	public static String generarToken() {
		String result = "";
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		for(int i = 0; i<32; i++) {
			result += characters.charAt((int) (Math.random()*characters.length()));
		}
		return result;
	}

	public static ResponseEntity<?> retornSuccess(String path, String missatge, Object dades) {
		return new ResponseEntity<>(missatge, HttpStatus.OK);
	}

	public static ResponseEntity<?> retornSuccess(String path, String missatge) {
		return new ResponseEntity<>(missatge, HttpStatus.OK);
	}

	public static ResponseEntity<?> retornSuccess(String path, String missatge, Object dades, String versio) {
		return new ResponseEntity<>(missatge, HttpStatus.OK);
	}



	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static String padLeftZeros(String inputString, int length) {
		if (inputString.length() >= length) {
			return inputString;
		}
		StringBuilder sb = new StringBuilder();
		while (sb.length() < length - inputString.length()) {
			sb.append('0');
		}
		sb.append(inputString);

		return sb.toString();
	}

	public static String getMonth(Long i) {
		String mes = "";
		if (i == 1L) {
			mes = "Enero";
		} else if (i == 2L) {
			mes = "Febrero";
		} else if (i == 3L) {
			mes = "Marzo";
		} else if (i == 4L) {
			mes = "Abril";
		} else if (i == 5L) {
			mes = "Mayo";
		} else if (i == 6L) {
			mes = "Junio";
		} else if (i == 7L) {
			mes = "Julio";
		} else if (i == 8L) {
			mes = "Agosto";
		} else if (i == 9L) {
			mes = "Septiembre";
		} else if (i == 10L) {
			mes = "Octubre";
		} else if (i == 11L) {
			mes = "Noviembre";
		} else if (i == 12L) {
			mes = "Diciembre";
		}
		return mes;
	}







}

