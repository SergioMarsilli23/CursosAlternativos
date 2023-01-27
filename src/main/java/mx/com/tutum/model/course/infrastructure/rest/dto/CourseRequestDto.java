package mx.com.tutum.model.course.infrastructure.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public final class CourseRequestDto implements Serializable, Cloneable {

	private static final long serialVersionUID = 6834788000623977329L;

	@JsonAlias("id")
	private Long id;
	
	@JsonProperty("nombre")
	@JsonAlias({"nombre","name"})
	@NotNull
	@NotBlank
	@NotEmpty
	private String name;
	
	@JsonProperty("activo")
	@JsonAlias({"activo","enabled"})
	@NotNull
	private Boolean enabled;
		
	
	public CourseRequestDto createClone () {
		try {
			return (CourseRequestDto) this.clone();
		} catch (CloneNotSupportedException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

}