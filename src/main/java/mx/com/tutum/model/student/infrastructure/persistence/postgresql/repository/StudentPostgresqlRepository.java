package mx.com.tutum.model.student.infrastructure.persistence.postgresql.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import mx.com.tutum.model.student.infrastructure.persistence.postgresql.dto.StudentDto;

@Repository
public interface StudentPostgresqlRepository extends CrudRepository<StudentDto, Long>{

}
