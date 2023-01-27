package mx.com.tutum.extra.infrastructure.converter;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import mx.com.tutum.extra.domain.ExtraStudent;
import mx.com.tutum.extra.infrastructure.rest.dto.response.ExtraCourseScoreResponseDto;
import mx.com.tutum.extra.infrastructure.rest.dto.response.ExtraStudentResponseDto;


@Mapper
public interface ExtraStudentToExtraStudentResponseDtoConverter extends Converter<ExtraStudent, ExtraStudentResponseDto> {
	
	@Override
	default ExtraStudentResponseDto convert(ExtraStudent extraStudent) {
		ExtraStudentResponseDto extraStudentResponseDto = ExtraStudentResponseDto.create(
				extraStudent.getStudent().getId(), 
				extraStudent.getStudent().getFirstName(), 
				//extraStudent.getStudent().getFirstLastName(), 
				extraStudent.getStudent().getSecondLastName(), 
				extraStudent.getAverage(), 
				null);
		
		List<ExtraCourseScoreResponseDto> extraCourseScoreResponseDtoList = null;
		
		if (extraStudent.getStudent().getScores() != null) {
			extraCourseScoreResponseDtoList = extraStudent.getStudent().getScores().stream()
					.map(score -> ExtraCourseScoreResponseDto.create(
							score.getCourse().getName(), 
							score.getScore(), 
							score.getRegistrationDate()))
					.collect(Collectors.toList());
			
			extraStudentResponseDto.modify(
					extraStudentResponseDto.getFirstName(), 
					extraStudentResponseDto.getFirstLastName(), 
					//extraStudentResponseDto.getSecondLastName(), 
					extraStudentResponseDto.getAverage(), 
					extraCourseScoreResponseDtoList);
		}
		
		return extraStudentResponseDto;
	}

}
