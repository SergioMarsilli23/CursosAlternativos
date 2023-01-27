package mx.com.tutum.extra.application;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import mx.com.tutum.extra.infrastructure.rest.dto.request.ExtraScoreSaveRequestDto;
import mx.com.tutum.extra.infrastructure.rest.dto.request.ExtraScoreUpdateRequestDto;
import mx.com.tutum.model.course.infrastructure.rest.dto.CourseRequestDto;
import mx.com.tutum.model.score.application.ScoreService;
import mx.com.tutum.model.score.domain.Score;
import mx.com.tutum.model.score.infrastructure.rest.dto.ScoreRequestDto;
import mx.com.tutum.model.student.infrastructure.rest.dto.StudentRequestDto;


@Service
@RequiredArgsConstructor
public class ExtraScoreService {
	
	private final ScoreService scoreService;
	private final ConversionService conversionService;

	public Optional<Score> save(@NotNull @Valid ExtraScoreSaveRequestDto extraScoreRequestDto) {
		ScoreRequestDto scoreRequestDto = new ScoreRequestDto(null, 
				extraScoreRequestDto.getScore(), 
				new CourseRequestDto(extraScoreRequestDto.getCourseId(), null, null),
				new StudentRequestDto(extraScoreRequestDto.getStudentId(), null, null, null, null));
		
		Score score = this.scoreService
				.save(conversionService.convert(scoreRequestDto, Score.class))
				.orElseThrow();
		
		return Optional.ofNullable(score);
	}

	public Optional<Score> save(@NotNull @Valid ExtraScoreUpdateRequestDto extraScoreUpdateRequestDto) {
		Score score = Score.create(extraScoreUpdateRequestDto.getId(), 
				extraScoreUpdateRequestDto.getScore(), 
				null, 
				null, 
				null);
		
		score = this.scoreService
				.save(score)
				.orElseThrow();
		
		return Optional.ofNullable(score);
	}

	public void delete(Long id) {
		this.scoreService.delete(id);
	}

}
