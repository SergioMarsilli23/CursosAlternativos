package mx.com.tutum.model.course.domain;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

	Optional<Course> byId(Long id);

	void delete(Long id);

	List<Course> all();

	Optional<Course> save(Course course);
	
}