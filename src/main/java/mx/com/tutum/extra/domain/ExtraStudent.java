package mx.com.tutum.extra.domain;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import mx.com.tutum.model.student.domain.Student;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Slf4j
public class ExtraStudent implements Serializable, Cloneable {
	
	private static final long serialVersionUID = -6158229453078905654L;
	
	private Student student;
	private Float average;
	
	private ExtraStudent(Student student, Float average) {
		this.setStudent(student);
		this.setAverage(average);
	}
	
	public static ExtraStudent create(Student student, Float average) {
		return new ExtraStudent(student, average);
	}
	
	public void modify(Float average) {
		this.setAverage(average);
	}
	
	public ExtraStudent createClone() {
		try {
			return (ExtraStudent) this.clone();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

}
