package mx.com.tutum.model.student.infrastructure.persistence.postgresql.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import mx.com.tutum.model.score.domain.Score;
import mx.com.tutum.model.score.infrastructure.persistence.postgresql.dto.ScoreDto;
import mx.com.tutum.model.student.domain.Student;
import mx.com.tutum.model.student.infrastructure.persistence.postgresql.dto.StudentDto;
import mx.com.tutum.shared.domain.ApplicationContextProvider;

@Mapper
public interface StudentDtoToStudentConverter extends Converter<StudentDto, Student> {
	
	@Override
	default Student convert(StudentDto studentDto) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		ConversionService service = applicationContext.getBean(ConversionService.class);
		
		Student student = Student.create(
				studentDto.getId(),
				studentDto.getFirstName(),
				studentDto.getFirstLastName(),
				studentDto.getSecondLastName(),
				studentDto.getEnabled(),
				null);
		
		if (studentDto.getScores() != null) {
			List<Score> scores = studentDto.getScores().stream().map(
					(scoreDto) -> {
						//Prevent a StackOverflow error
						ScoreDto scoreDtoCopy = scoreDto.createClone();
						scoreDtoCopy.setStudent(null);
						return service.convert(scoreDtoCopy, Score.class);
					})
				.collect(Collectors.toList());
			
			scores.stream().forEach(score -> score.modify(
					score.getScore(), 
					score.getRegistrationDate(), 
					score.getCourse(), 
					student));
			
			student.modify(
					student.getFirstName(), 
					student.getFirstLastName(), 
					student.getSecondLastName(),
					student.getEnabled(),
					scores);
		}
		
		return student;
	}
	
}
