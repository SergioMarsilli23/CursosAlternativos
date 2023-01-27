package mx.com.tutum.model.score.application;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import mx.com.tutum.model.score.domain.Score;
import mx.com.tutum.model.score.domain.ScoreRepository;

@Service
@RequiredArgsConstructor
public class ScoreService {

	private final ScoreRepository repository;


	public Optional<Score> save(Score score) {
		return repository.save(score);
	}

	public void delete(Long id) {
		repository.delete(id);
	}

	public Optional<Score> get(Long id) {
		return repository.byId(id);
	}

	public List<Score> getAll() {
		return repository.all();
	}

}
