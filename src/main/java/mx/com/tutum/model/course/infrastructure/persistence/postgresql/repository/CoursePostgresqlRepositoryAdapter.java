package mx.com.tutum.model.course.infrastructure.persistence.postgresql.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import mx.com.tutum.model.course.domain.Course;
import mx.com.tutum.model.course.domain.CourseRepository;
import mx.com.tutum.model.course.infrastructure.persistence.postgresql.dto.CourseDto;
import mx.com.tutum.shared.domain.ObjectNotFoundException;

@Repository
@RequiredArgsConstructor
public class CoursePostgresqlRepositoryAdapter implements CourseRepository {

	private final CoursePostgresqlRepository coursePostgresqlRepository;
	private final ConversionService conversionService;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Course> byId(Long id) {
		Optional<CourseDto> courseDto = coursePostgresqlRepository.findById(id);
		Optional<Course> course = Optional.of(conversionService.convert(courseDto.orElseThrow(() ->  new ObjectNotFoundException("Gestor de contenido no encontrado")), Course.class));
		
		return course;
	}

	@Override
	public List<Course> all() {
		List<Course> courseList = new ArrayList<Course>();
		coursePostgresqlRepository.findAll().forEach(courseDto -> courseList.add(conversionService.convert(courseDto, Course.class)));
		
		return courseList;
	}

	@Override
	public void delete(Long id) {
		coursePostgresqlRepository.deleteById(id);
	}

	@Override
	public Optional<Course> save(Course course) {
		CourseDto courseDto = coursePostgresqlRepository.save(conversionService.convert(course, CourseDto.class));
		return Optional.of(conversionService.convert(courseDto, Course.class));
	}

}