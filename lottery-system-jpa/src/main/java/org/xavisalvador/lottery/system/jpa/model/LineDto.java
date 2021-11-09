package org.xavisalvador.lottery.system.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineDto implements Serializable {

	private Long id;
	private int[] numbers;
	private int result;

}
