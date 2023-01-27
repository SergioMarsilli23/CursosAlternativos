package mx.com.tutum.extra.infrastructure.rest.dto.response;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ExtraStudentResponseDto implements Serializable {
	
	private static final long serialVersionUID = -4026071699615238149L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("nombre")
	private String firstName;

	@JsonProperty("apellido")
	private String firstLastName;

	//@JsonProperty("apellidoMaterno")
	//private String secondLastName;

	@JsonProperty("promedio")
	private Float average;
	
	@JsonProperty("calificaciones")
	@JsonIgnoreProperties({"student"})
	private List<ExtraCourseScoreResponseDto> scores;
	

	private ExtraStudentResponseDto(Long id, String firstName, String firstLastName, Float average, List<ExtraCourseScoreResponseDto> scores) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setFirstLastName(firstLastName);
		this.setAverage(Float.valueOf(String.format("%.2f", average)));
		this.setScores(scores);
	}
	
	public static ExtraStudentResponseDto create(Long id, String firstName, String firstLastName, Float average, List<ExtraCourseScoreResponseDto> scores) {
		return new ExtraStudentResponseDto(id, firstName, firstLastName, average, scores);
	}

	public void modify(String firstName, String firstLastName, Float average, List<ExtraCourseScoreResponseDto> scores) {
		this.setFirstName(firstName);
		this.setFirstLastName(firstLastName);
		this.setAverage(Float.valueOf(String.format("%.2f", average)));
		this.setScores(scores);
	}
	
}
