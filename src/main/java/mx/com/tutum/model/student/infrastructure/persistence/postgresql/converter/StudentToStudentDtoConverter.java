package mx.com.tutum.model.student.infrastructure.persistence.postgresql.converter;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import mx.com.tutum.model.student.domain.Student;
import mx.com.tutum.model.student.infrastructure.persistence.postgresql.dto.StudentDto;


@Mapper
public interface StudentToStudentDtoConverter extends Converter<Student, StudentDto> {

	@Override
	StudentDto convert(Student student);
}
