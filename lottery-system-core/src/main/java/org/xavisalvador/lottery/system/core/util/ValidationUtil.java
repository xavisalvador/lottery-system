package org.xavisalvador.lottery.system.core.util;

import org.xavisalvador.lottery.system.core.exception.AlreadyCheckedException;
import org.xavisalvador.lottery.system.core.exception.BadRequestException;

public class ValidationUtil {

	public static final String TICKET_NOT_FOUND_ERROR_MESSAGE = "Ticket does not exist.";
	public static final String TICKET_ALREADY_CHECKED_ERROR_MESSAGE = "Ticket was already checked.";

	private static final String GREATER_THAN_ZERO_ERROR_MESSAGE = "It must be greater than 0.";
	private static final String INVALID_TICKET_ID_ERROR_MESSAGE = "Invalid ticket ID. " + GREATER_THAN_ZERO_ERROR_MESSAGE;
	private static final String INVALID_NUMBER_OF_LINES_ERROR_MESSAGE = "Invalid number of lines. " + GREATER_THAN_ZERO_ERROR_MESSAGE;

	private ValidationUtil() {
		// Default private constructor
	}

	public static void validateTicketId(long id) throws BadRequestException {
		if (id < 1) {
			throw new BadRequestException(INVALID_TICKET_ID_ERROR_MESSAGE);
		}
	}

	public static void validateNumberOfLines(int numberOfLines) throws BadRequestException {
		if (numberOfLines < 1) {
			throw new BadRequestException(INVALID_NUMBER_OF_LINES_ERROR_MESSAGE);
		}
	}

	public static void validateTicketNotChecked(boolean isChecked) throws AlreadyCheckedException {
		if (isChecked) {
			throw new AlreadyCheckedException();
		}
	}

}
