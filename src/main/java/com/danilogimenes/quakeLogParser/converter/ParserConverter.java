package com.danilogimenes.quakeLogParser.converter;

import java.util.List;

public interface ParserConverter<O,D> {
	 D ParseToDTO(O origin);
	 O ParseToEntity(D origin);
	 List<O> ParseListToEntity(List<D> origin);
	 List<D> ParseListToDTO(List<O> origin);
}
