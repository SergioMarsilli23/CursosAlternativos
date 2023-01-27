package mx.com.tutum.model.score.infrastructure.persistence.postgresql.converter;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import mx.com.tutum.model.score.domain.Score;
import mx.com.tutum.model.score.infrastructure.persistence.postgresql.dto.ScoreDto;

@Mapper
public interface ScoreToScoreDtoConverter extends Converter<Score, ScoreDto> {

	@Override
	ScoreDto convert(Score score);
}
