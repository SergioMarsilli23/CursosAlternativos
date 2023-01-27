package mx.com.tutum.model.student.infrastructure.persistence.postgresql.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import mx.com.tutum.model.student.domain.Student;
import mx.com.tutum.model.student.domain.StudentRepository;
import mx.com.tutum.model.student.infrastructure.persistence.postgresql.dto.StudentDto;
import mx.com.tutum.shared.domain.ObjectNotFoundException;

@Repository
@RequiredArgsConstructor
public class StudentPostgresqlRepositoryAdapter implements StudentRepository {

	private final StudentPostgresqlRepository studentPostgresqlRepository;
	private final ConversionService conversionService;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Student> byId(Long id) {
		Optional<StudentDto> studentDto = studentPostgresqlRepository.findById(id);
		Optional<Student> student = Optional.of(conversionService.convert(studentDto.orElseThrow(() ->  new ObjectNotFoundException("Gestor de contenido no encontrado")), Student.class));
		
		return student;
	}

	@Override
	public List<Student> all() {
		List<Student> studentList = new ArrayList<Student>();
		studentPostgresqlRepository.findAll().forEach(studentDto -> studentList.add(conversionService.convert(studentDto, Student.class)));
		
		return studentList;
	}

	@Override
	public void delete(Long id) {
		studentPostgresqlRepository.deleteById(id);
	}

	@Override
	public Optional<Student> save(Student student) {
		StudentDto studentDto = studentPostgresqlRepository.save(conversionService.convert(student, StudentDto.class));
		return Optional.of(conversionService.convert(studentDto, Student.class));
	}

}