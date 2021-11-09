package org.xavisalvador.lottery.system.core.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xavisalvador.lottery.system.core.handler.LineResultHandler;

import java.util.Arrays;

@Service
public class LineResultHandlerImpl implements LineResultHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(LineResultHandlerImpl.class);

	@Override
	public int getResult(int[] line) {
		LOGGER.debug(">> lineResult() line {}", line);

		int lineResult = 0;
		if (Arrays.stream(line).sum() == 2) {
			lineResult = 10;
		} else if (line[0] == line[1] && line[0] == line[2]) {
			lineResult = 5;
		} else if (line[0] != line[1] && line[0] != line[2]) {
			lineResult = 1;
		}

		LOGGER.debug("<< lineResult() lineResult {}", lineResult);
		return lineResult;
	}

}
