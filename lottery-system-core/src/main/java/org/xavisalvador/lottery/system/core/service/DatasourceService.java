package org.xavisalvador.lottery.system.core.service;

import org.xavisalvador.lottery.system.core.exception.NotFoundException;
import org.xavisalvador.lottery.system.jpa.model.TicketDto;

import java.util.List;

public interface DatasourceService {

	TicketDto saveTicket(TicketDto ticket);

	List<TicketDto> getAllTickets();

	TicketDto getTicketById(long id) throws NotFoundException;

}
