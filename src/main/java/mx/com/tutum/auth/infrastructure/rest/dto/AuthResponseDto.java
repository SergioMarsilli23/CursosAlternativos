package mx.com.tutum.auth.infrastructure.rest.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AuthResponseDto implements Serializable {

	private static final long serialVersionUID = 7639683531510910598L;
	
	
	@JsonProperty("token")
	private String token;
	
	
	private AuthResponseDto(String token) {
		this.setToken(token);
	}
	
	public static AuthResponseDto create(String token) {
		return new AuthResponseDto(token);
	}
	
	private void setToken(String token) {
		this.token = token;
	}
	
}
