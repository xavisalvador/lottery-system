package org.xavisalvador.lottery.system.jpa.mapper.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.xavisalvador.lottery.system.jpa.entity.Line;
import org.xavisalvador.lottery.system.jpa.entity.Ticket;
import org.xavisalvador.lottery.system.jpa.mapper.LineMapper;
import org.xavisalvador.lottery.system.jpa.model.LineDto;

import java.util.*;

@Component
public class LineMapperImpl implements LineMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(LineMapperImpl.class);

	private final Random random = new Random();

	@Override
	public LineDto lineToLineDto(Line line) {
		LOGGER.debug(">> lineToLineDto() line {}", line);

		if (line == null) {
			LOGGER.debug("<< lineToLineDto() lineDto null");
			return null;
		}
		LineDto lineDto = LineDto.builder()
				.id(line.getId())
				.numbers(getNumbersByLineNumbers(line.getLineNumbers()))
				.result(line.getResult())
				.build();

		LOGGER.debug("<< lineToLineDto() lineDto {}", lineDto);
		return lineDto;
	}

	@Override
	public Line lineDtoToLine(LineDto lineDto, Long id) {
		LOGGER.debug(">> lineDtoToLine() lineDto {} id {}", lineDto, id);

		if (lineDto == null) {
			LOGGER.debug("<< lineDtoToLine() line null");
			return null;
		}
		Line line = Line.builder()
				.id(lineDto.getId())
				.lineNumbers(getLineNumbersByNumbers(lineDto.getNumbers()))
				.result(lineDto.getResult())
				.ticket(Ticket.builder().id(id).build())
				.serialNumber(getRandomToAvoidDuplications())
				.build();

		LOGGER.debug("<< lineDtoToLine() line {}", line);
		return line;
	}

	@Override
	public List<LineDto> lineListToLineDtoList(Set<Line> lineList) {
		LOGGER.debug(">> lineListToLineDtoList() lineList {}", lineList);

		if (lineList == null || lineList.isEmpty()) {
			LOGGER.debug("<< lineListToLineDtoList() lineDtoList is null or empty");
			return new ArrayList<>();
		}

		List<LineDto> lineDtoList = new ArrayList<>();
		for (Line line : lineList) {
			lineDtoList.add(lineToLineDto(line));
		}
		lineDtoList.sort((o1, o2) -> o2.getResult() - o1.getResult());

		LOGGER.debug("<< lineListToLineDtoList() lineDtoList {}", lineDtoList);
		return lineDtoList;
	}

	@Override
	public Set<Line> lineDtoListToLineList(List<LineDto> lineDtoList, Long id) {
		LOGGER.debug(">> lineDtoListToLineList() lineDtoList {} id {}", lineDtoList, id);

		if (lineDtoList == null || lineDtoList.isEmpty()) {
			LOGGER.debug("<< lineDtoListToLineList() lineList is null or empty");
			return new HashSet<>();
		}

		Set<Line> lineList = new HashSet<>();
		for (LineDto lineDto : lineDtoList) {
			lineList.add(lineDtoToLine(lineDto, id));
		}

		LOGGER.debug("<< lineDtoListToLineList() lineList {}", lineList);
		return lineList;
	}

	// region Private methods

	private static int getLineNumbersByNumbers(int[] numbers) {
		return 100 * numbers[0] + 10 * numbers[1] + numbers[2];
	}

	private static int[] getNumbersByLineNumbers(int lineNumbers) {
		return new int[]{lineNumbers / 100, (lineNumbers / 10) % 10, lineNumbers % 10};
	}

	private long getRandomToAvoidDuplications() {
		return System.currentTimeMillis() / 100000 * random.nextInt(999) + 1;
	}

	// endregion

}
