package mx.com.tutum.extra.infrastructure.rest.dto.request;

import javax.validation.constraints.Min;
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


@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class ExtraScoreSaveRequestDto {
	@JsonProperty("calificacion")
	@JsonAlias({"calificacion","score"})
	@NotNull
	@Range(min = 0, max = 10)
	private Float score;
	
	@JsonProperty("idCurso")
	@JsonAlias({"idCurso","courseId"})
	@NotNull
	@Min(1)
	private Long courseId;
	
	@JsonProperty("idAlumno")
	@JsonAlias({"idAlumno","studentId"})
	@NotNull
	@Min(1)
	private Long studentId;
	
}
