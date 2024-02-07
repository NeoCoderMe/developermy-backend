package com.developermy.trading.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CandleDTO {
	private Double open;
	private Double close;
	private Double lower;
	private Double higher;
}
