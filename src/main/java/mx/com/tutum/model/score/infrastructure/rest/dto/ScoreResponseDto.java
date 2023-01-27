package mx.com.tutum.model.score.infrastructure.rest.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mx.com.tutum.model.course.infrastructure.rest.dto.CourseResponseDto;
import mx.com.tutum.model.student.infrastructure.rest.dto.StudentResponseDto;


@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public final class ScoreResponseDto implements Serializable {

	private static final long serialVersionUID = -8372322317932168368L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("calificacion")
	private Float score;

	@JsonProperty("fechaRegistro")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date registrationDate;

	@JsonProperty("endpoint_base")
	private String baseEndpoint;
	
	@JsonProperty("materia")
	@JsonIgnoreProperties({"calificaciones","scores"})
	private CourseResponseDto course;

	@JsonProperty("alumno")
	@JsonIgnoreProperties({"calificaciones","scores"})
	private StudentResponseDto student;


	private ScoreResponseDto(Long id, Float score, Date registrationDate, CourseResponseDto course, 
			StudentResponseDto student) {
		this.setId(id);
		this.setScore(score);
		this.setRegistrationDate(registrationDate);
		this.setCourse(course);
		this.setStudent(student);
	}
	
	public static ScoreResponseDto create(Long id, Float score, Date registrationDate, CourseResponseDto course, 
			StudentResponseDto student) {
		return new ScoreResponseDto(id, score, registrationDate, course, student);
	}

	public void modify(Float score, Date registrationDate, CourseResponseDto course, StudentResponseDto student) {
		this.setScore(score);
		this.setRegistrationDate(registrationDate);
		this.setCourse(course);
		this.setStudent(student);
	}	

}