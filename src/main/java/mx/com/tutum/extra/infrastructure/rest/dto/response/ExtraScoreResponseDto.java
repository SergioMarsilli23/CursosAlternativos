package mx.com.tutum.extra.infrastructure.rest.dto.response;

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
public class ExtraScoreResponseDto {
	@JsonProperty("success")
	private String success;

	@JsonProperty("msg")
	private String msg;
	

	private ExtraScoreResponseDto(String success, String msg) {
		this.setSuccess(success);
		this.setMsg(msg);
	}
	
	public static ExtraScoreResponseDto create(String success, String msg) {
		return new ExtraScoreResponseDto(success, msg);
	}

	public void modify(String success, String msg) {
		this.setSuccess(success);
		this.setMsg(msg);
	}
}
