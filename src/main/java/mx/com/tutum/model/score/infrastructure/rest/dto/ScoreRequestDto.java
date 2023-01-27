package mx.com.tutum.model.score.infrastructure.rest.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import mx.com.tutum.model.course.infrastructure.rest.dto.CourseRequestDto;
import mx.com.tutum.model.student.infrastructure.rest.dto.StudentRequestDto;


@Slf4j
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Validated
public final class ScoreRequestDto implements Serializable, Cloneable {

	private static final long serialVersionUID = 6834788000623977329L;

	@JsonAlias("id")
	private Long id;
	
	@JsonProperty("calificacion")
	@JsonAlias({"calificacion","score"})
	@NotNull
	@Range(min = 0, max = 10)
	private Float score;
	
	@JsonProperty("materia")
	@JsonAlias({"materia","course"})
	private CourseRequestDto course;
	
	@JsonProperty("alumno")
	@JsonAlias({"alumno","student"})
	private StudentRequestDto student;
	
	
	public ScoreRequestDto createClone () {
		try {
			return (ScoreRequestDto) this.clone();
		} catch (CloneNotSupportedException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

}