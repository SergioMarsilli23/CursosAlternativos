package mx.com.tutum.extra.infrastructure.rest;

import java.util.NoSuchElementException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.com.tutum.extra.application.ExtraScoreService;
import mx.com.tutum.extra.application.ExtraStudentService;
import mx.com.tutum.extra.domain.ExtraStudent;
import mx.com.tutum.extra.infrastructure.rest.dto.request.ExtraScoreUpdateRequestDto;
import mx.com.tutum.extra.infrastructure.rest.dto.request.ExtraScoreSaveRequestDto;
import mx.com.tutum.extra.infrastructure.rest.dto.response.ExtraScoreResponseDto;
import mx.com.tutum.extra.infrastructure.rest.dto.response.ExtraStudentResponseDto;
import mx.com.tutum.model.score.domain.Score;


@RestController
@RequestMapping("/rest/extra")
@Api
@Slf4j
@AllArgsConstructor
public class ExtraController {
	private final static String SUCCESS_OK = "ok";
	private final static String MSG_SAVE_OK = "calificación registrada";
	private final static String MSG_UPDATE_OK = "calificación actualizada";
	private final static String MSG_DELETE_OK = "calificación eliminada";
	
	private final ExtraScoreService extraScoreService;
	private final ExtraStudentService extraStudentService;
	private final ConversionService conversionService;
	
	
	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ExtraScoreResponseDto> save(@NotNull @Valid @RequestBody final ExtraScoreSaveRequestDto extraScoreRequestDto) {
		try {
			Score score = extraScoreService.save(extraScoreRequestDto)
					.orElseThrow();
			
			log.info("Calificación almacenada: " + score.toString());
			
			ExtraScoreResponseDto extraScoreResponseDto = ExtraScoreResponseDto.create(SUCCESS_OK, MSG_SAVE_OK);
			
			return ResponseEntity.ok().body(extraScoreResponseDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ExtraScoreResponseDto> update(@NotNull @Valid @RequestBody final ExtraScoreUpdateRequestDto extraScoreUpdateRequestDto) {
		try {
			Score score = extraScoreService.save(extraScoreUpdateRequestDto)
					.orElseThrow();
			
			log.info("Calificación actualizada: " + score.toString());
			
			ExtraScoreResponseDto extraScoreResponseDto = ExtraScoreResponseDto.create(SUCCESS_OK, MSG_UPDATE_OK);
			
			return ResponseEntity.ok().body(extraScoreResponseDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ExtraScoreResponseDto> delete(@PathVariable final Long id) {
		try {
			extraScoreService.delete(id);
			
			ExtraScoreResponseDto extraScoreResponseDto = ExtraScoreResponseDto.create(SUCCESS_OK, MSG_DELETE_OK);
			
			return ResponseEntity.ok().body(extraScoreResponseDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ExtraStudentResponseDto> getById(@PathVariable Long id) {
		try {
			ExtraStudent extraStudent = this.extraStudentService.get(id)
					.orElseThrow();
			ExtraStudentResponseDto extraStudentResponseDto = conversionService
					.convert(extraStudent, ExtraStudentResponseDto.class);
			
			return ResponseEntity.ok().body(extraStudentResponseDto);
		} catch (NoSuchElementException nsee) {
			log.error(nsee.getMessage(), nsee);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}
	
}
