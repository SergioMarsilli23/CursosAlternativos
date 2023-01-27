package mx.com.tutum.model.score.infrastructure.rest.converter;

import org.mapstruct.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import mx.com.tutum.model.course.domain.Course;
import mx.com.tutum.model.course.infrastructure.rest.dto.CourseResponseDto;
import mx.com.tutum.model.score.domain.Score;
import mx.com.tutum.model.score.infrastructure.rest.dto.ScoreResponseDto;
import mx.com.tutum.model.student.domain.Student;
import mx.com.tutum.model.student.infrastructure.rest.dto.StudentResponseDto;
import mx.com.tutum.shared.domain.ApplicationContextProvider;


@Mapper
public interface ScoreToScoreResponseDtoConverter extends Converter<Score, ScoreResponseDto> {
	
	@Override
	default ScoreResponseDto convert(Score score) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		ConversionService service = applicationContext.getBean(ConversionService.class);
		
		ScoreResponseDto scoreResponseDto = ScoreResponseDto.create(
				score.getId(), 
				score.getScore(), 
				score.getRegistrationDate(), 
				null, 
				null);
		
		CourseResponseDto courseResponseDto = null;
		StudentResponseDto studentResponseDto = null;
		
		if (score.getCourse() != null) {
			Course courseCopy = score.getCourse().createClone();
			courseCopy.modify(
					courseCopy.getName(), 
					courseCopy.getEnabled());
			courseResponseDto = service.convert(courseCopy, CourseResponseDto.class);
		}
		
		if (score.getStudent() != null) {
			Student studentCopy = score.getStudent().createClone();
			studentCopy.modify(
					studentCopy.getFirstName(), 
					studentCopy.getFirstLastName(),
					studentCopy.getSecondLastName(),
					studentCopy.getEnabled(),
					null);
			studentResponseDto = service.convert(studentCopy, StudentResponseDto.class);
		}
		
		if (courseResponseDto != null || studentResponseDto != null) {
			scoreResponseDto.modify(scoreResponseDto.getScore(), 
					scoreResponseDto.getRegistrationDate(), 
					courseResponseDto, 
					studentResponseDto);
		}
		
		
		return scoreResponseDto;
	}	
}
