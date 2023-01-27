package mx.com.tutum.model.student.domain;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import mx.com.tutum.model.score.domain.Score;


@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Slf4j
public class Student implements Serializable, Cloneable {

	private static final long serialVersionUID = -4547827826219493698L;

	private Long id;
	private String firstName, firstLastName, secondLastName;
	private Boolean enabled;
	@JsonIgnoreProperties("student")
	private List<Score> scores;

	private Student(Long id, String firstName, String firstLastName, String secondLastName, Boolean enabled, List<Score> scores) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setFirstLastName(firstLastName);
		this.setSecondLastName(secondLastName);
		this.setEnabled(enabled);
		this.setScores(scores);
	}
	
	public static Student create(Long id, String firstName, String firstLastName, String secondLastName, Boolean enabled, List<Score> scores) {
		return new Student(id, firstName, firstLastName, secondLastName, enabled, scores);
	}

	public void modify(String firstName, String firstLastName, String secondLastName, Boolean enabled, List<Score> scores) {
		this.setFirstName(firstName);
		this.setFirstLastName(firstLastName);
		this.setSecondLastName(secondLastName);
		this.setEnabled(enabled);
		this.setScores(scores);
	}

	public Student createClone() {
		try {
			return (Student) this.clone();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

}