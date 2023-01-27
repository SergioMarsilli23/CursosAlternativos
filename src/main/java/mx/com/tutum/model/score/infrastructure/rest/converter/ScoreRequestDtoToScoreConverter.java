package mx.com.tutum.model.score.infrastructure.rest.converter;

import org.mapstruct.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import mx.com.tutum.model.course.domain.Course;
import mx.com.tutum.model.course.infrastructure.rest.dto.CourseRequestDto;
import mx.com.tutum.model.score.domain.Score;
import mx.com.tutum.model.score.infrastructure.rest.dto.ScoreRequestDto;
import mx.com.tutum.model.student.domain.Student;
import mx.com.tutum.model.student.infrastructure.rest.dto.StudentRequestDto;
import mx.com.tutum.shared.domain.ApplicationContextProvider;


@Mapper
public interface ScoreRequestDtoToScoreConverter extends Converter<ScoreRequestDto, Score> {
	
	@Override
	default Score convert(ScoreRequestDto scoreRequestDto) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		ConversionService service = applicationContext.getBean(ConversionService.class);
		
		Score score = Score.create(
				scoreRequestDto.getId(),
				scoreRequestDto.getScore(),
				null,
				null,
				null);
		
		Course course = null;
		Student student = null;
		
		if (scoreRequestDto.getCourse() != null) {
			CourseRequestDto courseRequestDto = scoreRequestDto.getCourse();
			CourseRequestDto courseRequestDtoCopy = new CourseRequestDto(
					courseRequestDto.getId(), 
					courseRequestDto.getName(), 
					courseRequestDto.getEnabled());
			course = service.convert(courseRequestDtoCopy, Course.class);
		}
		
		if (scoreRequestDto.getStudent() != null) {
			StudentRequestDto managerRequestDto = scoreRequestDto.getStudent();
			StudentRequestDto managerRequestDtoCopy = new StudentRequestDto(
					managerRequestDto.getId(), 
					managerRequestDto.getFirstName(), 
					managerRequestDto.getFirstLastName(), 
					managerRequestDto.getSecondLastName(), 
					managerRequestDto.getEnabled());
			student = service.convert(managerRequestDtoCopy, Student.class);
		}
		
		if (course != null || student != null) {
			score.modify(score.getScore(), 
					score.getRegistrationDate(), 
					course, 
					student);
		}
		
		return score;
	}
	
}
