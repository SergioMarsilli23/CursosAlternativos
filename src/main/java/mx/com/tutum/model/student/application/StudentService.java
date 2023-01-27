package mx.com.tutum.model.student.application;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import mx.com.tutum.model.student.domain.Student;
import mx.com.tutum.model.student.domain.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository repository;


	public Optional<Student> save(Student student) {
		return repository.save(student);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public Optional<Student> get(Long id) {
		return repository.byId(id);
	}

	public List<Student> getAll() {
		return repository.all();
	}

}
