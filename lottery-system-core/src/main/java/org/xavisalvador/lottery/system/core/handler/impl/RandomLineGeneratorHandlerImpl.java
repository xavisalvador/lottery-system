package org.xavisalvador.lottery.system.core.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xavisalvador.lottery.system.core.handler.LineGeneratorHandler;

import java.util.Random;

@Service
public class RandomLineGeneratorHandlerImpl implements LineGeneratorHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RandomLineGeneratorHandlerImpl.class);

	private final Random random = new Random();

	@Override
	public int[] generateLine() {
		LOGGER.debug(">> generateLine()");

		int[] line = {random.nextInt(3), random.nextInt(3), random.nextInt(3)};

		LOGGER.debug("<< generateLine() line {}", line);
		return line;
	}

}
