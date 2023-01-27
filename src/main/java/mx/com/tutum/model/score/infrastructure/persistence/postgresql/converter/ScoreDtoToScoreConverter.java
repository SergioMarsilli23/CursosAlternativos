package mx.com.tutum.model.score.infrastructure.persistence.postgresql.converter;

import org.mapstruct.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import mx.com.tutum.model.course.domain.Course;
import mx.com.tutum.model.course.infrastructure.persistence.postgresql.dto.CourseDto;
import mx.com.tutum.model.score.domain.Score;
import mx.com.tutum.model.score.infrastructure.persistence.postgresql.dto.ScoreDto;
import mx.com.tutum.model.student.domain.Student;
import mx.com.tutum.model.student.infrastructure.persistence.postgresql.dto.StudentDto;
import mx.com.tutum.shared.domain.ApplicationContextProvider;

@Mapper
public interface ScoreDtoToScoreConverter extends Converter<ScoreDto, Score> {
	
	@Override
	default Score convert(ScoreDto scoreDto) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		ConversionService service = applicationContext.getBean(ConversionService.class);
		
		Course course = null;
		Student student = null;
		
		Score score = Score.create(
				scoreDto.getId(),
				scoreDto.getScore(),
				scoreDto.getRegistrationDate(),
				null,
				null);
		
		if (scoreDto.getCourse() != null) {
			CourseDto courseDtoCopy = scoreDto.getCourse().createClone();
			course = service.convert(courseDtoCopy, Course.class);
		}
		
		if (scoreDto.getStudent() != null) {
			StudentDto studentDtoCopy = scoreDto.getStudent().createClone();
			studentDtoCopy.setScores(null);
			student = service.convert(studentDtoCopy, Student.class);
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
