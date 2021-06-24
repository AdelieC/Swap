package com.swap.ihm.notification;

public enum NotificationType {
	MESSAGE,
	BID,
	SALE,
	WIN,
	ADMIN;

	public static boolean check(String type) {
		boolean isType = false;
		int i = 0;
		do {
			isType = NotificationType.values()[i].name().equals(type);
			i++;
		} while (!isType && i < NotificationType.values().length);
		return isType;
	}
}
