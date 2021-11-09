package org.xavisalvador.lottery.system.jpa.mapper;

import org.xavisalvador.lottery.system.jpa.entity.Ticket;
import org.xavisalvador.lottery.system.jpa.model.TicketDto;

import java.util.List;

public interface TicketMapper {

	TicketDto ticketToTicketDto(Ticket ticket);

	Ticket ticketDtoToTicket(TicketDto ticketDto);

	List<TicketDto> ticketListToTicketDtoList(List<Ticket> ticketList);

}
