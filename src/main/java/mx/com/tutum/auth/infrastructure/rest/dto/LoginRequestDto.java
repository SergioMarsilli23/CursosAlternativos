package mx.com.tutum.auth.infrastructure.rest.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

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
public class LoginRequestDto implements Serializable {

	private static final long serialVersionUID = -3059643245500969273L;

	@NotNull
	@NotBlank
	@NotEmpty
	private String user;
	
	@NotNull
	@NotBlank
	@NotEmpty
	private String password;
}
