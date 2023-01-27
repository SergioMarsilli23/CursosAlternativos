package mx.com.tutum.model.student.infrastructure.persistence.postgresql.dto;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import mx.com.tutum.model.score.infrastructure.persistence.postgresql.dto.ScoreDto;
import mx.com.tutum.shared.infrastructure.SharedConstants;


@Entity(name = "Student")
@Table(name = "t_alumnos", schema = SharedConstants.DB_SCHEMA)
@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class StudentDto implements Cloneable {

	@Id
	@Column(name = "id_t_usuarios", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", nullable = false)
	private String firstName;

	@Column(name = "ap_paterno", nullable = false)
	private String firstLastName;

	@Column(name = "ap_materno", nullable = false)
	private String secondLastName;
	
	@Column(name = "activo", nullable = false)
	private Boolean enabled;

	@OneToMany(mappedBy = "student", targetEntity = ScoreDto.class, cascade = {CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<ScoreDto> scores;
	
	public StudentDto createClone () {
		try {
			return (StudentDto) this.clone();
		} catch (CloneNotSupportedException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}