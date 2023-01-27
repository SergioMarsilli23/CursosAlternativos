package mx.com.tutum.model.student.infrastructure.rest.dto;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import mx.com.tutum.model.score.infrastructure.rest.dto.ScoreResponseDto;


@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public final class StudentResponseDto implements Serializable {

	private static final long serialVersionUID = -8372322317932168368L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("nombre")
	private String firstName;

	@JsonProperty("apellidoPaterno")
	private String firstLastName;

	@JsonProperty("apellidoMaterno")
	private String secondLastName;
	
	@JsonProperty("activo")
	private Boolean enabled;

	@JsonProperty("calificaciones")
	@JsonIgnoreProperties({"student"})
	private List<ScoreResponseDto> scores;
	

	private StudentResponseDto(Long id, String firstName, String firstLastName, String secondLastName, 
			Boolean enabled, List<ScoreResponseDto> scores) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setFirstLastName(firstLastName);
		this.setSecondLastName(secondLastName);
		this.setEnabled(enabled);
		this.setScores(scores);
	}
	
	public static StudentResponseDto create(Long id, String firstName, String firstLastName, 
			String secondLastName, Boolean enabled, List<ScoreResponseDto> scores) {
		
		return new StudentResponseDto(id, firstName, firstLastName, secondLastName, enabled, scores);
	}

	public void modify(String firstName, String firstLastName, String secondLastName, Boolean enabled, 
			List<ScoreResponseDto> scores) {
		this.setFirstName(firstName);
		this.setFirstLastName(firstLastName);
		this.setSecondLastName(secondLastName);
		this.setEnabled(enabled);
		this.setScores(scores);
	}
	
}