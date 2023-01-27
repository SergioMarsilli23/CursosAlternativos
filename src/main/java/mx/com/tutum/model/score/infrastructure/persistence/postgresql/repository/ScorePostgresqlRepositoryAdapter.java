package mx.com.tutum.model.score.infrastructure.persistence.postgresql.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import mx.com.tutum.model.score.domain.Score;
import mx.com.tutum.model.score.domain.ScoreRepository;
import mx.com.tutum.model.score.infrastructure.persistence.postgresql.dto.ScoreDto;
import mx.com.tutum.shared.domain.ObjectNotFoundException;

@Repository
@RequiredArgsConstructor
public class ScorePostgresqlRepositoryAdapter implements ScoreRepository {

	private final ScorePostgresqlRepository scorePostgresqlRepository;
	private final ConversionService conversionService;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Score> byId(Long id) {
		Optional<ScoreDto> scoreDto = scorePostgresqlRepository.findById(id);
		Optional<Score> score = Optional.of(conversionService.convert(scoreDto.orElseThrow(() ->  new ObjectNotFoundException("Gestor de contenido no encontrado")), Score.class));
		
		return score;
	}

	@Override
	public List<Score> all() {
		List<Score> scoreList = new ArrayList<Score>();
		scorePostgresqlRepository.findAll().forEach(scoreDto -> scoreList.add(conversionService.convert(scoreDto, Score.class)));
		
		return scoreList;
	}

	@Override
	public void delete(Long id) {
		scorePostgresqlRepository.deleteById(id);
	}

	@Override
	public Optional<Score> save(Score score) {
		ScoreDto scoreDto = scorePostgresqlRepository.save(conversionService.convert(score, ScoreDto.class));
		return Optional.of(conversionService.convert(scoreDto, Score.class));
	}

}