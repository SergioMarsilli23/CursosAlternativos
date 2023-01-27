package mx.com.tutum.model.course.infrastructure.persistence.postgresql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tutum.model.course.infrastructure.persistence.postgresql.dto.CourseDto;

@Repository
public interface CoursePostgresqlRepository extends CrudRepository<CourseDto, Long>{

}
