package mx.com.tutum.model.student.infrastructure.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Validated
public final class StudentRequestDto implements Serializable, Cloneable {

	private static final long serialVersionUID = 6834788000623977329L;

	@JsonAlias("id")
	private Long id;
	
	@JsonProperty("nombre")
	@JsonAlias({"nombre","firstName"})
	@NotNull
	@NotBlank
	@NotEmpty
	private String firstName;
	
	@JsonProperty("apellidoPaterno")
	@JsonAlias({"firstLastName","apellidoPaterno"})
	@NotNull
	@NotBlank
	@NotEmpty
	private String firstLastName;
	
	@JsonProperty("apellidoMaterno")
	@JsonAlias({"secondLastName","apellidoMaterno"})
	@NotNull
	@NotBlank
	@NotEmpty
	private String secondLastName;
	
	@JsonProperty("activo")
	@JsonAlias({"enabled","activo"})
	@NotNull
	private Boolean enabled;
	
	
	public StudentRequestDto createClone () {
		try {
			return (StudentRequestDto) this.clone();
		} catch (CloneNotSupportedException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

}