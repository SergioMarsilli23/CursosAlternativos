package mx.com.tutum.model.student.infrastructure.rest.converter;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import mx.com.tutum.model.student.domain.Student;
import mx.com.tutum.model.student.infrastructure.rest.dto.StudentRequestDto;


@Mapper
public interface StudentRequestDtoToStudentConverter extends Converter<StudentRequestDto, Student> {
	
	@Override
	default Student convert(StudentRequestDto studentRequestDto) {
		Student student = Student.create(
				studentRequestDto.getId(),
				studentRequestDto.getFirstName(),
				studentRequestDto.getFirstLastName(),
				studentRequestDto.getSecondLastName(),
				studentRequestDto.getEnabled(),
				null);
		
		return student;
	}
	
}
