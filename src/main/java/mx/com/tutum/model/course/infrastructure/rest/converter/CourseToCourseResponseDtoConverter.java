package mx.com.tutum.model.course.infrastructure.rest.converter;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import mx.com.tutum.model.course.domain.Course;
import mx.com.tutum.model.course.infrastructure.rest.dto.CourseResponseDto;


@Mapper
public interface CourseToCourseResponseDtoConverter extends Converter<Course, CourseResponseDto> {
	
	@Override
	default CourseResponseDto convert(Course course) {
		CourseResponseDto courseResponseDto = CourseResponseDto.create(
				course.getId(), 
				course.getName(), 
				course.getEnabled());
				
		return courseResponseDto;
	}	
}
