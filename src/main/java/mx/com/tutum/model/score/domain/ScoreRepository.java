package mx.com.tutum.model.score.domain;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository {

	Optional<Score> byId(Long id);

	void delete(Long id);

	List<Score> all();

	Optional<Score> save(Score score);

}