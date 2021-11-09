package org.xavisalvador.lottery.system.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ticket request is not valid.")
public class BadRequestException extends LotterySystemException {

	public BadRequestException(String message) {
		super(message);
	}

}
