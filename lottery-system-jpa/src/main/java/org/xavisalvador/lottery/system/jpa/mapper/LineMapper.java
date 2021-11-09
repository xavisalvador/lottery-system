package org.xavisalvador.lottery.system.jpa.mapper;

import org.xavisalvador.lottery.system.jpa.entity.Line;
import org.xavisalvador.lottery.system.jpa.model.LineDto;

import java.util.List;
import java.util.Set;

public interface LineMapper {

	LineDto lineToLineDto(Line line);

	Line lineDtoToLine(LineDto lineDto, Long id);

	List<LineDto> lineListToLineDtoList(Set<Line> lineList);

	Set<Line> lineDtoListToLineList(List<LineDto> lineDtoList, Long id);

}
