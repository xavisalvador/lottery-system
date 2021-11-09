package org.xavisalvador.lottery.system.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER_ADVISOR = LoggerFactory.getLogger(ControllerAdvisor.class);

	private static final String MESSAGE = "message";
	private static final String SUCCESS = "success";

	// region ExceptionHandler methods

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
		LOGGER_ADVISOR.debug(">> handleBadRequestException()");

		Map<String, Object> body = getExceptionBody(ex.getMessage());

		LOGGER_ADVISOR.debug("<< handleBadRequestException() responseBody {}", body);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AlreadyCheckedException.class)
	public ResponseEntity<Object> handleAlreadyCheckedException(AlreadyCheckedException ex) {
		LOGGER_ADVISOR.debug(">> handleAlreadyCheckedException()");

		Map<String, Object> body = getExceptionBody(ex.getMessage());

		LOGGER_ADVISOR.debug("<< handleAlreadyCheckedException() responseBody {}", body);
		return new ResponseEntity<>(body, HttpStatus.ALREADY_REPORTED);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		LOGGER_ADVISOR.debug(">> handleNotFoundException()");

		Map<String, Object> body = getExceptionBody(ex.getMessage());

		LOGGER_ADVISOR.debug("<< handleNotFoundException() responseBody {}", body);
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(LotterySystemException.class)
	public ResponseEntity<Object> handleLotterySystemException(LotterySystemException ex) {
		LOGGER_ADVISOR.debug(">> handleLotterySystemException()");

		Map<String, Object> body = getExceptionBody(ex.getMessage());

		LOGGER_ADVISOR.debug("<< handleLotterySystemException() responseBody {}", body);
		return new ResponseEntity<>(body, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex) {
		LOGGER_ADVISOR.debug(">> handleException()");

		Map<String, Object> body = getExceptionBody("Unexpected error: " + ex.getMessage());

		LOGGER_ADVISOR.debug("<< handleException() responseBody {}", body);
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// endregion

	// region Private method

	private Map<String, Object> getExceptionBody(String message) {
		LOGGER_ADVISOR.trace(">> getExceptionBody() message {}", message);

		Map<String, Object> body = new HashMap<>();
		body.put(MESSAGE, message);
		body.put(SUCCESS, false);

		LOGGER_ADVISOR.trace("<< getExceptionBody() body {}", body);
		return body;
	}

	// endregion

}
