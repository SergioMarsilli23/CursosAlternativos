package mx.com.tutum.model.course.infrastructure.rest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.tutum.model.course.application.CourseService;
import mx.com.tutum.model.course.domain.Course;
import mx.com.tutum.model.course.infrastructure.rest.dto.CourseRequestDto;
import mx.com.tutum.model.course.infrastructure.rest.dto.CourseResponseDto;


@RestController
@RequestMapping("/rest/course")
@Api
@Slf4j
@AllArgsConstructor
public class CourseController {

	private final CourseService courseService;
	private final ConversionService conversionService;

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<CourseResponseDto>> getAll() {
		try {			
			List<Course> courseList = this.courseService.getAll();
			List<CourseResponseDto> courseResponseDtoList = courseList
					.parallelStream()
					.map(course -> conversionService.convert(course, CourseResponseDto.class))
					.collect(Collectors.toList());
			
			return ResponseEntity.ok().body(courseResponseDtoList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<CourseResponseDto> getById(@PathVariable Long id) {
		try {			
			Course course = this.courseService.get(id).orElseThrow();
			CourseResponseDto courseResponseDto = conversionService
					.convert(course, CourseResponseDto.class);
			
			return ResponseEntity.ok().body(courseResponseDto);
		} catch (NoSuchElementException nsee) {
			log.error(nsee.getMessage(), nsee);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CourseResponseDto> save(@NotNull @Valid @RequestBody final CourseRequestDto courseRequestDto) {
		try {
			Course course = this.courseService
					.save(conversionService.convert(courseRequestDto, Course.class))
					.orElseThrow();
			CourseResponseDto courseResponseDto = conversionService
					.convert(course, CourseResponseDto.class);
			
			return ResponseEntity.ok().body(courseResponseDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CourseResponseDto> update(@NotNull @Valid @RequestBody final CourseRequestDto courseRequestDto) {
		try {
			Course course = this.courseService
					.save(conversionService.convert(courseRequestDto, Course.class))
					.orElseThrow();
			CourseResponseDto courseResponseDto = conversionService
					.convert(course, CourseResponseDto.class);
			
			return ResponseEntity.ok().body(courseResponseDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable final Long id) {
		this.courseService.delete(id);
	}
	
}