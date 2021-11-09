package org.xavisalvador.lottery.system.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.xavisalvador.lottery.system.core.util.ValidationUtil.TICKET_NOT_FOUND_ERROR_MESSAGE;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = TICKET_NOT_FOUND_ERROR_MESSAGE)
public class NotFoundException extends LotterySystemException {

	public NotFoundException() {
		super(TICKET_NOT_FOUND_ERROR_MESSAGE);
	}

}
