package mx.com.tutum.model.course.infrastructure.persistence.postgresql.converter;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import mx.com.tutum.model.course.domain.Course;
import mx.com.tutum.model.course.infrastructure.persistence.postgresql.dto.CourseDto;


@Mapper
public interface CourseDtoToCourseConverter extends Converter<CourseDto, Course> {
	
	@Override
	default Course convert(CourseDto courseDto) {
		Course course = Course.create(
				courseDto.getId(),
				courseDto.getName(),
				courseDto.getEnabled());
		
		return course;
	}
	
}
