package mx.com.tutum.model.score.domain;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import mx.com.tutum.model.course.domain.Course;
import mx.com.tutum.model.student.domain.Student;


@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Slf4j
public class Score implements Serializable, Cloneable {

	private static final long serialVersionUID = -4547827826219493698L;

	private Long id;
	private Float score;
	private Date registrationDate;
	@JsonIgnoreProperties("scores")
	private Course course;
	@JsonIgnoreProperties("scores")
	private Student student;

	private Score(Long id, Float score, Date registrationDate, Course course, Student student) {
		this.setId(id);
		this.setScore(score);
		this.setRegistrationDate(registrationDate);
		this.setCourse(course);
		this.setStudent(student);
	}
	
	public static Score create(Long id, Float score, Date registrationDate, Course course, Student student) {
		return new Score(id, score, registrationDate, course, student);
	}

	public void modify(Float score, Date registrationDate, Course course, Student student) {
		this.setScore(score);
		this.setRegistrationDate(registrationDate);
		this.setCourse(course);
		this.setStudent(student);
	}
	
	public Score createClone() {
		try {
			return (Score) this.clone();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

}