package mx.com.tutum.model.course.application;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import mx.com.tutum.model.course.domain.Course;
import mx.com.tutum.model.course.domain.CourseRepository;

@Service
@RequiredArgsConstructor
public class CourseService {

	private final CourseRepository repository;


	public Optional<Course> save(Course course) {
		return repository.save(course);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public Optional<Course> get(Long id) {
		return repository.byId(id);
	}

	public List<Course> getAll() {
		return repository.all();
	}
}
