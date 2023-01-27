package mx.com.tutum.model.student.domain;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

	Optional<Student> byId(Long id);

	void delete(Long id);

	List<Student> all();

	Optional<Student> save(Student student);

}