package com.swap.ihm;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;

public class RequestVerification {
	public static int getInteger(HttpServletRequest request, String parameterName) {
		String tempParameter = request.getParameter(parameterName);
		if (tempParameter == null) {
			return 0;
		}
		return Integer.valueOf(tempParameter);
	}

	public static String getString(HttpServletRequest request, String parameterName) {
		String tempParameter = request.getParameter(parameterName);
		if (tempParameter == null) {
			return "";
		}
		return tempParameter;
	}

	public static LocalDate getLocalDate(HttpServletRequest request, String parameterName) {
		String tempParameter = request.getParameter(parameterName);
		if (tempParameter == null) {
			return LocalDate.MIN;
		}
		return LocalDate.parse(tempParameter);
	}
}
