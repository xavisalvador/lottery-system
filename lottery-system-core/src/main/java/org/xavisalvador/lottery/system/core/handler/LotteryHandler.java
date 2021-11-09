package org.xavisalvador.lottery.system.core.handler;

import org.xavisalvador.lottery.system.core.exception.AlreadyCheckedException;
import org.xavisalvador.lottery.system.core.exception.BadRequestException;
import org.xavisalvador.lottery.system.core.exception.NotFoundException;
import org.xavisalvador.lottery.system.jpa.model.TicketDto;

import java.util.List;

public interface LotteryHandler {

	TicketDto createATicket(int numberOfLines) throws BadRequestException;

	List<TicketDto> returnListOfTickets();

	TicketDto getIndividualTicket(long id) throws NotFoundException, BadRequestException;

	TicketDto amendTicketLines(long id, int numberOfLines) throws BadRequestException, NotFoundException, AlreadyCheckedException;

	TicketDto retrieveStatusOfTicket(long id) throws BadRequestException, NotFoundException;

}
