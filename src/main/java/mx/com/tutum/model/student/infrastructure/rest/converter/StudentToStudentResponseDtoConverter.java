package mx.com.tutum.model.student.infrastructure.rest.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import mx.com.tutum.model.score.domain.Score;
import mx.com.tutum.model.score.infrastructure.rest.dto.ScoreResponseDto;
import mx.com.tutum.model.student.domain.Student;
import mx.com.tutum.model.student.infrastructure.rest.dto.StudentResponseDto;
import mx.com.tutum.shared.domain.ApplicationContextProvider;


@Mapper
public interface StudentToStudentResponseDtoConverter extends Converter<Student, StudentResponseDto> {
	
	@Override
	default StudentResponseDto convert(Student student) {
		ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
		ConversionService service = applicationContext.getBean(ConversionService.class);
		
		StudentResponseDto studentResponseDto = StudentResponseDto.create(
				student.getId(), 
				student.getFirstName(), 
				student.getFirstLastName(), 
				student.getSecondLastName(), 
				student.getEnabled(),
				null);
		
		if (student.getScores() != null) {
			List<ScoreResponseDto> scoreResponseDtoList = student.getScores().stream()
					.map(score -> {
						Score scoreCopy = score.createClone();
						scoreCopy.modify(
								scoreCopy.getScore(), 
								scoreCopy.getRegistrationDate(), 
								scoreCopy.getCourse(), 
								null);
						return service.convert(scoreCopy, ScoreResponseDto.class);
					})
					.collect(Collectors.toList());
			
			scoreResponseDtoList.stream().forEach(scoreResponseDto -> scoreResponseDto.modify(
					scoreResponseDto.getScore(), 
					scoreResponseDto.getRegistrationDate(), 
					scoreResponseDto.getCourse(), 
					studentResponseDto));
			
			studentResponseDto.modify(
					studentResponseDto.getFirstName(), 
					studentResponseDto.getFirstLastName(), 
					studentResponseDto.getSecondLastName(), 
					studentResponseDto.getEnabled(),
					scoreResponseDtoList);
		}
		
		return studentResponseDto;
	}	
}
