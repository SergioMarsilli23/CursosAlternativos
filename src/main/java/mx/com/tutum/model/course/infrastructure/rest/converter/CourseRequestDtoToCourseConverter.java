package mx.com.tutum.model.course.infrastructure.rest.converter;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import mx.com.tutum.model.course.domain.Course;
import mx.com.tutum.model.course.infrastructure.rest.dto.CourseRequestDto;


@Mapper
public interface CourseRequestDtoToCourseConverter extends Converter<CourseRequestDto, Course> {
	
	@Override
	default Course convert(CourseRequestDto courseRequestDto) {
		Course course = Course.create(
				courseRequestDto.getId(),
				courseRequestDto.getName(),
				courseRequestDto.getEnabled());
		
		return course;
	}
	
}
