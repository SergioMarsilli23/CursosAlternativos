package mx.com.tutum.extra.infrastructure.rest.dto.response;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class ExtraCourseScoreResponseDto implements Serializable {
	
	private static final long serialVersionUID = 9153472183042005706L;
	
	@JsonProperty("materia")
	private String course;
	
	@JsonProperty("calificacion")
	private Float score;
	
	@JsonProperty("fecha_registro")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date registrationDate;
	
	
	private ExtraCourseScoreResponseDto(String course, Float score, Date registrationDate) {
		this.setCourse(course);
		this.setScore(score);
		this.setRegistrationDate(registrationDate);
	}
	
	public static ExtraCourseScoreResponseDto create(String course, Float score, Date registrationDate) {
		return new ExtraCourseScoreResponseDto(course, score, registrationDate);
	}
	
}
