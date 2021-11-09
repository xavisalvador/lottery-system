package org.xavisalvador.lottery.system.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.xavisalvador.lottery.system.core.util.ValidationUtil.TICKET_ALREADY_CHECKED_ERROR_MESSAGE;

@ResponseStatus(code = HttpStatus.ALREADY_REPORTED, reason = TICKET_ALREADY_CHECKED_ERROR_MESSAGE)
public class AlreadyCheckedException extends LotterySystemException {

	public AlreadyCheckedException() {
		super(TICKET_ALREADY_CHECKED_ERROR_MESSAGE);
	}
}
