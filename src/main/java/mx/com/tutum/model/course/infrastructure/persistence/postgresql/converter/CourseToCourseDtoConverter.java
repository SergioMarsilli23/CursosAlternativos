package mx.com.tutum.model.course.infrastructure.persistence.postgresql.converter;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import mx.com.tutum.model.course.domain.Course;
import mx.com.tutum.model.course.infrastructure.persistence.postgresql.dto.CourseDto;

@Mapper
public interface CourseToCourseDtoConverter extends Converter<Course, CourseDto> {

	@Override
	CourseDto convert(Course course);
}
