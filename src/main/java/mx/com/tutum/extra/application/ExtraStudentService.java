package mx.com.tutum.extra.application;

import java.util.Optional;
import java.util.OptionalDouble;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import mx.com.tutum.extra.domain.ExtraStudent;
import mx.com.tutum.model.student.application.StudentService;
import mx.com.tutum.model.student.domain.Student;


@Service
@RequiredArgsConstructor
public class ExtraStudentService {

	private final StudentService studentService;
	
	
	public Optional<ExtraStudent> get(Long id) {
		Student student = studentService.get(id).orElseThrow();
		Float average = null;
		if (student.getScores() != null && !student.getScores().isEmpty()) {
			OptionalDouble optionalAverage = student.getScores().stream()
					.map(score -> score.getScore())
					.mapToDouble(score -> score.doubleValue())
					.average();
			if (optionalAverage.isPresent()) {
				average = Double.valueOf(optionalAverage.getAsDouble()).floatValue();
			}
		}
		
		ExtraStudent extraStudent = ExtraStudent.create(student, average);
		
		return Optional.ofNullable(extraStudent);
	}
	
}
