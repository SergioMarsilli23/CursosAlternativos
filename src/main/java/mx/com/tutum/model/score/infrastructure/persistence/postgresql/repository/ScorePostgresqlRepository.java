package mx.com.tutum.model.score.infrastructure.persistence.postgresql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tutum.model.score.infrastructure.persistence.postgresql.dto.ScoreDto;

@Repository
public interface ScorePostgresqlRepository extends CrudRepository<ScoreDto, Long>{

}
