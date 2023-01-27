package mx.com.tutum.model.course.infrastructure.rest.dto;

import java.io.Serializable;
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
public final class CourseResponseDto implements Serializable {

	private static final long serialVersionUID = -8372322317932168368L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("nombre")
	private String name;

	@JsonProperty("activo")
	private Boolean enabled;


	private CourseResponseDto(Long id, String name, Boolean enabled) {
		this.setId(id);
		this.setName(name);
		this.setEnabled(enabled);
	}
	
	public static CourseResponseDto create(Long id, String name, Boolean enabled) {
		return new CourseResponseDto(id, name, enabled);
	}

	public void modify(String name, Boolean enabled) {
		this.setName(name);
		this.setEnabled(enabled);
	}	

}