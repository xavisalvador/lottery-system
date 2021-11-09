package org.xavisalvador.lottery.system.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.xavisalvador.lottery.system.core.dto.LineNumbersRequest;
import org.xavisalvador.lottery.system.core.exception.BadRequestException;
import org.xavisalvador.lottery.system.core.exception.LotterySystemException;
import org.xavisalvador.lottery.system.core.handler.LotteryHandler;
import org.xavisalvador.lottery.system.jpa.model.TicketDto;

import java.util.List;

@RestController
public class LotteryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LotteryController.class);

	private final LotteryHandler lotteryHandler;

	public LotteryController(LotteryHandler lotteryHandler) {
		this.lotteryHandler = lotteryHandler;
	}

	/**
	 * Create a lottery ticket with a given number of lines.
	 *
	 * @param request The {@link LineNumbersRequest} with the "numberOfLines" int.
	 * @return The {@link TicketDto} with the lines generated, the result of each line and the current status as false.
	 * @throws BadRequestException in case of invalid number of lines.
	 */
	@PostMapping(value = "/ticket", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TicketDto createATicket(@RequestBody LineNumbersRequest request) throws BadRequestException {
		LOGGER.info(">> createATicket() request {}", request);

		TicketDto ticket = lotteryHandler.createATicket(request.getNumberOfLines());

		LOGGER.info("<< createATicket() ticket {}", ticket);
		return ticket;
	}

	/**
	 * Get all current tickets objects registered.
	 *
	 * @return The {@link List} of {@link TicketDto} with its lines, the result of each line and the current status.
	 */
	@GetMapping(value = "/ticket", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TicketDto> returnListOfTickets() {
		LOGGER.info(">> returnListOfTickets()");

		List<TicketDto> tickets = lotteryHandler.returnListOfTickets();

		LOGGER.info("<< returnListOfTickets() tickets {}", tickets);
		return tickets;
	}

	/**
	 * Get an individual ticket by id.
	 *
	 * @param id the ticket ID.
	 * @return The {@link TicketDto} with its lines, the result of each line and the current status.
	 * @throws org.xavisalvador.lottery.system.core.exception.BadRequestException in case of invalid ID. It must be greater than 0.
	 * @throws org.xavisalvador.lottery.system.core.exception.NotFoundException   in case of non-existing ticket with the given ID.
	 */
	@GetMapping(value = "/ticket/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TicketDto getIndividualTicket(@PathVariable long id) throws LotterySystemException {
		LOGGER.info(">> getIndividualTicket() id {}", id);

		TicketDto ticket = lotteryHandler.getIndividualTicket(id);

		LOGGER.info("<< getIndividualTicket() ticket {}", ticket);
		return ticket;
	}

	/**
	 * Update a lottery ticket adding new generated lines.
	 *
	 * @param id      the ticket ID.
	 * @param request The {@link LineNumbersRequest} with the "numberOfLines" int.
	 * @return The {@link TicketDto} with its previous lines and the new generated, the result of each line and the current status as false.
	 * @throws org.xavisalvador.lottery.system.core.exception.BadRequestException     in case of invalid ID or invalid number of lines. They must be greater than 0.
	 * @throws org.xavisalvador.lottery.system.core.exception.NotFoundException       in case of non-existing ticket with the given ID.
	 * @throws org.xavisalvador.lottery.system.core.exception.AlreadyCheckedException in case of a ticket was checked before.
	 */
	@PutMapping(value = "/ticket/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TicketDto amendTicketLines(@PathVariable long id, @RequestBody LineNumbersRequest request) throws LotterySystemException {
		LOGGER.info(">> amendTicketLines() id {} request {}", id, request);

		TicketDto ticket = lotteryHandler.amendTicketLines(id, request.getNumberOfLines());

		LOGGER.info("<< amendTicketLines() ticket {}", ticket);
		return ticket;
	}

	/**
	 * Validate a lottery ticket and mark it as checked.
	 *
	 * @param id the ticket ID.
	 * @return The {@link TicketDto} with its lines, the result of each line and the current status.
	 * @throws org.xavisalvador.lottery.system.core.exception.BadRequestException in case of invalid ID. It must be greater than 0.
	 * @throws org.xavisalvador.lottery.system.core.exception.NotFoundException   in case of non-existing ticket with the given ID.
	 */
	@PutMapping(value = "/status/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public TicketDto retrieveStatusOfTicket(@PathVariable long id) throws LotterySystemException {
		LOGGER.info(">> retrieveStatusOfTicket() id {}", id);

		TicketDto ticket = lotteryHandler.retrieveStatusOfTicket(id);

		LOGGER.info("<< retrieveStatusOfTicket() ticket {}", ticket);
		return ticket;
	}

}
