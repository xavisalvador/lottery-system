package org.xavisalvador.lottery.system.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xavisalvador.lottery.system.core.exception.NotFoundException;
import org.xavisalvador.lottery.system.core.service.DatasourceService;
import org.xavisalvador.lottery.system.jpa.mapper.LineMapper;
import org.xavisalvador.lottery.system.jpa.mapper.TicketMapper;
import org.xavisalvador.lottery.system.jpa.model.TicketDto;
import org.xavisalvador.lottery.system.jpa.repository.LineRepository;
import org.xavisalvador.lottery.system.jpa.repository.TicketRepository;

import java.util.HashSet;
import java.util.List;

@Service
public class JpaServiceImpl implements DatasourceService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JpaServiceImpl.class);

	private final LineMapper lineMapper;
	private final TicketMapper ticketMapper;
	private final LineRepository lineRepository;
	private final TicketRepository ticketRepository;

	public JpaServiceImpl(LineMapper lineMapper, TicketMapper ticketMapper, LineRepository lineRepository, TicketRepository ticketRepository) {
		this.lineMapper = lineMapper;
		this.ticketMapper = ticketMapper;
		this.lineRepository = lineRepository;
		this.ticketRepository = ticketRepository;
	}

	@Override
	@Transactional
	public TicketDto saveTicket(TicketDto ticket) {
		LOGGER.debug(">> saveTicket() ticket {}", ticket);

		TicketDto persistedTicket = ticketMapper.ticketToTicketDto(ticketRepository.save(ticketMapper.ticketDtoToTicket(ticket)));
		persistedTicket.setLines(lineMapper.lineListToLineDtoList(new HashSet<>(lineRepository.saveAll(lineMapper.lineDtoListToLineList(ticket.getLines(),
				persistedTicket.getId())))));

		LOGGER.debug("<< saveTicket() persistedTicket {}", persistedTicket);
		return persistedTicket;
	}

	@Override
	@Transactional
	public List<TicketDto> getAllTickets() {
		LOGGER.debug(">> getAllTickets()");

		List<TicketDto> tickets = ticketMapper.ticketListToTicketDtoList(ticketRepository.findAll());

		LOGGER.debug("<< getAllTickets() tickets {}", tickets);
		return tickets;
	}

	@Override
	@Transactional
	public TicketDto getTicketById(long id) throws NotFoundException {
		LOGGER.debug(">> getAllTickets()");

		TicketDto ticket = ticketMapper.ticketToTicketDto(ticketRepository.findById(id).orElseThrow(NotFoundException::new));

		LOGGER.debug("<< getAllTickets() ticket {}", ticket);
		return ticket;
	}

}
