package mx.com.tutum.model.student.infrastructure.rest;

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
import mx.com.tutum.model.student.application.StudentService;
import mx.com.tutum.model.student.domain.Student;
import mx.com.tutum.model.student.infrastructure.rest.dto.StudentRequestDto;
import mx.com.tutum.model.student.infrastructure.rest.dto.StudentResponseDto;


@RestController
@RequestMapping("/rest/student")
@Api
@Slf4j
@AllArgsConstructor
public class StudentController {

	private final StudentService studentService;
	private final ConversionService conversionService;

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<StudentResponseDto>> getAll() {
		try {			
			List<Student> studentList = this.studentService.getAll();
			List<StudentResponseDto> studentResponseDtoList = studentList
					.parallelStream()
					.map(student -> conversionService.convert(student, StudentResponseDto.class))
					.collect(Collectors.toList());
			
			return ResponseEntity.ok().body(studentResponseDtoList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<StudentResponseDto> getById(@PathVariable Long id) {
		try {			
			Student student = this.studentService.get(id).orElseThrow();
			StudentResponseDto studentResponseDto = conversionService
					.convert(student, StudentResponseDto.class);
			
			return ResponseEntity.ok().body(studentResponseDto);
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
	public ResponseEntity<StudentResponseDto> save(@NotNull @Valid @RequestBody final StudentRequestDto studentRequestDto) {
		try {
			Student student = this.studentService
					.save(conversionService.convert(studentRequestDto, Student.class))
					.orElseThrow();
			StudentResponseDto studentResponseDto = conversionService
					.convert(student, StudentResponseDto.class);
			
			return ResponseEntity.ok().body(studentResponseDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<StudentResponseDto> update(@NotNull @Valid @RequestBody final StudentRequestDto studentRequestDto) {
		try {
			Student student = this.studentService
					.save(conversionService.convert(studentRequestDto, Student.class))
					.orElseThrow();
			StudentResponseDto studentResponseDto = conversionService
					.convert(student, StudentResponseDto.class);
			
			return ResponseEntity.ok().body(studentResponseDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable final Long id) {
		this.studentService.delete(id);
	}
	
}