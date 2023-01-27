package mx.com.tutum.model.score.infrastructure.rest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
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
import mx.com.tutum.model.score.application.ScoreService;
import mx.com.tutum.model.score.domain.Score;
import mx.com.tutum.model.score.infrastructure.rest.dto.ScoreRequestDto;
import mx.com.tutum.model.score.infrastructure.rest.dto.ScoreResponseDto;


@RestController
@RequestMapping("/rest/score")
@Api
@Slf4j
@AllArgsConstructor
public class ScoreController {

	private final ScoreService scoreService;
	private final ConversionService conversionService;

	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ScoreResponseDto>> getAll() {
		try {			
			List<Score> scoreList = this.scoreService.getAll();
			List<ScoreResponseDto> scoreResponseDtoList = scoreList
					.parallelStream()
					.map(score -> conversionService.convert(score, ScoreResponseDto.class))
					.collect(Collectors.toList());
			
			return ResponseEntity.ok().body(scoreResponseDtoList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ScoreResponseDto> getById(@PathVariable Long id) {
		try {			
			Score score = this.scoreService.get(id).orElseThrow();
			ScoreResponseDto scoreResponseDto = conversionService
					.convert(score, ScoreResponseDto.class);
			
			return ResponseEntity.ok().body(scoreResponseDto);
		} catch (NoSuchElementException nsee) {
			log.error(nsee.getMessage(), nsee);
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ScoreResponseDto> save(@NotNull @Valid @RequestBody final ScoreRequestDto scoreRequestDto) {
		try {
			Score score = this.scoreService
					.save(conversionService.convert(scoreRequestDto, Score.class))
					.orElseThrow();
			ScoreResponseDto scoreResponseDto = conversionService
					.convert(score, ScoreResponseDto.class);
			
			return ResponseEntity.ok().body(scoreResponseDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ScoreResponseDto> update(@NotNull @Valid @RequestBody final ScoreRequestDto scoreRequestDto) {
		try {
			Score score = this.scoreService
					.save(conversionService.convert(scoreRequestDto, Score.class))
					.orElseThrow();
			ScoreResponseDto scoreResponseDto = conversionService
					.convert(score, ScoreResponseDto.class);
			
			return ResponseEntity.ok().body(scoreResponseDto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable final Long id) {
		this.scoreService.delete(id);
	}
	
}