package org.xavisalvador.lottery.system.core.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xavisalvador.lottery.system.core.exception.AlreadyCheckedException;
import org.xavisalvador.lottery.system.core.exception.BadRequestException;
import org.xavisalvador.lottery.system.core.exception.NotFoundException;
import org.xavisalvador.lottery.system.core.handler.LineGeneratorHandler;
import org.xavisalvador.lottery.system.core.handler.LineResultHandler;
import org.xavisalvador.lottery.system.core.handler.LotteryHandler;
import org.xavisalvador.lottery.system.core.service.DatasourceService;
import org.xavisalvador.lottery.system.jpa.model.LineDto;
import org.xavisalvador.lottery.system.jpa.model.TicketDto;

import java.util.ArrayList;
import java.util.List;

import static org.xavisalvador.lottery.system.core.util.ValidationUtil.*;

@Service
public class LotteryHandlerImpl implements LotteryHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(LotteryHandlerImpl.class);

	private final DatasourceService datasourceService;
	private final LineResultHandler lineResultHandler;
	private final LineGeneratorHandler lineGeneratorHandler;

	public LotteryHandlerImpl(DatasourceService datasourceService, LineResultHandler lineResultHandler, LineGeneratorHandler lineGeneratorHandler) {
		this.datasourceService = datasourceService;
		this.lineResultHandler = lineResultHandler;
		this.lineGeneratorHandler = lineGeneratorHandler;
	}

	@Override
	public TicketDto createATicket(int numberOfLines) throws BadRequestException {
		LOGGER.debug(">> createATicket() numberOfLines {}", numberOfLines);

		validateNumberOfLines(numberOfLines);
		TicketDto ticket = datasourceService.saveTicket(TicketDto.builder().lines(getNewLines(numberOfLines)).build());

		LOGGER.debug("<< createATicket() ticket {}", ticket);
		return ticket;
	}

	@Override
	public List<TicketDto> returnListOfTickets() {
		LOGGER.debug(">> returnListOfTickets()");

		List<TicketDto> tickets = datasourceService.getAllTickets();

		LOGGER.debug("<< returnListOfTickets() tickets {}", tickets);
		return tickets;
	}

	@Override
	public TicketDto getIndividualTicket(long id) throws NotFoundException, BadRequestException {
		LOGGER.debug(">> getIndividualTicket() id {}", id);

		validateTicketId(id);
		TicketDto ticket = datasourceService.getTicketById(id);

		LOGGER.debug("<< getIndividualTicket() ticket {}", ticket);
		return ticket;
	}

	@Override
	public TicketDto amendTicketLines(long id, int numberOfLines) throws BadRequestException, NotFoundException, AlreadyCheckedException {
		LOGGER.debug(">> amendTicketLines() id {} numberOfLines {}", id, numberOfLines);

		validateTicketId(id);
		validateNumberOfLines(numberOfLines);
		TicketDto ticket = getIndividualTicket(id);
		validateTicketNotChecked(ticket.isChecked());
		ticket.getLines().addAll(getNewLines(numberOfLines));
		TicketDto persistedTicket = datasourceService.saveTicket(ticket);

		LOGGER.debug("<< amendTicketLines() persistedTicket {}", persistedTicket);
		return persistedTicket;
	}

	@Override
	public TicketDto retrieveStatusOfTicket(long id) throws BadRequestException, NotFoundException {
		LOGGER.debug(">> retrieveStatusOfTicket() id {}", id);

		validateTicketId(id);
		TicketDto ticket = getIndividualTicket(id);
		if (!ticket.isChecked()) {
			ticket.setChecked(true);
			datasourceService.saveTicket(ticket);
		}

		LOGGER.debug("<< retrieveStatusOfTicket() ticket {}", ticket);
		return ticket;
	}

	// region Private methods

	private List<LineDto> getNewLines(int numberOfLines) {
		LOGGER.trace(">> getNewLines() numberOfLines {}", numberOfLines);

		List<LineDto> newLines = new ArrayList<>();
		for (int i = 0; i < numberOfLines; i++) {
			int[] newLine = lineGeneratorHandler.generateLine();
			int result = lineResultHandler.getResult(newLine);
			newLines.add(LineDto.builder().numbers(newLine).result(result).build());
		}

		LOGGER.trace("<< getNewLines() newLines {}", newLines);
		return newLines;
	}

	// endregion

}
