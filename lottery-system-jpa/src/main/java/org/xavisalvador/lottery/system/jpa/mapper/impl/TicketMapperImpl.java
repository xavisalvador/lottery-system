package org.xavisalvador.lottery.system.jpa.mapper.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xavisalvador.lottery.system.jpa.entity.Ticket;
import org.xavisalvador.lottery.system.jpa.mapper.LineMapper;
import org.xavisalvador.lottery.system.jpa.mapper.TicketMapper;
import org.xavisalvador.lottery.system.jpa.model.TicketDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketMapperImpl implements TicketMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(TicketMapperImpl.class);

	private final LineMapper lineMapper;

	public TicketMapperImpl(LineMapper lineMapper) {
		this.lineMapper = lineMapper;
	}

	@Override
	public TicketDto ticketToTicketDto(Ticket ticket) {
		LOGGER.debug(">> ticketToTicketDto() ticket {}", ticket);

		if (ticket == null) {
			LOGGER.debug("<< ticketToTicketDto() ticketDto null");
			return null;
		}
		TicketDto ticketDto = TicketDto.builder()
				.id(ticket.getId())
				.lines(lineMapper.lineListToLineDtoList(ticket.getLines()))
				.checked(ticket.isChecked())
				.build();

		LOGGER.debug("<< ticketToTicketDto() ticketDto {}", ticketDto);
		return ticketDto;
	}

	@Override
	public Ticket ticketDtoToTicket(TicketDto ticketDto) {
		LOGGER.debug(">> ticketDtoToTicket() ticketDto {}", ticketDto);

		if (ticketDto == null) {
			LOGGER.debug("<< ticketDtoToTicket() ticket null");
			return null;
		}
		Ticket ticket = Ticket.builder()
				.id(ticketDto.getId())
				.checked(ticketDto.isChecked())
				.build();

		LOGGER.debug("<< ticketDtoToTicket() ticket {}", ticket);
		return ticket;
	}

	@Override
	public List<TicketDto> ticketListToTicketDtoList(List<Ticket> ticketList) {
		LOGGER.debug(">> ticketListToTicketDtoList() ticketList {}", ticketList);

		if (ticketList == null || ticketList.isEmpty()) {
			LOGGER.debug("<< ticketListToTicketDtoList() ticketDtoList is null or empty");
			return new ArrayList<>();
		}

		List<TicketDto> ticketDtoList = new ArrayList<>();
		for (Ticket ticket : ticketList) {
			ticketDtoList.add(ticketToTicketDto(ticket));
		}

		LOGGER.debug("<< ticketListToTicketDtoList() ticketDtoList {}", ticketDtoList);
		return ticketDtoList;
	}
}
